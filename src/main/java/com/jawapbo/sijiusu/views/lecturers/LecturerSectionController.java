package com.jawapbo.sijiusu.views.lecturers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.jawapbo.sijiusu.api.ApiClient;
import com.jawapbo.sijiusu.response.lecturer.LecturerResponse;
import com.jawapbo.sijiusu.response.student.StudentCoursesAvailableResponse;
import com.jawapbo.sijiusu.response.student.StudentResponse;
import com.jawapbo.sijiusu.utils.AppScene;
import com.jawapbo.sijiusu.utils.Endpoint;
import com.jawapbo.sijiusu.utils.Mapper;
import com.jawapbo.sijiusu.utils.StyledAlert;
import com.jawapbo.sijiusu.views.Controller;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

import static com.jawapbo.sijiusu.utils.JavaFxExt.*;
import static com.jawapbo.sijiusu.utils.JavaFxExt.setFont;
import static com.jawapbo.sijiusu.utils.JavaFxExt.setFontColor;
import static com.jawapbo.sijiusu.utils.JavaFxExt.setFontWeight;

public class LecturerSectionController extends Controller {

    @FXML private Button keluarButton;
    @FXML private Button dashBoardButton;
    @FXML private VBox itemsContainer;

    @FXML
    private void initialize() throws JsonProcessingException {
        // Add hover effects to buttons
        addHoverEffect(keluarButton);
        addHoverEffect(dashBoardButton);
        addHoverEffect(dashBoardButton);

        var lecturer = Mapper.getInstance().readValue(
            ApiClient.get(Endpoint.GET_LECTURER_INFO.getPath()).body(),
            LecturerResponse.class
        );

        var response = ApiClient.get(
            Endpoint.LECTURER_COURSES_SECTIONS.getPath()
        );

        if(response.statusCode() != 200) {
            StyledAlert.show("Error", "Failed to load available courses. Please try again later.");
            System.out.println("Error: " + response.statusCode() + " - " + response.body());
            return;
        }

        try {
            var sections = Mapper.getInstance().readValue(
                response.body(),
                new TypeReference<List<StudentCoursesAvailableResponse>>() {}
            );
            sections
                .stream()
//                .filter(section ->
//                    lecturer.coursesTaught().stream()
//                        .noneMatch(taught -> taught.courseName().equals(section.courseName()))
//                )
                .sorted((c1, c2) -> c1.sectionName().compareToIgnoreCase(c2.sectionName()))
                .sorted((c1, c2) -> c1.courseName().compareToIgnoreCase(c2.courseName()))
                .forEach(section ->
                    itemsContainer.getChildren().add(
                        createSectionCard(
                            section
                        )
                    )
                );
        } catch (Exception e) {
            StyledAlert.show("Error", "Failed to parse available courses: " + e.getMessage());
            return;
        }

    }

    private VBox createSectionCard(StudentCoursesAvailableResponse section) {
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

        var nameLabel = new Label(section.courseName());
        nameLabel.setPadding(new Insets(15, 0, 0, 0));
        setFont(nameLabel, "Segoe UI", 25);
        setFontColor(nameLabel, "#ffffff");
        setFontWeight(nameLabel, "bold");

        var sectionName = new Label("Section");
        var sectionValue = new Label(section.sectionName());

        var roomLabel = new Label("Room");
        var roomValue = new Label(section.room());

        var lecturerLabel = new Label("Lecturer");
        var lecturerValue = new Label(section.lecturer());

        var gridCells = new Label[][] {
            {sectionName, sectionValue},
            {roomLabel, roomValue},
            {lecturerLabel, lecturerValue},
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

        var enrollButton = new Button("Enroll Course");
        enrollButton.setStyle("""
            -fx-background-color: #2fa623;
            -fx-text-fill: #000000;
            -fx-font-weight: bold;
            -fx-padding: 10 20 10 20;
            -fx-background-radius: 5px;
            """
        );
        enrollButton.setOnAction(event -> {
            var body = """ 
            {
                "section_id": %d
            }
            """.formatted(section.id());

            var response = ApiClient.post(
                Endpoint.LECTURER_COURSES_SECTIONS.getPath(),
                body
            );

            try {
                if(response.statusCode() == 204) {
                    StyledAlert.show("Success", "Successfully enrolled in course section: " + section.sectionName());
                    switchScene(AppScene.LECTURER_DASHBOARD);
                } else {
                    StyledAlert.show("Error", "Failed to enroll in course section. Please try again later.");
                    switchScene(AppScene.LECTURER_DASHBOARD);
                }
            } catch (Exception e) {
                StyledAlert.show("Error", "An error occurred while switching scenes: " + e.getMessage());
            }

        });

        var buttonContainer = new HBox(enrollButton);
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
        buttonContainer.setPadding(new Insets(16, 16, 16, 0));

        card.getChildren().addAll(
            nameLabel,
            gridPane,
            buttonContainer
        );

        addHoverEffect(card);
        return card;
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
