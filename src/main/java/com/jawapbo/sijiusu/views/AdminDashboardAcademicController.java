package com.jawapbo.sijiusu.views;

import com.jawapbo.sijiusu.utils.AppScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminDashboardAcademicController extends  Controller{

    @FXML private Button profilButton;
    @FXML private Button keluarButton;
    @FXML private Button manajemenFakultasButton;
    @FXML private Button manajemenJurusanButton;

    @FXML
    private void initialize() {
        // Add hover effects to buttons
        addHoverEffect(manajemenFakultasButton);
        addHoverEffect(manajemenJurusanButton);
        addHoverEffect(profilButton);
        addHoverEffect(keluarButton);
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
    private void onFakultas() {
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_FACULTY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onJurusan() {
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_MAJOR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
