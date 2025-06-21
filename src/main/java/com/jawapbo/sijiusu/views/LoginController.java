package com.jawapbo.sijiusu.views;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jawapbo.sijiusu.api.ApiClient;
import com.jawapbo.sijiusu.auth.JWTHandler;
import com.jawapbo.sijiusu.auth.TokenManager;
import com.jawapbo.sijiusu.response.TokenResponse;
import com.jawapbo.sijiusu.utils.AppScene;
import com.jawapbo.sijiusu.utils.Endpoint;
import com.jawapbo.sijiusu.utils.Mapper;
import com.jawapbo.sijiusu.utils.StyledAlert;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.File;

public class LoginController extends Controller {
    @FXML private TextField identifierTextField;
    @FXML private PasswordField passwordField;

    @FXML
    private void initialize() {
        identifierTextField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                onLogin();
            }
        });

        passwordField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                onLogin();
            }
        });
    }

    @FXML
    private void onLogin() {
        String identifier = identifierTextField.getText();
        String password = passwordField.getText();

        if (identifier.isEmpty() || password.isEmpty()) {
            StyledAlert.show("Error", "Identifier and password cannot be empty.");
            return;
        }

        var body = """
            {
                "identifier": "%s",
                "password": "%s"
            }
            """.formatted(identifier, password);

        var response = ApiClient.post(
            Endpoint.LOGIN.getPath(),
            body
        );

        try {
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                var tokens = Mapper.getInstance().readValue(response.body(), TokenResponse.class);


                TokenManager.setTokens(tokens);
                var file = new File("tokens.json");
                Mapper.getInstance().writeValue(file, tokens);
                System.out.println("Token file path: " + file.getAbsolutePath());


                var role = JWTHandler.extractRole(tokens.accessToken());
                StyledAlert.show("Success", "Login successful!");

                switch (role) {
                    case Admin -> switchScene(AppScene.ADMIN_DASHBOARD);
//                    case Student -> switchScene(AppScene.STUDENT_DASHBOARD);
//                    case Lecturer -> switchScene(AppScene.LECTURER_DASHBOARD);
                }
                switchScene(AppScene.ADMIN_DASHBOARD);
            } else {
                StyledAlert.show("Error", "Failed to login: Identifier or password is incorrect.");
            }
        } catch (JsonProcessingException e) {
            StyledAlert.show("Error", "Failed to parse response: " + e.getMessage());
        } catch (Exception e) {
            StyledAlert.show("Error", "An error occurred while processing the request: " + e.getMessage());
        }
    }
}
