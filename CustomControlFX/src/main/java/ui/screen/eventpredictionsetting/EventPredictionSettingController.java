package ui.screen.eventpredictionsetting;

import commons.log.LogUtil;
import commons.log.Logger;
import commons.ui.UiUtil;
import commons.ui.control.chart.EventPredictionChart;
import commons.ui.control.datetimepicker.DateTimePicker;
import fds.FacadeService;
import fds.ui.common.GroundStationSelectionTableRowViewModel;
import fds.ui.common.TextViewerEventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by GSD on 2017-02-14.
 */
public class EventPredictionSettingController extends AnchorPane implements Initializable{
  private final static Logger logger = LogUtil.getLogger(FacadeService.class.getName());
  @FXML
  private Pane eventStartDateTimePickerPane;
  @FXML
  private Pane eventEndDateTimePickerPane;
  @FXML
  private Pane eventEpochDateTimePickerPane;
  @FXML
  private Button eventEpochSelectButton;
  @FXML
  private ComboBox<String> eventStackSourceTypeComboBox;
  @FXML
  private ComboBox<String> eventDisplayTypeComboBox;
  @FXML
  private ComboBox<String> eventCoordinateFrameComboBox;
  @FXML
  private GridPane eventEpochGridPane;
  @FXML
  private ComboBox<String> eventPredictionModeComboBox;
  @FXML
  private TextField eventOrbitPredictionDataTextField;
  @FXML
  private Button eventOrbitPredictionDataFileChooserButton;
  @FXML
  private TextField eventOrbitPredictionDataStartTextField;
  @FXML
  private TextField eventOrbitPredictionDataEndTextField;
  @FXML
  private Spinner<Integer> eventReferenceLongitudeSpinner;
  @FXML
  private Spinner<Integer> eventOrbitIntervalSpinner;
  @FXML
  private CheckBox eventApplyWheelCheckBox;
  @FXML
  private CheckBox eventApplyStationCheckBox;
  @FXML
  private CheckBox eventEarthGravityCheckBox;
  @FXML
  private CheckBox eventSunMoonGravityCheckBox;
  @FXML
  private Spinner<Integer> eventDegreeSpinner;
  @FXML
  private CheckBox eventSolarRadiationCheckBox;
  @FXML
  private Spinner<Integer> eventOrderSpinner;
  @FXML
  private CheckBox eventAtmosphericCheckBox;
  @FXML
  private ComboBox<String> eventPredictionPeriodComboBox;
  @FXML
  private Spinner<Integer> eventPredictionPeriodSpinner;
  @FXML
  private Button eventPredictionPeriodApplyButton;
  @FXML
  private CheckBox eventEclipseCheckBox;
  @FXML
  private CheckBox eventBoxLimitCheckBox;
  @FXML
  private CheckBox eventSensorIntrusionCheckBox;
  @FXML
  private CheckBox eventAMIIntrusionCheckBox;
  @FXML
  private CheckBox eventInterfaceCheckBox;
  @FXML
  private CheckBox eventOrbitCrossingCheckBox;
  @FXML
  private CheckBox eventEmptyCheckBox1;
  @FXML
  private CheckBox eventEmptyCheckBox2;
  @FXML
  private Button eventSaveParameterButton;
  @FXML
  private Button eventExcuteButton;
  @FXML
  private GridPane outputListGridPane;
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
  private CheckBox eventChartSunCheckBox;
  @FXML
  private CheckBox eventChartMoonCheckBox;
  @FXML
  private VBox EventPredictionChartPane;
  @FXML
  private TabPane eventMainTab;
  @FXML
  private BorderPane EventPredictionChartPaneContainer;
  @FXML
  private VBox PreciseOrbitPredictionBox;
  @FXML
  private VBox MeanOrbitPredictionBox;
  @FXML
  private VBox usingOrbitPredictionDataBox;
  //Predictoin Model 선택에 따른 변화로 visible을 변경하여 사용
  @FXML
  private TableView<GroundStationSelectionTableRowViewModel> groundStationTableView;
  @FXML
  private TableColumn<GroundStationSelectionTableRowViewModel, Integer> groundStationFirstColumn;
  @FXML
  private TableColumn<GroundStationSelectionTableRowViewModel, Integer> groundStationSecondColumn;
  @FXML
  private TableColumn<GroundStationSelectionTableRowViewModel, String> groundStationThirdColumn;
  @FXML
  private TableColumn<GroundStationSelectionTableRowViewModel, Double> groundStationFourthColumn;
  @FXML
  private TableColumn<GroundStationSelectionTableRowViewModel, Double> groundStationFifthColumn;
  @FXML
  private TableColumn<GroundStationSelectionTableRowViewModel, Double> groundStationSixthColumn;
  @FXML
  private TableColumn<GroundStationSelectionTableRowViewModel, Double> groundStationSeventhColumn;
  @FXML
  private TableColumn<GroundStationSelectionTableRowViewModel, Double> groundStationEighthColumn;

  private EventPredictionChart eventPredictionChart;
  private DateTimePicker startDateTimePicker;
  private DateTimePicker endDateTimePicker;
  private DateTimePicker epochDateTimePicker;
  private TextViewerEventHandler textViewerEventHandler;
  private EventPredictionChartViewerEventHandler eventPredictionChartViewerEventHandler;

