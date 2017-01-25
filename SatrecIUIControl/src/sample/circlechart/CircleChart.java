package sample.circlechart;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by GSD on 2017-01-24.
 */
public class CircleChart extends StackPane {
    ScatterChart coreChart;
    NumberAxis xAxis;
    NumberAxis yAxis;
    Circle outsideCircle;
    AnchorPane gradationCanvas;
    AnchorPane dateLabelCanvas;
    AnchorPane bottomCanvas;

    public CircleChart() {
        setPrefHeight(300);
        setPrefWidth(300);
        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        xAxis.setLowerBound(-5.5);
        xAxis.setUpperBound(5.5);
        xAxis.setTickUnit(1);

        yAxis.setLowerBound(-5.5);
        yAxis.setUpperBound(5.5);
        yAxis.setTickUnit(1);
        xAxis.setTickLabelsVisible(false);
        yAxis.setTickLabelsVisible(false);
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);

        coreChart = new ScatterChart(xAxis, yAxis);

        setMargin(coreChart, new Insets(11, 11, 0, 0));
        outsideCircle = new Circle(165, Color.TRANSPARENT);
        outsideCircle.setStroke(Color.BLACK);
        getChildren().add(outsideCircle);

        bottomCanvas = new AnchorPane();
        bottomCanvas.setTranslateX(190);
        bottomCanvas.setTranslateY(190);
        getChildren().add(bottomCanvas);

        for (int i = 1; i <= 12; i++) {
            Line line = new Line(-175, 0, 175, 0);
            line.setRotate(i * 15);
            line.setStroke(Paint.valueOf("#dadee5"));
            line.setStyle("-fx-stroke-dash-array: 12 2 4 2;");
            getChildren().add(line);
        }

        gradationCanvas = new AnchorPane();
        gradationCanvas.setTranslateX(190);
        gradationCanvas.setTranslateY(190);

        dateLabelCanvas = new AnchorPane();
        dateLabelCanvas.setTranslateX(190);
        dateLabelCanvas.setTranslateY(190);
        getChildren().add(gradationCanvas);
        getChildren().add(coreChart);
        getChildren().add(dateLabelCanvas);
        doDraw();
        drawObjectCircle(4.2);
        drawDateLabel(135, new GregorianCalendar(2014, 0, 13).getTime());
    }

    public ScatterChart getCoreChart() {
        return this.coreChart;
    }

    public NumberAxis getxAxis() {
        return xAxis;
    }

    public NumberAxis getyAxis() {
        return yAxis;
    }

    public void drawDateLabel(double degree, Date date){
        HBox hBox = new HBox();
        hBox.setPrefHeight(20);
        hBox.setPrefWidth(217);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        Label label = new Label("12-16");
        label.setPadding(new Insets(4));
        label.setStyle("-fx-background-color:white; -fx-text-fill:blue; -fx-border-color:gray;");
        label.setRotate(degree);

        Circle circle = new Circle(4, Color.BLACK);
        VBox box = new VBox();
        box.setAlignment(Pos.TOP_CENTER);
        box.getChildren().add(circle);
        hBox.getChildren().add(box);
        hBox.getChildren().add(label);


        double regulateValue = (degree % 180) / 90 == 0 ? (2 * (degree % 90)) / 15 : (2 * (90 - degree % 90)) / 15;
        hBox.setMargin(label, new Insets(-15,regulateValue,0,-regulateValue));
        box.setMargin(circle, new Insets(-3, 0, 0, 0));
        Rotate rotate = new Rotate(-degree);
        hBox.getTransforms().add(rotate);
        dateLabelCanvas.getChildren().add(hBox);
    }

    public void drawObjectCircle(double radiusCoordinate){
        Circle circle = new Circle((radiusCoordinate * outsideCircle.getRadius())/xAxis.getUpperBound(), Paint.valueOf("#D5FEAD"));
        circle.setStroke(Color.RED);
        bottomCanvas.getChildren().add(circle);
    }

    public void doDraw() {
        boolean state = true;
        for (int i = 1; i <= 4; i++) {
            state = !state;
            for (double j = 0 + xAxis.getTickUnit(); j < xAxis.getUpperBound(); j += xAxis.getTickUnit()) {
                Line line = new Line(6, 0, -6, 0);
                line.setFill(Color.BLACK);
                if (i <= 2) {
                    line.setRotate(90);
                    line.setLayoutX((j * outsideCircle.getRadius()) / xAxis.getUpperBound() * (state ? -1 : 1) );
                } else {
                    line.setLayoutY((j * outsideCircle.getRadius()) / xAxis.getUpperBound() * (state ? -1 : 1));
                }
                gradationCanvas.getChildren().add(line);
            }
        }
        for (double j = 0 + xAxis.getTickUnit(); j < xAxis.getUpperBound(); j += xAxis.getTickUnit()) {
            Circle circle = new Circle();
            circle.setStroke(Paint.valueOf("#dadee5"));
            circle.setFill(Color.TRANSPARENT);
            circle.setRadius((j * outsideCircle.getRadius()) / xAxis.getUpperBound());
            gradationCanvas.getChildren().add(circle);
        }
        for(int i = 0; i < 12; i++) {
            BorderPane border = new BorderPane();
            border.setPrefHeight(20);
            border.setPrefWidth(396);
            border.setStyle("-fx-background-color:transparent");
            border.setRotate(i * -15);
            border.setLayoutX(-198);
            border.setLayoutY(-10);
            Label label1 = new Label(i * 15 + 180 + "" );
            label1.setRotate(i * 15);
            Label label2 = new Label(i * 15 + "");
            VBox vBox = new VBox(label2);
            vBox.setAlignment(Pos.CENTER_LEFT);
            label2.setRotate(i * 15);
            border.setLeft(label1);
            border.setRight(vBox);
            gradationCanvas.getChildren().add(border);
        }
    }
}