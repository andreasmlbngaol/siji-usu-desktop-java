package com.jawapbo.sijiusu.views;

import com.jawapbo.sijiusu.MainApp;
import com.jawapbo.sijiusu.utils.AppScene;
import com.jawapbo.sijiusu.utils.StringRes;
import com.jawapbo.sijiusu.utils.StyledAlert;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class Controller {

    private Stage stage;

    protected void  addHoverEffect(Node node) {
        DropShadow shadow = new DropShadow();
        shadow.setRadius(20);
        shadow.setColor(Color.web("#ffffff", 0.4));

        node.setOnMouseEntered(e -> {
            node.setEffect(shadow);

            ScaleTransition st = new ScaleTransition(
                Duration.millis(200), node
            );
            st.setToX(1.10);
            st.setToY(1.10);
            st.play();
        });

        node.setOnMouseExited(e -> {
            node.setEffect(null);

            ScaleTransition st = new ScaleTransition(
                Duration.millis(200), node
            );
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        });
    }

    public void setStage(Stage stage) {
        stage.setResizable(false);
        this.stage = stage;
    }

    /**
     * Switch to a new scene using the given AppScene.
     * Used by MainApp and other controllers.
     */
    public void switchScene(AppScene appScene) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(appScene.getFxml()));
        fxmlLoader.setResources(StringRes.getBundle());
        Scene scene = new Scene(fxmlLoader.load(), AppScene.width, AppScene.height);
        Object controller = fxmlLoader.getController();
        if (controller instanceof Controller) {
            ((Controller) controller).setStage(stage);
        }

        stage.setScene(scene);
        stage.setTitle(appScene.getTitle());
    }

    @FXML
    protected void onExitApplication() {
        boolean confirmed = StyledAlert.showConfirmation(
            StringRes.get("exit_app_alert_title"),
            StringRes.get("exit_app_alert_content")
        );

        if (confirmed) {
            System.exit(0);
        }
    }
}
