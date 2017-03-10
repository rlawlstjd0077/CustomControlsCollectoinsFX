package ui.screen.eventpredictionsetting;

import commons.ui.control.chart.EventPredictionChart;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by GSD on 2017-02-17.
 */
public class EventPredictionNewWindowChartViewer extends BorderPane{
  @FXML
  private VBox EventPredictionChartPane;
  @FXML
  private CheckBox chartSunCheckBox;
  @FXML
  private CheckBox chartMoonCheckBox;

  public EventPredictionNewWindowChartViewer(EventPredictionChart chart){
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EventPredictionNewWindowChartViewer.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try{
      fxmlLoader.load();
    }catch (IOException e){
      e.printStackTrace();
    }

    chartSunCheckBox.setSelected(true);
    chartMoonCheckBox.setSelected(true);
    EventPredictionChartPane.getChildren().add(chart);
    chartSunCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
      chart.setDataSunVisible(newValue);
    });
    chartMoonCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
      chart.setMoonDataVisible(newValue);
    });

  }
}
