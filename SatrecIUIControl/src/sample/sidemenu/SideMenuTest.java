package sample.sidemenu;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.UiUtil;

/**
 */
public class SideMenuTest extends Application{
  private AnchorPane rootLayout;
  private ObservableList<TreeItem> treeData = FXCollections.observableArrayList();

  @Override
  public void start(Stage primaryStage) throws Exception {
    try {
      FXMLLoader loader = UiUtil.getFxmlLoader(SideMenu.class);
      rootLayout = loader.load();
      Scene scene = new Scene(rootLayout);

      TreeItem<String> sample1, sample2, sample3;
      Image menuIcon = new Image(getClass().getResourceAsStream("/commons/ui/images/sidemenu/icon_guidbox_24px.png"));
      ImageView[] imageViews = new ImageView[3];
      for (int i = 0; i < 3; i++) {
        imageViews[i] = new ImageView(menuIcon);
        imageViews[i].setFitHeight(25);
        imageViews[i].setFitWidth(25);
      }
      sample1 = new TreeItem<>("Sample1", imageViews[0]);
      makeBranch("first", sample1);
      makeBranch("second", sample1);
      makeBranch("third", sample1);

      sample2 = new TreeItem<>("Sample2", imageViews[1]);
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

      SideMenu controller = loader.getController();
      controller.setSideMenuTreeItem(treeData);

      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e){
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
    TreeItem<String> item;
    if (parent.getGraphic() != null) {
      parent.getGraphic().setId("treeitem_image");
      Label spaceLabel = new Label("");
      spaceLabel.setId("treeitem_space");
      item = new TreeItem<>(title, spaceLabel);
    } else {
      item = new TreeItem<>(title);
    }
    item.setExpanded(true);
    parent.getChildren().add(item);

    return item;
  }


  public void testSideMenu() {
    launch();
  }
}
