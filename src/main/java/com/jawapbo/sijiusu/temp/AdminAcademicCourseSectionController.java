package com.jawapbo.sijiusu.temp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jawapbo.sijiusu.api.ApiClient;
import com.jawapbo.sijiusu.response.admin.CourseResponse;
import com.jawapbo.sijiusu.response.admin.MajorResponse;
import com.jawapbo.sijiusu.response.admin.SectionResponse;
import com.jawapbo.sijiusu.utils.DataManager;
import com.jawapbo.sijiusu.utils.Endpoint;
import com.jawapbo.sijiusu.utils.Mapper;
import com.jawapbo.sijiusu.utils.StyledAlert;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import static com.jawapbo.sijiusu.utils.JavaFxExt.setFont;
import static com.jawapbo.sijiusu.utils.JavaFxExt.setFontColor;
import static com.jawapbo.sijiusu.utils.JavaFxExt.setFontWeight;

public class AdminAcademicCourseSectionController {
    @FXML private VBox itemsContainer;

    @FXML
    private void initialize() {
        var response = ApiClient.get(
            Endpoint.ADMIN_GET_COURSE_BY_ID.getPath()
                .formatted(DataManager.getCourseId())
        );

        if (response.statusCode() != 200) {
            StyledAlert.show("Error", "Failed to load majors data.");
            return;
        }

        try {
            var course = Mapper.getInstance().readValue(response.body(), new TypeReference<CourseResponse>() {});
            var sections = course.sections();

            sections.stream()
                .sorted((m1, m2) -> m1.name().compareToIgnoreCase(m2.name()))
                .forEach(section ->
                    itemsContainer.getChildren()
                        .add(createSectionCard(
                            section
                        ))
                );
        } catch (Exception e) {
            StyledAlert.show("Error", "Failed to parse majors data: " + e.getMessage());
        }
    }

    private VBox createSectionCard(SectionResponse section) {
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

        var nameLabel = new Label(section.name());
        nameLabel.setPadding(new Insets(15, 0, 0, 0));
        setFont(nameLabel, "Segoe UI", 25);
        setFontColor(nameLabel, "#ffffff");
        setFontWeight(nameLabel, "bold");

        var lecturereLabel = new Label("Lecturer");
        var lecturerValue = new Label(section.lecturer());

        var roomLabel = new Label("Room");
        var roomValue = new Label(section.room());

        var gridCells = new Label[][] {
            {lecturereLabel, lecturerValue},
            {roomLabel, roomValue},
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
            DataManager.setMajorId(section.id());
//            switchScene(AppScene.ADMIN_DASHBOARD_COURSES);
        });

        return card;
    }

}
