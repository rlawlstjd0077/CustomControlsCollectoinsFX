package ui.control.chart;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

/**
 * Created by JinSeong on 2017-02-10.
 */
public class CollocationInterLowerChart extends CollocationLineChart {
  private AnchorPane gradationCanvas;
  private final String CSS_PATH = "collocationinterlowerchart.css";

  public CollocationInterLowerChart() {
    this.getStylesheets().add(getClass().getResource(CSS_PATH).toExternalForm());

    gradationCanvas = new AnchorPane();
    setMargin(gradationCanvas, new Insets(4, 0, 28, 51));

    getChildren().add(gradationCanvas);

    Line interSatDistanceLine = new Line(-15, 0, 10, 0);
    interSatDistanceLine.setId("inter-sat-distance-line");
    interSatDistanceLine.setStrokeWidth(1.5);
    Label interSatDistanceLabel = new Label("Inter Sat Distance");
    interSatDistanceLabel.setStyle("-fx-font-size:10");
    HBox interSatIdstanceContainer = new HBox(interSatDistanceLine, interSatDistanceLabel);
    interSatIdstanceContainer.setMargin(interSatDistanceLine, new Insets(0, 4, 0, 0));
    interSatIdstanceContainer.setAlignment(Pos.CENTER_LEFT);

    Line lowerBoundLine = new Line(-15, 0, 10, 0);
    lowerBoundLine.setId("lower-bound-line");
    lowerBoundLine.setStrokeWidth(1.5);
    Label lowerBoundLabel = new Label("Lower Bound");
    lowerBoundLabel.setStyle("-fx-font-size:10");
    HBox lowerBoundContainer = new HBox(lowerBoundLine, lowerBoundLabel);
    lowerBoundContainer.setMargin(lowerBoundLine, new Insets(0, 4, 0, 0));
    lowerBoundContainer.setAlignment(Pos.CENTER_LEFT);

    VBox lineSampleBox = new VBox(2, interSatIdstanceContainer, lowerBoundContainer);
    lineSampleBox.setStyle("-fx-border-color:#C6C6C6");
    lineSampleBox.setPadding(new Insets(2));
    lineSampleBox.setAlignment(Pos.BASELINE_RIGHT);
    gradationCanvas.getChildren().add(lineSampleBox);
    gradationCanvas.setTranslateX(180);
    gradationCanvas.setTranslateY(80);
  }

  @Override
  public void setData(ObservableList<XYChart.Series<Number, Number>> seriesData) {
    coreChart.setData(seriesData);
    /**
     * List의 첫번쨰는 Inter Sat Distance data,
     * 두번째는 Lower Bound data 이여야 함
     */
    coreChart.getData().get(0).getNode().setId("inter-sat-distance-line");
    coreChart.getData().get(1).getNode().setId("lower-bound-line");
  }
}
