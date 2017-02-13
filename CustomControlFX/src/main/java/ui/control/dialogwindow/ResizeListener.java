package ui.control.dialogwindow;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 마우스로 스테이지의 크기를 조정할때 이벤트를 처리하는 기능 제공
 * Created by 신동찬
 */
public class ResizeListener implements EventHandler<MouseEvent> {
  private double dx;
  private double dy;
  private double deltaX;
  private double deltaY;
  private double border = 10;
  private boolean moveH;
  private boolean moveV;
  private boolean resizeH = false;
  private boolean resizeV = false;

  private Scene scene;
  private Stage stage;
  private double originalStageWidth;
  private double originalStageHeight;

  /**
   * 크기를 조정할때 한계치를 설정하기 위한 값 초기화
  **/
  public ResizeListener(Scene scene , Stage stage){
    this.scene = scene;
    this.stage = stage;

    this.originalStageHeight =stage.getHeight();
    this.originalStageWidth = stage.getWidth();
  }

  /**
   * 마우스로 크기를 조정할때 반응하는 핸들러
   * */
  @Override
  public void handle(MouseEvent t) {
    if(MouseEvent.MOUSE_MOVED.equals(t.getEventType())){
      if(t.getX() < border && t.getY() < border){
        scene.setCursor(Cursor.NW_RESIZE);
        resizeH = true;
        resizeV = true;
        moveH = true;
        moveV = true;
      }
      else if(t.getX() < border && t.getY() > scene.getHeight() -border){
        scene.setCursor(Cursor.SW_RESIZE);
        resizeH = true;
        resizeV = true;
        moveH = true;
        moveV = false;
      }
      else if(t.getX() > scene.getWidth() -border && t.getY() < border){
        scene.setCursor(Cursor.NE_RESIZE);
        resizeH = true;
        resizeV = true;
        moveH = false;
        moveV = true;
      }
      else if(t.getX() > scene.getWidth() -border && t.getY() > scene.getHeight() -border){
        scene.setCursor(Cursor.SE_RESIZE);
        resizeH = true;
        resizeV = true;
        moveH = false;
        moveV = false;
      }
      else if(t.getX() < border || t.getX() > scene.getWidth() -border){
        scene.setCursor(Cursor.E_RESIZE);
        resizeH = true;
        resizeV = false;
        moveH = (t.getX() < border);
        moveV = false;
      }
      else if(t.getY() < border || t.getY() > scene.getHeight() -border){
        scene.setCursor(Cursor.N_RESIZE);
        resizeH = false;
        resizeV = true;
        moveH = false;
        moveV = (t.getY() < border);
      }
      else{
        scene.setCursor(Cursor.DEFAULT);
        resizeH = false;
        resizeV = false;
        moveH = false;
        moveV = false;
      }
    }
    else if(MouseEvent.MOUSE_PRESSED.equals(t.getEventType())){
      dx = stage.getWidth() - t.getX();
      dy = stage.getHeight() - t.getY();
    }
    else if(MouseEvent.MOUSE_DRAGGED.equals(t.getEventType())){
      if(resizeH){
        if(stage.getWidth() <= originalStageWidth){
          if(moveH){
            deltaX = stage.getX()-t.getScreenX();
            if(t.getX() < 0){// if new > old, it's permitted
              stage.setWidth(deltaX+stage.getWidth());
              stage.setX(t.getScreenX());
            }
          }
          else{
            if(t.getX()+dx - stage.getWidth() > 0){
              stage.setWidth(t.getX()+dx);
            }
          }
        }
        else if(stage.getWidth() > originalStageWidth){
          if(moveH){
            deltaX = stage.getX()-t.getScreenX();
            stage.setWidth(deltaX+stage.getWidth());
            stage.setX(t.getScreenX());
          }
          else{
            stage.setWidth(t.getX()+dx);
          }
        }
      }
      if(resizeV){
        if(stage.getHeight() <= originalStageHeight){
          if(moveV){
            deltaY = stage.getY()-t.getScreenY();
            if(t.getY() < 0){// if new > old, it's permitted
              stage.setHeight(deltaY+stage.getHeight());
              stage.setY(t.getScreenY());
            }
          }
          else{
            if(t.getY()+dy - stage.getHeight() > 0){
              stage.setHeight(t.getY()+dy);
            }
          }
        }
        else if(stage.getHeight() > originalStageHeight){
          if(moveV){
            deltaY = stage.getY()-t.getScreenY();
            stage.setHeight(deltaY+stage.getHeight());
            stage.setY(t.getScreenY());
          }
          else{
            stage.setHeight(t.getY()+dy);
          }
        }
      }
    }
  }
}
