package com.jawapbo.sijiusu.views;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jawapbo.sijiusu.api.ApiClient;
import com.jawapbo.sijiusu.response.admin.AdminLecturerResponse;
import com.jawapbo.sijiusu.utils.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.List;

import static com.jawapbo.sijiusu.utils.JavaFxExt.*;

public class AdminDashboardUserLecturerController extends Controller{

    @FXML private Button profilButton;
    @FXML private Button dashboardButton;
    @FXML private Button keluarButton;
    @FXML private Button addButton;
    @FXML private VBox itemsContainer;

    @FXML
    private void initialize() {
        // Add hover effects to buttons
        addHoverEffect(addButton);
        addHoverEffect(profilButton);
        addHoverEffect(keluarButton);
        addHoverEffect(dashboardButton);

        var response = ApiClient.get(Endpoint.ADMIN_LECTURERS.getPath());

        if (response.statusCode() != 200) {
            StyledAlert.show("Error", "Failed to load lecturers data.");
            return;
        }

        try {
            var lecturers = Mapper.getInstance().readValue(response.body(), new TypeReference<List<AdminLecturerResponse>>() {}
            );
            lecturers
                .stream().sorted((l1, l2) -> l1.name().compareToIgnoreCase(l2.name()))
                .forEach(lecturer -> {
                itemsContainer.getChildren().add(
                    createLecturerCard(
                        lecturer.name(),
                        lecturer.email(),
                        lecturer.nip(),
                        lecturer.nidn(),
                        lecturer.faculty(),
                        lecturer.department()
                    )
                );
            });
        } catch (Exception e) {
            StyledAlert.show("Error", "Failed to load lecturers data. " + e.getMessage());
            System.out.println("Error parsing lecturers data: " + e.getMessage());
            return;
        }
    }

    private VBox createLecturerCard(
        String lecturerLabel,
        String email,
        String nip,
        String nidn,
        String facultyName,
        String departmentName
    ) {
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
        card.setPadding(new Insets(0, 30, 0, 30));
        card.setMaxWidth(1280);

        var nameLabel = new Label(lecturerLabel);
        nameLabel.setPadding(new Insets(15, 0, 0, 0));
        setFont(nameLabel, "Segoe UI", 25);
        setFontColor(nameLabel, "#ffffff");
        setFontWeight(nameLabel, "bold");

        var emailLabel = new Label("Email");
        var emailValue = new Label(email);

        var nipLabel = new Label("NIP");
        var nipValue = new Label(nip);

        var nidnLabel = new Label("NIDN");
        var nidnValue = new Label(nidn);

        var facultyLabel = new Label("Faculty");
        var facultyValue = new Label(facultyName);

        var departmentLabel = new Label("Major");
        var departmentValue = new Label(departmentName);

        var gridCells = new Label[][] {
            {emailLabel,        emailValue},
            {nipLabel,          nipValue},
            {nidnLabel,         nidnValue},
            {facultyLabel,      facultyValue},
            {departmentLabel,   departmentValue}
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

        var editButton = new Button("Edit");
        editButton.setFont(Font.font("Sagoe UI", 15));
        editButton.setStyle("""
            -fx-background-color:  linear-gradient(to right, #d97706, #fbbf24);
            -fx-font-weight: bold;
            """);
        editButton.setPrefHeight(25);
        editButton.setPrefWidth(125);
        addHoverEffect(editButton);

        var editButtonContainer = new HBox(editButton);
        editButtonContainer.setAlignment(Pos.CENTER_RIGHT);
        editButtonContainer.setPadding(new Insets(0, 15, 15, 0));
        editButton.setOnAction(event -> {
            StyledAlert.show("Coming Soon", "This feature is coming soon!");
        });

        card.getChildren().addAll(
            nameLabel,
            gridPane,
            editButtonContainer
        );

        return card;
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
    private void onBack(){
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_USER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    private void onAdd(){
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_USER_LECTURER_REGISTER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
