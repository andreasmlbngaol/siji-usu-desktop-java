package com.jawapbo.sijiusu.views;

import com.jawapbo.sijiusu.auth.TokenManager;
import com.jawapbo.sijiusu.utils.AppScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminDashboardUserController extends Controller {

    @FXML private Button profilButton;
    @FXML private Button dashboardButton;
    @FXML private Button keluarButton;
    @FXML private Button dosenButton;
    @FXML private Button mahasiswaButton;

    @FXML
    private void initialize() {
        // Add hover effects to buttons
        addHoverEffect(dosenButton);
        addHoverEffect(mahasiswaButton);
        addHoverEffect(profilButton);
        addHoverEffect(keluarButton);
        addHoverEffect(dashboardButton);
    }

    @FXML
    private void onDosen(){
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_USER_LECTURER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onMahasiswa(){
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_USER_STUDENTS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onBack(){
        try {
            switchScene(AppScene.ADMIN_DASHBOARD);
        } catch (IOException e) {
            throw new RuntimeException(e);
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

}
