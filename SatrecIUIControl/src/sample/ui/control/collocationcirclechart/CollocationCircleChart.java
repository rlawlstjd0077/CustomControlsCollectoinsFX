package sample.ui.control.collocationcirclechart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by GSD on 2017-01-24.
 */
public class CollocationCircleChart extends StackPane {
    private final String CSS_NAME = "collocationcirclechart.css";
    private LineChart<Number, Number> coreChart;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private Circle outsideCircle;
    private AnchorPane gradationCanvas;
    private AnchorPane circleDataPane;
    private VBox checkBoxContainer;
    private CheckBox gk2ACheckBox;
    private CheckBox gk2BCheckBox;
    private CheckBox eccCheckBox;
    private Circle gk2ACircleData;
    private Circle gk2BCircleData;
    private Circle eccCircleData;
    private HBox eccContainer;
    private final int FIXED_SIZE = 185;

    public CollocationCircleChart() {
        this.getStylesheets().add(getClass().getResource(CSS_NAME).toExternalForm());
        setPadding(new Insets(10));
        setPrefSize(FIXED_SIZE, FIXED_SIZE);
        setMaxSize(FIXED_SIZE, FIXED_SIZE);
        setMinSize(FIXED_SIZE, FIXED_SIZE);

        xAxis = new NumberAxis(-0.1, 0.1, 0.05);
        yAxis = new NumberAxis(-0.1, 0.1, 0.05);
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);
        xAxis.setTickLabelsVisible(false);
        yAxis.setTickLabelsVisible(false);

        coreChart = new LineChart<>(xAxis, yAxis);
        coreChart.setPrefSize(FIXED_SIZE, FIXED_SIZE);
        coreChart.setMaxSize(FIXED_SIZE, FIXED_SIZE);
        coreChart.setMinSize(FIXED_SIZE, FIXED_SIZE);
        coreChart.setLegendVisible(false);

        //Test Data
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        series1.getData().add(new XYChart.Data<>(0.01, 0.01));
        series1.getData().add(new XYChart.Data<>(0.05, 0.01));
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        series2.getData().add(new XYChart.Data<>(0.01, 0.5));
        series2.getData().add(new XYChart.Data<>(0.05, 0.5));
        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
        series3.getData().add(new XYChart.Data<>(0.01, 0.7));
        series3.getData().add(new XYChart.Data<>(0.05, 0.7));

        ObservableList<XYChart.Series<Number, Number>> series = FXCollections.observableArrayList();
        series.addAll(series1, series2, series3);

        setMargin(coreChart, new Insets(10, 10, 0, 0));
        outsideCircle = new Circle(coreChart.getMinHeight() / 2 - 20, Color.TRANSPARENT);
        outsideCircle.setStroke(Paint.valueOf("#ADADAD"));
        getChildren().add(outsideCircle);

        for (int i = 1; i <= 6; i++) {
            Line innerLine = new Line(-outsideCircle.getRadius(), 0, outsideCircle.getRadius(), 0);
            innerLine.setRotate(i * 30);
            innerLine.setStroke(Paint.valueOf("#dadee5"));
            innerLine.setStyle("-fx-stroke-dash-array: 12 2 4 2;");

            getChildren().addAll(innerLine);
        }

        gradationCanvas = new AnchorPane();
        gradationCanvas.setTranslateX(coreChart.getMinHeight() / 2 - 10);
        gradationCanvas.setTranslateY(coreChart.getMinHeight() / 2 - 10);

        getChildren().add(gradationCanvas);

