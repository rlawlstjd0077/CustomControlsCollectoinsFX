package ui.screen.stationkeeping;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ui.UiUtil;
import ui.common.DateAxis;
import ui.common.ManeuverListTableRowViewModel;
import ui.common.OutputListTableRowViewModel;
import ui.control.chart.CircleChart;
import ui.control.chart.ScrollChart;
import ui.control.datepicker.DatePickerControl;
import ui.control.datetimepicker.DateTimePicker;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

/**
 * Created by GSD on 2017-01-19.
 */
public class StationKeepingController extends AnchorPane implements Initializable {
  @FXML
  private Pane stationStartDateTimePickerPane;
  @FXML
  private Pane stationEndDateTimePickerPane;
  @FXML
  private Pane stationKeepingScrollChartPane;
  @FXML
  private Pane stationEpochDateTimePickerPane;
  @FXML
  private ComboBox<String> stationStackSourceTypeComboBox;
  @FXML
  private ComboBox<String> DisplayTypeComboBox;
  @FXML
  private ComboBox<String> CoordinateFrameComboBox;
  @FXML
  private GridPane stationEpochGridPane;
  //첫 column index 0부터 시작
  @FXML
  private Spinner<String> stationAlgorithmEWSpinner;
  @FXML
  private Spinner<String> stationAlgorithmNSSpinner;
  @FXML
  private Pane stationRefDateEWDatePickerPane;
  @FXML
  private Pane stationRefDataNSDatePickerPane;
  @FXML
  private Spinner<String> stationTaskingEWSpinner;
  @FXML
  private Spinner<String> stationTaskingNSSpinner;
  @FXML
  private Spinner<String> stationThrusterEWSpinner;
  @FXML
  private Spinner<String> stationThrusterNSSpinner;
  @FXML
  private Spinner<String> stationTargetEWSpinner;
  @FXML
  private Spinner<String> stationTargetNSSpinner;
  @FXML
  private Spinner<String> stationBoxWidthEWSpinner;
  @FXML
  private Spinner<String> stationBoxWidthNSSpinner;
  @FXML
  private Spinner<String> stationEccentricityLimitSpinner;
  @FXML
  private GridPane stationManeuverListGridPane;
  //첫 column은 공간 확보용임으로 column index는 1부터 시작
  @FXML
  private Label eastWestTotalLabel;
  @FXML
  private Label northSouthTotalLabel;
  @FXML
  private GridPane stationOutputListGridPane;
  //첫 column은 공간 확보용임으로 column index는 1부터 시작
  @FXML
  private Button stationNewWindow;
  @FXML
  private Button stationSave;
  @FXML
  private Button stationPrint;
  @FXML
  private VBox firstChartPane;
  @FXML
  private Pane secondChartPane;
  @FXML
  private NumberAxis thirdxAxis;
  @FXML
  private NumberAxis thirdyAxis;
  @FXML
  private LineChart thirdChart;
  @FXML
  private Pane fourthChartPane;
  @FXML
  private CheckBox firstUnmaneuveCheckBox;
  @FXML
  private Label firstResidualLabel;
  @FXML
  private Label secondInclinationLabel;
  @FXML
  private CheckBox secondUnmaneuverCheckBox;
  @FXML
  private CheckBox secondSunCheckBox;
  @FXML
  private CheckBox secondMoonCheckBox;
  @FXML
  private CheckBox thirdUnmaneuverCheckBox;
  @FXML
  private Label thirdLogitudeLabel;
  @FXML
  private Label fourthEccentricityLabel;
  @FXML
  private CheckBox fourthUnmaneuverCheckBox;
  @FXML
  private CheckBox fourthSunCheckBox;
  @FXML
  private TabPane stationMainTab;

  private TextViewerEventHandler handler;

  private DateTimePicker stationStartDateTimePicker;
  private DateTimePicker stationEndDateTimePicker;
  private DateTimePicker stationEpochDateTimePicker;
  private DatePickerControl stationRefDateEWDatePicker;
  private DatePickerControl stationRefDataNSDatePicker;
  private CircleChart secondChart;
  private CircleChart fourthChart;
  private ScrollChart stationKeepingScrollChart;
  private LineChart firstChart;
  private DateAxis firstxAxis;
  private NumberAxis firstyAxis;
  /**
   * Station Keeping 화면 생성자.
   *
   * @throws IOException
   */
  public StationKeepingController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  /**
   * Station Keeping 화면과 ViewModel을 바인딩.
   * @param viewModel
   */
  public void bind(StationKeepingViewModel viewModel) {

//    //첫번째 Chart Binding
//    firstxAxis.lowerBoundProperty().bind();
//    firstxAxis.upperBoundProperty().bind();
//    firstxAxis.tickLabelFormatterProperty().bind();
//
//    firstyAxis.lowerBoundProperty().bind();
//    firstyAxis.upperBoundProperty().bind();
//    firstyAxis.tickUnitProperty().bind();
//    firstyAxis.setLabel("longitude(deg)");
//    firstChart.dataProperty().bind();
//
//    //세번째 Chart Binding
//    thirdxAxis.lowerBoundProperty().bind();
//    thirdxAxis.upperBoundProperty().bind();
//    thirdxAxis.tickUnitProperty().bind();
//    thirdxAxis.setLabel("Longitude(deg)");
//    thirdyAxis.lowerBoundProperty().bind();
//    thirdyAxis.upperBoundProperty().bind();
//    thirdyAxis.tickUnitProperty().bind();
//    thirdyAxis.setLabel("Semi-Major Axis(km)");
//    thirdChart.dataProperty().bind();

    stationNewWindow.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
    stationSave.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
    stationPrint.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);

