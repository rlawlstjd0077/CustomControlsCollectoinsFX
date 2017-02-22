package ui.screen.orbitdeterminationBLSE;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by GSD on 2017-02-17.
 */
public class OrbitDeterminationBLSEViewModel {
  public ObservableList<SelectableFilesTableRowViewModel> selectableFilesTableRowViewModelList = FXCollections.observableArrayList(
    new SelectableFilesTableRowViewModel(new SimpleStringProperty("Test_Data1")),
    new SelectableFilesTableRowViewModel(new SimpleStringProperty("Test_Data2")),
    new SelectableFilesTableRowViewModel(new SimpleStringProperty("Test_Data3"))
  );
}
