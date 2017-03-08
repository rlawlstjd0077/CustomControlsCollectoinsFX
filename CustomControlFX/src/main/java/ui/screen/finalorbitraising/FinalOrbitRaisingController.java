package ui.screen.finalorbitraising;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import ui.UiUtil;
import ui.common.DateAxis;
import ui.common.TabTextViewerEventHandler;
import ui.control.datetimepicker.DateTimePicker;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

/**
 * Created by GSD on 2017-02-22.
 */
public class FinalOrbitRaisingController extends AnchorPane implements Initializable{
  @FXML
  private HBox finalOrbitStartDateTimePickerPane;
  @FXML
  private HBox finalOrbitEndDateTimePickerPane;
  @FXML
  private HBox finalOrbitEpochDateTimePickerPane;
  @FXML
  private Button finalOrbitEpochSelectButton;
  @FXML
  private ComboBox<String> stackSourceTypeComboBox;
  @FXML
  private ComboBox<String> displayTypeComboBox;
  @FXML
  private ComboBox<String> coordinateFrameComboBox;
  @FXML
  private GridPane finalOrbitEpochGridPane;
  @FXML
  private Button finalOrbitStackDataButton;
  @FXML
  private Button finalOrbitSettingButton;
  @FXML
  private Button finalOrbitSavePrameterButton;
  @FXML
  private ComboBox<String> finalOrbitManeuverTypeComboBox;
  @FXML
  private Spinner<Double> finalOrbitLongitudeSpinner;
  @FXML
  private CheckBox finalOrbitUpdateManeuverCheckBox;
  @FXML
  private CheckBox finalOrbitApplyManeuverCheckBox;
  @FXML
  private CheckBox finalOrbitApplyPlumeCheckBox;
  @FXML
  private Button finalOrbitPlanningButton;
  @FXML
  private Button finalOrbitReconstructionButton;
  @FXML
  private GridPane outputListGridPane;
  @FXML
  private TabPane finalOrbitMainTab;
  @FXML
  private Button textViewerSaveButton;
  @FXML
  private Button textViewerPrintButton;
  @FXML
  private Button textViewerNewWindowButton;
  @FXML
  private VBox firstChartBox;
  @FXML
  private VBox secondChartBox;
  @FXML
  private Label firstChartLabel;
  @FXML
  private Label secondChartLabel;

  private DateTimePicker startDateTimePicker;
  private DateTimePicker endDateTimePicker;
  private DateTimePicker epochDateTimePicker;
  private LineChart<Number, Number> firstChart;
  private LineChart<Date, Number> secondChart;
  private NumberAxis firstXAxis;
  private NumberAxis firstYAxis;
  private DateAxis secondXAxis;
  private NumberAxis secondYAxis;
  private TabTextViewerEventHandler tabTextViewerEventHandler;

  public FinalOrbitRaisingController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  /**
   * FinalOrbitRaising 화면과 ViewModel을 바인딩
   * @param viewModel
   */
  public void bind(FinalOrbitRaisingViewModel viewModel) {
    startDateTimePicker = new DateTimePicker(180, 28, 12);
    endDateTimePicker = new DateTimePicker(180, 28, 12);
    epochDateTimePicker = new DateTimePicker(180, 28, 12);
    finalOrbitStartDateTimePickerPane.getChildren().add(startDateTimePicker);
    finalOrbitEndDateTimePickerPane.getChildren().add(endDateTimePicker);
    finalOrbitEpochDateTimePickerPane.getChildren().add(epochDateTimePicker);
    finalOrbitStartDateTimePickerPane.setMargin(startDateTimePicker, new Insets(2,0,0,0));
    finalOrbitEndDateTimePickerPane.setMargin(endDateTimePicker, new Insets(2,0,0,0));
    /**
     *  first, second chart setting
     */
    firstXAxis = new NumberAxis();
    firstYAxis = new NumberAxis();
    firstXAxis.setAutoRanging(false);
    firstYAxis.setAutoRanging(false);
    firstXAxis.setLabel("Longitude (deg)");
    firstYAxis.setLabel("Semi - Major Axis (km)");
    firstChart = new LineChart(firstXAxis, firstYAxis);
    firstChart.setLegendVisible(false);
    firstChart.setStyle("-fx-background-color:transparent");
    firstChartBox.getChildren().add(firstChart);
    firstChart.setPrefHeight(275);

    secondXAxis = new DateAxis();
    secondYAxis = new NumberAxis();
    secondXAxis.setAutoRanging(false);
    secondYAxis.setAutoRanging(false);
    secondYAxis.setLabel("Longitude (deg)");
    secondXAxis.setLabel("Time");
    secondXAxis.setLowerBound(new GregorianCalendar(2016, 01,01).getTime());
    secondXAxis.setUpperBound(new GregorianCalendar(2017, 01,01).getTime());
    secondXAxis.setTickLabelGap(2);
    secondXAxis.setTickLabelFormatter(new StringConverter<Date>() {
      @Override
      public String toString(Date object) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(object);
      }

      @Override
      public Date fromString(String string) {
        return null;
      }
    });
    secondChart = new LineChart<>(secondXAxis, secondYAxis);
    secondChart.setPrefHeight(275);
    secondChart.setStyle("-fx-background-color:transparent");
    secondChartBox.getChildren().add(secondChart);

    /**
     * TextViewer, ChartNewWindowViewer 들을 세팅
     */
    tabTextViewerEventHandler = new TabTextViewerEventHandler(this, finalOrbitMainTab);

    textViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }
}
