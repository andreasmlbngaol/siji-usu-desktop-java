package com.jawapbo.sijiusu.views.students;

import com.jawapbo.sijiusu.api.ApiClient;
import com.jawapbo.sijiusu.response.student.CoursesTakenResponse;
import com.jawapbo.sijiusu.response.student.StudentResponse;
import com.jawapbo.sijiusu.utils.*;
import com.jawapbo.sijiusu.views.Controller;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static com.jawapbo.sijiusu.utils.JavaFxExt.setFont;
import static com.jawapbo.sijiusu.utils.JavaFxExt.setFontColor;
import static com.jawapbo.sijiusu.utils.JavaFxExt.setFontWeight;

public class StudentDashboardController extends Controller {
    @FXML
    private VBox itemsContainer;

    @FXML
    private void initialize() {
        var response = ApiClient.get(
            Endpoint.GET_STUDENT_INFO.getPath()
        );

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            try {
                var student = Mapper.getInstance().readValue(response.body(), StudentResponse.class);
                var courses = student.coursesTaken();

                courses
                    .stream()
                    .sorted((c1, c2) -> c1.courseName().compareToIgnoreCase(c2.courseName()))
                    .forEach(course ->
                        itemsContainer.getChildren().add(
                            createCourseCard(
                                course
                            )
                        )
                    );
            } catch (Exception e) {
                StyledAlert.show("Error", "Failed to parse student data: " + e.getMessage());
            }
        } else {
            StyledAlert.show("Error", "Failed to load student data.");
            System.out.println("Error: " + response.statusCode() + " - " + response.body());
        }
    }

    private VBox createCourseCard(CoursesTakenResponse course) {
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

        var nameLabel = new Label(course.courseName());
        nameLabel.setPadding(new Insets(15, 0, 0, 0));
        setFont(nameLabel, "Segoe UI", 25);
        setFontColor(nameLabel, "#ffffff");
        setFontWeight(nameLabel, "bold");

        var sectionName = new Label("Section");
        var sectionValue = new Label(course.sectionName());

        var roomLabel = new Label("Room");
        var roomValue = new Label(course.room());

        var lecturerLabel = new Label("Lecturer");
        var lecturerValue = new Label(course.lecturer());

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

        card.getChildren().addAll(
            nameLabel,
            gridPane
        );

        addHoverEffect(card);
        return card;
    }

    @FXML private void onSwitchToProfile() {
        try {
            switchScene(AppScene.STUDENT_PROFILE);
        } catch (IOException e) {
            StyledAlert.show("Error", "Failed to switch to profile: " + e.getMessage());
        }
    }
}