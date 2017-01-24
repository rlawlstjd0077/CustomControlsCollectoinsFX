package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import sample.accordion.Gk2Accordion;
import sample.primaryButton.PrimaryButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class Main extends Application {
    //    final String[] imageNames = new String[]{"Apples", "Flowers", "Leaves"};
//    final Image[] images = new Image[imageNames.length];
//    final ImageView[] pics = new ImageView[imageNames.length];
//    final TitledPane[] tps = new TitledPane[imageNames.length];
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//        ObservableList<XYChart.Series<Date, Number>> series = FXCollections.observableArrayList();
//
//        ObservableList<XYChart.Data<Date, Number>> series1Data = FXCollections.observableArrayList();
//        series1Data.add(new XYChart.Data<Date, Number>(new GregorianCalendar(2018,2, 12).getTime(), 2));
//        series1Data.add(new XYChart.Data<Date, Number>(new GregorianCalendar(2018, 2, 15).getTime(), 3));
//
//        series1Data.add(new XYChart.Data<Date, Number>(new GregorianCalendar(2018, 2, 16).getTime(), 4));
//
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
//
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
//        final CategoryAxis xAxis = new CategoryAxis();
//        final NumberAxis yAxis = new NumberAxis();
//        xAxis.setLabel("Month");
//        final LineChart<String,Number> lineChart =
//                new LineChart<String,Number>(xAxis,yAxis);
//        lineChart.setTitle("Stock Monitoring, 2010");
//        yAxis.setLowerBound(1000);
//        yAxis.setUpperBound(2000);
//        yAxis.setTickUnit(500);
//        XYChart.Series series1 = new XYChart.Series();
//        series1.setName("Portfolio 1");


//
//        Scene scene = new Scene(chart, 900, 400);
//        lineChart.getData().addAll(series1);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
