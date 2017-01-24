package sample.circlechart;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * Created by GSD on 2017-01-24.
 */
public class CircleChart extends StackPane {
    ScatterChart coreChart;
    NumberAxis xAxis;
    NumberAxis yAxis;
    Circle outsideCircle;
    AnchorPane gradationCanvas;


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


        getChildren().add(coreChart);
        setMargin(coreChart, new Insets(10, 10, 0, 0));
        outsideCircle = new Circle(165, Color.TRANSPARENT);
        outsideCircle.setStroke(Color.BLACK);
        getChildren().add(outsideCircle);
        for (int i = 1; i <= 12; i++) {
            Line line = new Line(-175, 0, 175, 0);
            line.setRotate(i * 15);
            line.setStroke(Color.GRAY);
            line.setStyle("-fx-stroke-dash-array: 12 2 4 2;");
            getChildren().add(line);
        }
        gradationCanvas = new AnchorPane();
        gradationCanvas.setTranslateX(190);
        gradationCanvas.setTranslateY(190);
        getChildren().add(gradationCanvas);
        doDraw();
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

    public void doDraw() {
        for (int i = 1; i <= 4; i++) {
            for (double j = 0 + xAxis.getTickUnit(); j < xAxis.getUpperBound(); j += xAxis.getTickUnit()) {
                Line line = new Line(5, 0, -5, 0);
                line.setRotate(i * 90);
                if (i % 2 == 1) {
                    line.setLayoutX((j * outsideCircle.getRadius()) / xAxis.getUpperBound());
                } else {
                    line.setLayoutY((j * outsideCircle.getRadius()) / xAxis.getUpperBound());
                }
                gradationCanvas.getChildren().add(line);
            }
        }
    }
}