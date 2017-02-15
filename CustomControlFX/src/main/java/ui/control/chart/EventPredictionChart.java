package ui.control.chart;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Created by GSD on 2017-02-15.
 */
public class EventPredictionChart extends StackPane{
  private final String CSS_NAME = "eventpredictionchart.css";
  private final int FIXED_WIDTH = 856;
  private final int FIXED_HEIGHT = 430;
  private LineChart<Number, Number> coreChart;
  private NumberAxis xAxis, yAxis;
  private VBox outsideLineBox;
  private AnchorPane markingLabelCanvas;
  private AnchorPane circleDataCanvas;

  public EventPredictionChart(){
    getStylesheets().add(getClass().getResource(CSS_NAME).toExternalForm());
    setPrefSize(FIXED_WIDTH, FIXED_HEIGHT);
    setMinSize(FIXED_WIDTH, FIXED_HEIGHT);
    setMaxSize(FIXED_WIDTH, FIXED_HEIGHT);

    xAxis = new NumberAxis(-35, 35, 5);
    yAxis = new NumberAxis(-25,25,5);
    xAxis.setTickLabelsVisible(false);
    yAxis.setTickLabelsVisible(false);
    xAxis.setTickMarkVisible(false);
    yAxis.setTickMarkVisible(false);
    xAxis.setAutoRanging(false);
    yAxis.setAutoRanging(false);

    coreChart = new LineChart<>(xAxis, yAxis);
    coreChart.setLegendVisible(false);
    outsideLineBox = new VBox();
    outsideLineBox.setStyle("-fx-border-color:#9AB3CA");
    setMargin(outsideLineBox, new Insets(15, 14, 17, 18));
    getChildren().add(outsideLineBox);

    markingLabelCanvas = new AnchorPane();
    circleDataCanvas = new AnchorPane();
    getChildren().add(coreChart);
    getChildren().add(markingLabelCanvas);
    getChildren().add(circleDataCanvas);
    circleDataCanvas.setTranslateX(430);
    circleDataCanvas.setTranslateY(214);
    markingLabelCanvas.setTranslateX(430);
    markingLabelCanvas.setTranslateY(214);
    markLabel();
  }

  public void setxAxis(double lowerBound, double upperBound, double tickUnit){
    xAxis.setLowerBound(lowerBound);
    xAxis.setUpperBound(upperBound);
    xAxis.setTickUnit(tickUnit);
    circleDataCanvas.getChildren().clear();
    markingLabelCanvas.getChildren().clear();
    markLabel();
  }

  public void setyAxis(double lowerBound, double upperBound, double tickUnit){
    yAxis.setLowerBound(lowerBound);
    yAxis.setUpperBound(upperBound);
    yAxis.setTickUnit(tickUnit);
    circleDataCanvas.getChildren().clear();
    markingLabelCanvas.getChildren().clear();
    markLabel();
  }

  public void markLabel(){
    AnchorPane xAxisLabelBox = new AnchorPane();
    AnchorPane yAxisLabelBox = new AnchorPane();
    xAxisLabelBox.setPrefSize(FIXED_WIDTH - 30, 20);
    xAxisLabelBox.setLayoutX(- FIXED_WIDTH / 2 + 15);

    yAxisLabelBox.setPrefSize(20, FIXED_HEIGHT - 30);
    yAxisLabelBox.setLayoutY(- FIXED_HEIGHT / 2 + 15);
    markingLabelCanvas.getChildren().addAll(xAxisLabelBox, yAxisLabelBox);
    xAxisLabelBox.setPadding(new Insets(0,20,0,10));

    double xAxisDistance = (xAxis.getTickUnit() * (FIXED_WIDTH - 30)) / (xAxis.getUpperBound() - xAxis.getLowerBound());
    double yAxisDistance = (yAxis.getTickUnit() * (FIXED_HEIGHT - 30)) / (yAxis.getUpperBound() - yAxis.getLowerBound());

    double i, x, y;
    for(i = xAxis.getLowerBound(), x= 0; i <= xAxis.getUpperBound(); i += xAxis.getTickUnit(), x += xAxisDistance){
      Label label = new Label(String.format("%.0f", i) + "");
      label.setPrefWidth(30);
      label.setAlignment(Pos.CENTER);
      label.setLayoutX(x - (label.getPrefWidth() / 2));
      xAxisLabelBox.getChildren().add(label);
    }
    for(i = yAxis.getUpperBound(), y = 0; i >= yAxis.getLowerBound(); i -= yAxis.getTickUnit(), y += yAxisDistance){
      Label label = new Label(String.format("%.0f", i) + "");
      label.setLayoutY(y - 10);
      yAxisLabelBox.getChildren().add(label);
    }
  }

  public void drawCircle(double radius){
    Circle circle = new Circle(radius);
    circle.setFill(Color.TRANSPARENT);
    circle.setStroke(Paint.valueOf("#5425CB"));
    circle.setStrokeWidth(2);
    circleDataCanvas.getChildren().add(circle);
  }
}
