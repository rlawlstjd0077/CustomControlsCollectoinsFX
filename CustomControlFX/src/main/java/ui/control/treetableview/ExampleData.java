package ui.control.treetableview;

import javafx.beans.property.SimpleStringProperty;

/**
 * 테스트에 쓰일 ExampleData
 * Created by JinSeong on 2017-01-11.
 */
public class ExampleData {

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

  public ExampleData(String item, String value) {
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