    for(int i = 0; i < viewModel.maneuverListTableRowViewModelList.size(); i++){
      ManeuverListTableRowViewModel element = viewModel.maneuverListTableRowViewModelList.get(i);
      element.setMyOnChecked(new ManeuverListTableRowViewModel.MyOnChecked() {
        @Override
        public void onChecked() {

        }
      });
      stationManeuverListGridPane.add(element.getChecked(), 1, i);
      stationManeuverListGridPane.add(new Label(element.timeProperty().getValue() + ""), 2 , i);
      stationManeuverListGridPane.add(new Label(element.typeProperty().getValue() + ""), 3 , i);
      stationManeuverListGridPane.add(new Label(element.thrusterSetIDProperty().getValue() + ""), 4 , i);
      stationManeuverListGridPane.add(new Label(element.delVxProperty().getValue() + ""), 5 , i);
      stationManeuverListGridPane.add(new Label(element.delVyProperty().getValue() + ""), 6 , i);
      stationManeuverListGridPane.add(new Label(element.delVzProperty().getValue() + ""), 7 , i);
    }
    for(int i = 0; i < viewModel.outputListTableRowViewModelList.size(); i++){
      OutputListTableRowViewModel element = viewModel.outputListTableRowViewModelList.get(i);
    }
    //두번째 Chart 바인딩
    secondChart.setAxisProperty(0, 10, 2);
    secondChart.drawObjectCircle(8);
    secondChart.drawDateLabel(60, ZonedDateTime.parse("2016-06-03T10:15:30+01:00[Europe/Paris]"));

    XYChart.Series series1 = new XYChart.Series();
    series1.getData().add(new XYChart.Data(4.2, 1));
    series1.getData().add(new XYChart.Data(2.8, 1));
    series1.getData().add(new XYChart.Data(6.2, 1));
    series1.getData().add(new XYChart.Data(1, 1));
    series1.getData().add(new XYChart.Data(1.2, 1));
    series1.getData().add(new XYChart.Data(4.4, 1));
    series1.getData().add(new XYChart.Data(8.5, 1));
    series1.getData().add(new XYChart.Data(6.9, 1));
    series1.getData().add(new XYChart.Data(9.9, 2));
    //ScrollChart 바인딩
    ZoneId zoneId = ZoneId.of("UTC+1");
    stationKeepingScrollChart.setDatas(null, ZonedDateTime.of(2015, 11, 1, 11, 45, 59, 1234, zoneId), ZonedDateTime.of(2015, 11, 20, 0, 45, 59, 1234, zoneId),null, null);
  }
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    handler = new TextViewerEventHandler(this, stationMainTab);
    stationEpochGridPane.add(new Button(), 0, 0);
    stationStartDateTimePicker = new DateTimePicker(200, 28, 12);
    stationEndDateTimePicker = new DateTimePicker(200, 28, 12);
    stationEpochDateTimePicker = new DateTimePicker(200, 28, 12);
    stationRefDateEWDatePicker = new DatePickerControl(118, 28, 12);
    stationRefDataNSDatePicker = new DatePickerControl(118, 28, 12);
    stationStartDateTimePickerPane.getChildren().add(stationStartDateTimePicker);
    stationEndDateTimePickerPane.getChildren().add(stationEndDateTimePicker);
    stationEpochDateTimePickerPane.getChildren().add(stationEpochDateTimePicker);
    stationRefDateEWDatePickerPane.getChildren().add(stationRefDateEWDatePicker);
    stationRefDataNSDatePickerPane.getChildren().add(stationRefDataNSDatePicker);

    firstxAxis = new DateAxis();
    firstyAxis = new NumberAxis();
    firstChart = new LineChart(firstxAxis, firstyAxis);
    firstChartPane.getChildren().add(firstChart);

    secondChart = new CircleChart();
    fourthChart = new CircleChart();
    stationKeepingScrollChart = new ScrollChart();
    secondChartPane.getChildren().add(secondChart);
    secondChart.setLayoutX(10);
    fourthChartPane.getChildren().add(fourthChart);
    fourthChart.setLayoutX(10);
    stationKeepingScrollChartPane.getChildren().add(stationKeepingScrollChart);
  }
  public void openNewFile(String path){
    handler.openNewTab(path);
  }
}
