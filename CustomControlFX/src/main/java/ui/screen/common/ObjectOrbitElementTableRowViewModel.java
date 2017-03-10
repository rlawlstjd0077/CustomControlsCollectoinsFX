package ui.screen.common;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by GSD on 2017-02-13.
 */
public class ObjectOrbitElementTableRowViewModel {
  private IntegerProperty index;
  private StringProperty name;
  private StringProperty dataType;
  private StringProperty epoch;
  private StringProperty semi_majorAxis;
  private StringProperty eccentricity;

  public ObjectOrbitElementTableRowViewModel(IntegerProperty index, StringProperty name, StringProperty dataType, StringProperty epoch,
                                             StringProperty semi_majorAxis, StringProperty eccentricity) {
    this.index = index;
    this.name = name;
    this.dataType = dataType;
    this.epoch = epoch;
    this.semi_majorAxis = semi_majorAxis;
    this.eccentricity = eccentricity;
  }

  public IntegerProperty indexProperty(){return index;}

  public StringProperty nameProperty() {
    return name;
  }

  public StringProperty dataTypeProperty() {
    return dataType;
  }

  public StringProperty epochProperty() {
    return epoch;
  }

  public StringProperty semi_majorAxisProperty() {
    return semi_majorAxis;
  }

  public StringProperty eccentricityProperty() {
    return eccentricity;
  }
}