        /**
         * 각도 Label 그리기
         */
        for (int i = 0; i < 6; i++) {
            BorderPane border = new BorderPane();
            border.setPrefHeight(20);
            border.setPrefWidth(coreChart.getMinHeight() + 4);
            border.setRotate(i * -30);
            border.setLayoutX((coreChart.getMinHeight() / 2 + 2) * -1);
            border.setLayoutY(-10);
            Label label1 = new Label(i * 30 + 180 + "");
            label1.setRotate(i * 30);
            Label label2 = new Label(i * 30 + "");
            label1.getStyleClass().add("degree-label");
            label2.getStyleClass().add("degree-label");
            VBox leftBox = new VBox(label1);
            leftBox.setAlignment(Pos.CENTER_LEFT);
            VBox rightBox = new VBox(label2);
            rightBox.setAlignment(Pos.CENTER_LEFT);
            label2.setRotate(i * 30);
            border.setLeft(leftBox);
            border.setRight(rightBox);
            gradationCanvas.getChildren().add(border);
        }
        circleDataPane = new AnchorPane();
        circleDataPane.setTranslateY(+10);
        circleDataPane.setTranslateX(10);
        getChildren().add(circleDataPane);

        checkBoxContainer = new VBox();
        checkBoxContainer.setAlignment(Pos.TOP_RIGHT);
        getChildren().add(checkBoxContainer);

        getChildren().add(coreChart);
        coreChart.setData(series);

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
        gk2AContainer.setMargin(gk2ALine, new Insets(0, 5, 0, 0));
        gk2AContainer.setMargin(gk2ALabel, new Insets(0, -100, 0, 0));

        Line gk2BLine = new Line(-20, 0, 10, 0);
        gk2BLine.setStrokeWidth(3);
        gk2BLine.setId("gk2b-line");
        gk2BCheckBox = new CheckBox();
        gk2BCheckBox.getStyleClass().add("fds-check-box");
        Label gk2BLabel = new Label("GK2B");
        HBox gk2BContainer = new HBox(gk2BLine, gk2BCheckBox, gk2BLabel);
        gk2BContainer.setAlignment(Pos.CENTER_RIGHT);
        gk2BContainer.setMargin(gk2BLine, new Insets(0, 5, 0, 0));
        gk2BContainer.setMargin(gk2BLabel, new Insets(0, -100, 0, 0));

        Line eccLine = new Line(-20, 0, 10, 0);
        eccLine.setStrokeWidth(3);
        eccLine.setId("EccTol-data");
        eccCheckBox = new CheckBox();
        eccCheckBox.getStyleClass().add("fds-check-box");
        Label eccLabel = new Label("GK2B");
        eccContainer = new HBox(eccLine, eccCheckBox, eccLabel);
        eccContainer.setAlignment(Pos.CENTER_RIGHT);
        eccContainer.setMargin(eccLine, new Insets(0, 5, 0, 0));
        eccContainer.setMargin(eccLabel, new Insets(0, -120, 0, 0));

        checkBoxContainer.getChildren().addAll(gk2AContainer, gk2BContainer, eccContainer);
        checkBoxContainer.setMargin(gk2BContainer, new Insets(4, 0, 0, 0));

        eccContainer.setVisible(false);

