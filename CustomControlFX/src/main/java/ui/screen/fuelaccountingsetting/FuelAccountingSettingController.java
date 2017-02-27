package ui.screen.fuelaccountingsetting;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import ui.common.TextViewerEventHandler;
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
public class FuelAccountingSettingController extends AnchorPane implements Initializable {
  @FXML
  private TextField fuelTOTDataTextField;
  @FXML
  private Button fuelTOTDataButton;
  @FXML
  private ComboBox<Integer> fuelConstantPoxComboBox;
  @FXML
  private Spinner<Integer> fuelConstantPfuSpinner;
  @FXML
  private CheckBox fuelConstantApplyCheckBox;
  @FXML
  private CheckBox fuelPeriodOptionSyncCheckBox;
  @FXML
  private CheckBox fuelOperationUpdateTOTCheckBox;
  @FXML
  private CheckBox fuelOperationUpdateManeuverCheckBox;
  @FXML
  private ComboBox<Integer> fuelOperationTaskingComboBox;
  @FXML
  private TextField fuelTOTStackIDFrontTextField;
  @FXML
  private TextField fuelTOTStackIDBackTextField;
  @FXML
  private Button fuelTOTStackDisplayButton;
  @FXML
  private Button fuelTOTStackDataUpdateButton;
  @FXML
  private Button fuelTOTStackInsertButton;
  @FXML
  private Button fuelTOTStackDeleteButtonl;
  @FXML
  private ComboBox<String> fuelTOTStackPlotGraphComboBox;
  @FXML
  private Button fuelTOTStackPlotGraphButton;
  @FXML
  private GridPane fuelTOTStackGridPane;
  @FXML
  private Button fuelSaveParameterButton;
  @FXML
  private Button fuelExcuteButton;
  @FXML
  private GridPane outputListGridPane;
  @FXML
  private HBox fuelTOTDataStartDateTimePickerPane;
  @FXML
  private HBox fuelTOTDataEndDateTimePickerPane;
  @FXML
  private HBox fuelConstantStartDateTimePickerPane;
  @FXML
  private HBox fuelConstantEndDateTimePickerPane;
  @FXML
  private HBox fuelTOTStackStartDateTimePickerPane;
  @FXML
  private HBox fuelTOTStackEndDateTimePickerPane;
  @FXML
  private VBox fuelAccountingChartBox;
  @FXML
  private Label fuelAccountingGraphLabel;
  @FXML
  private Button textViewerSaveButton;
  @FXML
  private Button textViewerPrintButton;
  @FXML
  private Button textViewerNewWindowButton;
  @FXML
  private TabPane fuelMainTab;

  private DateTimePicker fuelTOTDataStartDateTimePicker;
  private DateTimePicker fuelTOTDataEndDateTimePicker;
  private DateTimePicker fuelConstantStartDateTimePicker;
  private DateTimePicker fuelConstantEndDateTimePicker;
  private DateTimePicker fuelTOTStackStartDateTimePicker;
  private DateTimePicker fuelTOTStackEndDateTimePicker;
  private ScatterChart<Date, Number> chart;
  private DateAxis xAxis;
  private NumberAxis yAxis;
  private TextViewerEventHandler textViewerEventHandler;
  private ChartViewerEventHandler eventPredictionChartViewerEventHandler;

  public FuelAccountingSettingController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }


  /**
   * FuelAccountingSetting화면과 ViweModel을 바인딩
   * @param viewModel
   */
  public void bind(FuelAccountingSettingViewModel viewModel) {
    /**
     * Chart를 세팅
     */
    xAxis = new DateAxis();
    yAxis = new NumberAxis();
    xAxis.setAutoRanging(false);
    yAxis.setAutoRanging(false);
    yAxis.setLabel("FU_PRESSURE_BAR");
    xAxis.setLabel("Time");
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
    chart.setPrefWidth(500);
    chart.getStylesheets().add(getClass().getResource("fuelchart.css").toExternalForm());
    fuelAccountingChartBox.getChildren().add(chart);

    /**
     * TextView EventHandler 세팅
     */
    textViewerEventHandler = new TextViewerEventHandler(this, fuelMainTab);

    textViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);
    textViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);
    textViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);

    /**
     * DatePicker 세팅
     */

    fuelTOTDataStartDateTimePicker = new DateTimePicker(180, 28, 12);
    fuelTOTDataEndDateTimePicker = new DateTimePicker(180, 28, 12);
    fuelTOTDataStartDateTimePickerPane.getChildren().add(fuelTOTDataStartDateTimePicker);
    fuelTOTDataEndDateTimePickerPane.getChildren().add(fuelTOTDataEndDateTimePicker);

    fuelConstantStartDateTimePicker = new DateTimePicker(180, 28, 12);
    fuelConstantEndDateTimePicker = new DateTimePicker(180, 28, 12);
    fuelConstantStartDateTimePickerPane.getChildren().add(fuelConstantStartDateTimePicker);
    fuelConstantEndDateTimePickerPane.getChildren().add(fuelConstantEndDateTimePicker);

    fuelTOTStackStartDateTimePicker = new DateTimePicker(180, 28, 12);
    fuelTOTStackEndDateTimePicker = new DateTimePicker(180, 28, 12);
    fuelTOTStackStartDateTimePickerPane.getChildren().add(fuelTOTStackStartDateTimePicker);
    fuelTOTStackEndDateTimePickerPane.getChildren().add(fuelTOTStackEndDateTimePicker);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }
}
