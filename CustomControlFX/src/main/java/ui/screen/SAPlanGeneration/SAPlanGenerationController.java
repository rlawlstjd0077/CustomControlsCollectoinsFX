package ui.screen.SAPlanGeneration;


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
import ui.common.TextViewerEventHandler;
import ui.control.datetimepicker.DateTimePicker;

import java.io.IOException;

/**
 * Created by GSD on 2017-02-27.
 */
public class SAPlanGenerationController extends AnchorPane{
  @FXML
  private HBox SAPlanStartDateTimePickerPane;
  @FXML
  private HBox SAPlanEndDateTimePickerPane;
  @FXML
  private ComboBox<String> SAPlanDayComboBox;
  @FXML
  private Button SAPlanSearchButton;
  @FXML
  private TableView<DataSearchTableRowViewModel> SAPlanDataSearchTableView;
  @FXML
  private TableColumn<DataSearchTableRowViewModel, String> LARBurnPlanDataSearchFirstTableColumn;
  @FXML
  private TableColumn<DataSearchTableRowViewModel, String> LARBurnPlanDataSearchSecondTableColumn;
  @FXML
  private Button SAPlanExcuteButton;
  @FXML
  private GridPane outputListGridPane;
  @FXML
  private TabPane SAPlanMainTab;
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

  private Object oldValue;
  private DateTimePicker startDateTimePicker;
  private DateTimePicker endDateTimePicker;
  private TextViewerEventHandler textViewerEventHandler;
  
  public SAPlanGenerationController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  /**
   * SAPlanGeneration 화면과 ViewModel을 바인딩
   * @param viewModel
   */
  public void bind(SAPlanGenerationViewModel viewModel) {
    /**
     * DatePicker 들을 세팅
     */
    startDateTimePicker = new DateTimePicker(180, 28, 12);
    endDateTimePicker = new DateTimePicker(180, 28, 12);
    SAPlanStartDateTimePickerPane.getChildren().add(startDateTimePicker);
    SAPlanEndDateTimePickerPane.getChildren().add(endDateTimePicker);
    SAPlanStartDateTimePickerPane.setMargin(startDateTimePicker, new Insets(1,0,0,0));
    SAPlanEndDateTimePickerPane.setMargin(endDateTimePicker, new Insets(1,0,0,0));

    LARBurnPlanDataSearchFirstTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    LARBurnPlanDataSearchSecondTableColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
    SAPlanDataSearchTableView.setItems(viewModel.dataSearchTableRowViewModelList);
    SAPlanDataSearchTableView.setOnMouseClicked(event -> {
      if(SAPlanDataSearchTableView.getSelectionModel().getSelectedItem() != null) {
        if (event.getPickResult().getIntersectedNode().equals(oldValue)) {
          SAPlanDataSearchTableView.getSelectionModel().clearSelection();
          nameTextField.setText("");
          maneuverStartTimeTextField.setText("");
          delVRadialTextField.setText("");
          delVTransverseTextField.setText("");
          delVNormalTextField.setText("");
          oldValue = null;
        } else {
          oldValue = event.getPickResult().getIntersectedNode();
          nameTextField.setText(SAPlanDataSearchTableView.getSelectionModel().getSelectedItem().getName());
          maneuverStartTimeTextField.setText(SAPlanDataSearchTableView.getSelectionModel().getSelectedItem().getDate());
        }
      }
    });

    /**
     * TextViewer, ChartNewWindowViewer 세팅
     */
    textViewerEventHandler = new TextViewerEventHandler(this, SAPlanMainTab);

    textViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);
    textViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);
    textViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, textViewerEventHandler);
  }
}
