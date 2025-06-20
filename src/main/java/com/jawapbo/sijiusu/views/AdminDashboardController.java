package com.jawapbo.sijiusu.views;

import com.jawapbo.sijiusu.api.TokenManager;
import com.jawapbo.sijiusu.utils.AppScene;
import com.jawapbo.sijiusu.utils.StyledAlert;
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
    private void onProfil() {
        try {
            switchScene(AppScene.ADMIN_PROFILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onKeluar() {
        try {
            // Hapus token dulu
            TokenManager.clearTokens();

            // Kembali ke login scene
            switchScene(AppScene.LOGIN);

            // Show logout message dengan alert biasa
            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Anda telah berhasil keluar dari sistem.");
            alert.showAndWait();

        } catch (IOException e) {
            System.err.println("Error switching to login scene: " + e.getMessage());
            e.printStackTrace();

            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Gagal kembali ke halaman login: " + e.getMessage());
            alert.showAndWait();
        } catch (Exception e) {
            System.err.println("Error clearing tokens: " + e.getMessage());
            e.printStackTrace();

            javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Terjadi kesalahan: " + e.getMessage());
            alert.showAndWait();
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