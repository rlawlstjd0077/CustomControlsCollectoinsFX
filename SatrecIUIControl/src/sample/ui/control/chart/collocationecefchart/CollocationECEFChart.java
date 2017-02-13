package sample.ui.control.chart.collocationecefchart;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import sample.ui.control.chart.collocationlinechart.CollocationLineChart;

/**
 * Created by GSD on 2017-02-10.
 */
public class CollocationECEFChart extends CollocationLineChart {
    private AnchorPane gradationCanvas;

    public CollocationECEFChart() {
        getStylesheets().add("sample/ui/control/chart/collocationecefchart/collocationecefchart.css");

        xAxis.setLowerBound(5);
        xAxis.setUpperBound(40);
        xAxis.setTickUnit(5);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(400);
        yAxis.setTickUnit(50);

        xAxis.setLabel("time (day)");
        yAxis.setLabel("Distance(km)");
        coreChart.setTitle("Difference between SatA and SatB");

        gradationCanvas = new AnchorPane();
        setMargin(gradationCanvas, new Insets(4,0,28,51));

        getChildren().add(gradationCanvas);


        Line xLine = new Line(-15, 0, 10, 0);
        xLine.setId("x-line");
        xLine.setStrokeWidth(1);
        Label xLabel = new Label("X");
        xLabel.setStyle("-fx-font-size:10");
        HBox xContainer = new HBox(xLine, xLabel);
        xContainer.setMargin(xLine, new Insets(0,4,0,0));
        xContainer.setAlignment(Pos.CENTER_LEFT);

        Line yLine = new Line(-15, 0, 10, 0);
        yLine.setId("y-line");
        yLine.setStrokeWidth(1);
        Label yLabel = new Label("Y");
        yLabel.setStyle("-fx-font-size:10");
        HBox yContainer = new HBox(yLine, yLabel);
        yContainer.setMargin(yLine, new Insets(0,4,0,0));
        yContainer.setAlignment(Pos.CENTER_LEFT);

        Line zLine = new Line(-15, 0, 10, 0);
        zLine.setId("z-line");
        zLine.setStrokeWidth(1);
        Label zLabel = new Label("Z");
        zLabel.setStyle("-fx-font-size:10");
        HBox zContainer = new HBox(zLine, zLabel);
        zContainer.setMargin(zLine, new Insets(0,4,0,0));
        zContainer.setAlignment(Pos.CENTER_LEFT);

        VBox lineSampleBox = new VBox(2, xContainer, yContainer, zContainer);
        lineSampleBox.setStyle("-fx-border-color:#C6C6C6");
        lineSampleBox.setPadding(new Insets(2));
        lineSampleBox.setAlignment(Pos.BASELINE_RIGHT);
        gradationCanvas.getChildren().add(lineSampleBox);
        gradationCanvas.setTranslateX(260);
    }

    @Override
    public void setData(ObservableList<XYChart.Series<Number, Number>> seriesData) {
        coreChart.setData(seriesData);
        /**
         * List의 첫번쨰는 x data,
         * 두번째는 y data,
         * 세번째는 z data 이여야 함
         */
        coreChart.getData().get(0).getNode().setId("x-line");
        coreChart.getData().get(1).getNode().setId("y-line");
        coreChart.getData().get(1).getNode().setId("z-line");
    }
}
