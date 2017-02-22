package ui.common;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.ButtonBase;
import javafx.scene.input.MouseEvent;

/**
 * Created by JinSeong on 2017-01-25.
 */
public class Gk2ButtonCursorEventHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle( final MouseEvent ME )
    {
      Object obj = ME.getSource();

      /**
       * 모든 버튼을 포함하는 상위 클래스인 ButtonBase를 사용
       */
      ButtonBase button = (ButtonBase) obj;
      button.setCursor(Cursor.HAND);
    }
}
