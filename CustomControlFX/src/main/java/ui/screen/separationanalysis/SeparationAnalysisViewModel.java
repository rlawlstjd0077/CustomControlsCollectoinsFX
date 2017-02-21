package ui.screen.separationanalysis;

import fds.ui.common.ObjectOrbitElementTableRowViewModel;
import fds.ui.common.OutputListTableRowViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by GSD on 2017-02-08.
 */
public class SeparationAnalysisViewModel {
  public ObservableList<OutputListTableRowViewModel> outputListTableRowViewModelList;
  public ObservableList<ObjectOrbitElementTableRowViewModel> objectOrbitElementTableRowViewModelList =FXCollections.observableArrayList(
    new ObjectOrbitElementTableRowViewModel(new SimpleStringProperty("201812140432435.624"),
      new SimpleStringProperty("Epoch Stack Data"),new SimpleStringProperty( "2015-01-01 00:00:00"),
      new SimpleStringProperty(null), new SimpleStringProperty(null)),
    new ObjectOrbitElementTableRowViewModel(new SimpleStringProperty("201812140432435.624"),
      new SimpleStringProperty("Orbit Sample Data"),new SimpleStringProperty( "2015-01-01 00:00:00"),
      new SimpleStringProperty(null), new SimpleStringProperty(null)),
    new ObjectOrbitElementTableRowViewModel(new SimpleStringProperty("201812140432435.624"),
      new SimpleStringProperty("TLE Data"),new SimpleStringProperty( "2015-01-01 00:00:00"),
      new SimpleStringProperty(null), new SimpleStringProperty(null))
  );
}
