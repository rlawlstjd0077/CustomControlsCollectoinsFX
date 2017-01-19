package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.accordion.Gk2Accordion;
import sample.primaryButton.PrimaryButton;

public class Main extends Application {
    final String[] imageNames = new String[]{"Apples", "Flowers", "Leaves"};
    final Image[] images = new Image[imageNames.length];
    final ImageView[] pics = new ImageView[imageNames.length];
    final TitledPane[] tps = new TitledPane[imageNames.length];
    @Override
    public void start(Stage primaryStage) throws Exception{
//        primaryStage.setTitle("TitledPane");
//        Scene scene = new Scene(new Group(), 80, 180);
//        scene.setFill(Color.GHOSTWHITE);
//
//        final Gk2Accordion accordion = new Gk2Accordion();
//
//        for (int i = 0; i < imageNames.length; i++) {
//
//            pics[i] = new ImageView(images[i]);
//            tps[i] = new TitledPane(imageNames[i],pics[i]);
//        }
//        accordion.getPanes().addAll(tps);
//        accordion.setExpandedPane(tps[0]);
//
//        Group root = (Group)scene.getRoot();
//        root.getChildren().add(accordion);
//        primaryStage.setScene(scene);
//        primaryStage.show();

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 4000, 3000));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
