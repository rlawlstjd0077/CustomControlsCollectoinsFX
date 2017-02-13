package ui.screen.scenedefinition;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import ui.screen.viewmodel.ExampleModel;

import java.util.List;

/**
 * Created by GSD on 2017-01-11.
 */
public class SceneDefinitionViewModel {
  private final ObjectProperty<ObservableList<ExampleModel>> itemList = new SimpleObjectProperty<>();
  private final ObjectProperty<TableColumn<ExampleModel, String>> columnTitle = new SimpleObjectProperty<>();

  public SceneDefinitionViewModel(){
    ObservableList<ExampleModel> data = FXCollections.observableArrayList(
      new ExampleModel("Full Disk AMI(Scene)"),
      new ExampleModel("ELA AMI(Scene)"),
      new ExampleModel("RA AMI(Scene)")
    );
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

  //Test HTML 데이터
  private List<String> htmlData = FXCollections.observableArrayList(
    getClass().getResource("/mps/gk2amps/html/SceneDef.html").toExternalForm(),
    "http://www.naver.com",
    "http://www.google.com"
  );

  /**
   * index 에 해당하는 htmlData 리턴
   * @param index
   * @return
   */
  public String getPathFromPick(int index){
    return htmlData.get(index);
  }
}
