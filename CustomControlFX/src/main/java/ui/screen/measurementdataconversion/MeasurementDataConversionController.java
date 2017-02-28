package ui.screen.measurementdataconversion;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import ui.UiUtil;
import ui.common.DataSearchTableRowViewModel;
import ui.common.SelectedItemData;
import ui.common.TextViewerEventHandler;
import ui.control.datetimepicker.DateTimePicker;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by GSD on 2017-02-27.
 */
public class MeasurementDataConversionController extends AnchorPane {
  @FXML
  private HBox mesurementDataStartDateTimePickerPane;
  @FXML
  private HBox mesurementDataEndDateTimePickerPane;
  @FXML
  private ComboBox<String> mesurementDataDayComboBox;
  @FXML
  private Button mesurementDataSearchButton;
  @FXML
  private TableView<DataSearchTableRowViewModel> mesurementDataDataSearchTableView;
  @FXML
  private TableColumn<DataSearchTableRowViewModel, String> LARBurnPlanDataSearchFirstTableColumn;
  @FXML
  private TableColumn<DataSearchTableRowViewModel, String> LARBurnPlanDataSearchSecondTableColumn;
  @FXML
  private Button mesurementDataExcuteButton;
  @FXML
  private GridPane outputListGridPane;
  @FXML
  private TabPane mesurementDataMainTab;
  @FXML
  private Button textViewerSaveButton;
  @FXML
  private Button textViewerPrintButton;
  @FXML
  private Button textViewerNewWindowButton;
  @FXML
  private TextField nameTextField;
  @FXML
  private TextField maneuverStartTimeTextField;
  @FXML
  private TextField delVRadialTextField;
  @FXML
  private TextField delVTransverseTextField;
  @FXML
  private TextField delVNormalTextField;
  @FXML
  private GridPane measurementSearchDataGridPane;

  private Object oldValue;
  private DateTimePicker startDateTimePicker;
  private DateTimePicker endDateTimePicker;
  private TextViewerEventHandler textViewerEventHandler;
  private ArrayList<SelectedItemData> selectedItems;


  public MeasurementDataConversionController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  /**
   * MeasurementDataConversion 화면과 ViewModel을 바인딩
   *
   * @param viewModel
   */
  public void bind(MeasurementDataConversionViewModel viewModel) {
    /**
     * DatePicker 들을 세팅
     */
    startDateTimePicker = new DateTimePicker(180, 28, 12);
    endDateTimePicker = new DateTimePicker(180, 28, 12);
    mesurementDataStartDateTimePickerPane.getChildren().add(startDateTimePicker);
    mesurementDataEndDateTimePickerPane.getChildren().add(endDateTimePicker);
    mesurementDataStartDateTimePickerPane.setMargin(startDateTimePicker, new Insets(1, 0, 0, 0));
    mesurementDataEndDateTimePickerPane.setMargin(endDateTimePicker, new Insets(1, 0, 0, 0));

    /**
     * TableView 세팅
     */
    selectedItems = new ArrayList<>();
    LARBurnPlanDataSearchFirstTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    LARBurnPlanDataSearchSecondTableColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
    mesurementDataDataSearchTableView.setItems(viewModel.dataSearchTableRowViewModelList);
    mesurementDataDataSearchTableView.setRowFactory(tv -> {
      TableRow<DataSearchTableRowViewModel> row = new TableRow<>();
      row.setOnMousePressed(event -> {
        DataSearchTableRowViewModel item = row.getItem();
        int index = row.getIndex();
        if (mesurementDataDataSearchTableView.getSelectionModel().getSelectedItem() != null) {
          for (SelectedItemData data : selectedItems) {
            if (data.getIndex() == index) {
              selectedItems.remove(data);
              setSelecions();
              insertSeletedDataGrid();
              return;
            }
          }
          selectedItems.add(new SelectedItemData(index, item));
          setSelecions();
          insertSeletedDataGrid();
        }
      });
      row.setOnMouseClicked(event1 -> {
        setSelecions();
      });
      return row;
    });

    /**
     * TextViewer 세팅
     */
    textViewerEventHandler = new TextViewerEventHandler(this, mesurementDataMainTab);

    textViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);
    textViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);
    textViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);
  }

  public void insertSeletedDataGrid() {
    measurementSearchDataGridPane.getChildren().clear();
    int rowCount = 0, columnCount;
    for (SelectedItemData data : selectedItems) {
      columnCount = 0;
      measurementSearchDataGridPane.add(new Label(data.getData().getName()), columnCount++, rowCount);
      measurementSearchDataGridPane.add(new Label(data.getData().getDate()), columnCount++, rowCount);
      rowCount++;
    }
  }

  public void setSelecions() {
    mesurementDataDataSearchTableView.getSelectionModel().clearSelection();
    for (SelectedItemData data : selectedItems) {
      mesurementDataDataSearchTableView.getSelectionModel().select(data.getIndex());
    }
  }
}
