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
    TabPane tab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tab tab1 = new Tab();
        tab1.setText("Tab" + 1);
        TextArea area = new TextArea();
        area.setStyle("-fx-background-insets:20");
        tab1.setContent(area);
        tab.getTabs().add(tab1);
    }
}