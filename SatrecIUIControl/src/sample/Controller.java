package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import sample.ui.control.circlechart.CircleChart;
import sample.ui.control.collocationcirclechart.CollocationCircleChart;

import java.net.URL;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    CollocationCircleChart colloCircleChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data(1, 1));
        series.getData().add(new XYChart.Data(2, 2));
        series.getData().add(new XYChart.Data(3, 3));
        series.getData().add(new XYChart.Data(4, 4));
        series.getData().add(new XYChart.Data(5, 5));
        series.getData().add(new XYChart.Data(6, 4));
        series.getData().add(new XYChart.Data(7, 4));
        series.getData().add(new XYChart.Data(8, 2));
        series.getData().add(new XYChart.Data(9, 2));
        series.getData().add(new XYChart.Data(10, 1));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));

        colloCircleChart.getCoreChart().getData().add(series);
    }
}