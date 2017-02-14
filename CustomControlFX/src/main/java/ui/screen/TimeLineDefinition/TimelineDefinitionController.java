package ui.screen.timelinedefinition;

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
 * Created by JinSeong on 2017-01-12.
 */
public class TimelineDefinitionController extends BorderPane {
  @FXML
  private Button timelineDefImportButton;
  @FXML
  private WebView timelineDefWebView;
  @FXML
  private Gk2TableView<ExampleModel> timelineDefTableView;

  private WebEngine engine;

  /**
   * Timeline Definition 화면 생성자
   * @throws IOException
   */
  public TimelineDefinitionController() throws IOException{
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  public void bind(TimelineDefinitionViewModel viewModel) {
    engine = timelineDefWebView.getEngine();
    engine.load(viewModel.getPathFromPick(0));

    timelineDefTableView.getColumns().add(viewModel.columnTitleProperty().getValue());
    timelineDefTableView.itemsProperty().bind(viewModel.itemListProperty());
    timelineDefTableView.getSelectionModel().select(0);
    timelineDefTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      System.out.println(timelineDefTableView.getSelectionModel().getSelectedIndex());
      engine.load(viewModel.getPathFromPick(timelineDefTableView.getSelectionModel().getSelectedIndex()));
    });
    timelineDefImportButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {

      }
    });
  }
}
