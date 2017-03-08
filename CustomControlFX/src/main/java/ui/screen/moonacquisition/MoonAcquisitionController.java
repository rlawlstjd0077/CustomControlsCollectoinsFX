package ui.screen.moonacquisition;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import ui.UiUtil;
import ui.common.ChartViewerEventHandler;
import ui.common.TabTextViewerEventHandler;
import ui.control.datetimepicker.DateTimePicker;

import java.io.IOException;

/**
 * Created by GSD on 2017-03-02.
 */
public class MoonAcquisitionController extends AnchorPane{
  @FXML
  private HBox moonAcquisitionStartDateTimePickerPane;
  @FXML
  private HBox moonAcquisitionEndDateTimePickerPane;
  @FXML
  private HBox moonAcquisitionEpochDateTimePickerPane;
  @FXML
  private Button moonAcquisitionEpochSelectButton;
  @FXML
  private ComboBox<String> stackSourceTypeComboBox;
  @FXML
  private ComboBox<String> displayTypeComboBox;
  @FXML
  private ComboBox<String> coordinateFrameComboBox;
  @FXML
  private GridPane moonAcquisitionEpochGridPane;
  @FXML
  private ComboBox<String> moonAcquisitionFileTypeComboBox;
  @FXML
  private Button moonAcquisitionSaveParameterButton;
  @FXML
  private Button moonAcquisitionExcuteButton;
  @FXML
  private GridPane outputListGridPane;
  @FXML
  private TabPane moonAcquisitionMainTab;
  @FXML
  private Button textViewerSaveButton;
  @FXML
  private Button textViewerPrintButton;
  @FXML
  private Button textViewerNewWindowButton;
  @FXML
  private Spinner<Integer> moonAcquisitionReferenceSpinner;
  @FXML
  private ComboBox<String> moonAcquisitionPredictionComboBox;
  @FXML
  private Spinner<String> moonAcquisitionPredictionSpinner;
  @FXML
  private Button moonAcquisitionPredictionButton;
  @FXML
  private GridPane moonAcquisitionGridPane;

  private DateTimePicker startDateTimePicker;
  private DateTimePicker endDateTimePicker;
  private DateTimePicker epochDateTimePicker;
  private TabTextViewerEventHandler tabTextViewerEventHandler;
  private ChartViewerEventHandler eventPredictionChartViewerEventHandler;

  public MoonAcquisitionController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  /**
   * MoonAcquisition화면과 VieModel을 바인딩
   * @param viewModel
   */
  public void bind(MoonAcquisitionViewModel viewModel){
    startDateTimePicker = new DateTimePicker(180, 28, 12);
    endDateTimePicker = new DateTimePicker(180, 28, 12);
    epochDateTimePicker = new DateTimePicker(180, 28, 12);
    moonAcquisitionStartDateTimePickerPane.getChildren().add(startDateTimePicker);
    moonAcquisitionEndDateTimePickerPane.getChildren().add(endDateTimePicker);
    moonAcquisitionEpochDateTimePickerPane.getChildren().add(epochDateTimePicker);
    moonAcquisitionStartDateTimePickerPane.setMargin(startDateTimePicker, new Insets(5, 0, 0, 0));
    moonAcquisitionEndDateTimePickerPane.setMargin(endDateTimePicker, new Insets(5, 0, 0, 0));

    tabTextViewerEventHandler = new TabTextViewerEventHandler(this, moonAcquisitionMainTab);
    textViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
  }
}
