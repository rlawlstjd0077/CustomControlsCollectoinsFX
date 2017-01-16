package sample.tabview;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
/**
 * Created by 신동찬
 */
public class MainTab2Test extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    FlowPane rootLayout = new FlowPane();
    rootLayout.setMinWidth(600);
    rootLayout.setMinHeight(600);
    rootLayout.setPadding(new Insets(10));
    try {
      MainTab2 mainTab = new MainTab2();
      mainTab.setPrefHeight(500);
      mainTab.setPrefWidth(500);

      mainTab.addTab("TabTabTab 1");
      mainTab.addTab("TabTabTab 2");
      mainTab.addTab("Tab 3");
      mainTab.addTab("Tab 4");

      rootLayout.getChildren().add(mainTab);

      Scene scene = new Scene(rootLayout);
      primaryStage.setScene(scene);

      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


//  @Test
//  @Ignore
//  public void testMainTab2() {
//    launch();
//  }
}
