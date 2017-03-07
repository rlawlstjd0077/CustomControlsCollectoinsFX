package ui.screen.epocherrorsimulation;

import commons.ui.UiUtil;
import commons.ui.control.datetimepicker.DateTimePicker;
import fds.ui.common.*;
import fds.ui.orbitdeterminationBLSE.SelectableFilesTableRowViewModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by GSD on 2017-03-02.
 */
public class EpochErrorSimulationController extends AnchorPane {
  @FXML
  private HBox epochErrorStartDateTimePickerPane;
  @FXML
  private HBox epochErrorEndDateTimePickerPane;
  @FXML
  private HBox epochErrorEpochDateTimePickerPane;
  @FXML
  private Button epochErrorEpochSelectButton;
  @FXML
  private ComboBox<String> stackSourceTypeComboBox;
  @FXML
  private ComboBox<String> displayTypeComboBox;
  @FXML
  private ComboBox<String> coordinateFrameComboBox;
  @FXML
  private GridPane epochErrorEpochGridPane;
  @FXML
  private ComboBox<String> epochErrorFileTypeComboBox;
  @FXML
  private TableView<SelectableFilesTableRowViewModel> SelectableFilesPreviousTableView;
  @FXML
  private TableColumn<SelectableFilesTableRowViewModel, String> SelectableFilesPreviousColumn;
  @FXML
  private ImageView SelectableFilesRemoveButton;
  @FXML
  private ImageView SelectableFilesAddButton;
  @FXML
  private TableColumn<SelectableFilesTableRowViewModel, String> SelectableFilesSelectedColumn;
  @FXML
  private GridPane selectedDataInformationGridPane;
  @FXML
  private GridPane measurementDataBiasGridPane;
  @FXML
  private GridPane DeterminationOptionStationGridPane;
  @FXML
  private Spinner<Integer> epochErrorThresholdMultiplierSpinner;
  @FXML
  private Spinner<Integer> epochErrorConvergenceCriteriaSpinner;
  @FXML
  private Spinner<Integer> epochErrorMinIterationsSpinner;
  @FXML
  private Spinner<Integer> epochErrorMaxIterationsSpinner;
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
  private Button epochErrorSaveParameterButton;
  @FXML
  private Button epochErrorExcuteButton;
  @FXML
  private GridPane outputListGridPane;
  @FXML
  private TabPane epochErrorMainTab;
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
  private ComboBox<String> epochErrorGraphInformationGSComboBox;
  @FXML
  private ComboBox<String> epochErrorGraphInformationValueComboBox;
  @FXML
  private Label epochErrorGraphInformationStartLabel;
  @FXML
  private Label epochErrorGraphInformationEndLabel;
  @FXML
  private Label epochErrorGraphInformationMinLabel;
  @FXML
  private Label epochErrorGraphInformationMaxLabel;
  @FXML
  private Label epochErrorGraphStatisticsCountLabel;
  @FXML
  private Label epochErrorGraphStatisticsMeanLabel;
  @FXML
  private Label epochErrorGraphStatisticsVarianceLabel;
  @FXML
  private Label epochErrorGraphStatisticsStdDevLabel;
  @FXML
  private VBox chartEntireBox;
  @FXML
  private Button epochErrorOpenPopupButton;

  private DateTimePicker startDateTimePicker;
  private DateTimePicker endDateTimePicker;
  private DateTimePicker epochDateTimePicker;
  private ScatterChart<Date, Number> chart;
  private DateAxis xAxis;
  private NumberAxis yAxis;
  private TextViewerEventHandler textViewerEventHandler;
  private ChartViewerEventHandler eventPredictionChartViewerEventHandler;
  private ArrayList<SelectedItemData> selectedItemDatas;

  public EpochErrorSimulationController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  /**
   * EpochErrorSimulation 화면과 ViewModel을 바인딩
   */
  public void bind(EpochErrorSimulationViewModel viewModel) {
    epochErrorEpochSelectButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
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
    epochErrorOpenPopupButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
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
    chart.getStylesheets().add(getClass().getResource("epocherrorsimulationchart.css").toExternalForm());

    startDateTimePicker = new DateTimePicker(180, 28, 12);
    endDateTimePicker = new DateTimePicker(180, 28, 12);
    epochDateTimePicker = new DateTimePicker(180, 28, 12);
    epochErrorStartDateTimePickerPane.getChildren().add(startDateTimePicker);
    epochErrorEndDateTimePickerPane.getChildren().add(endDateTimePicker);
    epochErrorEpochDateTimePickerPane.getChildren().add(epochDateTimePicker);
    epochErrorStartDateTimePickerPane.setMargin(startDateTimePicker, new Insets(2, 0, 0, 0));
    epochErrorEndDateTimePickerPane.setMargin(endDateTimePicker, new Insets(2, 0, 0, 0));

    textViewerEventHandler = new TextViewerEventHandler(this, epochErrorMainTab);
    eventPredictionChartViewerEventHandler = new ChartViewerEventHandler(this, chartEntireBox, chart);

    textViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);
    textViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);
    textViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);
    chartViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventPredictionChartViewerEventHandler);
    chartViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventPredictionChartViewerEventHandler);
    chartViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, eventPredictionChartViewerEventHandler);

    selectedItemDatas = new ArrayList<>();
    SelectableFilesPreviousColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    SelectableFilesPreviousTableView.setItems(viewModel.selectableFilesTableRowModelList);
    SelectableFilesPreviousTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    SelectableFilesPreviousTableView.setRowFactory(tv -> {
      TableRow<SelectableFilesTableRowViewModel> row = new TableRow<>();
      row.setOnMousePressed(event -> {
        SelectableFilesTableRowViewModel item = row.getItem();
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


  class SelectedItemData {
    private int index;
    private SelectableFilesTableRowViewModel data;

    public SelectedItemData(int index, SelectableFilesTableRowViewModel data) {
      this.index = index;
      this.data = data;
    }

    public int getIndex() {
      return index;
    }

    public void setIndex(int index) {
      this.index = index;
    }

    public SelectableFilesTableRowViewModel getData() {
      return data;
    }

    public void setData(SelectableFilesTableRowViewModel data) {
      this.data = data;
    }
  }
}
