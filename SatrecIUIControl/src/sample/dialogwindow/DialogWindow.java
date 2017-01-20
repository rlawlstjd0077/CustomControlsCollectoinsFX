package sample.dialogwindow;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * DialogWindow의 Controller로 Contents들에 대해 메소드를 통해 접근할 수 있음
 */
public class DialogWindow {
  @FXML
  private AnchorPane dialogWindowContainer;
  @FXML
  private AnchorPane dialogTopPane;
  @FXML
  private Label dialogTitle;
  @FXML
  private ImageView exitImg;
  @FXML
  private Button button;
  @FXML
  private AnchorPane labelContainer;

  private double xOffset = 0;
  private double yOffset = 0;

  private double prefContentsHeight = 0;

  /**
   * Exit Image가 눌렸을때 DialogWindow가 닫히도록 설정하고
   * 가장 윗부분 Pane을 드래그해서 Stage가 이동할수있도록 설정
   * */
  @FXML
  private void initialize(){
    Font.loadFont(getClass().getResourceAsStream("/commons/ui/fonts/Roboto-Bold.ttf"),
      14
    );
    Font.loadFont(getClass().getResourceAsStream("/commons/ui/fonts/Roboto-Regular.ttf"),
      14
    );

    dialogWindowContainer.getStyleClass().add("dialog_window");
    dialogWindowContainer.getStylesheets().add("/commons/ui/control/dialogwindow/DialogWindow.css");
    button.getStyleClass().add("primary-button");
    button.getStylesheets().add("/commons/ui/control/primarybutton/primarybutton.css");

    exitImg.setImage(new Image(getClass().getResourceAsStream("/commons/ui/images/dialogwindow/cancel white.png")));
    exitImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        Stage stage =(Stage) exitImg.getScene().getWindow();
        stage.close();
      }
    });

    dialogTopPane.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
      }
    });

    dialogTopPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        dialogTopPane.getScene().getWindow().setX(event.getScreenX() - xOffset);
        dialogTopPane.getScene().getWindow().setY(event.getScreenY() - yOffset);
      }
    });
  }

  /**
   * 소제목과 내용을 추가하는 기능을 제공
   *
   * @param subtitle 추가할 소제목
   * @param contents 추가할 내용
   */
  public void addContents(String subtitle, String contents) {
    Label subtitleLabel = new Label(subtitle);
    prefContentsHeight += 7;
    AnchorPane.setLeftAnchor(subtitleLabel, 12.0);
    AnchorPane.setTopAnchor(subtitleLabel, prefContentsHeight);
    prefContentsHeight += 23;
    subtitleLabel.setId("dialogwindow_content_title");

    Label contentsLabel = new Label(contents);
    AnchorPane.setLeftAnchor(contentsLabel, 12.0);
    AnchorPane.setTopAnchor(contentsLabel, prefContentsHeight);
    prefContentsHeight = prefContentsHeight + numberOfContentsLine(contents) * 14 + 50;
    contentsLabel.setId("dialogwindow_contents");

    labelContainer.getChildren().add(subtitleLabel);
    labelContainer.getChildren().add(contentsLabel);
  }

  /**
   * 내용을 추가하는 기능을 제공
   *
   * @param contents 추가할 내용
   */
  public void addContents(String contents) {
    Label contentsLabel = new Label(contents);
    AnchorPane.setLeftAnchor(contentsLabel, 12.0);
    if (prefContentsHeight == 0) {
      prefContentsHeight += 7;
    }
    AnchorPane.setTopAnchor(contentsLabel, prefContentsHeight);
    prefContentsHeight = prefContentsHeight + numberOfContentsLine(contents) * 14 + 50;
    contentsLabel.setId("dialogwindow_contents");

    labelContainer.getChildren().add(contentsLabel);
  }

  /**
   * DialogWindow의 제목을 설정하는 메소드
   * @param title DialogWindow의 제목으로 설정할 문자열
   * */
  public void setTitle(String title){
    this.dialogTitle.setText(title);
  }

  public Button getButton(){
    return this.button;
  }

  /**
   * 입력받은 문자열의 줄의 갯수를 알려주는 메소드
   *
   * @param contents 입력할 문자열
   * @return int 줄의 갯수
   */
  private int numberOfContentsLine(String contents) {
    int line = 0;
    for (int i = 0; i < contents.length(); i++) {
      if (contents.charAt(i) == '\n') {
        line++;
      }
    }
    return line;
  }
}

