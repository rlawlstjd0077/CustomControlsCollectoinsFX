package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import sample.accordion.Gk2Accordion;
import sample.combobox.Gk2ComboBox;
import sample.darklistview.Gk2DarkListView;
import sample.listview.Gk2ListView;
import sample.spinner.Gk2Spinner;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    LineChart lineChart;
    @FXML
    NumberAxis xAxis;
    @FXML
    NumberAxis yAxis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        xAxis.setLowerBound(100);
        xAxis.setUpperBound(200);
        xAxis.setTickUnit(1);
    }
}