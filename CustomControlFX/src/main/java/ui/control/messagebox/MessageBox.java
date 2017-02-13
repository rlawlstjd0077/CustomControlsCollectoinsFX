package ui.control.messagebox;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

/**
 * Tooltip을 상속받은 형태로 사용법은 Gk2Tooltip과 유사함
 * Created by 신동찬
 */
public class MessageBox extends Tooltip{
  /**
   * MessageBox 생성자
   * 예) new MessageBox("title", "blah blah" ,MessageBox.MessageBoxMode.INFO);
   * @param title 제목
   * @param explane MessageBox에 들어갈 내용
   * @param mode 3가지 형태로 INFO, WARNING, CRITICAL 이 있음
   * */
  public MessageBox(String title, String explane, MessageBoxMode mode) {
    AnchorPane contents=new AnchorPane();
    contents.setPadding(new Insets(2));
    contents.setMinWidth(200);
    contents.setMinHeight(80);

    if (title.length() > explane.length()) {
      contents.setPrefWidth(measureMessageBoxWidth(title) * 12 + 50);
    } else {
      contents.setPrefWidth(measureMessageBoxWidth(explane) * 7.5 + 50);
    }

    Label titleLabel = new Label(title);
    titleLabel.setId("messagebox_title");

    switch (mode.value) {
      case 0:
        this.setId("messagebox_info");
        AnchorPane.setLeftAnchor(titleLabel, 12.0);
        break;
      case 1:
        this.setId("messagebox_warning");
        ImageView warningImg = new ImageView(new Image(getClass().getResourceAsStream("/commons/ui/images/massagebox/warning.png")));
        AnchorPane.setLeftAnchor(warningImg, 10.0);
        AnchorPane.setTopAnchor(warningImg, 12.0);
        contents.getChildren().add(warningImg);
        AnchorPane.setLeftAnchor(titleLabel, 48.0);
        break;
      case 2:
        this.setId("messagebox_critical");
        AnchorPane.setLeftAnchor(titleLabel, 12.0);
        break;
      default:
        this.setId("messagebox_info");
        AnchorPane.setLeftAnchor(titleLabel, 12.0);
        break;
    }
    AnchorPane.setTopAnchor(titleLabel, 10.0);

    Label explaneLabel = new Label(explane);
    explaneLabel.setId("messagebox_explain");
    AnchorPane.setLeftAnchor(explaneLabel, 12.0);
    AnchorPane.setTopAnchor(explaneLabel, 50.0);

    ImageView exitImage = new ImageView(new Image(getClass().getResourceAsStream("/commons/ui/images/massagebox/cancel_black.png")));
    exitImage.setFitWidth(25);
    exitImage.setFitHeight(25);

    Button exitButton = new Button("");
    exitButton.setGraphic(exitImage);
    exitButton.setPadding(new Insets(0));
    exitButton.setId("messagebox_exit");
    exitButton.setOpacity(0.45);
    AnchorPane.setRightAnchor(exitButton, -5.0);
    AnchorPane.setTopAnchor(exitButton, -5.0);

    exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        hide();
      }
    });
    contents.getChildren().add(titleLabel);
    contents.getChildren().add(explaneLabel);
    contents.getChildren().add(exitButton);
    this.setGraphic(contents);
  }

  /**
   * Node가 마우스로 클릭되었을때 해당 Node의 위치에서 MessageBox가 생성되도록 하는 메소드
   *
   * @param region MessageBox를 생성하는 region
   */
  public void setInstaller(Region region) {
    region.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        Point2D p = region.localToScreen(region.getLayoutBounds().getMaxX(), region.getLayoutBounds().getMaxY());
        if (!isShowing()) {
          show(region, p.getX(), p.getY());
        }
      }
    });
    region.getStylesheets().add(getClass().getResource("MessageBox.css").toExternalForm());
  }

  /**
   * Node가 마우스로 클릭되었을때 입력받은 위치에서 MessageBox가 생성되도록 하는 메소드
   *
   * @param region      MessageBox를 생성하는 region
   * @param xCoordinate MessageBox가 생성될 위치의 X 좌표
   * @param yCoordinate MessageBox가 생성될 위치의 Y 좌표
   */
  public void setInstaller(Region region, double xCoordinate, double yCoordinate) {
    region.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (!isShowing()) {
          show(region, xCoordinate, yCoordinate);
        }
      }
    });
    region.getStylesheets().add(getClass().getResource("MessageBox.css").toExternalForm());
  }

  /**
   * 입력받은 String을 개행 단위로 길이를 구분해 가장 큰값을 ( 긴 ) 리턴함
   *
   * @param contents 툴팁에 들어갈 내용
   * @return int
   */
  private int measureMessageBoxWidth(String contents) {
    int max = 0;
    int current = 0;

    for (int i = 0; i < contents.length(); i++) {
      if (contents.charAt(i) == '\n') {
        if (max < current) {
          max = current;
          current = 0;
        } else {
          current = 0;
        }
      } else {
        current++;
      }
    }
    if (max == 0) {
      max = current;
    }
    return max;
  }

  public enum MessageBoxMode {
    INFO(0), WARNING(1), CRITICAL(2);
    private int value;

    MessageBoxMode(int value) {
      this.value = value;
    }
  }
}
