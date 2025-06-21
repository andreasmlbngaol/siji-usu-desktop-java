package com.jawapbo.sijiusu.views.students;

import com.jawapbo.sijiusu.utils.AppScene;
import com.jawapbo.sijiusu.utils.StyledAlert;
import com.jawapbo.sijiusu.views.Controller;
import javafx.fxml.FXML;

public class StudentProfileController extends Controller {



    @FXML private void onSwitchToProfile() {
        try {
            switchScene(AppScene.STUDENT_DASHBOARD);
        } catch (Exception e) {
            StyledAlert.show("Error", "An error occurred while switching scenes: " + e.getMessage());
        }
    }
}
