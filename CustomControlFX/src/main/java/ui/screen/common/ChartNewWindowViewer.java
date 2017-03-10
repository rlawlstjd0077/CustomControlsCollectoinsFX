package ui.screen.common;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Date;

/**
 * Created by GSD on 2017-02-20.
 */
public class ChartNewWindowViewer extends VBox{
  @FXML
  private VBox chartViewerChartBox;
  @FXML
  private Label ResidualValue4441Label;
  @FXML
  private Label ResidualValue1202Label;


  public ChartNewWindowViewer(ScatterChart<Date, Number> chart, String sample1, String sample2){
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChartNewWindowViewer.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    setStyle("-fx-background-color:white");


    try{
      fxmlLoader.load();
    }catch (IOException e){
      e.printStackTrace();
    }
//    ResidualValue4441Label.setText(sample1);
//    ResidualValue1202Label.setText(sample2);
    chartViewerChartBox.getChildren().add(chart);
    chart.setAnimated(true);
    chart.setPrefHeight(800);
  }
}
