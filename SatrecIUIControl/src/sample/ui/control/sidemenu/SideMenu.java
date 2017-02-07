package sample.ui.control.sidemenu;

import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 기본 형태의 Side Menu
 */
public class SideMenu implements Initializable{
  @FXML
  AnchorPane sideMenu;
  @FXML
  AnchorPane treeViewContainer;
  @FXML
  TreeView<String> treeView;
  @FXML
  Button hideButton;
  @FXML
  Button showButton;

  private ObservableList<TreeItem> sideMenuTreeItem;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    Font.loadFont(getClass().getResourceAsStream("/commons/ui/fonts/Roboto-Bold.ttf"),
      14
    );
    Font.loadFont(getClass().getResourceAsStream("/commons/ui/fonts/Roboto-Regular.ttf"),
      14
    );
    Font.loadFont(getClass().getResourceAsStream("/commons/ui/fonts/Roboto-Medium.ttf"),
      14
    );

    sideMenu.getStylesheets().add("/commons/ui/control/sidemenu/SideMenu.css");
    sideMenu.getStyleClass().add("sidemenu");

    Image hideButtonImg = new Image(getClass().getResourceAsStream("/commons/ui/images/sidemenu/arrow_icon/white Left.png"));
    Image showButtonImg = new Image(getClass().getResourceAsStream("/commons/ui/images/sidemenu/arrow_icon/white Right.png"));
    hideButton.setGraphic(new ImageView(hideButtonImg));
    showButton.setGraphic(new ImageView(showButtonImg));

    PseudoClass subElementPseudoClass = PseudoClass.getPseudoClass("sub-tree-item");

    treeView.setCellFactory(tv -> {
      TreeCell<String> cell = new TreeCell<String>() {
        @Override
        public void updateItem(String item, boolean empty) {
          super.updateItem(item, empty);
          if (empty) {
            setText("");
            setGraphic(null);
          } else {
            setText(item);
            setGraphic(getTreeItem().getGraphic());
          }
        }

      };
      // Children이 없는 Cell을 css로 다루기 위해 'sub-tree-item' 클래스 부여
      cell.treeItemProperty().addListener((obs, oldTreeItem, newTreeItem) -> {
        cell.pseudoClassStateChanged(subElementPseudoClass,
          newTreeItem != null && newTreeItem.getChildren().size() == 0);
      });
      return cell;
    });
  }

  public ObservableList<TreeItem> getSideMenuTreeItem() {
    return sideMenuTreeItem;
  }

  public void setSideMenuTreeItem(ObservableList<TreeItem> sideMenuTreeItem) {
    this.sideMenuTreeItem = sideMenuTreeItem;
    refreshTreeData();
  }

  /**
   * sideMenuTreeItem을 통해 Treeview를 갱신
   */
  private void refreshTreeData() {
    TreeItem<String> root= new TreeItem<>();
    for (int i = 0; i < sideMenuTreeItem.size(); i++) {
      root.getChildren().add(sideMenuTreeItem.get(i));
    }
    root.setExpanded(true);
    treeView.setShowRoot(false);
    treeView.setRoot(root);
  }
}
