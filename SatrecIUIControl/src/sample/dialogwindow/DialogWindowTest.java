package sample.dialogwindow;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.UiUtil;

import java.io.IOException;

/**
 */
public class DialogWindowTest extends Application{
  Stage parentStage;

  @Override
  public void start(Stage primaryStage) throws Exception {
    this.parentStage = primaryStage;
    Pane pane = new Pane();
    pane.setMinWidth(50);
    pane.setMinHeight(50);
    Button button = new Button("Test");

    button.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        // 테스트에 쓰일 데이터
        String dialogWindowTitle = "Test DialogWindow Title";
        String buttonName = "Test Button";

        EventHandler buttonEvent = new EventHandler() {
          @Override
          public void handle(Event event) {
            System.out.println("Button Clicked");
          }
        };

        DialogWindow testDialogWindow = showDialogWindow(dialogWindowTitle, buttonName, buttonEvent);
        testDialogWindow.addContents("TEST");
        testDialogWindow.addContents("Subtitle 1", "This is test dialog window.\ncan be moved, redsized and closed with the 'x' icon");
        testDialogWindow.addContents("Subtitle 2", "test");
        testDialogWindow.addContents("Subtitle 3", "test");
      }
    });

    pane.getChildren().add(button);
    Scene scene = new Scene(pane);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   *  입력받은 파라미터를 통해 DialogWindow를 만들기
   *  @param title 가장 상단에 위치할 제목
   *  @param buttonName 버튼 이름
   *  @param buttonEvent 버튼이 클릭됬을때 event
   * */
  private DialogWindow showDialogWindow(String title, String buttonName, EventHandler buttonEvent) {
    FXMLLoader loader = UiUtil.getFxmlLoader(DialogWindow.class);
    UiUtil.initializeFont();
    try {
      AnchorPane dialogLayout = loader.load();
      DialogWindow dialogWindow = loader.getController();

      dialogWindow.setTitle(title);
      dialogWindow.getButton().setText(buttonName);
      dialogWindow.getButton().setOnMouseClicked(buttonEvent);

      Stage dialogStage = new Stage();
      dialogStage.initStyle(StageStyle.TRANSPARENT);
      dialogStage.initOwner(parentStage);

      Scene scene = new Scene(dialogLayout);

      dialogStage.setScene(scene);
      dialogStage.show();

      ResizeListener listener = new ResizeListener(scene, dialogStage);
      scene.setOnMouseMoved(listener);
      scene.setFill(Color.TRANSPARENT);
      scene.setOnMousePressed(listener);
      scene.setOnMouseDragged(listener);

      return dialogWindow;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }


  public void testDialogWindow() {
    launch();
  }
}
