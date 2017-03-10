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
import ui.control.chart.CollocationCircleChart;
import ui.control.chart.CollocationECEFChart;
import ui.control.chart.CollocationInterLowerChart;
import ui.control.chart.CollocationLongitudeChart;
import ui.control.datetimepicker.DateTimePicker;
import ui.screen.common.ObjectOrbitElementTableRowViewModel;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

/**
 * Created by GSD on 2017-02-08.
 */
public class SeparationAnalysisController extends AnchorPane implements Initializable {

  @FXML
  private HBox separationStartDateTimePickerPane;
  @FXML
  private HBox separationEndDateTimePickerPane;
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
  private TableColumn<ObjectOrbitElementTableRowViewModel, Integer> objectOrbitListTopColumn1;
  @FXML
  private TableColumn<ObjectOrbitElementTableRowViewModel, String> objectOrbitListTopColumn2;
  @FXML
  private TableColumn<ObjectOrbitElementTableRowViewModel, String> objectOrbitListTopColumn3;
  @FXML
  private TableColumn<ObjectOrbitElementTableRowViewModel, String> objectOrbitListTopColumn4;
  @FXML
  private TableColumn<ObjectOrbitElementTableRowViewModel, String> objectOrbitListTopColumn5;
  @FXML
  private TableColumn<ObjectOrbitElementTableRowViewModel, String> objectOrbitListTopColumn6;
  @FXML
  private TableView<ObjectOrbitElementTableRowViewModel> objectOrbitListTopTableView;
  @FXML
  private TableView<ObjectOrbitElementTableRowViewModel> objectOrbitListBottomTableView;
  @FXML
  private TableColumn<ObjectOrbitElementTableRowViewModel, Integer> objectOrbitListBottomColumn1;
  @FXML
  private TableColumn<ObjectOrbitElementTableRowViewModel, String> objectOrbitListBottomColumn2;
  @FXML
  private TableColumn<ObjectOrbitElementTableRowViewModel, String> objectOrbitListBottomColumn3;
  @FXML
  private TableColumn<ObjectOrbitElementTableRowViewModel, String> objectOrbitListBottomColumn4;
  @FXML
  private TableColumn<ObjectOrbitElementTableRowViewModel, String> objectOrbitListBottomColumn5;
  @FXML
  private TableColumn<ObjectOrbitElementTableRowViewModel, String> objectOrbitListBottomColumn6;
  @FXML
  private Button excuteButton;
  @FXML
  private GridPane outputListGridPane;
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
   *
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
    HBox.setMargin(separationStartDateTimePicker, new Insets(5, 0, 0, 5));
    HBox.setMargin(separationEndDateTimePicker, new Insets(5, 0, 0, 5));
  }

  /**
   * Separation Analysis 화면과 ViewModel을 바인딩.
   */
  public void bind(SeparationAnalysisViewModel viewModel){
    /**
     * 8개의 차트 들을 세팅한다.
     * first, second chart :CollocationCircleChart
     * third chart : CollocationLongitudeChart
     * fourth chat : CollocationInterLowerChart
     * fifth chart : CollocationLowerChart
     * sixth, seventh, eighth chart : 기본 LineChart
     */
    firstChart = new CollocationCircleChart();
    secondChart = new CollocationCircleChart();
    firstChart.setAxisProperty(0, 0.1, 0.05);
    secondChart.setAxisProperty(0, 0.0004, 0.0001);
    firstChartPane.getChildren().add(firstChart);
    secondChartPane.getChildren().add(secondChart);

    thirdChart = new CollocationLongitudeChart();
    thirdChart.setxAxis(0, 400, 50);
    thirdChart.setyAxis(128.18, 128.25, 0.01);
    thirdChart.setAxisInfo("Longitude of SetA and SetB", "time (day)", "Longitude(deg)");
    thirdChartPane.getChildren().add(thirdChart);

    fourthChart = new CollocationInterLowerChart();
    fourthChart.setxAxis(5, 40, 5);
    fourthChart.setyAxis(0, 400, 50);
    fourthChart.setAxisInfo("Difference between SatA and SatB", "time (day)", "Distance(km)");
    fourthChartPane.getChildren().add(fourthChart);

    fifthChart = new CollocationECEFChart();
    fifthChart.setxAxis(-40, 40, 20);
    fifthChart.setyAxis(0, 400, 50);
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

    objectOrbitListTopColumn1.setCellValueFactory(cellData -> cellData.getValue().indexProperty().asObject());
    objectOrbitListTopColumn1.setCellFactory(fc -> {
      TableCell<ObjectOrbitElementTableRowViewModel, Integer> cell = new TableCell<ObjectOrbitElementTableRowViewModel, Integer>() {
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
    objectOrbitListTopColumn2.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    objectOrbitListTopColumn3.setCellValueFactory(cellData -> cellData.getValue().dataTypeProperty());
    objectOrbitListTopColumn4.setCellValueFactory(cellData -> cellData.getValue().epochProperty());
    objectOrbitListTopColumn5.setCellValueFactory(cellData -> cellData.getValue().semi_majorAxisProperty());
    objectOrbitListTopColumn6.setCellValueFactory(cellData -> cellData.getValue().eccentricityProperty());
    objectOrbitListTopTableView.setItems(viewModel.objectOrbitElementTopTableRowViewModelList);
    objectOrbitListTopTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    /**
     * objectOrbit Table View selected 감지 이벤트
     */
    objectOrbitListTopTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      System.out.println(objectOrbitListTopTableView.getSelectionModel().getSelectedItem());
      System.out.println(objectOrbitListTopTableView.getSelectionModel().getSelectedIndex());
    });


    objectOrbitListBottomColumn1.setCellValueFactory(cellData -> cellData.getValue().indexProperty().asObject());
    objectOrbitListBottomColumn1.setCellFactory(fc -> {
      TableCell<ObjectOrbitElementTableRowViewModel, Integer> cell = new TableCell<ObjectOrbitElementTableRowViewModel, Integer>() {
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
    objectOrbitListBottomColumn2.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    objectOrbitListBottomColumn3.setCellValueFactory(cellData -> cellData.getValue().dataTypeProperty());
    objectOrbitListBottomColumn4.setCellValueFactory(cellData -> cellData.getValue().epochProperty());
    objectOrbitListBottomColumn5.setCellValueFactory(cellData -> cellData.getValue().semi_majorAxisProperty());
    objectOrbitListBottomColumn6.setCellValueFactory(cellData -> cellData.getValue().eccentricityProperty());
    objectOrbitListBottomTableView.setItems(viewModel.objectOrbitElementBottomTableRowViewModelList);
    objectOrbitListBottomTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    objectOrbitListBottomTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

      }
    );

    objectOrbitListBottomTableView.widthProperty().addListener((source, oldWidth, newWidth) -> {
      //Don't show header
      Pane header = (Pane) objectOrbitListBottomTableView.lookup("TableHeaderRow");
      if (header.isVisible()) {
        header.setPrefHeight(0);
        header.setVisible(false);
      }
    });
  }
}
