package ui.control.treeview;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 *
 * Created by JinSeong on 2017-01-06.
 */
public class Gk2TreeView extends TreeView<String> {
  private String rootValue = "Root";
  private String subRootValue = "Sub Root";
  private String DEFAULT_STYLE_CLASS = "gk2-tree-view";
  private String CSS_PATH = "/commons/ui/control/treeview/treeview.css";
  private String[] rootItem = {"1", "2", "3"};


  public Gk2TreeView() {
    getStylesheets().add(CSS_PATH);
    getStyleClass().add(DEFAULT_STYLE_CLASS);
    setPrefWidth(200);
    setPrefHeight(200);

    TreeItem<String> root = new Gk2RootTreeItem(rootValue);
    TreeItem<String> subRoot = new Gk2RootTreeItem(subRootValue);
    root.setExpanded(true);
    subRoot.setExpanded(true);

    for (String itemString : rootItem) {
      subRoot.getChildren().add(new Gk2ChildTreeItem(itemString));
    }
    root.getChildren().add(subRoot);

    //TreeCell 인스턴스 생성
    this.setCellFactory(treeView -> new Gk2TreeCell());
    this.setRoot(root);

    // 처음 실행 시 root의 focus를 통해 이상 현상 해결
    this.getSelectionModel().select(0);
  }
}
