package sample.tableview;

import javafx.beans.property.SimpleStringProperty;

/**
 * 테스트에 쓰일 TableView의 모델
 */
public class ExampleModel {
  private SimpleStringProperty first;
  private SimpleStringProperty second;

  public ExampleModel(String first, String second) {
    this.first = new SimpleStringProperty(first);
    this.second = new SimpleStringProperty(second);
  }

  public String getFirst() {
    return first.get();
  }

  public void setFirst(String first) {
    this.first.set(first);
  }

  public SimpleStringProperty firstProperty() {
    return first;
  }

  public String getSecond() {
    return second.get();
  }

  public void setSecond(String second) {
    this.second.set(second);
  }

  public SimpleStringProperty secondProperty() {
    return second;
  }
}
