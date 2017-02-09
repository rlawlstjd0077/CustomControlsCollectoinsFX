package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        root.setStyle("-fx-background-color:white");
        primaryStage.setTitle("TextViewer Test");
        primaryStage.setScene(new Scene(root, 800,800));
        primaryStage.show();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ObservableList<XYChart.Series<Date, Number>> series = FXCollections.observableArrayList();
//
//        ObservableList<XYChart.Data<Date, Number>> series1Data = FXCollections.observableArrayList();
//        series1Data.add(new XYChart.Data<Date, Number>(new GregorianCalendar(2018,2, 12).getTime(), 2));
//        series1Data.add(new XYChart.Data<Date, Number>(new GregorianCalendar(2018, 2, 15).getTime(), 3));
//
//        series1Data.add(new XYChart.Data<Date, Number>(new GregorianCalendar(2018, 2, 16).getTime(), 4));
//
//
//        series.add(new XYChart.Series<>("Series1", series1Data));
//
//        NumberAxis numberAxis = new NumberAxis();
//        DateAxis dateAxis = new DateAxis();
//        dateAxis.setAutoRanging(false);
//        dateAxis.setLowerBound(new GregorianCalendar(2018,2, 12).getTime());
//        dateAxis.setUpperBound(new GregorianCalendar(2018,2, 16).getTime());
//        dateAxis.setTickLabelFormatter(new StringConverter<Date>() {
//            @Override
//            public String toString(Date object) {
//                return simpleDateFormat.format(object);
//            }
//            @Override
//            public Date fromString(String string) {
//                return null;
//            }
//        });
//        NumberAxis xAxis = new NumberAxis(-100, 100, 10);
//        NumberAxis yAxis = new NumberAxis(-100, 100, 10);
//        xAxis.setTickLabelsVisible(false);
//        yAxis.setTickLabelsVisible(false);
//        ScatterChart<Number, Number> scatterChart = new ScatterChart<Number, Number>(xAxis, yAxis);
//        scatterChart .getStylesheets().add("/sample/style.css");
//        primaryStage.setTitle("Line Chart Sample");
//        yAxis.setLowerBound(1000);
//        yAxis.setUpperBound(2000);
//        yAxis.setTickUnit(500);
//        XYChart.Series series1 = new XYChart.Series();
//        series1.setName("Portfolio 1");
//        LineChart lineChart = new LineChart(dateAxis, numberAxis, series);
//        Line line = new Line(0, -5, 0, 5);
//        line.setStrokeWidth(10);
//        HBox hBox = new HBox(lineChart, line);
//        Scene scene = new Scene(hBox, 900, 400);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
