package com.jawapbo.sijiusu.views;

import com.jawapbo.sijiusu.api.TokenManager;
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
    @FXML private Button manajemenPenggunaButton;
    @FXML private Button manajemenAkademikButton;

    @FXML
    private void initialize() {
        // Add hover effects to buttons
        addHoverEffect(dosenButton);
        addHoverEffect(mahasiswaButton);
        addHoverEffect(profilButton);
        addHoverEffect(keluarButton);
        addHoverEffect(dashboardButton);
        addHoverEffect(manajemenPenggunaButton);
        addHoverEffect(manajemenAkademikButton);
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




}
