package com.jawapbo.sijiusu.views;

import com.jawapbo.sijiusu.utils.AppScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AdminDashboardAcademicFacultyController extends Controller{

    @FXML private Button profilButton;
    @FXML private Button keluarButton;
    @FXML private Button newFakultasButton;
    @FXML private Button namaFakultasButton;
    @FXML private Button editFakultasButton;
    @FXML private Button dashboardButton;

    @FXML
    private void initialize() {
        // Add hover effects to buttons
        addHoverEffect(newFakultasButton);
        addHoverEffect(namaFakultasButton);
        addHoverEffect(profilButton);
        addHoverEffect(keluarButton);
        addHoverEffect(dashboardButton);
        addHoverEffect(editFakultasButton);
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
    private void onBack(){
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onNewFakultas(){
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_FACULTY_REGISTER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onNamaFakultas(){
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_FACULTY_ID_DEPARTMENT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onEditFakultas(){
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_FACULTY_ID_DEPARTMENT_ID_EDIT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
