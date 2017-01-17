package sample.tooltip;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
/**
 * 툴팁은
 */
public class Gk2TooltipTest extends Application{
  @Override
  public void start(Stage primaryStage) throws Exception {
    FlowPane root = new FlowPane();
    root.setHgap(5);
    root.setVgap(5);
    root.setPadding(new Insets(25));
    Scene scene = new Scene(root, 350, 200);

    //Simple Tooltip
    Gk2Tooltip simpleTooltip = new Gk2Tooltip("This is a simple Tooltip");
    Button simpleTooltipButton = new Button("SimpleTooltip Button");

    root.getChildren().add(simpleTooltipButton);
    simpleTooltip.setInstaller(simpleTooltipButton);

    // Another form Tooltip
    String sentence = "Clicked popups will close if you click away,\n" +
      "but not if you click the popup";
    EventHandler eventHandler = new EventHandler() {
      @Override
      public void handle(Event event) {
        System.out.println("button clicked ");
      }
    };

    Gk2Tooltip tooltip = new Gk2Tooltip("Title", sentence, "Move", eventHandler);
    Button testButton = new Button("Button");
    tooltip.setInstaller(testButton, scene);
    root.getChildren().add(testButton);


    primaryStage.setScene(scene);
    primaryStage.show();
  }

//  @Test
//  @Ignore
//  public void testTooltip() {
//    launch();
//  }
}
