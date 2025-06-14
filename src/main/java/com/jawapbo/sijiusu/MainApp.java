package com.jawapbo.sijiusu;

import com.jawapbo.sijiusu.utils.AppScene;
import com.jawapbo.sijiusu.utils.StyledAlert;
import com.jawapbo.sijiusu.views.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        try {
            AppScene firstScene = AppScene.LOGIN;

            var controller = new Controller();

            controller.setStage(stage);
            controller.switchScene(firstScene);

            stage.show();
        } catch (Exception e) {
            StyledAlert.show(
                "Error",
                "An error occurred while starting the application."
            );
        }
    }
}