  public EventPredictionSettingController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }
  /**
   * EventPreciseSetting 화면과 ViewModel을 바인딩.
   */
  public void bind(EventPredictionSettingViewModel viewModel) {
    startDateTimePicker = new DateTimePicker(205, 28, 12);
    endDateTimePicker = new DateTimePicker(205, 28, 12);
    epochDateTimePicker = new DateTimePicker(200, 28, 12);
    eventStartDateTimePickerPane.getChildren().add(startDateTimePicker);
    eventEndDateTimePickerPane.getChildren().add(endDateTimePicker);
    eventEpochDateTimePickerPane.getChildren().add(epochDateTimePicker);

    eventChartSunCheckBox.setSelected(true);
    eventChartMoonCheckBox.setSelected(true);
    eventChartSunCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
      eventPredictionChart.setDataSunVisible(newValue);
    });
    eventChartMoonCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
      eventPredictionChart.setMoonDataVisible(newValue);
    });

    textViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);
    textViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);
    textViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);
    chartViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventPredictionChartViewerEventHandler);
    chartViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventPredictionChartViewerEventHandler);
    chartViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventPredictionChartViewerEventHandler);

    groundStationFirstColumn.setCellValueFactory(cellData -> cellData.getValue().indexProperty().asObject());
    groundStationFirstColumn.setCellFactory(fc -> {
      TableCell<GroundStationSelectionTableRowViewModel, Integer> cell = new TableCell<GroundStationSelectionTableRowViewModel, Integer>(){
        @Override
        protected void updateItem(Integer item, boolean empty) {
          if (!empty) {
            CheckBox checkBox = new CheckBox();
            checkBox.getStyleClass().add("fds-check-box");
            setGraphic(checkBox);
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
              System.out.println(item + "clicked");
            });
            super.updateItem(item, empty);
          }
        }
      };
      return cell;
    });
    groundStationSecondColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
    groundStationThirdColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    groundStationFourthColumn.setCellValueFactory(cellData -> cellData.getValue().logitudeProperty().asObject());
    groundStationFifthColumn.setCellValueFactory(cellData -> cellData.getValue().latitudeProperty().asObject());
    groundStationSixthColumn.setCellValueFactory(cellData -> cellData.getValue().heightProperty().asObject());
    groundStationSeventhColumn.setCellValueFactory(cellData -> cellData.getValue().elevationProperty().asObject());
    groundStationEighthColumn.setCellValueFactory(cellData -> cellData.getValue().beamWidthProperty().asObject());

    groundStationTableView.setItems(viewModel.groundStationSelectionTableRowViewModelList);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    eventPredictionChart = new EventPredictionChart();
    EventPredictionChartPane.getChildren().add(eventPredictionChart);
    eventPredictionChart.setxAxis(-35, 35, 5);
    eventPredictionChart.setyAxis(-25, 25, 5);
//    eventPredictionChart.setCircleData(50); Circle(Earth) Data Setting

    XYChart.Series series1 = new XYChart.Series();
    //populating the series with data
    series1.getData().add(new XYChart.Data(1, 3));
    series1.getData().add(new XYChart.Data(2, 4));
    series1.getData().add(new XYChart.Data(3, 5));
    series1.getData().add(new XYChart.Data(4, 4));
    series1.getData().add(new XYChart.Data(5, 4));
    series1.getData().add(new XYChart.Data(6, 6));
    series1.getData().add(new XYChart.Data(7, 2));
    series1.getData().add(new XYChart.Data(8, 5));
    series1.getData().add(new XYChart.Data(9, 3));
    series1.getData().add(new XYChart.Data(10, 7));
    series1.getData().add(new XYChart.Data(11, 9));
    series1.getData().add(new XYChart.Data(12, 5));

    XYChart.Series series2 = new XYChart.Series();
    //populating the series with data
    series2.getData().add(new XYChart.Data(1, -3));
    series2.getData().add(new XYChart.Data(2, -4));
    series2.getData().add(new XYChart.Data(3, -5));
    series2.getData().add(new XYChart.Data(4, 4));
    series2.getData().add(new XYChart.Data(5, -4));
    series2.getData().add(new XYChart.Data(6, -6));
    series2.getData().add(new XYChart.Data(7, -2));
    series2.getData().add(new XYChart.Data(8, -5));
    series2.getData().add(new XYChart.Data(9, -3));
    series2.getData().add(new XYChart.Data(10, -7));
    series2.getData().add(new XYChart.Data(11, -9));
    series2.getData().add(new XYChart.Data(12, -5));

    XYChart.Series series3 = new XYChart.Series();
    //populating the series with data
    series3.getData().add(new XYChart.Data(-1, 23));
    series3.getData().add(new XYChart.Data(-2, 14));
    series3.getData().add(new XYChart.Data(-3, 15));
    series3.getData().add(new XYChart.Data(-4, 24));
    series3.getData().add(new XYChart.Data(-5, 34));
    series3.getData().add(new XYChart.Data(-6, 36));
    series3.getData().add(new XYChart.Data(-7, 22));
    series3.getData().add(new XYChart.Data(-8, 45));
    series3.getData().add(new XYChart.Data(-9, 43));
    series3.getData().add(new XYChart.Data(-10, 17));
    series3.getData().add(new XYChart.Data(-11, 29));
    series3.getData().add(new XYChart.Data(-12, 25));

    ObservableList<XYChart.Series<Number, Number>> list = FXCollections.observableArrayList();
    list.addAll(series1, series2);
    eventPredictionChart.setSunMoonData(list);
    eventPredictionChart.setOtherData(series3, "green");

    textViewerEventHandler = new TextViewerEventHandler(this, eventMainTab);
    eventPredictionChartViewerEventHandler = new EventPredictionChartViewerEventHandler(this, EventPredictionChartPaneContainer, eventPredictionChart);
  }
}
