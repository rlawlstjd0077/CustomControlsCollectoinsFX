package ui.screen.common;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * Created by GSD on 2017-02-13.
 */
public class EpochSelectorPopup extends BorderPane{
  @FXML
  private ComboBox<String> epochStackDataComboBox;
  @FXML
  private ComboBox<String> epochCoordinateComboBox;
  @FXML
  private GridPane epochDataGridPane;
  @FXML
  private Button epochOKButton;
  @FXML
  private Button epochCancelButton;
  @FXML
  private Button epochApplyButton;
  public EpochSelectorPopup(){
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EpochSelector.fxml"));

    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
