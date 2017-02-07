package sample.ui.control.tableview;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * TableView에 접근하려면 getter를 통해 접근해야 하는것과 생성자를 제외하면
 * TableView와 사용법이 동일함.
 */
public class Gk2TableViewGroupHeaderTest extends Application {
  ObservableList<ExampleModel> data = FXCollections.observableArrayList(
    new ExampleModel("a", "b"),
    new ExampleModel("c", "d"),
    new ExampleModel("e", "f"),
    new ExampleModel("g", "h"),
    new ExampleModel("i", "j"),
    new ExampleModel("g", "h"),
    new ExampleModel("i", "j"),
    new ExampleModel("k", "l"),
    new ExampleModel("m", "n"),
    new ExampleModel("o", "p"),
    new ExampleModel("q", "r"),
    new ExampleModel("s", "t"),
    new ExampleModel("u", "v"),
    new ExampleModel("w", "x"),
    new ExampleModel("y", "z @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@22")
  );

  @Override
  public void start(Stage primaryStage) throws Exception {
    FlowPane pane = new FlowPane();
    pane.setPrefWidth(1000);
    pane.setPrefHeight(800);
    pane.setPadding(new Insets(10));

    Gk2TableViewGroupHeader[] tableViews = new Gk2TableViewGroupHeader[3];
    tableViews[0] = new Gk2TableViewGroupHeader("Title", Gk2TableViewGroupHeader.HeaderTableStyle.BASIC);
    tableViews[1] = new Gk2TableViewGroupHeader("Title", Gk2TableViewGroupHeader.HeaderTableStyle.DARK);

    for (int i = 0; i < 2; i++) {
      tableViews[i].setMinWidth(200);
      tableViews[i].setMaxWidth(400);

      TableColumn<ExampleModel, String> firstColumn = new TableColumn<>("Column 1");
      firstColumn.setMinWidth(200);
      firstColumn.setCellValueFactory(cellData -> cellData.getValue().firstProperty());

      TableColumn<ExampleModel, String> secondColumn = new TableColumn<>("Column 2");
      secondColumn.setMinWidth(300);
      secondColumn.setCellValueFactory(cellData -> cellData.getValue().secondProperty());

      tableViews[i].getTableView().getColumns().addAll(firstColumn, secondColumn);
      tableViews[i].getTableView().setItems(data);

      pane.getChildren().add(tableViews[i]);
    }
    Scene scene = new Scene(pane);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
  }

//  @Test
//  @Ignore
//  public void testGk2TableViewGroupHeader() {
//    launch();
//  }

}
