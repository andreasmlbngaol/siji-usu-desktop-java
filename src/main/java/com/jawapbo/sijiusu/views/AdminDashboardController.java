package com.jawapbo.sijiusu.views;

import com.jawapbo.sijiusu.utils.AppScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminDashboardController extends Controller {

    @FXML private Button profilButton;
    @FXML private Button keluarButton;
    @FXML private Button manajemenPenggunaButton;
    @FXML private Button manajemenAkademikButton;

    @FXML
    private void initialize() {
        // Add hover effects to buttons
        addHoverEffect(manajemenPenggunaButton);
        addHoverEffect(manajemenAkademikButton);
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
    private void onManajemenPengguna() {
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_USER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onManajemenAkademik() {
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}