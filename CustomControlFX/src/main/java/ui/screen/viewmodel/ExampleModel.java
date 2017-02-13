package ui.screen.viewmodel;

import javafx.beans.property.SimpleStringProperty;

public class ExampleModel {
  private SimpleStringProperty title;

  public ExampleModel(String title){
    this.title = new SimpleStringProperty(title);
  }

  public String getTitle() {
    return title.get();
  }

  public void setTitle(String first) {
    this.title.set(first);
  }

  public SimpleStringProperty titleProperty() {
    return title;
  }
}
