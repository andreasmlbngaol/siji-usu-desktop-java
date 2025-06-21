package com.jawapbo.sijiusu.views;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jawapbo.sijiusu.api.ApiClient;
import com.jawapbo.sijiusu.response.admin.CourseResponse;
import com.jawapbo.sijiusu.response.admin.SectionResponse;
import com.jawapbo.sijiusu.utils.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static com.jawapbo.sijiusu.utils.JavaFxExt.*;
import static com.jawapbo.sijiusu.utils.JavaFxExt.setFont;
import static com.jawapbo.sijiusu.utils.JavaFxExt.setFontColor;
import static com.jawapbo.sijiusu.utils.JavaFxExt.setFontWeight;

public class AdminDashboardAcademicMajorIdCoursesSectionController extends Controller{

    @FXML private Button profilButton;
    @FXML private Button keluarButton;
    @FXML private Button dashboardButton;
    @FXML private VBox itemsContainer;
    @FXML private Button createButton;

    @FXML
    private void initialize() {
        // Add hover effects to buttons
        addHoverEffect(profilButton);
        addHoverEffect(keluarButton);
        addHoverEffect(createButton);
        addHoverEffect(dashboardButton);

        System.out.println("Course ID: " + DataManager.getCourseId());

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
            System.out.println("Course: " + course);
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
        card.setPadding(new Insets(0, 30, 30, 30));
        card.setMaxWidth(1280);

        var nameLabel = new Label(section.name());
        nameLabel.setPadding(new Insets(15, 0, 0, 0));
        setFont(nameLabel, "Segoe UI", 25);
        setFontColor(nameLabel, "#ffffff");
        setFontWeight(nameLabel, "bold");

        var lecturereLabel = new Label("Lecturer");
        var lecturerValue = new Label(section.lecturer() != null ? section.lecturer() : "-");


        var gridCells = new Label[][] {
            {lecturereLabel, lecturerValue}
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
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_MAJOR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onCreate() {
        try {
            switchScene(AppScene.ADMIN_DASHBOARD_ACADEMIC_MAJOR_ID_COURSE_SECTION_CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
