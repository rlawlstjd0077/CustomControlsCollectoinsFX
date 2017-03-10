package ui.screen.common;

import javafx.beans.property.StringProperty;

/**
 * Created by GSD on 2017-02-27.
 */
public class DataSearchTableRowViewModel {
  private StringProperty name;
  private StringProperty Date;

  public DataSearchTableRowViewModel(StringProperty name, StringProperty date) {
    this.name = name;
    Date = date;
  }

  public StringProperty nameProperty() {
    return name;
  }

  public StringProperty dateProperty() {
    return Date;
  }

  public String getName() {
    return name.get();
  }

  public String getDate() {
    return Date.get();
  }
}
