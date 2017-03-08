package ui.screen.orbitdeterminationBLSE;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import ui.UiUtil;
import ui.common.*;
import ui.control.datetimepicker.DateTimePicker;
import ui.control.epochselector.EpochSelectorPopup;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

/**
 * Created by GSD on 2017-02-17.
 */
public class OrbitDeterminationBLSEController extends AnchorPane implements Initializable {
  @FXML
  private HBox satelliteStartDateTimePickerPane;
  @FXML
  private HBox satelliteEndDateTimePickerPane;
  @FXML
  private HBox satelliteEpochDateTimePickerPane;
  @FXML
  private Button satelliteEpochSelectButton;
  @FXML
  private ComboBox<String> stackSourceTypeComboBox;
  @FXML
  private ComboBox<String> displayTypeComboBox;
  @FXML
  private ComboBox<String> coordinateFrameComboBox;
  @FXML
  private GridPane satelliteEpochGridPane;
  @FXML
  private ComboBox<String> satelliteFileTypeComboBox;
  @FXML
  private TableView<SelectableFilesTableRowModel> SelectableFilesPreviousTableView;
  @FXML
  private TableColumn<SelectableFilesTableRowModel, String> SelectableFilesPreviousColumn;
  @FXML
  private ImageView SelectableFilesRemoveButton;
  @FXML
  private ImageView SelectableFilesAddButton;
  @FXML
  private TableColumn<SelectableFilesTableRowModel, String> SelectableFilesSelectedColumn;
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
  private Button satelliteSaveParameterButton;
  @FXML
  private Button satelliteExcuteButton;
  @FXML
  private GridPane outputListGridPane;
  @FXML
  private TabPane satelliteMainTab;
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
  private ComboBox<String> SatelliteGraphInformationGSComboBox;
  @FXML
  private ComboBox<String> SatelliteGraphInformationValueComboBox;
  @FXML
  private Label SatelliteGraphInformationStartLabel;
  @FXML
  private Label SatelliteGraphInformationEndLabel;
  @FXML
  private Label SatelliteGraphInformationMinLabel;
  @FXML
  private Label SatelliteGraphInformationMaxLabel;
  @FXML
  private Label SatelliteGraphStatisticsCountLabel;
  @FXML
  private Label SatelliteGraphStatisticsMeanLabel;
  @FXML
  private Label SatelliteGraphStatisticsVarianceLabel;
  @FXML
  private Label SatelliteGraphStatisticsStdDevLabel;
  @FXML
  private VBox chartEntireBox;
  @FXML
  private Button orbitBLSEOpenPopupButton;

  private DateTimePicker startDateTimePicker;
  private DateTimePicker endDateTimePicker;
  private DateTimePicker epochDateTimePicker;
  private ScatterChart<Date, Number> chart;
  private DateAxis xAxis;
  private NumberAxis yAxis;
  private TabTextViewerEventHandler tabTextViewerEventHandler;
  private ChartViewerEventHandler eventPredictionChartViewerEventHandler;
  private ArrayList<SelectedItemData> selectedItemDatas;

