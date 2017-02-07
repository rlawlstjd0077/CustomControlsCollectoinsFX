package sample.ui.control.treeview;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * Created by GSD on 2017-01-06.
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


    TreeItem<String> root = new Gk2DirTreeItem(rootValue);
    TreeItem<String> subRoot = new Gk2DirTreeItem(subRootValue);
    root.setExpanded(true);
    subRoot.setExpanded(true);

    for (String itemString : rootItem) {
      subRoot.getChildren().add(new Gk2FileTreeItem(itemString));
    }
    root.getChildren().add(subRoot);
    this.setCellFactory(treeView -> new Gk2TreeCell());
//    this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
//      @Override
//      public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue, TreeItem<String> newValue) {
//        if(oldValue != null) {
//          TreeItem<String> exitedItem = (TreeItem<String>) oldValue;
//          System.out.println("Selected Text : " + exitedItem.getValue());
//          ImageView oldImage = exitedItem.isLeaf() ? new ImageView(normalChildIcon) : new ImageView(normalIcon);
//          exitedItem.setGraphic(oldImage);
//        }
//        TreeItem<String> selectedItem = (TreeItem<String>) newValue;
//        ImageView newImage = selectedItem.isLeaf() ? new ImageView(focusedChildIcon) : new ImageView(focusedIcon);
//        selectedItem.setGraphic(newImage);
//        System.out.println("Selected Text : " + selectedItem.isLeaf());
//      }
//    });
    this.setRoot(root);
    this.getSelectionModel().select(0);
//  }
  }
}
