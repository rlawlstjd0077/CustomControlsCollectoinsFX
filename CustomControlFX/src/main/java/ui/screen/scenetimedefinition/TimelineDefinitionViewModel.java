package ui.screen.scenetimedefinition;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import ui.screen.viewmodel.ExampleModel;

import java.util.List;

/**
 * Created by JinSeong on 2017-01-12.
 */
public class TimelineDefinitionViewModel {
  private final ObjectProperty<ObservableList<ExampleModel>> itemList = new SimpleObjectProperty<>();
  private final ObjectProperty<TableColumn<ExampleModel, String>> columnTitle = new SimpleObjectProperty<>();

  public TimelineDefinitionViewModel(){
    itemListProperty().set(data);

    TableColumn<ExampleModel, String> titleColumn = new TableColumn<>("Scene Definition List");
    titleColumn.setPrefWidth(250);
    titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
    columnTitleProperty().set(titleColumn);
  }

  public ObjectProperty<ObservableList<ExampleModel>> itemListProperty(){
    return itemList;
  }

  public ObjectProperty<TableColumn<ExampleModel, String>> columnTitleProperty(){
    return columnTitle;
  }

  //Test List Item 데이터
  private ObservableList<ExampleModel> data = FXCollections.observableArrayList(
    new ExampleModel("AMI Ops"),
    new ExampleModel("FD"),
    new ExampleModel("ELA")
  );

  //Test HTML 데이터
  private List<String> htmlData = FXCollections.observableArrayList(
    getClass().getResource("/mps/gk2amps/html/TimelineDef.html").toExternalForm(),
    "http://www.naver.com",
    "http://www.google.com"
  );

  public String getPathFromPick(int index){
    return htmlData.get(index);
  }
}
