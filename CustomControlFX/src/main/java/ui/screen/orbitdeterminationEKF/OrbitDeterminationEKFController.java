package ui.screen.orbitdeterminationEKF;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import ui.UiUtil;
import ui.common.ChartViewerEventHandler;
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
 * Created by GSD on 2017-02-21.
 */
public class OrbitDeterminationEKFController extends AnchorPane implements Initializable {
  @FXML
  private HBox orbitEKFStartDateTimePickerPane;
  @FXML
  private HBox orbitEKFEndDateTimePickerPane;
  @FXML
  private HBox orbitEKFEpochDateTimePickerPane;
  @FXML
  private Button orbitEKFEpochSelectButton;
  @FXML
  private ComboBox<String> stackSourceTypeComboBox;
  @FXML
  private ComboBox<String> displayTypeComboBox;
  @FXML
  private ComboBox<String> coordinateFrameComboBox;
  @FXML
  private GridPane orbitEKFEpochGridPane;
  @FXML
  private CheckBox determinationApplyWheelCheckBox;
  @FXML
  private CheckBox determinationApplyStationCheckBox;
  @FXML
  private CheckBox determinationApplyRangeCheckBox;
  @FXML
  private CheckBox determinationApplyAximuthCheckBox;
  @FXML
  private CheckBox determinationApplyElevationCheckBox;
  @FXML
  private CheckBox updateOptionOrbitStackCheckBox;
  @FXML
  private Button orbitEKFSaveParameterButton;
  @FXML
  private Button orbitEKFExcuteButton;
  @FXML
  private GridPane outputListGridPane;
  @FXML
  private TabPane orbitEKFMainTab;
  @FXML
  private Button textViewerSaveButton;
  @FXML
  private Button textViewerPrintButton;
  @FXML
  private Button textViewerNewWindowButton;
  @FXML
  private Button chartViewerSaveButton;
  @FXML
  private Button chartViewerPrintButton;
  @FXML
  private Button chartViewerNewWindowButton;
  @FXML
  private VBox chartEntireBox;
  @FXML
  private VBox chartViewerChartBox;
  @FXML
  private Label ResidualValue4441Label;
  @FXML
  private Label ResidualValue1202Label;
  @FXML
  private ComboBox<String> orbitEKFGraphInformationGSComboBox;
  @FXML
  private ComboBox<String> orbitEKFGraphInformationValueComboBox;
  @FXML
  private Label orbitEKFGraphInformationStartLabel;
  @FXML
  private Label orbitEKFGraphInformationEndLabel;
  @FXML
  private Label orbitEKFGraphInformationMinLabel;
  @FXML
  private Label orbitEKFGraphInformationMaxLabel;
  @FXML
  private Label orbitEKFGraphStatisticsCountLabel;
  @FXML
  private Label orbitEKFGraphStatisticsMeanLabel;
  @FXML
  private Label orbitEKFGraphStatisticsVarianceLabel;
  @FXML
  private Label orbitEKFGraphStatisticsStdDevLabel;

  private DateTimePicker startDateTimePicker;
  private DateTimePicker endDateTimePicker;
  private DateTimePicker epochDateTimePicker;
  private ScatterChart<Date, Number> chart;
  private DateAxis xAxis;
  private NumberAxis yAxis;
  private TabTextViewerEventHandler tabTextViewerEventHandler;
  private ChartViewerEventHandler eventPredictionChartViewerEventHandler;


  public OrbitDeterminationEKFController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  /**
   * OrbitDeterminationEKFViewModel과 OrbitDeterminationEKFC 화면을 바인딩
   *
   * @param viewModel
   */
  public void bind(OrbitDeterminationEKFViewModel viewModel) {
    /**
     * DatePicker 들을 세팅
     */
    startDateTimePicker = new DateTimePicker(180, 28, 12);
    endDateTimePicker = new DateTimePicker(180, 28, 12);
    epochDateTimePicker = new DateTimePicker(180, 28, 12);
    orbitEKFStartDateTimePickerPane.getChildren().add(startDateTimePicker);
    orbitEKFEndDateTimePickerPane.getChildren().add(endDateTimePicker);
    orbitEKFEpochDateTimePickerPane.getChildren().add(epochDateTimePicker);
    orbitEKFStartDateTimePickerPane.setMargin(startDateTimePicker, new Insets(7,0,0,0));
    orbitEKFEndDateTimePickerPane.setMargin(endDateTimePicker, new Insets(7,0,0,0));
    /**
     *
     * Chart Setting
     */
    xAxis = new DateAxis();
    yAxis = new NumberAxis();
    xAxis.setAutoRanging(false);
    yAxis.setAutoRanging(false);
    yAxis.setLabel("Time");
    xAxis.setLabel("Residual Value");
    xAxis.setLowerBound(new GregorianCalendar(2016, 01,01).getTime());
    xAxis.setUpperBound(new GregorianCalendar(2017, 01,01).getTime());
    xAxis.setTickLabelGap(2);

    xAxis.setTickLabelFormatter(new StringConverter<Date>() {
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
    chart = new ScatterChart<>(xAxis, yAxis);
    chart.setLegendVisible(false);
    chart.setPrefHeight(345);
    chartViewerChartBox.getChildren().add(chart);
    chart.getStylesheets().add(getClass().getResource("orbitdeterminationEKFchart.css").toExternalForm());

    /**
     * TextViewer, ChartNewWindowViewer 세팅
     */
    tabTextViewerEventHandler = new TabTextViewerEventHandler(this, orbitEKFMainTab);
    eventPredictionChartViewerEventHandler = new ChartViewerEventHandler(this, chartEntireBox, chart);

    textViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    chartViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventPredictionChartViewerEventHandler);
    chartViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventPredictionChartViewerEventHandler);
    chartViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventPredictionChartViewerEventHandler);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }
}
