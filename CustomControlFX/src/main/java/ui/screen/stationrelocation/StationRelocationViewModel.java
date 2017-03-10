package ui.screen.stationrelocation;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by GSD on 2017-02-21.
 */
public class StationRelocationViewModel {
  ObservableList<ExcludedLongitudeZoneTableRowViewModel> excludedLongitudeZoneTableRowViewModelList = FXCollections.observableArrayList(
      new ExcludedLongitudeZoneTableRowViewModel(new SimpleIntegerProperty(108), new SimpleDoubleProperty(0.5))
  );
}
