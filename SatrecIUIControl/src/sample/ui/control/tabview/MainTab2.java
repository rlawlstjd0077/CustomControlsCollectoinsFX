package sample.ui.control.tabview;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

/**
 * MainTab 2
 * 선택된 탭을 X버튼을 통해 닫을 수 있음
 */
public class MainTab2 extends TabPane {
  public MainTab2() {
    getStyleClass().add("maintab2");
    this.setBlendMode(BlendMode.SRC_ATOP);
    this.setPadding(new Insets(-5.9, 0, 0, 0));
    this.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);

    Font.loadFont(getClass().getResourceAsStream("/commons/ui/fonts/Roboto-Bold.ttf"),
            14
    );
    Font.loadFont(getClass().getResourceAsStream("/commons/ui/fonts/Roboto-Regular.ttf"),
            14
    );

    getStylesheets().add(MainTab.class.getResource("MainTab2.css").toExternalForm());
    this.setTabMinHeight(10);
    Tab tab1 = new Tab();
    tab1.setText("Tab" + 1);
//    Gk2TextArea textArea = new Gk2TextArea();
//    textArea.setEditable(false);
//
//    HBox hBox = new HBox();
//    hBox.setStyle("-fx-background-color:white");
//    HBox.setHgrow(textArea, Priority.ALWAYS);
//    hBox.setMargin(textArea, new Insets(35, 3, 3, 3));
//
//    hBox.getChildren().add(textArea);
//    tab1.setContent(hBox);
    getTabs().add(tab1);
  }

  /**
   * MainTab에 새로운 탭을 추가하고 탭에 AnchorPane을 삽입함.
   *
   * @param name 추가될 탭의 이름.
   * @return Tab 추가된 탭
   */
  public Tab addTab(String name) {
    Tab tab = new Tab();
    AnchorPane contentsPain = new AnchorPane();
    contentsPain.setId("tab_contents");
    tab.setContent(contentsPain);

    AnchorPane tabNode = new AnchorPane();
    Label tabName = new Label(name);
    AnchorPane.setLeftAnchor(tabName, 0.0);
    AnchorPane.setRightAnchor(tabName, 55.0);
    AnchorPane.setTopAnchor(tabName, 10.0);
    AnchorPane.setBottomAnchor(tabName, 10.0);

    tabNode.getChildren().add(tabName);
    tab.setGraphic(tabNode);
    this.getTabs().add(tab);

    return tab;
  }

//  public Tab addTabFromFile(File txtFile){
//    Tab tab = new Tab();
//    tab.setText(txtFile.getName());
//    Gk2TextArea textArea = new Gk2TextArea();
//    textArea.setEditable(false);
//
//    HBox hBox = new HBox();
//    hBox.setStyle("-fx-background-color:white");
//    HBox.setHgrow(textArea, Priority.ALWAYS);
//    hBox.setMargin(textArea, new Insets(35, 3, 3, 3));
//
//    BufferedReader br = null;
//    try{
//      br = new BufferedReader(new InputStreamReader(new FileInputStream(txtFile)));
//      String line;
//      while((line = br.readLine()) != null){
//        textArea.appendText(line + "\n");
//      }
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    hBox.getChildren().add(textArea);
//    tab.setContent(hBox);
//    return tab;
//  }
//}
}