package sample.ui.control.collocationlongitudechart;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import sample.ui.control.collocationlinechart.CollocationLineChart;

/**
 * Created by GSD on 2017-01-24.
 */
public class CollocationLongitudeChart extends CollocationLineChart {
    private CheckBox gk2ACheckBox, gk2BCheckBox;
    private final String CSS_PATH = "sample/ui/control/collocationlongitudechart/collocationlongitudechart.css";
    private VBox gradationCanvas;

    public CollocationLongitudeChart() {
        getStylesheets().add(CSS_PATH);

        xAxis.setLowerBound(0);
        xAxis.setUpperBound(400);
        xAxis.setTickUnit(50);
        yAxis.setLowerBound(128.18);
        yAxis.setUpperBound(128.25);
        yAxis.setTickUnit(0.01);

        xAxis.setLabel("time (day)");
        yAxis.setLabel("Longitude(deg)");
        coreChart.setTitle("Longitude of SetA and SetB");

        gradationCanvas = new VBox();
        setMargin(gradationCanvas, new Insets(4,0,28,51));

        getChildren().add(gradationCanvas);

        /**
         * 우측의 CheckBox 그리기
         */
        Line gk2ALine = new Line(-20, 0, 10, 0);
        gk2ALine.setStrokeWidth(3);
        gk2ALine.setId("gk2a-line");
        gk2ACheckBox = new CheckBox();
        gk2ACheckBox.getStyleClass().add("fds-check-box");
        Label gk2ALabel = new Label("GK2A");
        HBox gk2AContainer = new HBox(gk2ALine, gk2ACheckBox, gk2ALabel);
        gk2AContainer.setAlignment(Pos.CENTER_RIGHT);
        gk2AContainer.setStyle("-fx--border-color:red");
        gk2AContainer.setMargin(gk2ALine, new Insets(0, 5, 0, 0));

        Line gk2BLine = new Line(-20, 0, 10, 0);
        gk2BLine.setStrokeWidth(3);
        gk2BLine.setId("gk2b-line");
        gk2BCheckBox = new CheckBox();
        gk2BCheckBox.getStyleClass().add("fds-check-box");
        Label gk2BLabel = new Label("GK2B");
        HBox gk2BContainer = new HBox(gk2BLine, gk2BCheckBox, gk2BLabel);
        gk2BContainer.setAlignment(Pos.CENTER_RIGHT);
        gk2BContainer.setMargin(gk2BLine, new Insets(0, 5, 0, 0));



        gradationCanvas.getChildren().addAll(gk2AContainer, gk2BContainer);

        gk2ACheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                coreChart.getData().get(0).getNode().setId("gk2a-line");
            }else{
                coreChart.getData().get(0).getNode().setId("gk2a-line-unselect");
            }
        });

        gk2BCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                coreChart.getData().get(1).getNode().setId("gk2b-line");
            }else{
                coreChart.getData().get(1).getNode().setId("gk2b-line-unselect");
            }
        });
        gk2ACheckBox.setDisable(true);
        gk2BCheckBox.setDisable(true);
    }

    @Override
    public void setData(ObservableList<XYChart.Series<Number, Number>> seriesData) {
        coreChart.setData(seriesData);
        /**
         * List의 첫번쨰는 Gk2A data,
         * 두번째는 Gk2B data 이여야 함
         */
        coreChart.getData().get(0).getNode().setId("gk2a-line");
        coreChart.getData().get(1).getNode().setId("gk2b-line");
        gk2ACheckBox.setSelected(true);
        gk2BCheckBox.setSelected(true);
        gk2ACheckBox.setDisable(false);
        gk2BCheckBox.setDisable(false);
    }
}