        gk2ACheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                coreChart.getData().get(0).getNode().setId("gk2a-line");
                gk2ACircleData.setId("gk2a-line");
            } else {
                coreChart.getData().get(0).getNode().setId("gk2a-line-unselect");
                gk2ACircleData.setId("gk2a-line-unselect");
            }
        });

        gk2BCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                coreChart.getData().get(1).getNode().setId("gk2b-line");
                gk2BCircleData.setId("gk2b-line");
            } else {
                coreChart.getData().get(1).getNode().setId("gk2b-line-unselect");
                gk2BCircleData.setId("gk2b-line-unselect");
            }
        });

        eccCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                eccCircleData.setId("EccTol-data");
            } else {
                eccCircleData.setId("EccTol-data-unselect");
            }
        });

        gk2ACheckBox.setDisable(true);
        gk2BCheckBox.setDisable(true);
        doDraw();
    }

    /**
     * 축 데이터를 세팅하는 메소드
     * x축, y축 의 Axis 데이터는 동일 하기 때문에 한 축만 setting
     *
     * @param lowerBound : 축의 시작값
     * @param upperBound : 축의  최댓값
     * @param tickUnit   : 눈금의 값 단위
     */
    public void setAxisProperty(double lowerBound, double upperBound, double tickUnit) {
        xAxis.setUpperBound(upperBound);
        xAxis.setLowerBound(0 - upperBound);
        xAxis.setTickUnit(tickUnit);
        yAxis.setUpperBound(upperBound);
        yAxis.setLowerBound(0 - upperBound);
        yAxis.setTickUnit(tickUnit);
        gradationCanvas.getChildren().removeAll();
        circleDataPane.getChildren().removeAll();
        doDraw();
    }

    /**
     * Chart에 데이터를 세팅 시키는 메소드
     * @param seriesData
     * @param circleDatas
     */
    public void setDatas(ObservableList<XYChart.Series<Number, Number>> seriesData, ArrayList<CircleData> circleDatas, CircleData EccTolData) {
        coreChart.setData(seriesData);
        coreChart.getData().get(0).getNode().setId("gk2a-line");
        coreChart.getData().get(1).getNode().setId("gk2b-line");
        gk2ACircleData = drawDataCircle(circleDatas.get(0), "gk2a-line");
        gk2BCircleData = drawDataCircle(circleDatas.get(1), "gk2b-line");
        gk2ACheckBox.setSelected(true);
        gk2BCheckBox.setSelected(true);
        gk2ACheckBox.setDisable(false);
        gk2BCheckBox.setDisable(false);
        if (EccTolData != null) {
            eccContainer.setVisible(true);
            eccCircleData = drawDataCircle(EccTolData, "EccTol-data-unselect");
        }
    }

    /**
     * 원  내부의 눈금등 전체적인 contents들을 그려주는 메소드
     */
    public void doDraw() {
        double upperBound = xAxis.getUpperBound();
        double tickUnit = xAxis.getTickUnit();

        /**
         * 눈금 Label 그리기
         */
        AnchorPane innerMarkingLabelContainer = new AnchorPane();
        innerMarkingLabelContainer.setPrefHeight(20);
        innerMarkingLabelContainer.setPrefWidth(coreChart.getMinHeight() + 4);
        innerMarkingLabelContainer.setLayoutX((coreChart.getMinHeight() / 2 + 2) * -1);
        innerMarkingLabelContainer.setLayoutY(-10);
        innerMarkingLabelContainer.setRotate(-70);

        AnchorPane outerMarkingLabelCanvas = new AnchorPane();
        gradationCanvas.getChildren().add(outerMarkingLabelCanvas);
        gradationCanvas.getChildren().add(innerMarkingLabelContainer);

        for (double j = 0 + tickUnit; j <= upperBound; j += tickUnit) {
            Circle circle = new Circle();
            circle.setStroke(Paint.valueOf("#dadee5"));
            circle.setFill(Color.TRANSPARENT);
            circle.setRadius((j * outsideCircle.getRadius()) / upperBound);
            outerMarkingLabelCanvas.getChildren().add(circle);

            DecimalFormat format = new DecimalFormat("0.####");
            Label markingLabel = new Label();
            markingLabel.setText(format.format(j) + "");
            innerMarkingLabelContainer.getChildren().add(markingLabel);
            markingLabel.setLayoutX(innerMarkingLabelContainer.getPrefWidth() / 2 + (j * outsideCircle.getRadius()) / upperBound);
            markingLabel.setRotate(70);
            markingLabel.getStyleClass().add("marking-label");
        }
    }

    /**
     * Circle Data를 그려주는 메소드
     *
     * @param circleData
     */
    public Circle drawDataCircle(CircleData circleData, String styleId) {
        double upperBound = xAxis.getUpperBound();
        Circle circle = new Circle(circleData.getRadius(), Color.TRANSPARENT);
        circleDataPane.getChildren().add(circle);
        circle.setLayoutX(((upperBound + circleData.getX()) * (outsideCircle.getRadius() * 2)) / (upperBound * 2));
        circle.setLayoutY(((upperBound + circleData.getY()) * (outsideCircle.getRadius() * 2)) / (upperBound * 2));
        circle.setId(styleId);
        return circle;
    }
}
