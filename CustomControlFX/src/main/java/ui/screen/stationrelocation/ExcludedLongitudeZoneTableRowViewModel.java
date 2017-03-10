package ui.screen.stationrelocation;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;

/**
 * Created by GSD on 2017-02-22.
 */
public class ExcludedLongitudeZoneTableRowViewModel {
  private IntegerProperty longitude;
  private DoubleProperty boxRange;

  public ExcludedLongitudeZoneTableRowViewModel(IntegerProperty longitude, DoubleProperty boxRange) {
    this.longitude = longitude;
    this.boxRange = boxRange;
  }

  public IntegerProperty longitudeProperty() {
    return longitude;
  }

  public DoubleProperty boxRangeProperty() {
    return boxRange;
  }
}
