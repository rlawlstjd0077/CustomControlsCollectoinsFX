package ui.common;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import ui.screen.preciseorbitprediction.PreciseOrbitPredictionController;

/**
 * Created by GSD on 2017-01-18.
 */
public class OutputListViewCellController {
  final private ImageView viewImage = new ImageView(new Image(getClass().getResourceAsStream("/fds/ui/images/view.png")));
  final private Button viewButton = new Button();
  private PreciseOrbitPredictionController controller;


  public OutputListViewCellController(PreciseOrbitPredictionController controller, String item) {
//    this.controller = controller;
    viewButton.getStylesheets().add("/fds/ui/preciseorbitprediction/viewbutton.css");
    viewButton.getStyleClass().add("view-button");
    viewButton.setMinWidth(14);
    viewButton.setMinHeight(14);
    viewButton.setGraphic(viewImage);

    viewButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        if (item != null) {
          controller.openNewFile(item);
        }
      }
    });

  }

  public Button getViewButton() {
    return viewButton;
  }
}

