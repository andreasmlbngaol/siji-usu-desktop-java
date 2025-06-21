package com.jawapbo.sijiusu.views;

import com.jawapbo.sijiusu.auth.TokenManager;
import com.jawapbo.sijiusu.utils.AppScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminDashboardUserStudentsController extends  Controller{

    @FXML private Button profilButton;
    @FXML private Button dashboardButton;
    @FXML private Button keluarButton;
    @FXML private Button addButton;

    @FXML
    private void initialize() {
        // Add hover effects to buttons
        addHoverEffect(addButton);
        addHoverEffect(profilButton);
        addHoverEffect(keluarButton);
        addHoverEffect(dashboardButton);
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
            switchScene(AppScene.ADMIN_DASHBOARD_USER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onAdd(){
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_USER_STUDENTS_REGISTER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
