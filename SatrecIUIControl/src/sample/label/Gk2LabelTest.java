package sample.label;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class Gk2LabelTest extends Application{
  @Override
  public void start(Stage primaryStage) throws Exception {
    FlowPane root =new FlowPane();

    Gk2Label errorLabel = new Gk2Label(Gk2Label.LabelUse.ERROR);
    Gk2Label warningLabel = new Gk2Label(Gk2Label.LabelUse.WARNING);
    Gk2Label infoLabel = new Gk2Label(Gk2Label.LabelUse.INFO);
    Gk2Label offLabel = new Gk2Label(Gk2Label.LabelUse.OFF);

    root.getChildren().add(errorLabel);
    root.getChildren().add(warningLabel);
    root.getChildren().add(infoLabel);
    root.getChildren().add(offLabel);

    Scene scene = new Scene(root);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

//  @Test
//  @Ignore
//  public void testLabel() {
//    launch();
//  }

}


