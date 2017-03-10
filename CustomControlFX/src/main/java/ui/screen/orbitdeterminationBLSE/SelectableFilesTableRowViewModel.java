package ui.screen.orbitdeterminationBLSE;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by GSD on 2017-02-20.
 */
public class SelectableFilesTableRowViewModel {
  private StringProperty name;
  private StringProperty station;
  private DoubleProperty range;
  private DoubleProperty azimuth;
  private DoubleProperty elevation;

  public SelectableFilesTableRowViewModel(StringProperty name, StringProperty station,
                                      DoubleProperty range, DoubleProperty azimuth, DoubleProperty elevation) {
    this.name = name;
    this.station = station;
    this.range = range;
    this.azimuth = azimuth;
    this.elevation = elevation;
  }


  public StringProperty nameProperty() {
    return name;
  }

  public StringProperty stationProperty() {
    return station;
  }

  public DoubleProperty rangeProperty() {
    return range;
  }

  public DoubleProperty azimuthProperty() {
    return azimuth;
  }

  public DoubleProperty elevationProperty() {
    return elevation;
  }
}
