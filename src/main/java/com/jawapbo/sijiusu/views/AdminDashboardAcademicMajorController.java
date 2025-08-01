package com.jawapbo.sijiusu.views;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jawapbo.sijiusu.api.ApiClient;
import com.jawapbo.sijiusu.response.admin.MajorResponse;
import com.jawapbo.sijiusu.utils.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

import static com.jawapbo.sijiusu.utils.JavaFxExt.setFont;
import static com.jawapbo.sijiusu.utils.JavaFxExt.setFontColor;
import static com.jawapbo.sijiusu.utils.JavaFxExt.setFontWeight;

public class AdminDashboardAcademicMajorController extends Controller{

    @FXML private Button profilButton;
    @FXML private Button keluarButton;
    @FXML private Button dashboardButton;

    @FXML private VBox itemsContainer;


    @FXML
    private void initialize() {
        // Add hover effects to buttons
        addHoverEffect(profilButton);
        addHoverEffect(keluarButton);
        addHoverEffect(dashboardButton);

        var response = ApiClient.get(Endpoint.ADMIN_GET_MAJORS.getPath());

        if (response.statusCode() != 200) {
            StyledAlert.show("Error", "Failed to load majors data.");
            return;
        }

        try {
            var majors = Mapper.getInstance().readValue(response.body(), new TypeReference<List<MajorResponse>>() {});
            majors.stream()
                .sorted((m1, m2) -> m1.name().compareToIgnoreCase(m2.name()))
                .forEach(major ->
                    itemsContainer.getChildren()
                        .add(createMajorCard(
                            major
                        ))
                );
        } catch (Exception e) {
            StyledAlert.show("Error", "Failed to parse majors data: " + e.getMessage());
        }
    }

    private VBox createMajorCard(MajorResponse major) {
        VBox card = new VBox();
        card.setAlignment(Pos.TOP_LEFT);
        card.setStyle("""
            -fx-background-color:  #493628;
            -fx-border-color: #493628;
            -fx-background-radius: 15px;
            -fx-border-radius: 15px;
            """
        );
        card.setSpacing(16);
        card.setPadding(new Insets(30, 30, 30, 30));
        card.setMaxWidth(1280);

        var nameLabel = new Label(major.name());
        nameLabel.setPadding(new Insets(15, 0, 0, 0));
        setFont(nameLabel, "Segoe UI", 25);
        setFontColor(nameLabel, "#ffffff");
        setFontWeight(nameLabel, "bold");

//        var codeLabel = new Label("Code");
//        var codeValue = new Label(major.code());
//
        var facultyLabel = new Label("Faculty");
        var facultyValue = new Label(major.faculty().name());

        var gridCells = new Label[][] {
//            {codeLabel, codeValue},
            {facultyLabel, facultyValue},
        };

        var gridPane = new GridPane(0, 16);
        for (int i = 0; i < gridCells.length; i++) {
            for (int j = 0; j < gridCells[i].length; j++) {
                var cell = gridCells[i][j];
                if(j == 0) {
                    cell.setPadding(new Insets(0, 20, 0, 0 ));
                }
                setFont(cell, "Segoe UI", 15);
                setFontWeight(cell, "bold");
                setFontColor(cell, "#ffffff");
                gridPane.add(cell, j, i);
            }
        }

        card.getChildren().addAll(
            nameLabel,
            gridPane
        );

        card.setOnMouseClicked(event -> {
            DataManager.setMajorId(major.id());
            try {
                switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_MAJOR_ID_COURSES);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        addHoverEffect(card);
        return card;
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
    private void onBackDashboard() {
        try {
            switchScene(AppScene.ADMIN_DASHBOARD);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onCourse() {
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_MAJOR_ID_COURSES);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
