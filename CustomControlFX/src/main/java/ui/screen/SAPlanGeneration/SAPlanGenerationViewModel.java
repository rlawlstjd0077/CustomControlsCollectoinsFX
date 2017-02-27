package ui.screen.SAPlanGeneration;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ui.common.DataSearchTableRowViewModel;

/**
 * Created by GSD on 2017-02-27.
 */
public class SAPlanGenerationViewModel {
  ObservableList<DataSearchTableRowViewModel> dataSearchTableRowViewModelList = FXCollections.observableArrayList(
    new DataSearchTableRowViewModel(new SimpleStringProperty("FOCUSLEOP_DATA_1"), new SimpleStringProperty("2015-01-01 00:00:00")),
    new DataSearchTableRowViewModel(new SimpleStringProperty("FOCUSLEOP_DATA_2"), new SimpleStringProperty("2015-01-01 00:00:00")),
    new DataSearchTableRowViewModel(new SimpleStringProperty("FOCUSLEOP_DATA_3"), new SimpleStringProperty("2015-01-01 00:00:00")),
    new DataSearchTableRowViewModel(new SimpleStringProperty("FOCUSLEOP_DATA_4"), new SimpleStringProperty("2015-01-01 00:00:00")),
    new DataSearchTableRowViewModel(new SimpleStringProperty("FOCUSLEOP_DATA_5"), new SimpleStringProperty("2015-01-01 00:00:00")),
    new DataSearchTableRowViewModel(new SimpleStringProperty("FOCUSLEOP_DATA_6"), new SimpleStringProperty("2015-01-01 00:00:00"))
  );
}
