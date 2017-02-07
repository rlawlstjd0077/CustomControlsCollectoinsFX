package sample.ui.control.treetableview;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by GSD on 2017-01-06.
 */
public class Gk2TreeTableView extends TreeTableView {
  List<Data> datas1 = Arrays.<Data> asList(new Data("Scene Center Longtitude", "125.0351"),
    new Data("Scene Center Longtitude", "35.2125"));
  List<Data> datas2 = Arrays.<Data> asList(new Data("Scene Center Longtitude", "125.0351"),
    new Data("Scene Center Longtitude", "35.2125"));
  List<Data> datas3 = Arrays.<Data> asList(new Data("Scene Center Longtitude", "125.0351"),
    new Data("Scene Center Longtitude", "35.2125"));

  final TreeItem<Data> emptyRoot = new TreeItem<>(new Data("Number of Timeline Swaths", "6"));
  final TreeItem<Data> root1 = new TreeItem<>(new Data("Scene #1", ""));
  final TreeItem<Data> root2 = new TreeItem<>(new Data("Scene #2", ""));
  final TreeItem<Data> root3 = new TreeItem<>(new Data("Scene #3", ""));

  public Gk2TreeTableView(){
    getStylesheets().add("/commons/ui/control/treetableview/treetableview.css");
    getStyleClass().add("gk2-tree-table-view");
    TreeItem<Data> dummy = new TreeItem<>();
    root1.setExpanded(true);
    root2.setExpanded(true);
    root3.setExpanded(true);
    datas1.stream().forEach((data) -> {
      root1.getChildren().addAll(new TreeItem<Data>(data));
    });
    datas2.stream().forEach((data) -> {
      root2.getChildren().addAll(new TreeItem<Data>(data));
    });
    datas3.stream().forEach((data) -> {
      root3.getChildren().addAll(new TreeItem<Data>(data));
    });

    TreeTableColumn<Data, String> testColumn =
      new TreeTableColumn<>("Item");
    testColumn.setPrefWidth(200);
    testColumn.setCellValueFactory((
      TreeTableColumn.CellDataFeatures<Data, String> param) ->
      new ReadOnlyStringWrapper(param.getValue().getValue().getItem())
    );
    TreeTableColumn<Data, String> tmpColumn =
      new TreeTableColumn<>("Value");
    tmpColumn.setPrefWidth(100);
    tmpColumn.setCellValueFactory(
      (TreeTableColumn.CellDataFeatures<Data, String> param) ->
        new ReadOnlyStringWrapper(param.getValue().getValue().getValue())
    );
    this.getColumns().setAll(testColumn, tmpColumn);
    dummy.getChildren().addAll(emptyRoot,root1, root2, root3);
    this.setRoot(dummy);
    this.setShowRoot(false);

  }
  public class Data {

    private SimpleStringProperty item;
    private SimpleStringProperty value;

    public SimpleStringProperty itemProperty() {
      if (item == null) {
        item = new SimpleStringProperty(this, "item");
      }
      return item;
    }

    public SimpleStringProperty valueProperty() {
      if (value == null) {
        value = new SimpleStringProperty(this, "value");
      }
      return value;
    }

    private Data(String item, String value) {
      this.item = new SimpleStringProperty(item);
      this.value = new SimpleStringProperty(value);
    }

    public String getItem() {
      return item.get();
    }

    public void setItem(String fName) {
      item.set(fName);
    }

    public String getValue() {
      return value.get();
    }

    public void setValue(String fName) {
      value.set(fName);
    }
  }

}
