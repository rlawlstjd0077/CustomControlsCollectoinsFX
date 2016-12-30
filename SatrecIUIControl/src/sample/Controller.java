package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.accordion.Gk2Accordion;
import sample.combobox.Gk2ComboBox;
import sample.darklistview.Gk2DarkListView;
import sample.listview.Gk2ListView;
import sample.spinner.Gk2Spinner;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    Gk2Accordion gk2Accordion;

    final String[] imageNames = new String[]{"Apples", "Flowers", "Leaves"};
    final Image[] images = new Image[imageNames.length];
    final ImageView[] pics = new ImageView[imageNames.length];
    final TitledPane[] tps = new TitledPane[imageNames.length];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < imageNames.length; i++) {
            images[i] = new
                    Image(getClass().getResourceAsStream("image/"+imageNames[i] + ".png"));
            pics[i] = new ImageView(images[i]);
            tps[i] = new TitledPane(imageNames[i],pics[i]);
        }
        gk2Accordion.getPanes().addAll(tps);
        gk2Accordion.setExpandedPane(tps[0]);
    }

}
