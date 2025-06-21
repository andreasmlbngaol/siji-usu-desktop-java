package com.jawapbo.sijiusu.views.lecturers;

import com.jawapbo.sijiusu.utils.AppScene;
import com.jawapbo.sijiusu.views.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class LecturerSectionController extends Controller {

    @FXML private Button keluarButton;
    @FXML private Button dashBoardButton;
    @FXML private Button daftarButton;


    @FXML
    private void initialize() {
        // Add hover effects to buttons
        addHoverEffect(keluarButton);
        addHoverEffect(dashBoardButton);
        addHoverEffect(dashBoardButton);
        addHoverEffect(daftarButton);
    }

    @FXML
    private void onBackDashboard() {
        try {
            switchScene(AppScene.LECTURER_DASHBOARD);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
