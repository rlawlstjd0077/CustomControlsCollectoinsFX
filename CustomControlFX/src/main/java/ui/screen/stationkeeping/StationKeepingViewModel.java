package ui.screen.stationkeeping;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ui.common.ManeuverListTableRowViewModel;
import ui.common.OutputListTableRowViewModel;

/**
 * Created by GSD on 2017-01-19.
 */
public class StationKeepingViewModel {
  final public ObservableList<OutputListTableRowViewModel> outputListTableRowViewModelList = FXCollections.observableArrayList(
    new OutputListTableRowViewModel(new SimpleStringProperty("Report"), false,
      new SimpleStringProperty("OPReport_20180407000000_1464_txt"), new SimpleStringProperty("C:/Users/GSD/Desktop/a.txt")),
    new OutputListTableRowViewModel(new SimpleStringProperty("Orbit Prediction Data (Cartesian)"), false,
      new SimpleStringProperty(""), new SimpleStringProperty(null)),
    new OutputListTableRowViewModel(new SimpleStringProperty("Orbit Prediction Data(keplerian)"), false,
      new SimpleStringProperty("prediction_kep_20180407000000.dat"), new SimpleStringProperty("C:/Users/GSD/Desktop/b.txt")),
    new OutputListTableRowViewModel(new SimpleStringProperty("Ground Track Data"), false,
      new SimpleStringProperty("GTD_20180407000000_1464.dat"), new SimpleStringProperty("C:/Users/GSD/Desktop/c.txt")),
    new OutputListTableRowViewModel(new SimpleStringProperty("TLE Data"), false,
      new SimpleStringProperty(""), new SimpleStringProperty(null)),
    new OutputListTableRowViewModel(new SimpleStringProperty("Sun Ephemeris"), false,
      new SimpleStringProperty(""), new SimpleStringProperty(null)),
    new OutputListTableRowViewModel(new SimpleStringProperty("Moon Ephemeris"), false,
      new SimpleStringProperty(""), new SimpleStringProperty(null)),
    new OutputListTableRowViewModel(new SimpleStringProperty("CCSDS"), false,
      new SimpleStringProperty("OPReport_20180407000000_1464.opm"), new SimpleStringProperty("C:/Users/GSD/Desktop/c.txt"))
  );
  final public ObservableList<ManeuverListTableRowViewModel> maneuverListTableRowViewModelList = FXCollections.observableArrayList(
    new ManeuverListTableRowViewModel(new SimpleDoubleProperty(201812140432435.624),
      new SimpleStringProperty("NS_STATION_KEEPING"), new SimpleStringProperty("TS_SOUTH"), new SimpleDoubleProperty(0.0000000),
      new SimpleDoubleProperty(-0.9456785), new SimpleDoubleProperty(0.0000000)),
    new ManeuverListTableRowViewModel(new SimpleDoubleProperty(201812140432435.624),
      new SimpleStringProperty("EW_STATION_KEEPING"), new SimpleStringProperty("TS_EAST"), new SimpleDoubleProperty(0.0128323),
      new SimpleDoubleProperty(-0.9456785), new SimpleDoubleProperty(0.0000000)),
    new ManeuverListTableRowViewModel(new SimpleDoubleProperty(201812140432435.624),
      new SimpleStringProperty("NS_STATION_KEEPING"), new SimpleStringProperty("TS_SOUTH"), new SimpleDoubleProperty(-0.1125412),
      new SimpleDoubleProperty(-0.9456785), new SimpleDoubleProperty(0.0000000))
    );
  public StationKeepingViewModel() {

  }
}
