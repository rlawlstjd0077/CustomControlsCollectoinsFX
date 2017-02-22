package ui.screen.orbitdeterminationBLSE;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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
 * Created by GSD on 2017-02-17.
 */
public class OrbitDeterminationBLSEController extends AnchorPane implements Initializable{
  @FXML
  private HBox orbitBLSEStartDateTimePickerPane;
  @FXML
  private HBox orbitBLSEEndDateTimePickerPane;
  @FXML
  private HBox orbitBLSEEpochDateTimePickerPane;
  @FXML
  private Button orbitBLSEEpochSelectButton;
  @FXML
  private ComboBox<String> stackSourceTypeComboBox;
  @FXML
  private ComboBox<String> displayTypeComboBox;
  @FXML
  private ComboBox<String> coordinateFrameComboBox;
  @FXML
  private GridPane orbitBLSEEpochGridPane;
  @FXML
  private ComboBox<String> orbitBLSEFileTypeComboBox;
  @FXML
  private TableView<SelectableFilesTableRowViewModel> SelectableFilesPreviousTableView;
  @FXML
  private TableColumn<SelectableFilesTableRowViewModel, String> SelectableFilesPreviousColumn;
  @FXML
  private ImageView SelectableFilesRemoveButton;
  @FXML
  private ImageView SelectableFilesAddButton;
  @FXML
  private TableView<SelectableFilesTableRowViewModel> SelectableFilesSelectedTableView;
  @FXML
  private TableColumn<SelectableFilesTableRowViewModel, String> SelectableFilesSelectedColumn;
  @FXML
  private GridPane selectedDataInformationGridPane;
  @FXML
  private GridPane measurementDataBiasGridPane;
  @FXML
  private GridPane DeterminationOptionStationGridPane;
  @FXML
  private Spinner<Integer> BLSEThresholdMultiplierSpinner;
  @FXML
  private Spinner<Integer> BLSEConvergenceCriteriaSpinner;
  @FXML
  private Spinner<Integer> BLSEMinIterationsSpinner;
  @FXML
  private Spinner<Integer> BLSEMaxIterationsSpinner;
  @FXML
  private CheckBox determinationEstimateSoloarPressureCheckBox;
  @FXML
  private CheckBox determinationEstimateWheelOffCheckBox;
  @FXML
  private CheckBox determinationEstimateStationKeepingCheckBox;
  @FXML
  private CheckBox determinationApplyWheelOffCheckBox;
  @FXML
  private CheckBox determinationApplyStationKeepingCheckBox;
  @FXML
  private CheckBox UpdateOptionOrbitStackCheckBox;
  @FXML
  private CheckBox UpdateOptionSolarPressureCheckBox;
  @FXML
  private Button orbitBLSESaveParameterButton;
  @FXML
  private Button orbitBLSEExcuteButton;
  @FXML
  private GridPane outputListGridPane;
  @FXML
  private TabPane orbitBLSEMainTab;
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
  private VBox chartViewerChartBox;
  @FXML
  private Label ResidualValue4441Label;
  @FXML
  private Label ResidualValue1202Label;
  @FXML
  private ComboBox<String> orbitBLSEGraphInformationGSComboBox;
  @FXML
  private ComboBox<String> orbitBLSEGraphInformationValueComboBox;
  @FXML
  private Label orbitBLSEGraphInformationStartLabel;
  @FXML
  private Label orbitBLSEGraphInformationEndLabel;
  @FXML
  private Label orbitBLSEGraphInformationMinLabel;
  @FXML
  private Label orbitBLSEGraphInformationMaxLabel;
  @FXML
  private Label orbitBLSEGraphStatisticsCountLabel;
  @FXML
  private Label orbitBLSEGraphStatisticsMeanLabel;
  @FXML
  private Label orbitBLSEGraphStatisticsVarianceLabel;
  @FXML
  private Label orbitBLSEGraphStatisticsStdDevLabel;
  @FXML
  private VBox chartEntireBox;

  private DateTimePicker startDateTimePicker;
  private DateTimePicker endDateTimePicker;
  private DateTimePicker epochDateTimePicker;
  private ScatterChart<Date, Number> chart;
  private DateAxis xAxis;
  private NumberAxis yAxis;
  private TextViewerEventHandler textViewerEventHandler;
  private ChartViewerEventHandler eventPredictionChartViewerEventHandler;

  public OrbitDeterminationBLSEController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  /**
   * orbitBLSE Orbit Monitoring 화면과 ViewModel을 바인딩.
   * @param viewModel
   */
  public void bind(OrbitDeterminationBLSEViewModel viewModel) {
    SelectableFilesPreviousTableView.widthProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) {
        Pane header = (Pane) SelectableFilesPreviousTableView.lookup("TableHeaderRow");
        if (header.isVisible()) {
          header.setPrefHeight(0);
          header.setVisible(false);
        }
      }
    });
    SelectableFilesSelectedTableView.widthProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) {
        Pane header = (Pane) SelectableFilesSelectedTableView.lookup("TableHeaderRow");
        if (header.isVisible()) {
          header.setPrefHeight(0);
          header.setVisible(false);
        }
      }
    });

    /**
     * Chart를 세팅
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
    chart.getStylesheets().add(getClass().getResource("orbitdeterminationBLSEchart.css").toExternalForm());


    /**
     * DatePicker 들을 세팅
     */
    startDateTimePicker = new DateTimePicker(180, 28, 12);
    endDateTimePicker = new DateTimePicker(180, 28, 12);
    epochDateTimePicker = new DateTimePicker(180, 28, 12);
    orbitBLSEStartDateTimePickerPane.getChildren().add(startDateTimePicker);
    orbitBLSEEndDateTimePickerPane.getChildren().add(endDateTimePicker);
    orbitBLSEEpochDateTimePickerPane.getChildren().add(epochDateTimePicker);
    orbitBLSEStartDateTimePickerPane.setMargin(startDateTimePicker, new Insets(2,0,0,0));
    orbitBLSEEndDateTimePickerPane.setMargin(endDateTimePicker, new Insets(2,0,0,0));

    /**
     * TextViewer, ChartNewWindowViewer 들을 세팅
     */
    textViewerEventHandler = new TextViewerEventHandler(this, orbitBLSEMainTab);
    eventPredictionChartViewerEventHandler = new ChartViewerEventHandler(this, chartEntireBox, chart);

    textViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);
    textViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);
    textViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);
    chartViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventPredictionChartViewerEventHandler);
    chartViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventPredictionChartViewerEventHandler);
    chartViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventPredictionChartViewerEventHandler);

    /**
     * SelectableFilePreviousColumn, SelectableFileSelectedColumn, 들을 세팅
     */
    SelectableFilesSelectedColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    SelectableFilesPreviousColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    SelectableFilesPreviousTableView.setItems(viewModel.selectableFilesTableRowViewModelList);
    SelectableFilesAddButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      if(SelectableFilesPreviousTableView.getSelectionModel().getSelectedItem() != null){
        SelectableFilesSelectedTableView.getItems().add(viewModel.selectableFilesTableRowViewModelList.get(SelectableFilesPreviousTableView.getSelectionModel().getSelectedIndex()));
        //getSelectedIndex()으로 얻은 index를 통해 ViewModel의 데이터 리스트를 가져와 SelectableFilesSelectedTableView에 추가
      }
    });
    SelectableFilesRemoveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      if(SelectableFilesSelectedTableView.getSelectionModel().getSelectedItem() != null){
        SelectableFilesSelectedTableView.getItems().remove(SelectableFilesSelectedTableView.getSelectionModel().getSelectedIndex());
      }
    });
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }
}
