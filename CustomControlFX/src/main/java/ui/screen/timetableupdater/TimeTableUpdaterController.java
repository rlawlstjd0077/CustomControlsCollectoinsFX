package ui.screen.timetableupdater;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import ui.UiUtil;
import ui.screen.common.TabTextViewerEventHandler;

import java.io.File;
import java.io.IOException;

/**
 * Created by GSD on 2017-03-03.
 */
public class TimeTableUpdaterController extends AnchorPane {
  @FXML
  private TextField timeTableUpdatedLeapTextField;
  @FXML
  private Button timeTableUpdatedLeapFileChooseButton;
  @FXML
  private TextField timeTableUpdatedEarthTextField;
  @FXML
  private Button timeTableUpdatedEarthFileChooseButton;
  @FXML
  private TextField timeTableLocationTextField;
  @FXML
  private Button timeTableLocationFileChooseButton;
  @FXML
  private Button timeTableCheckCurrentButton;
  @FXML
  private Button timeTableUpdateButton;
  @FXML
  private TabPane timeTableMainTab;
  @FXML
  private Button textViewerSaveButton;
  @FXML
  private Button textViewerPrintButton;
  @FXML
  private Button textViewerNewWindowButton;

  private TabTextViewerEventHandler tabTextViewerEventHandler;

  public TimeTableUpdaterController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  /**
   * TimetableUpdater 화면과 ViewModel을 바인딩
   */
  public void bind(TimeTableUpdaterViewModel viewModel) {
    tabTextViewerEventHandler = new TabTextViewerEventHandler(this, timeTableMainTab);
    textViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);
    textViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, tabTextViewerEventHandler);

    timeTableUpdatedLeapFileChooseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      File file = chooseFile();
      if(file != null) {
        timeTableUpdatedLeapTextField.setText(file.getAbsolutePath());
      }
    });
    timeTableUpdatedEarthFileChooseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      File file = chooseFile();
      if(file != null) {
        timeTableUpdatedEarthTextField.setText(file.getAbsolutePath());
      }
    });
    timeTableLocationFileChooseButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      File file = chooseFile();
      if(file != null) {
        timeTableLocationTextField.setText(file.getAbsolutePath());
      }
    });
  }

  public File chooseFile(){
    FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showOpenDialog(this.getScene().getWindow());
    return file;
  }
}
