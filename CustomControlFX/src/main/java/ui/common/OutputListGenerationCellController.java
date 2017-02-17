package ui.common;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

/**
 * Created by GSD on 2017-01-18.
 */
public class OutputListGenerationCellController extends TableCell<OutputListTableRowViewModel, String>{
  FDSCheckBox checkBox = new FDSCheckBox();

  @Override
  protected void updateItem(String item, boolean empty) {
    if(item != null){
      HBox hBox = new HBox();
      hBox.setAlignment(Pos.CENTER);
      hBox.setMargin(checkBox, new Insets(0, 8, 0, 0));
      hBox.getChildren().add(checkBox);
      setGraphic(hBox);
    }
  }
}
