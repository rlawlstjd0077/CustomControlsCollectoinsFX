package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import sample.ui.control.circlechart.CircleChart;
import sample.ui.control.collocationcirclechart.CollocationCircleChart;
import sample.ui.control.textviewer.TextViewerEventHandler;

import java.net.URL;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    Button saveButton;
    @FXML
    Button printButton;
    @FXML
    Button newFileButton;
    @FXML
    Button newWindowButton;
    @FXML
    TabPane mainainTab;
    @FXML
    StackPane viewerPane;

    private TextViewerEventHandler handler;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handler = new TextViewerEventHandler(viewerPane, mainainTab);
        newFileButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        printButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        newWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
    }
}