package ui.screen.leopeventprediction;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ui.common.GroundStationSelectionTableRowViewModel;

/**
 * Created by GSD on 2017-03-02.
 */
public class LEOPEventPredictionVieModel {
  public ObservableList<GroundStationSelectionTableRowViewModel> groundStationSelectionTableRowViewModelList = FXCollections.observableArrayList(
    new GroundStationSelectionTableRowViewModel(new SimpleIntegerProperty(0), new SimpleIntegerProperty(4441), new SimpleStringProperty("TAEJON-S")
      , new SimpleDoubleProperty(128.4), new SimpleDoubleProperty(36.4), new SimpleDoubleProperty(36.4), new SimpleDoubleProperty(36.4), new SimpleDoubleProperty(36.4)),
    new GroundStationSelectionTableRowViewModel(new SimpleIntegerProperty(1), new SimpleIntegerProperty(4441), new SimpleStringProperty("TAEJON-S")
      , new SimpleDoubleProperty(128.4), new SimpleDoubleProperty(36.4), new SimpleDoubleProperty(36.4), new SimpleDoubleProperty(36.4), new SimpleDoubleProperty(36.4)),
    new GroundStationSelectionTableRowViewModel(new SimpleIntegerProperty(2), new SimpleIntegerProperty(4441), new SimpleStringProperty("TAEJON-S")
      , new SimpleDoubleProperty(128.4), new SimpleDoubleProperty(36.4), new SimpleDoubleProperty(36.4), new SimpleDoubleProperty(36.4), new SimpleDoubleProperty(36.4)),
    new GroundStationSelectionTableRowViewModel(new SimpleIntegerProperty(3), new SimpleIntegerProperty(4441), new SimpleStringProperty("TAEJON-S")
      , new SimpleDoubleProperty(128.4), new SimpleDoubleProperty(36.4), new SimpleDoubleProperty(36.4), new SimpleDoubleProperty(36.4), new SimpleDoubleProperty(36.4))
  );
}
