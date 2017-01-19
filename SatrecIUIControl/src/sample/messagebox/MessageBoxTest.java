package sample.messagebox;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 */
public class MessageBoxTest extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {

    FlowPane root = new FlowPane();
    root.setHgap(5);
    root.setVgap(5);
    root.setPadding(new Insets(25));

    Button firstButton = new Button("Button1");
    MessageBox firstMessageBox = new MessageBox("Change in Service", "Blah Blah Blah Blah Blah Blah Blah Blah", MessageBox.MessageBoxMode.INFO);

    Button secondButton = new Button("Button2");
    MessageBox secondMessageBox = new MessageBox("You must register before you can do that!", "Blah Blah", MessageBox.MessageBoxMode.WARNING);

    Button thirdButton = new Button("Button3");
    MessageBox thirdMessageBox = new MessageBox("We're sorry we can't apply that discount", "Blah Blah", MessageBox.MessageBoxMode.CRITICAL);

    firstMessageBox.setInstaller(firstButton);
    secondMessageBox.setInstaller(secondButton);
    thirdMessageBox.setInstaller(thirdButton, 100, 100);

    root.getChildren().add(firstButton);
    root.getChildren().add(secondButton);
    root.getChildren().add(thirdButton);

    Scene scene = new Scene(root, 350, 200);

    primaryStage.setScene(scene);
    primaryStage.show();
  }


  public void testMessageBox() {
    launch();
  }
}
