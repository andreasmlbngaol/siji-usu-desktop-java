package com.jawapbo.sijiusu.utils;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class JavaFxExt {
    public static void setFont(Label label, String fontName, double fontSize) {
        label.setFont(new Font(fontName, fontSize));
    }

    public static void setFontColor(Label label, String color) {
        var current = label.getStyle();
        label.setStyle(current + "-fx-text-fill: " + color + ";");
    }

    public static void setFontWeight(Label label, String fontWeight) {
        var current = label.getStyle();
        label.setStyle(current + "-fx-font-weight: " + fontWeight + ";");
    }

}
