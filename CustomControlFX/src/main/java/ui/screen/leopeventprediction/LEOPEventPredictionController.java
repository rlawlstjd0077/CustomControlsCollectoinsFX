package ui.screen.leopeventprediction;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import ui.UiUtil;
import ui.common.GroundStationSelectionTableRowViewModel;
import ui.common.TabTextViewerEventHandler;
import ui.control.datetimepicker.DateTimePicker;

import java.io.IOException;

/**
 * Created by GSD on 2017-03-02.
 */
public class LEOPEventPredictionController extends AnchorPane {
  @FXML
  private Pane leopEventStartDateTimePickerPane;
  @FXML
  private Pane leopEventEndDateTimePickerPane;
  @FXML
  private Pane leopEventEpochDateTimePickerPane;
  @FXML
  private Button leopEventEpochSelectButton;
  @FXML
  private ComboBox<String> leopEventStackSourceTypeComboBox;
  @FXML
  private ComboBox<String> leopEventDisplayTypeComboBox;
  @FXML
  private ComboBox<String> leopEventCoordinateFrameComboBox;
  @FXML
  private GridPane leopEventEpochGridPane;
  @FXML
  private ComboBox<String> leopEventPredictionModeComboBox;
  @FXML
  private TextField leopEventOrbitPredictionDataTextField;
  @FXML
  private Button leopEventOrbitPredictionDataFileChooserButton;
  @FXML
  private TextField leopEventOrbitPredictionDataStartTextField;
  @FXML
  private TextField leopEventOrbitPredictionDataEndTextField;
  @FXML
  private Spinner<Integer> leopEventReferenceLongitudeSpinner;
  @FXML
  private Spinner<Integer> leopEventOrbitIntervalSpinner;
  @FXML
  private CheckBox leopEventApplyWheelCheckBox;
  @FXML
  private CheckBox leopEventApplyStationCheckBox;
  @FXML
  private CheckBox leopEventEarthGravityCheckBox;
  @FXML
  private CheckBox leopEventSunMoonGravityCheckBox;
  @FXML
  private Spinner<Integer> leopEventDegreeSpinner;
  @FXML
  private CheckBox leopEventSolarRadiationCheckBox;
  @FXML
  private Spinner<Integer> leopEventOrderSpinner;
  @FXML
  private CheckBox leopEventAtmosphericCheckBox;
  @FXML
  private Button leopEventSaveParameterButton;
  @FXML
  private Button leopEventExcuteButton;
  @FXML
  private GridPane outputListGridPane;
  @FXML
  private Button textViewerSaveButton;
  @FXML
  private Button textViewerPrintButton;
  @FXML
  private Button textViewerNewWindowButton;
  @FXML
  private TabPane leopEventMainTab;
  @FXML
  private BorderPane leopEventPredictionChartPaneContainer;
  @FXML
  private VBox PreciseOrbitPredictionBox;
  @FXML
  private VBox MeanOrbitPredictionBox;
  @FXML
  private VBox usingOrbitPredictionDataBox;
  //Predictoin Model 선택에 따른 변화로 visible을 변경하여 사용
  @FXML
  private TableView<GroundStationSelectionTableRowViewModel> groundStationTableView;
  @FXML
  private TableColumn<GroundStationSelectionTableRowViewModel, Integer> groundStationFirstColumn;
  @FXML
  private TableColumn<GroundStationSelectionTableRowViewModel, Integer> groundStationSecondColumn;
  @FXML
  private TableColumn<GroundStationSelectionTableRowViewModel, String> groundStationThirdColumn;
  @FXML
  private TableColumn<GroundStationSelectionTableRowViewModel, Double> groundStationFourthColumn;
  @FXML
  private TableColumn<GroundStationSelectionTableRowViewModel, Double> groundStationFifthColumn;
  @FXML
  private TableColumn<GroundStationSelectionTableRowViewModel, Double> groundStationSixthColumn;
  @FXML
  private TableColumn<GroundStationSelectionTableRowViewModel, Double> groundStationSeventhColumn;
  @FXML
  private TableColumn<GroundStationSelectionTableRowViewModel, Double> groundStationEighthColumn;

  private DateTimePicker startDateTimePicker;
  private DateTimePicker endDateTimePicker;
  private DateTimePicker epochDateTimePicker;
  private TabTextViewerEventHandler tabTextViewerEventHandler;

  public LEOPEventPredictionController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  /**
   * LEOPEventPrediction 화면과 ViewModel을 바인딩
   */
  public void bind(LEOPEventPredictionVieModel viewModel) throws IOException {
    /**
     * DatePicker들 세팅
     */
    startDateTimePicker = new DateTimePicker(205, 28, 12);
    endDateTimePicker = new DateTimePicker(205, 28, 12);
    epochDateTimePicker = new DateTimePicker(200, 28, 12);
    leopEventStartDateTimePickerPane.getChildren().add(startDateTimePicker);
    leopEventEndDateTimePickerPane.getChildren().add(endDateTimePicker);
    leopEventEpochDateTimePickerPane.getChildren().add(epochDateTimePicker);

    /**
     * TextViewer 핸들러 세팅
     */
    tabTextViewerEventHandler = new TabTextViewerEventHandler(this, leopEventMainTab);

    textViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);

    /**
     * TableView 세팅
     */
    groundStationFirstColumn.setCellValueFactory(cellData -> cellData.getValue().indexProperty().asObject());
    groundStationFirstColumn.setCellFactory(fc -> {
      TableCell<GroundStationSelectionTableRowViewModel, Integer> cell = new TableCell<GroundStationSelectionTableRowViewModel, Integer>(){
        @Override
        protected void updateItem(Integer item, boolean empty) {
          if (!empty) {
            CheckBox checkBox = new CheckBox();
            checkBox.getStyleClass().add("fds-check-box");
            setGraphic(checkBox);
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
              System.out.println(item + "clicked");
            });
          }
        }
      };
      return cell;
    });
    groundStationSecondColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
    groundStationThirdColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    groundStationFourthColumn.setCellValueFactory(cellData -> cellData.getValue().logitudeProperty().asObject());
    groundStationFifthColumn.setCellValueFactory(cellData -> cellData.getValue().latitudeProperty().asObject());
    groundStationSixthColumn.setCellValueFactory(cellData -> cellData.getValue().heightProperty().asObject());
    groundStationSeventhColumn.setCellValueFactory(cellData -> cellData.getValue().elevationProperty().asObject());
    groundStationEighthColumn.setCellValueFactory(cellData -> cellData.getValue().beamWidthProperty().asObject());

    groundStationTableView.setItems(viewModel.groundStationSelectionTableRowViewModelList);

  }
}
