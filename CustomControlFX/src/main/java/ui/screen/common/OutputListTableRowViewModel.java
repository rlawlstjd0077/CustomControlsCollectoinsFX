package ui.screen.common;

import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;

/**
 * Created by GSD on 2017-01-18.
 */
public class OutputListTableRowViewModel {
  private StringProperty item;
  private CheckBox generation;
  private StringProperty fileName;
  private StringProperty fileLocation;

  public OutputListTableRowViewModel(StringProperty item, boolean generation,
                                     StringProperty fileName, StringProperty fileLocation) {
    this.item = item;
    this.fileName = fileName;
    this.fileLocation = fileLocation;
    this.generation = new CheckBox();
    this.generation.setSelected(generation);

    if(item.getValue().contains("Antenna Pointing Data")){
      this.generation.setDisable(true);
    }
  }

  public CheckBox generationProperty() {
    return generation;
  }

  public StringProperty itemProperty() {
    return item;
  }

  public StringProperty fileNameProperty() {
    return fileName;
  }

  public StringProperty fileLocationProperty() {
    return fileLocation;
  }
}
