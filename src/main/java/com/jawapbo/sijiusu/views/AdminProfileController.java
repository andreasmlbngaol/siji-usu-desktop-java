package com.jawapbo.sijiusu.views;

import com.jawapbo.sijiusu.api.ApiClient;
import com.jawapbo.sijiusu.response.AdminDataResponse;
import com.jawapbo.sijiusu.utils.AppScene;
import com.jawapbo.sijiusu.utils.Endpoint;
import com.jawapbo.sijiusu.utils.Mapper;
import com.jawapbo.sijiusu.utils.StyledAlert;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class AdminProfileController extends Controller {

    @FXML private Label nameLabel;
    @FXML private Label emailLabel;
    @FXML private PasswordField oldPasswordField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmPasswordField;

    @FXML
    private void initialize() {
        var response = ApiClient.get(
            Endpoint.GET_ADMIN.getPath()
        );

        try {
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                var adminData = Mapper.getInstance().readValue(response.body(), AdminDataResponse.class);
                nameLabel.setText(adminData.name());
                emailLabel.setText(adminData.email());
            } else {
                // Handle error response
                System.err.println("Failed to retrieve admin data: " + response.body());
            }
        } catch (Exception e) {
            System.err.println("An error occurred while retrieving admin data: " + e.getMessage());
        }
    }

    @FXML private void onBackToDashboard() {
        try {
            switchScene(AppScene.ADMIN_DASHBOARD);
        } catch (Exception e) {
            StyledAlert.show("Error", "An error occurred while switching scenes: " + e.getMessage());
        }
    }

    @FXML private void onChangePassword() {
        var oldPassword = oldPasswordField.getText();
        var newPassword = newPasswordField.getText();
        var confirmPassword = confirmPasswordField.getText();

        if(oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            StyledAlert.show("Error", "All fields are required.");
            return;
        }

        if(!newPassword.equals(confirmPassword)) {
            StyledAlert.show("Error", "New password and confirmation do not match.");
            return;
        }

        var body = """
        {
            "old_password": "%s",
            "new_password": "%s"
        }
        """.formatted(oldPassword, newPassword);

        var response = ApiClient.patch(
            Endpoint.CHANGE_PASSWORD.getPath(),
            body
        );

        try {
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                StyledAlert.show("Success", "Password updated successfully.");
                oldPasswordField.clear();
                newPasswordField.clear();
                confirmPasswordField.clear();
            } else {
                StyledAlert.show("Error", "Failed to update password: " + response.body());
            }
        } catch (Exception e) {
            StyledAlert.show("Error", "An error occurred while updating the password: " + e.getMessage());
        }

    }
}
