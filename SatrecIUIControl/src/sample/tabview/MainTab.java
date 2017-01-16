package sample.tabview;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * MainTab (Floating)
 * Created by 신동찬
 */
public class MainTab extends TabPane {
  public MainTab() {
    getStyleClass().add("maintab");
    this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    Font.loadFont(getClass().getResourceAsStream("/commons/ui/fonts/Roboto-Bold.ttf"),
      14
    );
    Font.loadFont(getClass().getResourceAsStream("/commons/ui/fonts/Roboto-Regular.ttf"),
      14
    );

    getStylesheets().add(MainTab.class.getResource("MainTab.css").toExternalForm());
    this.setTabMinHeight(30);
    this.setTabMinWidth(70);
    Tab tab1 = new Tab();
    tab1.setText("Tab" + 1);
    HBox hbox = new HBox();
    VBox vbox = new VBox();
    vbox.setStyle("-fx-background-color:red");
    tab1.setContent(vbox);
    hbox.setAlignment(Pos.CENTER);
    getTabs().add(tab1);
    getTabs().add(tab1);
  }

  /**
   * MainTab에 새로운 탭을 추가하고 탭에 AnchorPane을 삽입함.
   *
   * @param name 추가될 탭의 이름.
   * @return Tab 추가된 탭
   */
  public Tab addTab(String name) {
    Tab tab = new Tab(name);
    AnchorPane contentsPain = new AnchorPane();
    contentsPain.setId("tab_contents");

    tab.setContent(contentsPain);

    this.getTabs().add(tab);

    return tab;
  }
}
