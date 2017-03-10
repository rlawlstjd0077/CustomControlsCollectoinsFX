package ui.screen.orbitdeterminationBLSE;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by GSD on 2017-02-28.
 */
public class MeasurementDataTableRowViewModel {
  private StringProperty station;
  private DoubleProperty range;
  private DoubleProperty azimuth;
  private DoubleProperty elevation;

  public MeasurementDataTableRowViewModel(StringProperty station, DoubleProperty range,
                                          DoubleProperty azimuth, DoubleProperty elevation) {
    this.station = station;
    this.range = range;
    this.azimuth = azimuth;
    this.elevation = elevation;
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