  public OrbitDeterminationBLSEController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  /**
   * Satellite Orbit Monitoring 화면과 ViewModel을 바인딩.
   *
   * @param viewModel
   */
  public void bind(OrbitDeterminationBLSEViewModel viewModel) {
    satelliteEpochSelectButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      EpochSelectorPopup popup = new EpochSelectorPopup();
      Scene scene = new Scene(popup);
      Stage newStage = new Stage();
      newStage.setScene(scene);
      newStage.initModality(Modality.APPLICATION_MODAL);
      newStage.setTitle("Epoch Selector");
      newStage.showAndWait();
    });

    for (int i = 0; i < viewModel.determinationStationDataTableRowViewModelList.size(); i++) {
      int count = 0;
      DeterminationStationDataTableRowViewModel model = viewModel.determinationStationDataTableRowViewModelList.get(i);
      DeterminationOptionStationGridPane.add(new Label(model.itemProperty().getValue()), count++, i);
      DeterminationOptionStationGridPane.add(model.getAppR(), count++, i);
      DeterminationOptionStationGridPane.add(model.getAppA(), count++, i);
      DeterminationOptionStationGridPane.add(model.getAppE(), count++, i);
      DeterminationOptionStationGridPane.add(model.getEstR(), count++, i);
      DeterminationOptionStationGridPane.add(model.getEstA(), count++, i);
      DeterminationOptionStationGridPane.add(model.getEstE(), count++, i);
    }
    orbitBLSEOpenPopupButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      MeasurementDataBiasPopup popup = new MeasurementDataBiasPopup();
      Scene scene = new Scene(popup);
      Stage popupStage = new Stage();
      popupStage.setScene(scene);
      popupStage.initModality(Modality.APPLICATION_MODAL);
      popupStage.setTitle("MeasurementDataBiasPopup");
      popupStage.showAndWait();
    });

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

    xAxis = new DateAxis();
    yAxis = new NumberAxis();
    xAxis.setAutoRanging(false);
    yAxis.setAutoRanging(false);
    yAxis.setLabel("Time");
    xAxis.setLabel("Residual Value");
    xAxis.setLowerBound(new GregorianCalendar(2016, 01, 01).getTime());
    xAxis.setUpperBound(new GregorianCalendar(2017, 01, 01).getTime());
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

    startDateTimePicker = new DateTimePicker(180, 28, 12);
    endDateTimePicker = new DateTimePicker(180, 28, 12);
    epochDateTimePicker = new DateTimePicker(180, 28, 12);
    satelliteStartDateTimePickerPane.getChildren().add(startDateTimePicker);
    satelliteEndDateTimePickerPane.getChildren().add(endDateTimePicker);
    satelliteEpochDateTimePickerPane.getChildren().add(epochDateTimePicker);
    satelliteStartDateTimePickerPane.setMargin(startDateTimePicker, new Insets(2, 0, 0, 0));
    satelliteEndDateTimePickerPane.setMargin(endDateTimePicker, new Insets(2, 0, 0, 0));

    tabTextViewerEventHandler = new TabTextViewerEventHandler(this, satelliteMainTab);
    eventPredictionChartViewerEventHandler = new ChartViewerEventHandler(this, chartEntireBox, chart);

    textViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    chartViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventPredictionChartViewerEventHandler);
    chartViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventPredictionChartViewerEventHandler);
    chartViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventPredictionChartViewerEventHandler);

    selectedItemDatas = new ArrayList<>();
    SelectableFilesPreviousColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    SelectableFilesPreviousTableView.setItems(viewModel.selectableFilesTableRowModelList);
    SelectableFilesPreviousTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    SelectableFilesPreviousTableView.setRowFactory(tv -> {
      TableRow<SelectableFilesTableRowModel> row = new TableRow<>();
      row.setOnMousePressed(event -> {
        SelectableFilesTableRowModel item = row.getItem();
        int index = row.getIndex();
        if (SelectableFilesPreviousTableView.getSelectionModel().getSelectedItem() != null) {
          for (SelectedItemData data : selectedItemDatas) {
            if (data.getIndex() == index) {
              selectedItemDatas.remove(data);
              setSelecions();
              insertSeletedDataGrid();
              return;
            }
          }
          selectedItemDatas.add(new SelectedItemData(index, item));
          setSelecions();
          insertSeletedDataGrid();
        }
      });
      row.setOnMouseClicked(event1 -> {
        setSelecions();
      });
      return row;
    });
  }

  public void insertSeletedDataGrid() {
    //GridPane에 데이터 바인딩 메소드
  }

  public void setSelecions() {
    SelectableFilesPreviousTableView.getSelectionModel().clearSelection();
    for (SelectedItemData data : selectedItemDatas) {
      SelectableFilesPreviousTableView.getSelectionModel().select(data.getIndex());
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  class SelectedItemData {
    private int index;
    private SelectableFilesTableRowModel data;

    public SelectedItemData(int index, SelectableFilesTableRowModel data) {
      this.index = index;
      this.data = data;
    }

    public int getIndex() {
      return index;
    }

    public void setIndex(int index) {
      this.index = index;
    }

    public SelectableFilesTableRowModel getData() {
      return data;
    }

    public void setData(SelectableFilesTableRowModel data) {
      this.data = data;
    }
  }
}
