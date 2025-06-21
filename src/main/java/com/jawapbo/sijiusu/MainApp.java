package com.jawapbo.sijiusu;

import com.jawapbo.sijiusu.auth.JWTHandler;
import com.jawapbo.sijiusu.auth.TokenManager;
import com.jawapbo.sijiusu.response.TokenResponse;
import com.jawapbo.sijiusu.utils.AppScene;
import com.jawapbo.sijiusu.utils.Mapper;
import com.jawapbo.sijiusu.utils.StyledAlert;
import com.jawapbo.sijiusu.views.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class MainApp extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        try {

            AppScene firstScene = AppScene.LOGIN;

            var file = new File("tokens.json");
            if(file.exists()) {
                var tokens = Mapper.getInstance().readValue(file, TokenResponse.class);
                TokenManager.setTokens(tokens);
                switch (JWTHandler.extractRole(tokens.accessToken())) {
                    case Admin -> firstScene = AppScene.ADMIN_DASHBOARD;
//                    case Lecturer -> firstScene = AppScene.LECTURER_DASHBOARD;
//                    case Student -> firstScene = AppScene.STUDENT_DASHBOARD;
//                    default -> firstScene = AppScene.LOGIN;
                }
            }

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
