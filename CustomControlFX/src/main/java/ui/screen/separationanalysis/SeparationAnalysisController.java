package ui.screen.separationanalysis;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.StringConverter;
import ui.UiUtil;
import ui.common.ObjectOrbitElementTableRowViewModel;
import ui.control.chart.CollocationCircleChart;
import ui.control.chart.CollocationECEFChart;
import ui.control.chart.CollocationInterLowerChart;
import ui.control.chart.CollocationLongitudeChart;
import ui.control.datetimepicker.DateTimePicker;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 * Created by GSD on 2017-02-08.
 */
public class SeparationAnalysisController extends AnchorPane implements Initializable{

  @FXML
  private HBox separationStartDateTimePickerPane;
  @FXML
  private HBox separationEndDateTimePickerPane;
  @FXML
  private Pane separationEpochDateTimePickerPane;
  @FXML
  private ComboBox<String> stackSourceTypeComboBox;
  @FXML
  private ComboBox<String> displayTypeComboBox;
  @FXML
  private ComboBox<String> coordinateFrameComboBox;
  @FXML
  private GridPane separationEpochGridPane;
  @FXML
  private TextField collocationControlCircleTextField;
  @FXML
  private TextField collocationToleranceCircleTextField;
  @FXML
  private TextField collocationAngleTextField;
  @FXML
  private Button addObjectButton;
  @FXML
  private Button removeObjectButton;
  @FXML
  private TableColumn<ObjectOrbitElementTableRowViewModel, String> objectOrbitListColumn1;
  @FXML
  private TableColumn<ObjectOrbitElementTableRowViewModel, String> objectOrbitListColumn2;
  @FXML
  private TableColumn<ObjectOrbitElementTableRowViewModel, String> objectOrbitListColumn3;
  @FXML
  private TableColumn<ObjectOrbitElementTableRowViewModel, String> objectOrbitListColumn4;
  @FXML
  private TableColumn<ObjectOrbitElementTableRowViewModel, String> objectOrbitListColumn5;
  @FXML
  private TableView<ObjectOrbitElementTableRowViewModel> objectOrbitListTableView;
  @FXML
  private Button excuteButton;
  @FXML
  private GridPane outputListGridPane;
  //첫 column은 공간 확보용임으로 column index는 1부터 시작
  @FXML
  private VBox firstChartPane;
  @FXML
  private VBox secondChartPane;
  @FXML
  private VBox thirdChartPane;
  @FXML
  private VBox fourthChartPane;
  @FXML
  private VBox fifthChartPane;
  @FXML
  private LineChart<Number, Number> sixthChart;
  @FXML
  private NumberAxis sixthxAxis;
  @FXML
  private NumberAxis sixthyAxis;
  @FXML
  private LineChart<Number, Number> seventhChart;
  @FXML
  private NumberAxis seventhxAxis;
  @FXML
  private NumberAxis seventhyAxis;
  @FXML
  private LineChart<Number, Number> eighthChart;
  @FXML
  private NumberAxis eighthxAxis;
  @FXML
  private NumberAxis eighthyAxis;
  @FXML
  private Label firstGK2AValueLabel;
  @FXML
  private Label firstGK2BValueLabel;
  @FXML
  private Label secondGK2AValueLabel;
  @FXML
  private Label secondGK2BValueLabel;
  @FXML
  private Label thirdGK2AValueLabel;
  @FXML
  private Label thirdGK2BValueLabel;
  @FXML
  private Label fourthValueLabel;
  @FXML
  private Label fifthValueLabel;
  @FXML
  private Label sixthValueLabel;
  @FXML
  private Label seventhGK2AValueLabel;
  @FXML
  private Label eighthGK2AValueLabel;

  private DateTimePicker separationStartDateTimePicker;
  private DateTimePicker separationEndDateTimePicker;
  private DateTimePicker separationEpochDateTimePicker;

  private CollocationCircleChart firstChart;
  private CollocationCircleChart secondChart;
  private CollocationLongitudeChart thirdChart;
  private CollocationInterLowerChart fourthChart;
  private CollocationECEFChart fifthChart;

