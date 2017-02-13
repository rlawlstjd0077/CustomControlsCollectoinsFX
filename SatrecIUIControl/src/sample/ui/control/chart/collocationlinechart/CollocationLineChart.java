package sample.ui.control.chart.collocationlinechart;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;

/**
 * Created by GSD on 2017-02-10.
 */
public class CollocationLineChart extends StackPane{
    public LineChart<Number, Number> coreChart;
    public NumberAxis xAxis, yAxis;
    private  final int FIXED_WIDTH = 380;
    private final int FIXED_HEIGHT = 200;

    public CollocationLineChart(){
        getStylesheets().add("sample/ui/control/chart/collocationlinechart/collocationlinechart.css");
        setPadding(new Insets(10));
        setPrefSize(FIXED_WIDTH, FIXED_HEIGHT);
        setMaxSize(FIXED_WIDTH, FIXED_HEIGHT);
        setMinSize(FIXED_WIDTH, FIXED_HEIGHT);

        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);

        coreChart = new LineChart<>(xAxis, yAxis);
        coreChart.setPrefSize(FIXED_WIDTH, FIXED_HEIGHT);
        coreChart.setMaxSize(FIXED_WIDTH, FIXED_HEIGHT);
        coreChart.setMinSize(FIXED_WIDTH, FIXED_HEIGHT);

        getChildren().add(coreChart);
    }

    /**
     * x 축 정보 세팅 메소드
     * @param lowerBound
     * @param upperBound
     * @param tickUnit
     */
    public void setxAxis(double lowerBound, double upperBound, double tickUnit){
        xAxis.setLowerBound(lowerBound);
        xAxis.setUpperBound(upperBound);
        xAxis.setTickUnit(tickUnit);
    }

    /**
     * y 축 정보 세팅 메소드
     * @param lowerBound
     * @param upperBound
     * @param tickUnit
     */
    public void setyAxis(double lowerBound, double upperBound, double tickUnit){
        yAxis.setLowerBound(lowerBound);
        yAxis.setUpperBound(upperBound);
        yAxis.setTickUnit(tickUnit);
    }

    /**
     * 축 이름, Chart 이름 세팅 메소드
     * @param title
     * @param xLabel
     * @param yLabel
     */
    public void setAxisInfo(String title, String xLabel, String yLabel){
        coreChart.setTitle(title);
        xAxis.setLabel(xLabel);
        yAxis.setLabel(yLabel);
    }
    /**
     * Data setting 메소드
     */
    public void setData(ObservableList<XYChart.Series<Number, Number>> seriesData){
        coreChart.setData(seriesData);
    }

}
