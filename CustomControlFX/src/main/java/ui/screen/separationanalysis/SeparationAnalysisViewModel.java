package ui.screen.separationanalysis;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ui.screen.common.ObjectOrbitElementTableRowViewModel;
import ui.screen.common.OutputListTableRowViewModel;

/**
 * Created by GSD on 2017-02-08.
 */
public class SeparationAnalysisViewModel {
  public ObservableList<OutputListTableRowViewModel> outputListTableRowViewModelList;
  public ObservableList<ObjectOrbitElementTableRowViewModel> objectOrbitElementTopTableRowViewModelList =FXCollections.observableArrayList(
    new ObjectOrbitElementTableRowViewModel(new SimpleIntegerProperty(0),new SimpleStringProperty("201812140432435.624"),
      new SimpleStringProperty("Epoch Stack Data"),new SimpleStringProperty( "2015-01-01 00:00:00"),
      new SimpleStringProperty(null), new SimpleStringProperty(null)),
    new ObjectOrbitElementTableRowViewModel(new SimpleIntegerProperty(1), new SimpleStringProperty("201812140432435.624"),
      new SimpleStringProperty("Orbit Sample Data"),new SimpleStringProperty( "2015-01-01 00:00:00"),
      new SimpleStringProperty(null), new SimpleStringProperty(null))
  );

  public ObservableList<ObjectOrbitElementTableRowViewModel> objectOrbitElementBottomTableRowViewModelList = FXCollections.observableArrayList(
    new ObjectOrbitElementTableRowViewModel(new SimpleIntegerProperty(2), new SimpleStringProperty("201812140432435.624"),
      new SimpleStringProperty("Epoch Stack Data"),new SimpleStringProperty( "2015-01-01 00:00:00"),
      new SimpleStringProperty(null), new SimpleStringProperty(null)),
    new ObjectOrbitElementTableRowViewModel(new SimpleIntegerProperty(3),  new SimpleStringProperty("201812140432435.624"),
      new SimpleStringProperty("Orbit Sample Data"),new SimpleStringProperty( "2015-01-01 00:00:00"),
      new SimpleStringProperty(null), new SimpleStringProperty(null)),
    new ObjectOrbitElementTableRowViewModel(new SimpleIntegerProperty(4), new SimpleStringProperty("201812140432435.624"),
      new SimpleStringProperty("Orbit Sample Data"),new SimpleStringProperty( "2015-01-01 00:00:00"),
      new SimpleStringProperty(null), new SimpleStringProperty(null)),
    new ObjectOrbitElementTableRowViewModel(new SimpleIntegerProperty(5), new SimpleStringProperty("201812140432435.624"),
      new SimpleStringProperty("Orbit Sample Data"),new SimpleStringProperty( "2015-01-01 00:00:00"),
      new SimpleStringProperty(null), new SimpleStringProperty(null))

  );
}
