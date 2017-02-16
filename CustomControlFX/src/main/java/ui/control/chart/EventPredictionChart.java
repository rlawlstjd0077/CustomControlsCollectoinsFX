package ui.control.chart;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * Created by GSD on 2017-02-15.
 */
public class EventPredictionChart extends StackPane{
  private final String CSS_NAME = "eventpredictionchart.css";
  private final int FIXED_WIDTH = 750;
  private final int FIXED_HEIGHT = 430;
  private LineChart<Number, Number> coreChart;
  private NumberAxis xAxis, yAxis;
  private AnchorPane outsideLineBox;
  private AnchorPane markingLabelCanvas;
  private AnchorPane circleDataCanvas;
  private XYChart.Series<Number, Number> sunData;
  private XYChart.Series<Number, Number> moonData;

  public EventPredictionChart(){
    getStylesheets().add(getClass().getResource(CSS_NAME).toExternalForm());
    setPrefSize(FIXED_WIDTH, FIXED_HEIGHT);
    setMinSize(FIXED_WIDTH, FIXED_HEIGHT);
    setMaxSize(FIXED_WIDTH, FIXED_HEIGHT);

    xAxis = new NumberAxis();
    yAxis = new NumberAxis();
    xAxis.setTickLabelsVisible(false);
    yAxis.setTickLabelsVisible(false);
    xAxis.setTickMarkVisible(false);
    yAxis.setTickMarkVisible(false);
    xAxis.setAutoRanging(false);
    yAxis.setAutoRanging(false);

    coreChart = new LineChart<Number, Number>(xAxis, yAxis);
    coreChart.setLegendVisible(false);
    outsideLineBox = new AnchorPane();
    outsideLineBox.setStyle("-fx-border-color:#9AB3CA");
    setMargin(outsideLineBox, new Insets(15, 14, 17, 18));

    markingLabelCanvas = new AnchorPane();
    circleDataCanvas = new AnchorPane();

    getChildren().add(outsideLineBox);
    getChildren().add(markingLabelCanvas);
    getChildren().add(circleDataCanvas);
    getChildren().add(coreChart);

    circleDataCanvas.setTranslateX(376);
    circleDataCanvas.setTranslateY(214);
    markingLabelCanvas.setTranslateX(376);
    markingLabelCanvas.setTranslateY(214);
  }

  /**
   * x축 데이터를 세팅하는 메소드
   * @param lowerBound
   * @param upperBound
   * @param tickUnit
   */
  public void setxAxis(double lowerBound, double upperBound, double tickUnit){
    xAxis.setLowerBound(lowerBound);
    xAxis.setUpperBound(upperBound);
    xAxis.setTickUnit(tickUnit);
    outsideLineBox.getChildren().clear();
    circleDataCanvas.getChildren().clear();
    markingLabelCanvas.getChildren().clear();
    coreChart.getData().removeAll();
    markGradationLineLabel();
  }

  /**
   * y축 데이터를 세팅하는 메소드
   * @param lowerBound
   * @param upperBound
   * @param tickUnit
   */
  public void setyAxis(double lowerBound, double upperBound, double tickUnit){
    yAxis.setLowerBound(lowerBound);
    yAxis.setUpperBound(upperBound);
    yAxis.setTickUnit(tickUnit);
    outsideLineBox.getChildren().clear();
    circleDataCanvas.getChildren().clear();
    markingLabelCanvas.getChildren().clear();
    coreChart.getData().removeAll();
    markGradationLineLabel();
  }

