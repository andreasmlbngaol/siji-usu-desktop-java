package com.jawapbo.sijiusu.views;

import com.jawapbo.sijiusu.auth.TokenManager;
import com.jawapbo.sijiusu.utils.AppScene;
import com.jawapbo.sijiusu.utils.JavaFxExt;
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

        itemsContainer
            .getChildren()
            .addAll(
                createLecturerCard(
                    "Dr. John Doe, M.Sc.",
                    "test@example.com",
                    "1234567890",
                    "9876543210",
                    "Faculty of Science",
                    "Department of Physics"
                ),
                createLecturerCard(
                    "Amalia",
                    "amalia@usu",
                    "43255512",
                    "43253525",
                    "Fasilkom-TI",
                    "Ilmu Komputer"
                )
            );
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
        VBox.setMargin(card, new Insets(0, 30, 0, 30));
        card.setSpacing(16);
        card.setMaxWidth(1280);

        var nameLabel = new Label(lecturerLabel);
        nameLabel.setPadding(new Insets(15, 0, 0, 30));
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

        var departmentLabel = new Label("Department");
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
                cell.setPadding(new Insets(0, 30, 0, 30));
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

        var editButtonContainer = new HBox(editButton);
        editButtonContainer.setAlignment(Pos.CENTER_RIGHT);
        editButtonContainer.setPadding(new Insets(0, 15, 15, 0));

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
