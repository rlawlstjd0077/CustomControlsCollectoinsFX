package sample.tabview;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 */
public class MainTabTest extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception{
    try {
      MainTab mainTab = new MainTab();
      mainTab.setPrefHeight(500);
      mainTab.setPrefWidth(500);

      mainTab.addTab("Tab 1");
      mainTab.addTab("Tab 2");
      mainTab.addTab("Tab 3");
      mainTab.addTab("Tab 4");

      Scene scene = new Scene(mainTab);
      primaryStage.setScene(scene);

      primaryStage.show();
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }


//  @Test
//  @Ignore
//  public void testMainTab() {
//    launch();
//  }
}
