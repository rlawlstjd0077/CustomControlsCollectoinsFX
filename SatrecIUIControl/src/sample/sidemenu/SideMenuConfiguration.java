package sample.sidemenu;

import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Cofiguration 화면에서 쓰이는 Side Menu
 */
public class SideMenuConfiguration implements Initializable {
  private final float HEIGHT_UNIT = (float) 26.35;
  @FXML
  AnchorPane sideMenu;
  @FXML
  TreeView<String> treeView;
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
    sideMenu.getStylesheets().add("/commons/ui/control/sidemenu/SideMenuConfiguration.css");
    sideMenu.getStyleClass().add("sidemenu");

    //클릭될때마다 TreeView의 Height 갱신
    treeView.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        try {
          sideMenu.setPrefHeight(treeView.getExpandedItemCount() * HEIGHT_UNIT);
        } catch (Exception e) {
        }
      }
    });

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

    //마우스 스크롤을 사용할수 없도록 막음.
    sideMenu.addEventFilter(ScrollEvent.ANY, new EventHandler<ScrollEvent>() {
      @Override
      public void handle(ScrollEvent event) {
        event.consume();
      }
    });

  }

  public ObservableList<TreeItem> getSideMenuTreeItem() {
    return sideMenuTreeItem;
  }

  public void setSideMenuTreeItem(ObservableList<TreeItem> sideMenuTreeItem) {
    this.sideMenuTreeItem = sideMenuTreeItem;
    refreshTreeData();
  }

  private void refreshTreeData() {
    TreeItem<String> root = new TreeItem<>();
    for (int i = 0; i < sideMenuTreeItem.size(); i++) {
      root.getChildren().add(sideMenuTreeItem.get(i));
    }
    root.setExpanded(true);
    treeView.setShowRoot(false);
    treeView.setRoot(root);
    sideMenu.setPrefHeight(sideMenuTreeItem.size() * HEIGHT_UNIT);
  }
}
