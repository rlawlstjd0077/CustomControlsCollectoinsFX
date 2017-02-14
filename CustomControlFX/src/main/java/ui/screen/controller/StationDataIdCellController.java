package ui.screen.controller;

import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import ui.screen.viewmodel.StationDataRowViewModel;

/**
 * Created by JinSeong on 2017-01-18.
 */
public class StationDataIdCellController extends TableCell<StationDataRowViewModel, String>{
  final private CheckBox checkBox = new CheckBox();
  public StationDataIdCellController(){

  }

  @Override
  protected void updateItem(String item, boolean empty) {
    if(item != null){
      setGraphic(checkBox);
      setText(item);

      checkBox.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
        @Override
        public void handle(javafx.scene.input.MouseEvent event) {
          if (checkBox.isSelected()) {

          }

        }
      });
    }
  }
}
