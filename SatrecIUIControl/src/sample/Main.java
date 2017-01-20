package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.accordion.Gk2Accordion;
import sample.primaryButton.PrimaryButton;

import java.util.Locale;

public class Main extends Application {
//    final String[] imageNames = new String[]{"Apples", "Flowers", "Leaves"};
//    final Image[] images = new Image[imageNames.length];
//    final ImageView[] pics = new ImageView[imageNames.length];
//    final TitledPane[] tps = new TitledPane[imageNames.length];
    @Override
    public void start(Stage primaryStage) throws Exception{

//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 800, 500));
//        primaryStage.show();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0.53000, 0.59, 0.0005);

        final LineChart lineChart1 = new LineChart(xAxis,  yAxis);
        XYChart.Series data = new XYChart.Series();

        data.getData().add(new XYChart.Data("2004", 0.582502));
        data.getData().add(new XYChart.Data("2005", 0.584026));
        data.getData().add(new XYChart.Data("2006", 0.585007));
        data.getData().add(new XYChart.Data("2007", 0.586216));
        data.getData().add(new XYChart.Data("2008", 0.585559));
        data.getData().add(new XYChart.Data("2009", 0.584491));
        data.getData().add(new XYChart.Data("2010", 0.587672));
        data.getData().add(new XYChart.Data("2011", 0.588575));
        data.getData().add(new XYChart.Data("2012", 0.589837));
        data.getData().add(new XYChart.Data("2013", 0.590701));

        lineChart1.getData().add(data);
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
        Scene scene  = new Scene(lineChart1,800,600);
//        lineChart.getData().addAll(series1);
//
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
