package ui.screen.common;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by GSD on 2017-02-16.
 */
public class GroundStationSelectionTableRowViewModel {
  private IntegerProperty index;
  private IntegerProperty id;
  private StringProperty name;
  private DoubleProperty logitude;
  private DoubleProperty latitude;
  private DoubleProperty height;
  private DoubleProperty elevation;
  private DoubleProperty beamWidth;

  public GroundStationSelectionTableRowViewModel(IntegerProperty index, IntegerProperty id, StringProperty name,
                                                 DoubleProperty logitude, DoubleProperty latitude, DoubleProperty height,
                                                 DoubleProperty elevation, DoubleProperty beamWidth) {
    this.index = index;
    this.id = id;
    this.name = name;
    this.logitude = logitude;
    this.latitude = latitude;
    this.height = height;
    this.elevation = elevation;
    this.beamWidth = beamWidth;
  }

  public IntegerProperty indexProperty() {
    return index;
  }

  public IntegerProperty idProperty() {
    return id;
  }

  public StringProperty nameProperty() {
    return name;
  }

  public DoubleProperty logitudeProperty() {
    return logitude;
  }
  public DoubleProperty latitudeProperty() {
    return latitude;
  }
  public DoubleProperty heightProperty() {
    return height;
  }

  public DoubleProperty elevationProperty() {
    return elevation;
  }

  public DoubleProperty beamWidthProperty() {
    return beamWidth;
  }
}
