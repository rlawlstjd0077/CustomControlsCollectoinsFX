package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import sample.circlechart.CircleChart;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    CircleChart circleChart;
    private NumberAxis xAxis;
    private NumberAxis yAxis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        xAxis = circleChart.getxAxis();
//        yAxis = circleChart.getyAxis();
//
//        xAxis.setLowerBound(-7);
//        xAxis.setUpperBound(7);
//        xAxis.setTickUnit(1);
//        xAxis.setAutoRanging(false);
//        yAxis.setAutoRanging(false);
//
//        yAxis.setLowerBound(-7);
//        yAxis.setUpperBound(7);
//        yAxis.setTickUnit(1);
//
//        XYChart.Series series = new XYChart.Series();
//
//        series.getData().add(new XYChart.Data<>(1, 1));
//        series.getData().add(new XYChart.Data<>(1.5, 1.5));
//        series.getData().add(new XYChart.Data<>(2.5, 2));
//        series.getData().add(new XYChart.Data<>(3, 2.5));
//        series.getData().add(new XYChart.Data<>(2, 3));
//        series.getData().add(new XYChart.Data<>(1, 3.5));
//        circleChart.getCoreChart().getData().add(series);
//        circleChart.getCoreChart().setLegendVisible(false);
    }
}