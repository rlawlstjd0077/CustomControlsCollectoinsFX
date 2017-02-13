package ui.control.treetableview;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by JinSeong on 2017-01-06.
 */
public class Gk2TreeTableView extends TreeTableView {


    List<ExampleData> datas1 = Arrays.<ExampleData> asList(new ExampleData("Scene Center Longtitude", "125.0351"),
      new ExampleData("Scene Center Longtitude", "35.2125"));
    List<ExampleData> datas2 = Arrays.<ExampleData> asList(new ExampleData("Scene Center Longtitude", "125.0351"),
      new ExampleData("Scene Center Longtitude", "35.2125"));
    List<ExampleData> datas3 = Arrays.<ExampleData> asList(new ExampleData("Scene Center Longtitude", "125.0351"),
      new ExampleData("Scene Center Longtitude", "35.2125"));

    final TreeItem<ExampleData> emptyRoot = new TreeItem<>(new ExampleData("Number of Timeline Swaths", "6"));
    final TreeItem<ExampleData> root1 = new TreeItem<>(new ExampleData("Scene #1", ""));
    final TreeItem<ExampleData> root2 = new TreeItem<>(new ExampleData("Scene #2", ""));
    final TreeItem<ExampleData> root3 = new TreeItem<>(new ExampleData("Scene #3", ""));

  public Gk2TreeTableView(){
    getStylesheets().add("/commons/ui/control/treetableview/treetableview.css");
    getStyleClass().add("gk2-tree-table-view");
    TreeItem<ExampleData> dummy = new TreeItem<>();
    root1.setExpanded(true);
    root2.setExpanded(true);
    root3.setExpanded(true);

    datas1.stream().forEach((data) -> {
      root1.getChildren().addAll(new TreeItem<ExampleData>(data));
    });
    datas2.stream().forEach((data) -> {
      root2.getChildren().addAll(new TreeItem<ExampleData>(data));
    });
    datas3.stream().forEach((data) -> {
      root3.getChildren().addAll(new TreeItem<ExampleData>(data));
    });

    TreeTableColumn<ExampleData, String> testColumn =
      new TreeTableColumn<>("Item");
    testColumn.setPrefWidth(200);
    testColumn.setCellValueFactory((
      TreeTableColumn.CellDataFeatures<ExampleData, String> param) ->
      new ReadOnlyStringWrapper(param.getValue().getValue().getItem())
    );
    TreeTableColumn<ExampleData, String> tmpColumn =
      new TreeTableColumn<>("Value");
    tmpColumn.setPrefWidth(100);
    tmpColumn.setCellValueFactory(
      (TreeTableColumn.CellDataFeatures<ExampleData, String> param) ->
        new ReadOnlyStringWrapper(param.getValue().getValue().getValue())
    );

    this.getColumns().setAll(testColumn, tmpColumn);
    dummy.getChildren().addAll(emptyRoot,root1, root2, root3);
    this.setRoot(dummy);
    this.setShowRoot(false);
  }
}
