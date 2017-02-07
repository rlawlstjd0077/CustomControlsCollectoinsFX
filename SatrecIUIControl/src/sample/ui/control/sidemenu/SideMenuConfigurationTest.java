package sample.ui.control.sidemenu;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import sample.UiUtil;

/**
 */
public class SideMenuConfigurationTest extends Application {
  private AnchorPane rootLayout;
  private ObservableList<TreeItem> treeData = FXCollections.observableArrayList();

  @Override
  public void start(Stage primaryStage) throws Exception {
    try {
      FlowPane layout = new FlowPane();
      layout.setPrefHeight(500);
      layout.setPrefWidth(400);
      layout.setPadding(new Insets(0));

      FXMLLoader loader = UiUtil.getFxmlLoader(SideMenuConfiguration.class);
      rootLayout = loader.load();

      layout.getChildren().add(rootLayout);
      Scene scene = new Scene(layout);

      TreeItem<String> sample1, sample2, sample3;
      sample1 = new TreeItem<>("Sample1");
      makeBranch("first", sample1);
      makeBranch("second", sample1);
      makeBranch("third", sample1);

      sample2 = new TreeItem<>("Sample2");
      makeBranch("first", sample2);
      makeBranch("second", sample2);
      makeBranch("third", sample2);

      sample3 = new TreeItem<>("Sample3");
      makeBranch("first", sample3);
      makeBranch("second", sample3);
      makeBranch("third", sample3);

      treeData.add(sample1);
      treeData.add(sample2);
      treeData.add(sample3);

      SideMenuConfiguration controller = loader.getController();
      controller.setSideMenuTreeItem(treeData);

      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 입력받은 이름을 가진 Sub Tree 만들기
   *
   * @param title  sub tree 의 이름
   * @param parent sub tree가 속할 TreeItem
   * @return sub tree item
   */
  private TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
    TreeItem<String> item = new TreeItem<>(title);
    item.setExpanded(true);
    parent.getChildren().add(item);

    return item;
  }


}
