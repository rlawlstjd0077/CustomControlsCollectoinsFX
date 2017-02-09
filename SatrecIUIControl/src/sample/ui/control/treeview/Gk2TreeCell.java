package sample.ui.control.treeview;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeCell;
import javafx.scene.image.ImageView;

/**
 * Created by GSD on 2017-01-09.
 */
public class Gk2TreeCell extends TreeCell<String> {

  public Gk2TreeCell() {
//    setOnMouseEntered(new EventHandler<MouseEvent>() {
//      @Override
//      public void handle(MouseEvent event) {
//        if(!isFocused()) {
//          Gk2TreeItem item = (Gk2TreeItem)getTreeItem();
//          setGraphic(new ImageView(item.getFocusedIcon()));
//        }
//      }
//    });
//    setOnMouseExited(new EventHandler<MouseEvent>() {
//      @Override
//      public void handle(MouseEvent event) {
//        if(!isFocused()) {
//          Gk2TreeItem item = (Gk2TreeItem)getTreeItem();
//          setGraphic(new ImageView(item.getNormalIcon()));
//        }
//      }
//    });
    hoverProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (getItem() != null && !isFocused()) {
          if (newValue) {
            Gk2TreeItem item = (Gk2TreeItem) getTreeItem();
            setGraphic(new ImageView(item.getFocusedIcon()));
          } else {
            Gk2TreeItem item = (Gk2TreeItem) getTreeItem();
            setGraphic(new ImageView(item.getNormalIcon()));
          }
        }
      }
    });
    focusedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (getItem() != null ) {
          if (newValue) {
            Gk2TreeItem item = (Gk2TreeItem) getTreeItem();
            setGraphic(new ImageView(item.getFocusedIcon()));
          }else{
            Gk2TreeItem item = (Gk2TreeItem) getTreeItem();
            setGraphic(new ImageView(item.getNormalIcon()));
          }
        }
      }
    });
//    setOnMouseExited(new EventHandler<MouseEvent>() {
//      @Override
//      public void handle(MouseEvent event) {
//        Gk2TreeItem item = (Gk2TreeItem)getTreeItem();
//        setGraphic(new ImageView(item.getNormalIcon()));
//      }
//    });

    selectedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if (newValue) {
          Gk2TreeItem item = (Gk2TreeItem) getTreeItem();
          setGraphic(new ImageView(item.getFocusedIcon()));
        }
      }
    });
  }

  @Override
  protected void updateItem(String item, boolean empty) {
    super.updateItem(item, empty);
    if(!empty){
      setText(item);
      Gk2TreeItem treeItem = (Gk2TreeItem)getTreeItem();
      setGraphic(new ImageView(treeItem.getNormalIcon()));
    }else{
      setText(null);
      setGraphic(null);
    }
  }
}