  /**
   * 눈금 라벨, 분기선을 그려주는 메소드
   */
  public void markGradationLineLabel(){
    AnchorPane xAxisLabelBox = new AnchorPane();
    AnchorPane yAxisLabelBox = new AnchorPane();
    xAxisLabelBox.setPrefSize(FIXED_WIDTH - 36, 20);
    xAxisLabelBox.setLayoutX(- FIXED_WIDTH / 2 + 18);

    yAxisLabelBox.setPrefSize(20, FIXED_HEIGHT - 36);
    yAxisLabelBox.setLayoutY(- FIXED_HEIGHT / 2 + 18);
    markingLabelCanvas.getChildren().addAll(xAxisLabelBox, yAxisLabelBox);

    double xAxisDistance = (xAxis.getTickUnit() * (FIXED_WIDTH - 36)) / (xAxis.getUpperBound() - xAxis.getLowerBound());
    double yAxisDistance = (yAxis.getTickUnit() * (FIXED_HEIGHT - 36)) / (yAxis.getUpperBound() - yAxis.getLowerBound());

    double i, x, y;
    for(i = xAxis.getLowerBound(), x= 0; i <= xAxis.getUpperBound(); i += xAxis.getTickUnit(), x += xAxisDistance){
      if(i < xAxis.getUpperBound() - xAxis.getTickUnit()) {
        Line line = new Line(0, 0, 0, 396);
        line.setLayoutX(x + xAxisDistance+1);
        line.getStyleClass().add("gradation-line");
        outsideLineBox.getChildren().add(line);
      }
      Label label = new Label(String.format("%.0f", i) + "");
      label.setPrefWidth(30);
      label.setAlignment(Pos.CENTER);
      label.setLayoutX(x - (label.getPrefWidth() / 2));
      xAxisLabelBox.getChildren().addAll(label);
    }
    for(i = yAxis.getUpperBound(), y = 0; i >= yAxis.getLowerBound(); i -= yAxis.getTickUnit(), y += yAxisDistance){
      if(i >  yAxis.getLowerBound() + yAxis.getTickUnit()) {
        Line line = new Line(0, 0, 716, 0);
        line.getStyleClass().add("gradation-line");
        line.setLayoutY(y + yAxisDistance + 1);
        outsideLineBox.getChildren().add(line);
      }
      Label label = new Label(String.format("%.0f", i) + "");
      label.setLayoutY(y - 10);
      yAxisLabelBox.getChildren().add(label);
    }
  }

  /**
   * Circle Data(Earth) 를 세팅하는 메소드
   * @param data
   * @param color
   */
  public void setCircleData(XYChart.Series<Number, Number> data, String color){
    coreChart.getData().add(data);
    data.getNode().setStyle("-fx-stroke:" + color);
  }

  /**
   * 추가 적인 데이터를 세팅하는 메소드
   * @param data
   * @param color
   */
  public void setOtherData(XYChart.Series<Number, Number> data, String color){
    coreChart.getData().add(data);
    data.getNode().setStyle("-fx-stroke:" + color);
  }

  /**
   * Sun, Moon data를 세팅 하는 메소드 ( OtherData 보다 먼저 세팅되어야 함).
   * @param datas
   */
  public void setSunMoonData(ObservableList<XYChart.Series<Number, Number>> datas){
    coreChart.setData(datas);
    sunData = coreChart.getData().get(0);
    moonData = coreChart.getData().get(1);

    for(XYChart.Series<Number, Number> s : coreChart.getData()){
      for(XYChart.Data d : s.getData()){
        Tooltip.install(d.getNode(), new Tooltip(d.getXValue().toString()));
        // ToolTip 커서 2초 이상 유지해야 show 됨.
      }
    }
  }

  /**
   * SunData의 Visible 을 변경하는 메소드.
   * @param state
   */
  public void setDataSunvisible(boolean state){
    if(coreChart.getData() != null) {
      if (!state) {
        for (XYChart.Data<Number, Number> data : sunData.getData()) {
          data.getNode().getStyleClass().remove("default-color0");
          data.getNode().getStyleClass().add("invisible-color");
        }
        sunData.getNode().getStyleClass().remove("default-color0");
        sunData.getNode().getStyleClass().add("invisible-color");
      } else {
        for (XYChart.Data<Number, Number> data : sunData.getData()) {
          data.getNode().getStyleClass().remove("invisible-color");
          data.getNode().getStyleClass().add("default-color0");
        }
        sunData.getNode().getStyleClass().remove("invisible-color");
        sunData.getNode().getStyleClass().add("default-color0");
      }
    }
  }

  /**
   * MoonData 의 Visible 상태를 변경하는 메소드
   * @param state
   */
  public void setMoonDataVisible(boolean state) {
    if (coreChart.getData() != null) {
      if (!state) {
        for (XYChart.Data<Number, Number> data : moonData.getData()) {
          data.getNode().getStyleClass().remove("default-color0");
          data.getNode().getStyleClass().add("invisible-color");
        }
        moonData.getNode().getStyleClass().remove("default-color0");
        moonData.getNode().getStyleClass().add("invisible-color");
      } else {
        for (XYChart.Data<Number, Number> data : moonData.getData()) {
          data.getNode().getStyleClass().remove("invisible-color");
          data.getNode().getStyleClass().add("default-color0");
        }
        moonData.getNode().getStyleClass().remove("invisible-color");
        moonData.getNode().getStyleClass().add("default-color0");
      }
    }
  }

  public LineChart getCoreChart(){
    return coreChart;
  }
}