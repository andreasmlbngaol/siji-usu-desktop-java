package com.jawapbo.sijiusu.views;

import com.jawapbo.sijiusu.utils.AppScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminDashboardAcademicFacultyIdDepartmentController extends Controller{

    @FXML private Button profilButton;
    @FXML private Button keluarButton;
    @FXML private Button majorNameButton;
    @FXML private Button dashboardButton;
    @FXML private Button newRoomButton;
    @FXML private Button newMajorButton;

    @FXML
    private void initialize() {
        // Add hover effects to buttons
        addHoverEffect(profilButton);
        addHoverEffect(keluarButton);
        addHoverEffect(dashboardButton);
        addHoverEffect(majorNameButton);
        addHoverEffect(newRoomButton);
        addHoverEffect(newMajorButton);

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
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_FACULTY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onNewMajor() {
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_FACULTY_ID_REGISTER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onNameMajor() {
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_FACULTY_ID_DEPARTMENT_ROOM);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
