package ui.screen.measurementdatamerge;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import ui.UiUtil;
import ui.common.DataSearchTableRowViewModel;
import ui.common.SelectedItemData;
import ui.common.TabTextViewerEventHandler;
import ui.control.datetimepicker.DateTimePicker;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by GSD on 2017-03-03.
 */
public class MeasurementDataMergeController extends AnchorPane {
  @FXML
  private HBox measurementMergeDataStartDateTimePickerPane;
  @FXML
  private HBox measurementMergeDataEndDateTimePickerPane;
  @FXML
  private ComboBox<String> measurementMergeDataDayComboBox;
  @FXML
  private Button measurementMergeDataSearchButton;
  @FXML
  private TableView<DataSearchTableRowViewModel> measurementMergeDataDataSearchTableView;
  @FXML
  private TableColumn<DataSearchTableRowViewModel, String> measurementMergeDataSearchFirstTableColumn;
  @FXML
  private TableColumn<DataSearchTableRowViewModel, String> measurementMergeDataSearchSecondTableColumn;
  @FXML
  private TableView<DataSearchTableRowViewModel> measurementMergeSelectedDataDataSearchTableView;
  @FXML
  private TableColumn<DataSearchTableRowViewModel, String> measurementMergeSelectedDataSearchFirstTableColumn;
  @FXML
  private TableColumn<DataSearchTableRowViewModel, String> measurementMergeSelectedDataSearchSecondTableColumn;
  @FXML
  private Button measurementMergeDataExcuteButton;
  @FXML
  private TabPane measurementMergeDataMainTab;
  @FXML
  private Button textViewerSaveButton;
  @FXML
  private Button textViewerPrintButton;
  @FXML
  private Button textViewerNewWindowButton;

  private DateTimePicker startDateTimePicker;
  private DateTimePicker endDateTimePicker;
  private TabTextViewerEventHandler tabTextViewerEventHandler;
  private ArrayList<SelectedItemData> selectedItems;


  public MeasurementDataMergeController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  /**
   * MeasurementDataMerge화면과 ViewModel을 바인딩
   * @param viewModel
   */
  public void bind(MeasurementDataMergeViewModel viewModel) {
    selectedItems = new ArrayList<>();

    startDateTimePicker = new DateTimePicker(180, 28, 12);
    endDateTimePicker = new DateTimePicker(180, 28, 12);
    measurementMergeDataStartDateTimePickerPane.getChildren().add(startDateTimePicker);
    measurementMergeDataEndDateTimePickerPane.getChildren().add(endDateTimePicker);
    measurementMergeDataStartDateTimePickerPane.setMargin(startDateTimePicker, new Insets(1, 0, 0, 0));
    measurementMergeDataEndDateTimePickerPane.setMargin(endDateTimePicker, new Insets(1, 0, 0, 0));

    tabTextViewerEventHandler = new TabTextViewerEventHandler(this, measurementMergeDataMainTab);
    textViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);

    measurementMergeDataDataSearchTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    measurementMergeDataSearchFirstTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    measurementMergeDataSearchSecondTableColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
    measurementMergeSelectedDataSearchFirstTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    measurementMergeSelectedDataSearchSecondTableColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
    measurementMergeDataDataSearchTableView.setItems(viewModel.dataSearchTableRowViewModelList);
    measurementMergeDataDataSearchTableView.setRowFactory(tv -> {
      TableRow<DataSearchTableRowViewModel> row = new TableRow<>();
      row.setOnMousePressed(event -> {
        DataSearchTableRowViewModel item = row.getItem();
        int index = row.getIndex();
        if (measurementMergeDataDataSearchTableView.getSelectionModel().getSelectedItem() != null) {
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
  }
  public void insertSeletedDataGrid() {
    measurementMergeSelectedDataDataSearchTableView.getItems().clear();
    for (SelectedItemData data : selectedItems) {
      measurementMergeSelectedDataDataSearchTableView.getItems().add(data.getData());
    }
  }

  public void setSelecions() {
    measurementMergeDataDataSearchTableView.getSelectionModel().clearSelection();
    for (SelectedItemData data : selectedItems) {
      measurementMergeDataDataSearchTableView.getSelectionModel().select(data.getIndex());
    }
  }
}
