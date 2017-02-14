package ui.screen.scenedefinition;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import ui.UiUtil;
import ui.control.gk2tableview.Gk2TableView;
import ui.screen.viewmodel.ExampleModel;

import java.io.IOException;

/**
 * Created by JinSeong on 2017-01-10.
 */
public class SceneDefinitionController extends BorderPane{
  @FXML
  private Button sceneDefImportButton;
  @FXML
  private WebView sceneDefWebView;
  @FXML
  private Gk2TableView<ExampleModel> sceneDefTableView;

  private WebEngine engine;
  /**
   * Scene Definition 화면 생성자
   * @throws  IOException
   */
  public SceneDefinitionController() throws IOException{
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  /**
   * Scene Definition 화면과 ViewModel을 바인딩.
   *
   * @param viewModel
   */
  public void bind(SceneDefinitionViewModel viewModel){
    engine = sceneDefWebView.getEngine();
    engine.load(viewModel.getPathFromPick(0));

    sceneDefTableView.getColumns().add(viewModel.columnTitleProperty().getValue());
    sceneDefTableView.itemsProperty().bind(viewModel.itemListProperty());
    sceneDefTableView.getSelectionModel().select(0);
    sceneDefTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
      engine.load(viewModel.getPathFromPick(sceneDefTableView.getSelectionModel().getSelectedIndex())));
    sceneDefImportButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {

      }
    });
  }
}
