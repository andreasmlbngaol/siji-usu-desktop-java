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

public class AdminDashboardUserLecturerRegister extends Controller{

    @FXML private Button profilButton;
    @FXML private Button dashboardButton;
    @FXML private Button keluarButton;
    @FXML private Button registerButton;
    @FXML private TextField emailTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField nipTextField;
    @FXML private TextField nidnTextField;
    @FXML private ComboBox<FacultyResponse> facultyDropdown;
    @FXML private ComboBox<FacultyDepartmentResponse> departmentDropdown;

    private Long departmentIdSelected;

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
                    departmentDropdown.getItems().addAll(departments);
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

        departmentDropdown.setOnAction(e -> {
            FacultyDepartmentResponse selected = departmentDropdown.getValue();
            if(selected != null) {
                departmentIdSelected = selected.id();
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

    private boolean validateNip(String nip) {
        return nip.trim().matches("\\d{18}");
    }

    private boolean validateNidn(String nidn) {
        return nidn.trim().matches("\\d{10}");
    }

    @FXML
    private void onRegister() {
        var name = nameTextField.getText();
        var email = emailTextField.getText();
        var password = passwordTextField.getText();
        var nip = nipTextField.getText();
        var nidn = nidnTextField.getText();
        departmentIdSelected = departmentDropdown.getValue().id();

        if(name.isEmpty()
            || email.isEmpty()
            || password.isEmpty()
            || nip.isEmpty()
            || nidn.isEmpty()
            || departmentIdSelected == null
        ) {
            StyledAlert.show("Error", "All fields are required.");
            return;
        } else if (!validatePassword(password)) {
            StyledAlert.show("Error", "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, and one digit.");
            return;
        } else if (!validateNip(nip)) {
            StyledAlert.show("Error", "NIP must be exactly 18 digits.");
            return;
        } else if (!validateNidn(nidn)) {
            StyledAlert.show("Error", "NIDN must be exactly 10 digits.");
            return;
        }

        var body = """
        {
            "name": "%s",
            "email": "%s",
            "password": "%s",
            "nip": "%s",
            "nidn": "%s",
            "department_id": %d
        }
        """.formatted(name, email, password, nip, nidn, departmentIdSelected);

        var response = ApiClient.post(
            Endpoint.ADMIN_LECTURERS.getPath(),
            body
        );

        try {
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                StyledAlert.show("Success", "Lecturer has been registered.");
                switchScene(AppScene.ADMIN_DASHBOARD_USER_LECTURER);
            } else {
                StyledAlert.show("Error", "An error occurred while registering the lecturer.");
            }
        } catch (Exception e) {
            StyledAlert.show("Error", "An error occurred while registering the lecturer: " + e.getMessage());
            System.out.println("Error registering lecturer: " + e.getMessage());
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
            switchScene(AppScene.ADMIN_DASHBOARD_USER_LECTURER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
