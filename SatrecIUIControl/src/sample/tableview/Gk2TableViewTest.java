package sample.tableview;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


/**
 * 생성자를 제외한 사용법은 TableView 와 동일함
 * Created by 신동찬
 */
public class Gk2TableViewTest extends Application {
  private ObservableList<ExampleModel> data = FXCollections.observableArrayList(
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
    new ExampleModel("y", "z @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
  );

  @Override
  public void start(Stage primaryStage) throws Exception {
    FlowPane pane = new FlowPane();
    pane.setPrefWidth(1000);
    pane.setPrefHeight(800);
    pane.setPadding(new Insets(10));

    Gk2TableView[] tableViews = new Gk2TableView[3];
    tableViews[0] = new Gk2TableView(Gk2TableView.TableStyle.BASIC);
    tableViews[1] = new Gk2TableView(Gk2TableView.TableStyle.DARK);
    tableViews[2] = new Gk2TableView(Gk2TableView.TableStyle.TOPCOLOR);
    for (int i = 0; i < 3; i++) {
      tableViews[i].setMinWidth(200);
      tableViews[i].setMaxWidth(400);

      TableColumn<ExampleModel, String> firstColumn = new TableColumn<>("Column 1");
      firstColumn.setMinWidth(200);
      firstColumn.setCellValueFactory(cellData -> cellData.getValue().firstProperty());

      TableColumn<ExampleModel, String> secondColumn = new TableColumn<>("Column 2");
      secondColumn.setMinWidth(300);
      secondColumn.setCellValueFactory(cellData -> cellData.getValue().secondProperty());

      tableViews[i].getColumns().addAll(firstColumn, secondColumn);
      tableViews[i].setItems(data);

      pane.getChildren().add(tableViews[i]);
    }

    Scene scene = new Scene(pane);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
  }

//  @Test
//  @Ignore
//  public void testGk2TableView() {
//    launch();
//  }

}
