package ui.screen.stationrelocation;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.StringConverter;
import ui.UiUtil;
import ui.control.datetimepicker.DateTimePicker;
import ui.screen.common.DateAxis;
import ui.screen.common.TabTextViewerEventHandler;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

/**
 * Created by GSD on 2017-02-21.
 */
public class StationRelocationController extends AnchorPane implements Initializable{
  @FXML
  private HBox stationRelocationStartDateTimePickerPane;
  @FXML
  private HBox stationRelocationEndDateTimePickerPane;
  @FXML
  private HBox stationRelocationEpochDateTimePickerPane;
  @FXML
  private Button stationRelocationEpochSelectButton;
  @FXML
  private ComboBox<String> stackSourceTypeComboBox;
  @FXML
  private ComboBox<String> displayTypeComboBox;
  @FXML
  private ComboBox<String> coordinateFrameComboBox;
  @FXML
  private GridPane stationRelocationEpochGridPane;
  @FXML
  private Spinner<Integer> excludedLongitudeSpinner;
  @FXML
  private Spinner<Integer> excludedBoxRangeSpinner;
  @FXML
  private Button excludedAddButton;
  @FXML
  private Button excludedDeleteButton;
  @FXML
  private TableView<ExcludedLongitudeZoneTableRowViewModel> excludedTableView;
  @FXML
  private TableColumn<ExcludedLongitudeZoneTableRowViewModel, Integer> excludedTableFirstColumn;
  @FXML
  private TableColumn<ExcludedLongitudeZoneTableRowViewModel, Double> excludedTableSecondColumn;
  @FXML
  private Button stationRelocationStackDataButton;
  @FXML
  private Button stationRelocationSettingButton;
  @FXML
  private Button stationRelocationSavePrameterButton;
  @FXML
  private ComboBox<String> stationRelocationTransferComboBox;
  @FXML
  private Spinner<Double> stationRelocationLongitudeSpinner;
  @FXML
  private Spinner<Integer> stationRelocationMaxNumberSpinner;
  @FXML
  private Spinner<Integer> stationRelocationMaxTotalSpinner;
  @FXML
  private Spinner<Integer> stationRelocationTaskingMaxSpinner;
  @FXML
  private Spinner<Integer> stationRelocationTaskingMinSpinner;
  @FXML
  private CheckBox stationRelocationUpdateManeuverCheckBox;
  @FXML
  private CheckBox stationRelocationApplyManeuverCheckBox;
  @FXML
  private CheckBox stationRelocationApplyPlumeCheckBox;
  @FXML
  private Button stationRelocationCalculateButton;
  @FXML
  private ComboBox<String> stationRelocationControlComboBox;
  @FXML
  private GridPane stationRelocationControlGridPane;
  @FXML
  private Button stationRelocationPlanningButton;
  @FXML
  private Button stationRelocationReconstructionButton;
  @FXML
  private GridPane outputListGridPane;
  @FXML
  private TabPane stationRelocationMainTab;
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

  public StationRelocationController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }
  /**
   * StationRelocationViewModel 화면과 Viewmodel을 바인딩
   * @param viewModel
   */
  public void bind(StationRelocationViewModel viewModel){
    startDateTimePicker = new DateTimePicker(180, 28, 12);
    endDateTimePicker = new DateTimePicker(180, 28, 12);
    epochDateTimePicker = new DateTimePicker(180, 28, 12);
    stationRelocationStartDateTimePickerPane.getChildren().add(startDateTimePicker);
    stationRelocationEndDateTimePickerPane.getChildren().add(endDateTimePicker);
    stationRelocationEpochDateTimePickerPane.getChildren().add(epochDateTimePicker);
    stationRelocationStartDateTimePickerPane.setMargin(startDateTimePicker, new Insets(2,0,0,0));
    stationRelocationEndDateTimePickerPane.setMargin(endDateTimePicker, new Insets(2,0,0,0));

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
    tabTextViewerEventHandler = new TabTextViewerEventHandler(this, stationRelocationMainTab);

    textViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);

    /**
     * Excluded Table View Setting
     */
    excludedTableView.widthProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) {
        Pane header = (Pane) excludedTableView.lookup("TableHeaderRow");
        if (header.isVisible()) {
          header.setPrefHeight(0);
          header.setVisible(false);
        }
      }
    });


    excludedTableFirstColumn.setCellValueFactory(cellData -> cellData.getValue().longitudeProperty().asObject());
    excludedTableSecondColumn.setCellValueFactory(cellData -> cellData.getValue().boxRangeProperty().asObject());
    excludedTableView.setItems(viewModel.excludedLongitudeZoneTableRowViewModelList);

    /**
     * item 삭제
     */
    excludedDeleteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      excludedTableView.getItems().remove(excludedTableView.getSelectionModel().getSelectedIndex());
    });
  }
  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }
}