  /**
   * Separation Analysis 화면 생성자.
   * @throws IOException
   */
  public SeparationAnalysisController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    separationStartDateTimePicker = new DateTimePicker(175, 28, 12);
    separationEndDateTimePicker = new DateTimePicker(175, 28, 12);
    separationEpochDateTimePicker = new DateTimePicker(175, 28, 12);
    separationStartDateTimePickerPane.getChildren().add(separationStartDateTimePicker);
    separationEndDateTimePickerPane.getChildren().add(separationEndDateTimePicker);
    separationEpochDateTimePickerPane.getChildren().add(separationEpochDateTimePicker);
    HBox.setMargin(separationStartDateTimePicker, new Insets(5,0,0,5));
    HBox.setMargin(separationEndDateTimePicker, new Insets(5,0,0,5));

  }

  /**
   * Separation Analysis 화면과 ViewModel을 바인딩.
   */
  public void bind(SeparationAnalysisViewModel viewModel){
    firstChart = new CollocationCircleChart();
    secondChart = new CollocationCircleChart();
    firstChart.setAxisProperty(0, 0.1,0.05);
    secondChart.setAxisProperty(0, 0.0004, 0.0001);
    firstChartPane.getChildren().add(firstChart);
    secondChartPane.getChildren().add(secondChart);

    thirdChart = new CollocationLongitudeChart();
    thirdChart.setxAxis(0, 400, 50);
    thirdChart.setyAxis(128.18, 128.25, 0.01);
    thirdChart.setAxisInfo("Longitude of SetA and SetB", "time (day)", "Longitude(deg)");
    thirdChartPane.getChildren().add(thirdChart);

    fourthChart = new CollocationInterLowerChart();
    fourthChart.setxAxis(5,40,5);
    fourthChart.setyAxis(0,400,50);
    fourthChart.setAxisInfo("Difference between SatA and SatB","time (day)","Distance(km)");
    fourthChartPane.getChildren().add(fourthChart);

    fifthChart = new CollocationECEFChart();
    fifthChart.setxAxis(-40,40,20);
    fifthChart.setyAxis(0,400,50);
    fifthChart.setAxisInfo("Difference between SatA and SatB", "time (day)", "Position Differece (km)");
    fifthChartPane.getChildren().add(fifthChart);

    sixthxAxis.setLowerBound(-0.0035);
    sixthxAxis.setUpperBound(0);
    sixthxAxis.setTickUnit(0.0005);
    sixthxAxis.setLabel("Radial");
    sixthxAxis.setTickLabelFormatter(new StringConverter<Number>() {
      @Override
      public String toString(Number object) {
        DecimalFormat format = new DecimalFormat("0.####");
        return format.format(object);
      }

      @Override
      public Number fromString(String string) {
        return null;
      }
    });

    sixthyAxis.setLowerBound(-1.5);
    sixthyAxis.setUpperBound(0);
    sixthyAxis.setTickUnit(0.3);
    sixthyAxis.setLabel("Along-Track");

    seventhxAxis.setLowerBound(-0.0035);
    seventhxAxis.setUpperBound(0);
    seventhxAxis.setTickUnit(0.0005);
    seventhxAxis.setLabel("Radial");
    seventhxAxis.setTickLabelFormatter(new StringConverter<Number>() {
      @Override
      public String toString(Number object) {
        DecimalFormat format = new DecimalFormat("0.####");
        return format.format(object);
      }

      @Override
      public Number fromString(String string) {
        return null;
      }
    });

    seventhyAxis.setLowerBound(-15);
    seventhyAxis.setUpperBound(0);
    seventhyAxis.setTickUnit(3);
    seventhyAxis.setLabel("Cross-track");

    eighthxAxis.setLowerBound(-1.4);
    eighthxAxis.setUpperBound(-0.4);
    eighthxAxis.setTickUnit(0.1);
    eighthxAxis.setLabel("Cross-track");

    eighthyAxis.setLowerBound(-15);
    eighthyAxis.setUpperBound(0);
    eighthyAxis.setTickUnit(3);
    eighthyAxis.setLabel("Along-track");
    objectOrbitListColumn1.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    objectOrbitListColumn2.setCellValueFactory(cellData -> cellData.getValue().dataTypeProperty());
    objectOrbitListColumn3.setCellValueFactory(cellData -> cellData.getValue().epochProperty());
    objectOrbitListColumn4.setCellValueFactory(cellData -> cellData.getValue().semi_majorAxisProperty());
    objectOrbitListColumn5.setCellValueFactory(cellData -> cellData.getValue().eccentricityProperty());
    objectOrbitListTableView.setItems(viewModel.objectOrbitElementTableRowViewModelList);

    /**
     * objectOrbit Table View selected 감지 이벤트
     */
    objectOrbitListTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      System.out.println(objectOrbitListTableView.getSelectionModel().getSelectedItem());
      System.out.println(objectOrbitListTableView.getSelectionModel().getSelectedIndex());
    });
  }
}
