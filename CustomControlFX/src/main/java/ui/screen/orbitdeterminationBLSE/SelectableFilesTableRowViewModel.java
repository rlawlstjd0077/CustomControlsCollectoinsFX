package ui.screen.orbitdeterminationBLSE;

import javafx.beans.property.StringProperty;

/**
 * Created by GSD on 2017-02-20.
 */
public class SelectableFilesTableRowViewModel {
  private StringProperty name;

  public SelectableFilesTableRowViewModel(StringProperty name) {
    this.name = name;
  }

  public StringProperty nameProperty() {
    return name;
  }
}
