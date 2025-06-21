package com.jawapbo.sijiusu.views;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jawapbo.sijiusu.api.ApiClient;
import com.jawapbo.sijiusu.response.admin.CourseResponse;
import com.jawapbo.sijiusu.utils.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

import static com.jawapbo.sijiusu.utils.JavaFxExt.*;

public class AdminDashboardAcademicMajorIdController extends Controller{

    @FXML private Button profilButton;
    @FXML private Button keluarButton;
    @FXML private Button dashboardButton;
    @FXML private Button addButton;
    @FXML
    private VBox itemsContainer;


    @FXML
    private void initialize() {
        // Add hover effects to buttons
        addHoverEffect(profilButton);
        addHoverEffect(keluarButton);
        addHoverEffect(dashboardButton);
        addHoverEffect(addButton);

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
        nameLabel.setPadding(new Insets(20, 0, 20, 0));
        setFont(nameLabel, "Segoe UI", 25);
        setFontColor(nameLabel, "#ffffff");
        setFontWeight(nameLabel, "bold");

        card.getChildren().add(nameLabel);

        card.setOnMouseClicked(event -> {
            DataManager.setCourseId(course.id());
            try {
                switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_MAJOR_ID_COURSE_SECTION);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

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
    private void onBackCourses() {
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_MAJOR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onAdd() {
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_MAJOR_ID_COURSE_CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onCourse() {
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_MAJOR_ID_COURSE_SECTION);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
