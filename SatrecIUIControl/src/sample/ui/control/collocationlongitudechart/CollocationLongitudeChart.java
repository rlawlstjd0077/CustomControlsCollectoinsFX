package sample.ui.control.collocationlongitudechart;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

/**
 * Created by GSD on 2017-01-24.
 */
public class CollocationLongitudeChart extends StackPane {
    private LineChart<Number, Number> coreChart;
    private NumberAxis xAxis, yAxis;
    private VBox gradationCanvas;
    private final int FIXED_WIDTH = 380;
    private final int FIXED_HEIGHT = 200;
    private CheckBox gk2ACheckBox, gk2BCheckBox;

    public CollocationLongitudeChart() {
        getStylesheets().add("sample/ui/control/collocationlongitudechart/collocationlongitudechart.css");
        setPadding(new Insets(10));
        setPrefSize(FIXED_WIDTH, FIXED_HEIGHT);
        setMaxSize(FIXED_WIDTH, FIXED_HEIGHT);
        setMinSize(FIXED_WIDTH, FIXED_HEIGHT);

        xAxis = new NumberAxis(0,400,50);
        yAxis = new NumberAxis(128.18, 128.25, 0.01);
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);
        xAxis.setLabel("time (day)");
        yAxis.setLabel("Longitude(deg)");

        coreChart = new LineChart<>(xAxis, yAxis);
        coreChart.setPrefSize(FIXED_WIDTH, FIXED_HEIGHT);
        coreChart.setMaxSize(FIXED_WIDTH, FIXED_HEIGHT);
        coreChart.setMinSize(FIXED_WIDTH, FIXED_HEIGHT);
        coreChart.setTitle("Longitude of SetA and SetB");

        gradationCanvas = new VBox();
        setMargin(gradationCanvas, new Insets(4,0,28,51));

        getChildren().add(coreChart);
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
        gk2ACheckBox.setSelected(true);
        gk2BCheckBox.setSelected(true);
        gk2ACheckBox.setDisable(true);
        gk2BCheckBox.setDisable(true);
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
        gk2ACheckBox.setDisable(false);
        gk2BCheckBox.setDisable(false);
    }


}
