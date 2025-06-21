package com.jawapbo.sijiusu.views;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jawapbo.sijiusu.api.ApiClient;
import com.jawapbo.sijiusu.response.admin.FacultyDepartmentResponse;
import com.jawapbo.sijiusu.response.admin.FacultyResponse;
import com.jawapbo.sijiusu.utils.AppScene;
import com.jawapbo.sijiusu.utils.Endpoint;
import com.jawapbo.sijiusu.utils.Mapper;
import com.jawapbo.sijiusu.utils.StyledAlert;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

public class AdminDashboardUserStudentsRegisterController extends Controller{

    @FXML private Button profilButton;
    @FXML private Button dashboardButton;
    @FXML private Button keluarButton;
    @FXML private Button registerButton;
    @FXML private TextField emailTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField nimTextField;
    @FXML private ComboBox<FacultyResponse> facultyDropdown;
    @FXML private ComboBox<FacultyDepartmentResponse> majorDropdown;

    private Long majorIdSelected;

    private void runFetchFacultyThread() {
        new Thread(() -> {
            var response = ApiClient.get(Endpoint.ADMIN_GET_FACULTIES.getPath());

            if(response.statusCode() != 200){
                StyledAlert.show("Error", "Failed to load faculties data.");
                return;
            }

            try {
                var faculties = Mapper.getInstance().readValue(response.body(), new TypeReference<List<FacultyResponse>>() {});

                Platform.runLater(() -> {
                    facultyDropdown.getItems().addAll(faculties);
                });

            } catch (Exception e) {
                StyledAlert.show("Error", "Failed to load faculties data. " + e.getMessage());
                System.out.println("Error parsing faculties data: " + e.getMessage());
                return;
            }
        }).start();
    }

    private void runFetchDepartmentThread(Long facultyId) {
        new Thread(() -> {
            var response = ApiClient.get(Endpoint.ADMIN_GET_FACULTY_BY_ID.getPath().formatted(facultyId));

            if(response.statusCode() != 200){
                StyledAlert.show("Error", "Failed to load departments data.");
                return;
            }

            try {
                var departments = Mapper.getInstance()
                    .readValue(
                        response.body(),
                        FacultyResponse.class
                    ).departments();

                Platform.runLater(() -> {
                    majorDropdown.getItems().clear();
                    majorDropdown.getItems().addAll(departments);
                });

            } catch (Exception e) {
                System.out.println("Error parsing departments data: " + e.getMessage());
                return;
            }
        }).start();
    }

    @FXML
    private void initialize() {
        // Add hover effects to buttons
        addHoverEffect(registerButton);
        addHoverEffect(profilButton);
        addHoverEffect(keluarButton);
        addHoverEffect(dashboardButton);

        runFetchFacultyThread();

        facultyDropdown.setOnAction(e -> {
            FacultyResponse selected = facultyDropdown.getValue();
            if(selected != null) {
                runFetchDepartmentThread(selected.id());
            }
        });

        majorDropdown.setOnAction(e -> {
            FacultyDepartmentResponse selected = majorDropdown.getValue();
            if(selected != null) {
                majorIdSelected = selected.id();
            }
        });
    }

    private boolean validatePassword(String password) {
        var trimmed = password.trim();

        return trimmed.length() >= 8
            && trimmed.matches(".*[A-Z].*") // At least one uppercase letter;
            && trimmed.matches(".*[a-z].*") // At least one lowercase letter
            && trimmed.matches(".*\\d.*")   // At least one digit
            ;
    }

    private boolean validateNim(String nim) {
        return nim.trim().matches("\\d{9}");
    }


    @FXML
    private void onRegister() {
        var name = nameTextField.getText();
        var email = emailTextField.getText();
        var password = passwordTextField.getText();
        var nim = nimTextField.getText();
        majorIdSelected = majorDropdown.getValue().id();

        if(name.isEmpty()
            || email.isEmpty()
            || password.isEmpty()
            || nim.isEmpty()
            || majorIdSelected == null
        ) {
            StyledAlert.show("Error", "All fields are required.");
            return;
        } else if (!validatePassword(password)) {
            StyledAlert.show("Error", "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, and one digit.");
            return;
        } else if (!validateNim(nim)) {
            StyledAlert.show("Error", "NIM must be exactly 9 digits.");
            return;
        }

        var body = """
        {
            "name": "%s",
            "email": "%s",
            "password": "%s",
            "year": 2025,
            "nim": "%s",
            "major_id": %d
        }
        """.formatted(name, email, password, nim, majorIdSelected);

        var response = ApiClient.post(
            Endpoint.ADMIN_STUDENTS.getPath(),
            body
        );

        try {
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                StyledAlert.show("Success", "Student has been registered.");
                switchScene(AppScene.ADMIN_DASHBOARD_USER_STUDENTS);
            } else {
                StyledAlert.show("Error", "An error occurred while registering the student.");
            }
        } catch (Exception e) {
            StyledAlert.show("Error", "An error occurred while registering the student. " + e.getMessage());
            System.out.println("Error registering student: " + e.getMessage());
        }
    }


    @FXML
    private void onProfile() {

        try {
            switchScene(AppScene.ADMIN_PROFILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onBack(){
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_USER_STUDENTS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
