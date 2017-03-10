package ui.screen.common;

import fds.ui.orbitdeterminationBLSE.MeasurementDataTableRowViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * Created by GSD on 2017-02-28.
 */
public class MeasurementDataBiasPopup extends BorderPane {
  @FXML
  private GridPane measurementDataBiasGridPane;

  public MeasurementDataBiasPopup() {
    this(null);
  }

  public MeasurementDataBiasPopup(ObservableList<MeasurementDataTableRowViewModel> dataList) {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MeasurementDataBiasPopup.fxml"));

    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (dataList != null) {
      for (int i = 0; i < dataList.size(); i++) {
        int count = 0;
        measurementDataBiasGridPane.add(new Label(dataList.get(i).stationProperty().getValue()), count++, i);
        measurementDataBiasGridPane.add(new Label(dataList.get(i).rangeProperty().toString()), count++, i);
        measurementDataBiasGridPane.add(new Label(dataList.get(i).azimuthProperty().toString()), count++, i);
        measurementDataBiasGridPane.add(new Label(dataList.get(i).elevationProperty().toString()), count++, i);
      }
    }
  }
}
