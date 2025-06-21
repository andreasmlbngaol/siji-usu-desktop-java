package com.jawapbo.sijiusu.temp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jawapbo.sijiusu.api.ApiClient;
import com.jawapbo.sijiusu.response.admin.CourseResponse;
import com.jawapbo.sijiusu.response.admin.MajorResponse;
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

import java.util.List;

import static com.jawapbo.sijiusu.utils.JavaFxExt.setFont;
import static com.jawapbo.sijiusu.utils.JavaFxExt.setFontColor;
import static com.jawapbo.sijiusu.utils.JavaFxExt.setFontWeight;

public class AdminAcademicCourseController {
    @FXML
    private VBox itemsContainer;

    @FXML
    private void initialize() {
        var response = ApiClient.get(
            Endpoint.ADMIN_GET_COURSES_BY_MAJOR_ID.getPath()
                .formatted(DataManager.getMajorId())
        );

        if (response.statusCode() != 200) {
            StyledAlert.show("Error", "Failed to load courses data.");
            return;
        }

        try {
            var courses = Mapper.getInstance().readValue(response.body(), new TypeReference<List<CourseResponse>>() {});
            courses.stream()
                .sorted((m1, m2) -> m1.name().compareToIgnoreCase(m2.name()))
                .forEach(course ->
                    itemsContainer.getChildren()
                        .add(createCourseCard(
                            course
                        ))
                );
        } catch (Exception e) {
            StyledAlert.show("Error", "Failed to parse majors data: " + e.getMessage());
        }
    }

    private VBox createCourseCard(CourseResponse course) {
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

        var nameLabel = new Label(course.name());
        nameLabel.setPadding(new Insets(15, 0, 0, 0));
        setFont(nameLabel, "Segoe UI", 25);
        setFontColor(nameLabel, "#ffffff");
        setFontWeight(nameLabel, "bold");

        card.getChildren().add(nameLabel);

        card.setOnMouseClicked(event -> {
            DataManager.setCourseId(course.id());
//            switchScene(AppScene.ADMIN_DASHBOARD_COURSE_SECTION);
        });

        return card;
    }
}
