package com.jawapbo.sijiusu.views;

import com.jawapbo.sijiusu.utils.AppScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminDashboardAcademicFacultyIdDepartmentRoomController extends  Controller{

    @FXML private Button profilButton;
    @FXML private Button keluarButton;
    @FXML private Button dashboardButton;
    @FXML private Button editButton;

    @FXML
    private void initialize() {
        // Add hover effects to buttons
        addHoverEffect(profilButton);
        addHoverEffect(keluarButton);
        addHoverEffect(dashboardButton);
        addHoverEffect(editButton);

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
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_FACULTY_ID_DEPARTMENT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onEdit() {
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_FACULTY_ID_DEPARTMENT_ID_EDIT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
