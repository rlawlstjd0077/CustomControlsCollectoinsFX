package ui.common;

import javafx.beans.property.StringProperty;

/**
 * Created by GSD on 2017-01-18.
 */
public class OutputListTableRowViewModel {
  private StringProperty item;
  private FDSCheckBox generation;
  private StringProperty fileName;
  private StringProperty fileLication;

  public OutputListTableRowViewModel(StringProperty item, boolean generation,
                                     StringProperty fileName, StringProperty fileLication) {
    this.item = item;
    this.fileName = fileName;
    this.fileLication = fileLication;
    this.generation = new FDSCheckBox();
    this.generation.setSelected(generation);

    if(item.getValue().contains("Antenna Pointing Data")){
      this.generation.setDisable(true);
    }
  }

  public FDSCheckBox generationProperty() {
    return generation;
  }


  public StringProperty itemProperty() {
    return item;
  }

  public StringProperty fileNameProperty() {
    return fileName;
  }

  public StringProperty fileLocationProperty() {
    return fileLication;
  }
}
