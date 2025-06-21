package com.jawapbo.sijiusu.views;

import com.jawapbo.sijiusu.api.ApiClient;
import com.jawapbo.sijiusu.utils.AppScene;
import com.jawapbo.sijiusu.utils.DataManager;
import com.jawapbo.sijiusu.utils.Endpoint;
import com.jawapbo.sijiusu.utils.StyledAlert;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AdminDashboardAcademicMajorIdCoursesCreateController extends Controller {

    @FXML private Button profilButton;
    @FXML private Button keluarButton;
    @FXML private Button dashboardButton;
    @FXML private Button registerButton;
    @FXML private Button cancelButton;
    @FXML private TextField nameTextField;

    @FXML
    private void initialize() {
        // Add hover effects to buttons
        addHoverEffect(profilButton);
        addHoverEffect(keluarButton);
        addHoverEffect(dashboardButton);
        addHoverEffect(cancelButton);
        addHoverEffect(registerButton);
    }

    @FXML
    private void onRegister() {
        String name = nameTextField.getText();
        if (name.isBlank()) {
            StyledAlert.show("Error", "Course name cannot be empty.");
            return;
        }


        var body = """
        {
            "name": "%s"
        }
        """.formatted(name);

        System.out.println("Request body: " + body);

        var response = ApiClient.post(
            Endpoint.ADMIN_GET_CREATE_COURSE_WITH_MAJOR_ID.getPath()
                .formatted(DataManager.getMajorId()),
            body
        );

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            StyledAlert.show("Success", "Course created successfully.");
            try {
                switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_MAJOR_ID_COURSES);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            StyledAlert.show("Error", "Failed to create course: " + response.body());
            System.out.println(response.body());
        }
    }

    @FXML
    private void onSwitchToProfile() {
        try {
            switchScene(AppScene.ADMIN_PROFILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onBackDashboard() {
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_MAJOR_ID_COURSES);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
