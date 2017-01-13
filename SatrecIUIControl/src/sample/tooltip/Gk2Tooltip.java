package sample.tooltip;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.lang.reflect.Field;

/**
 * Created by 신동찬
 */
public class Gk2Tooltip extends Tooltip{

  private Button button= new Button();
  private AnchorPane layout;
  /**
   *  텍스트만 들어가는 형태의 툴팁을 생성할때 사용되는 생성자
   *  @param contents 툴팁에 들어갈 내용
   * */
  public Gk2Tooltip(String contents){
    this.setId("gk2tooltip");
    Font.loadFont(getClass().getResourceAsStream("/commons/ui/fonts/Roboto-Bold.ttf"),
      14
    );
    Font.loadFont(getClass().getResourceAsStream("/commons/ui/fonts/Roboto-Regular.ttf"),
      14
    );
    this.setText(contents);
    setTooltipStartTiming(this, 500);
  }

  /**
   * 제목, 내용, 버튼이 들어가는 툴팁을 생성할때 사용되는 생성자
   * @param title 툴팁의 제목
   * @param contents 툴팁에 들어갈 내용
   * @param buttonName 버튼의 이름
   * */
  public Gk2Tooltip(String title, String contents, String buttonName, EventHandler eventHandler){
    this.setId("gk2tooltip");
    Font.loadFont(getClass().getResourceAsStream("/commons/ui/fonts/Roboto-Bold.ttf"),
      14
    );
    Font.loadFont(getClass().getResourceAsStream("/commons/ui/fonts/Roboto-Regular.ttf"),
      14
    );
    layout = new AnchorPane();
    layout.setMinHeight(100);
    layout.setMinWidth(200);

    Label tooltipTitle = new Label(title);
    Label tooltipContents = new Label(contents);
    tooltipContents.setWrapText(true);

    Button button = new Button();
    button.getStyleClass().add("primary-button");
    button.setPadding(new Insets(0));
    ImageView arrow = new ImageView(new Image(getClass().getResourceAsStream("/commons/ui/images/sidemenu/arrow_icon/white Right.png")));
    AnchorPane buttonContents = new AnchorPane();
    buttonContents.setPrefWidth(buttonName.length() * 10.5);

    Label buttonTitle = new Label(buttonName);

    AnchorPane.setLeftAnchor(buttonTitle, 7.0);
    AnchorPane.setTopAnchor(buttonTitle, 0.0);
    AnchorPane.setBottomAnchor(buttonTitle, 0.0);

    AnchorPane.setRightAnchor(arrow, -2.0);
    AnchorPane.setTopAnchor(arrow, 0.0);
    AnchorPane.setBottomAnchor(arrow, 0.0);

    buttonContents.getChildren().add(buttonTitle);
    buttonContents.getChildren().add(arrow);
    buttonContents.setPrefHeight(20);

    button.setGraphic(buttonContents);

    tooltipTitle.setId("tooltip_title");
    tooltipContents.setId("tooltip_contents");

    layout.getChildren().add(tooltipTitle);
    layout.getChildren().add(tooltipContents);
    layout.getChildren().add(button);

    AnchorPane.setLeftAnchor(tooltipTitle, 5.0);
    AnchorPane.setTopAnchor(tooltipTitle, 3.0);

    AnchorPane.setLeftAnchor(tooltipContents, 5.0);
    AnchorPane.setTopAnchor(tooltipContents, 33.0);
    layout.setMinWidth(measureTooltipWidth(contents) * 6.25);
    AnchorPane.setBottomAnchor(tooltipContents, 30.0);

    AnchorPane.setRightAnchor(button, -4.0);
    AnchorPane.setBottomAnchor(button, -4.0);

    EventHandler finalEventHandler = eventHandler;
    eventHandler = new EventHandler() {
      @Override
      public void handle(Event event) {
        finalEventHandler.handle(event);
        hide();
      }
    };
    button.setOnMouseClicked(eventHandler);

    this.setGraphic(layout);
  }

  /**
   * 입력받은 String을 개행 단위로 길이를 구분해 가장 큰값을 리턴함
   * @param contents 툴팁에 들어갈 내용
   * @return Int 가장 긴 문장의 길이
   * */
  private int measureTooltipWidth(String contents){
    int max=0;
    int current=0;

    for(int i=0; i<contents.length() ; i++){
      if(contents.charAt(i) == '\n'){
        if(max < current){
          max = current;
          current = 0;
        } else{
          current = 0;
        }
      }else{
        current++;
      }
    }
    return max;
  }

  /**
   * 입력받은 노드에 마우스가 올려지면 툴팁이 보여지고 나갔을때 사라지도록 설정
   *
   * @param region 툴팁의 주체가 될 region
   */
  public void setInstaller(Region region) {
    region.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        Point2D p = region.localToScreen(region.getLayoutBounds().getMaxX(), region.getLayoutBounds().getMaxY());
        if (!isShowing()) {
          show(region, p.getX(), p.getY());
        }
      }
    });

    region.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        hide();
      }
    });
    region.getStylesheets().add(getClass().getResource("Gk2Tooltip.css").toExternalForm());
  }

  /**
   * 입력받은 노드에 마우스가 올려지면 툴팁이 보여지고 툴팁이 아닌 다른곳을 눌렸을때 사라지게 함
   *
   * @param region 툴팁의 주체가 될 region
   * @param scene  눌렀을때 툴팁이 사라지게 할 scene
   */
  public void setInstaller(Region region, Scene scene) {
    region.setOnMouseEntered(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        Point2D p = region.localToScreen(region.getLayoutBounds().getMaxX(), region.getLayoutBounds().getMaxY());
        if (!isShowing()) {
          show(region, p.getX(), p.getY());
        }
      }
    });

    scene.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (event.getTarget() != layout) {
          hide();
        }
      }
    });
    region.getStylesheets().add(getClass().getResource("Gk2Tooltip.css").toExternalForm());
  }

  public Button getButton() {
    return button;
  }

  /**
   * 툴팁을 나타나게하는 시간 설정.
   *
   * @param tooltip 설정할 툴팁
   * @param time    설정할 시간 ( Millis )
   */
  private void setTooltipStartTiming(Tooltip tooltip, int time) {
    try {
      Field fieldBehavior = Tooltip.class.getDeclaredField("BEHAVIOR");
      fieldBehavior.setAccessible(true);
      Object objBehavior = fieldBehavior.get(tooltip);

      Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
      fieldTimer.setAccessible(true);
      Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

      objTimer.getKeyFrames().clear();
      objTimer.getKeyFrames().add(new KeyFrame(new Duration(time)));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
