package ui.screen.osculatingconversion;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.UiUtil;
import ui.control.datetimepicker.DateTimePicker;
import ui.screen.common.TextViewerEventHandler;

import java.io.IOException;

/**
 * Created by GSD on 2017-03-02.
 */
public class OsculatingConversionController extends AnchorPane{
  @FXML
  private ComboBox<String> osculatingConversionComboBox;
  @FXML
  private HBox osculatingElementDatePickerPane;
  @FXML
  private ComboBox<String> osculatingElementComboBox;
  @FXML
  private TextField osculatingElementSemiMajorTextField;
  @FXML
  private TextField osculatingElementEccentricityTextField;
  @FXML
  private TextField osculatingElementInclinationTextField;
  @FXML
  private TextField osculatingElementRAAscendingTextField;
  @FXML
  private TextField osculatingElementArgPerigeeTextField;
  @FXML
  private TextField osculatingElementMeanAnomalyTextField;
  @FXML
  private BorderPane singleConversionContainer;
  @FXML
  private VBox multiConversionContainer;
  @FXML
  private TextField multiConversionFileNameTextField;
  @FXML
  private TextField multiConversionStartTimeTextField;
  @FXML
  private TextField multiConversionEndTimeTextField;
  @FXML
  private TextField multiConversionOrbitCountTextField;
  @FXML
  private Button multiConversionFileOpenButton;
  @FXML
  private TextField multiConversionOutputFileNameTextField;
  @FXML
  private Button multiConversionOutputFileOpenButton;
  @FXML
  private VBox OrbitStackConversionContainer;
  @FXML
  private TextField orbitStackConversionStartTimeTextField;
  @FXML
  private TextField orbitStackConversionEndTimeTextField;
  @FXML
  private TextField orbitStackConversionOrbitCountTextField;
  @FXML
  private Button orbitStackConversionFileOpenButton;
  @FXML
  private TextField orbitStackConversionOutputFileNameTextField;
  @FXML
  private Button orbitStackConversionOutputFileOpenButton;
  @FXML
  private Button osculatingConversionConvertButton;
  @FXML
  private TextArea osculatingOrbitElementTextArea;
  @FXML
  private TextArea meanOrbitElementTextArea;
  @FXML
  private Button firstTextViewerSaveButton;
  @FXML
  private Button firstTextViewerPrintButton;
  @FXML
  private Button firstTextViewerNewWindowButton;
  @FXML
  private Button secondTextViewerSaveButton;
  @FXML
  private Button secondTextViewerPrintButton;
  @FXML
  private Button secondTextViewerNewWindowButton;

  private DateTimePicker dateTimePicker;
  private TextViewerEventHandler firstTextViewerEventHandler;
  private TextViewerEventHandler secondTextViewerEventHandler;

  public OsculatingConversionController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  /**
   * OsculatingConversion 화면과 ViewModel을 바인딩
   * @param viewModel
   */
  public void bind(OsculatingConversionViewModel viewModel) {
    dateTimePicker = new DateTimePicker(220, 28,12);
    osculatingElementDatePickerPane.getChildren().add(dateTimePicker);
    osculatingElementDatePickerPane.setMargin(dateTimePicker, new Insets(4, 0, 0, 0));

    firstTextViewerEventHandler = new TextViewerEventHandler(this, osculatingOrbitElementTextArea, "firstTextViewer", "Osculating Orbit Element");
    secondTextViewerEventHandler = new TextViewerEventHandler(this, meanOrbitElementTextArea, "secondTextViewer", "Mean Orbit Element");
    firstTextViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, firstTextViewerEventHandler);
    firstTextViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, firstTextViewerEventHandler);
    firstTextViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, firstTextViewerEventHandler);
    secondTextViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, secondTextViewerEventHandler);
    secondTextViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, secondTextViewerEventHandler);
    secondTextViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, secondTextViewerEventHandler);
  }
}
