package ui.screen.LAEburnplangeneration;

import ui.UiUtil;
import ui.control.datetimepicker.DateTimePicker;
import ui.screen.common.DataSearchTableRowViewModel;
import ui.screen.common.TabTextViewerEventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by GSD on 2017-02-23.
 */
public class LAEBurnPlanGenerationController extends AnchorPane implements Initializable{
  @FXML
  private HBox LAEBurnPlanStartDateTimePickerPane;
  @FXML
  private HBox LAEBurnPlanEndDateTimePickerPane;
  @FXML
  private ComboBox<String> LAEBurnPlanDayComboBox;
  @FXML
  private Button LAEBurnPlanSearchButton;
  @FXML
  private TableView<DataSearchTableRowViewModel> LAEBurnPlanDataSearchTableView;
  @FXML
  private TableColumn<DataSearchTableRowViewModel, String> LARBurnPlanDataSearchFirstTableColumn;
  @FXML
  private TableColumn<DataSearchTableRowViewModel, String> LARBurnPlanDataSearchSecondTableColumn;
  @FXML
  private Button LAEBurnExcuteButton;
  @FXML
  private GridPane outputListGridPane;
  @FXML
  private TabPane LAEBurnPlanMainTab;
  @FXML
  private Button textViewerSaveButton;
  @FXML
  private Button textViewerPrintButton;
  @FXML
  private Button textViewerNewWindowButton;
  @FXML
  private TextField nameTextField;
  @FXML
  private TextField burnStartTimeTextField;
  @FXML
  private TextField rightAscTextField;
  @FXML
  private TextField declinationTextField;
  @FXML
  private TextField burnDurationTextField;

  private Object oldValue;
  private DateTimePicker startDateTimePicker;
  private DateTimePicker endDateTimePicker;
  private TabTextViewerEventHandler tabTextViewerEventHandler;

  public LAEBurnPlanGenerationController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  /**
   * LAEBurnPlanGeneration화면과 ViewModel을 바인딩
   * @param viewModel
   */
  public void bind(LAEBurnPlanGenerationViewModel viewModel) {
    /**
     * DatePicker 들을 세팅
     */
    startDateTimePicker = new DateTimePicker(180, 28, 12);
    endDateTimePicker = new DateTimePicker(180, 28, 12);
    LAEBurnPlanStartDateTimePickerPane.getChildren().add(startDateTimePicker);
    LAEBurnPlanEndDateTimePickerPane.getChildren().add(endDateTimePicker);
    LAEBurnPlanStartDateTimePickerPane.setMargin(startDateTimePicker, new Insets(1,0,0,0));
    LAEBurnPlanEndDateTimePickerPane.setMargin(endDateTimePicker, new Insets(1,0,0,0));

    LARBurnPlanDataSearchFirstTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    LARBurnPlanDataSearchSecondTableColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
    LAEBurnPlanDataSearchTableView.setItems(viewModel.dataSearchTableRowViewModelList);
    LAEBurnPlanDataSearchTableView.setOnMouseClicked(event -> {
      if(LAEBurnPlanDataSearchTableView != null) {
        if (event.getPickResult().getIntersectedNode().equals(oldValue)) {
          LAEBurnPlanDataSearchTableView.getSelectionModel().clearSelection();
          nameTextField.setText("");
          burnStartTimeTextField.setText("");
          rightAscTextField.setText("");
          declinationTextField.setText("");
          burnDurationTextField.setText("");
          oldValue = null;
        } else {
          oldValue = event.getPickResult().getIntersectedNode();
          nameTextField.setText(LAEBurnPlanDataSearchTableView.getSelectionModel().getSelectedItem().getName());
          burnStartTimeTextField.setText(LAEBurnPlanDataSearchTableView.getSelectionModel().getSelectedItem().getDate());
        }
      }
    });

    /**
     * TextViewer, ChartNewWindowViewer 세팅
     */
    tabTextViewerEventHandler = new TabTextViewerEventHandler(this, LAEBurnPlanMainTab);

    textViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }
}
