package sample.ui.control.collocationcirclechart;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

import java.time.ZonedDateTime;

/**
 * Created by GSD on 2017-01-24.
 */
public class CollocationCircleChart extends StackPane {
    private final String CSS_PATH = "/sample/ui/control/collocationcirclechart/collocationcirclechart.css";
    private LineChart<Number, Number> coreChart;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private Circle outsideCircle;
    private AnchorPane gradationCanvas;
    private AnchorPane dateLabelCanvas;
    private AnchorPane bottomCanvas;
    private AnchorPane baseCanvas;
    private final int FIXED_SIZE = 230;

    public CollocationCircleChart() {
        getStylesheets().add(CSS_PATH);
        setPadding(new Insets(10));
        setPrefSize(FIXED_SIZE, FIXED_SIZE);
        setMaxSize(FIXED_SIZE, FIXED_SIZE);
        setMinSize(FIXED_SIZE, FIXED_SIZE);

        xAxis = new NumberAxis(0, 10,1);
        yAxis = new NumberAxis(0, 10,1);
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);

        coreChart = new LineChart<>(xAxis, yAxis);
        coreChart.setPrefSize(FIXED_SIZE, FIXED_SIZE);
        coreChart.setMaxSize(FIXED_SIZE, FIXED_SIZE);
        coreChart.setMinSize(FIXED_SIZE, FIXED_SIZE);
        coreChart.setLegendVisible(false);
        getChildren().add(coreChart);

        XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
        series.getData().add(new XYChart.Data<Number, Number>(1, 1));
        series.getData().add(new XYChart.Data<Number, Number>(2, 1));
        XYChart.Series<Number, Number> series1 = new XYChart.Series<Number, Number>();
        series1.getData().add(new XYChart.Data<Number, Number>(1, 2));
        series1.getData().add(new XYChart.Data<Number, Number>(2, 2));
        XYChart.Series<Number, Number> series2 = new XYChart.Series<Number, Number>();
        series2.getData().add(new XYChart.Data<Number, Number>(1, 3));
        series2.getData().add(new XYChart.Data<Number, Number>(2, 3));
        coreChart.getData().addAll(series, series1, series2);
    }

    public LineChart getCoreChart(){
        return this.coreChart;
    }
}
