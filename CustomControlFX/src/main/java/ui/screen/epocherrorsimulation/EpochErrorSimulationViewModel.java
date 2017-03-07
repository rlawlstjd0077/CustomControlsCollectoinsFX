package ui.screen.epocherrorsimulation;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by GSD on 2017-03-02.
 */
public class EpochErrorSimulationViewModel {
  public ObservableList<SelectableFilesTableRowViewModel> selectableFilesTableRowModelList = FXCollections.observableArrayList(
    new SelectableFilesTableRowViewModel(new SimpleStringProperty("Test_Data1"), new SimpleStringProperty("TAEJSON-S"),
      new SimpleDoubleProperty(265), new SimpleDoubleProperty(265), new SimpleDoubleProperty(265)),
    new SelectableFilesTableRowViewModel(new SimpleStringProperty("Test_Data1"), new SimpleStringProperty("Perth-S"),
      new SimpleDoubleProperty(265), new SimpleDoubleProperty(265), new SimpleDoubleProperty(265)),
    new SelectableFilesTableRowViewModel(new SimpleStringProperty("Test_Data1"), new SimpleStringProperty("YONG-IN"),
      new SimpleDoubleProperty(265), new SimpleDoubleProperty(265), new SimpleDoubleProperty(265))
  );
  public ObservableList<DeterminationStationDataTableRowViewModel> determinationStationDataTableRowViewModelList = FXCollections.observableArrayList(
    new DeterminationStationDataTableRowViewModel(new SimpleStringProperty("TAEJON-S"),false, false,false,false,false,false),
    new DeterminationStationDataTableRowViewModel(new SimpleStringProperty("Perth"),false, false,false,false,false,false),
    new DeterminationStationDataTableRowViewModel(new SimpleStringProperty("YONG-IN"),false, false,false,false,false,false)
  );

  public ObservableList<MeasurementDataTableRowViewModel> measurementDataTableRowViewModelList = FXCollections.observableArrayList(
    new MeasurementDataTableRowViewModel(new SimpleStringProperty("TAEJSON-S"),
      new SimpleDoubleProperty(265), new SimpleDoubleProperty(265), new SimpleDoubleProperty(265)),
    new MeasurementDataTableRowViewModel(new SimpleStringProperty("Perth-S"),
      new SimpleDoubleProperty(265), new SimpleDoubleProperty(265), new SimpleDoubleProperty(265)),
    new MeasurementDataTableRowViewModel(new SimpleStringProperty("YONG-IN"),
      new SimpleDoubleProperty(265), new SimpleDoubleProperty(265), new SimpleDoubleProperty(265))
  );
}
