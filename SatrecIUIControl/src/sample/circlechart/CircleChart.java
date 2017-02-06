package sample.circlechart;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

import java.time.ZonedDateTime;

/**
 * Created by GSD on 2017-01-24.
 */
public class  CircleChart extends StackPane {
    private ScatterChart<Number, Number> coreChart;
    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private Circle outsideCircle;
    private AnchorPane gradationCanvas;
    private AnchorPane dateLabelCanvas;
    private AnchorPane bottomCanvas;
    private AnchorPane baseCanvas;
    private final int FIXED_SIZE = 230;

    public CircleChart() {
        getStylesheets().add("/commons/ui/control/circlechart/circlechart.css");
        setPadding(new Insets(10));
        setPrefSize(FIXED_SIZE, FIXED_SIZE);
        setMaxSize(FIXED_SIZE, FIXED_SIZE);
        setMinSize(FIXED_SIZE, FIXED_SIZE);

        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        xAxis.setTickLabelsVisible(false);
        yAxis.setTickLabelsVisible(false);
        xAxis.setAutoRanging(false);
        yAxis.setAutoRanging(false);

        coreChart = new ScatterChart<>(xAxis, yAxis);
        coreChart.setPrefSize(FIXED_SIZE, FIXED_SIZE);
        coreChart.setMaxSize(FIXED_SIZE, FIXED_SIZE);
        coreChart.setMinSize(FIXED_SIZE, FIXED_SIZE);

        baseCanvas = new AnchorPane();
        baseCanvas.setTranslateX(coreChart.getMinHeight() / 2 - 10);
        baseCanvas.setTranslateY(coreChart.getMinHeight() / 2 - 10);
        getChildren().add(baseCanvas);

        setMargin(coreChart, new Insets(10, 12, 0, 0));
        outsideCircle = new Circle(coreChart.getMinHeight() / 2 - 20, Color.TRANSPARENT);
        outsideCircle.setStroke(Color.BLACK);
        getChildren().add(outsideCircle);

        bottomCanvas = new AnchorPane();
        bottomCanvas.setTranslateX(coreChart.getMinHeight() / 2 - 10);
        bottomCanvas.setTranslateY(coreChart.getMinHeight() / 2 - 10);
        getChildren().add(bottomCanvas);

        for (int i = 1; i <= 12; i++) {
            Line innerLine = new Line(-outsideCircle.getRadius(), 0, outsideCircle.getRadius(), 0);
            Line outerPrimaryLine = new Line(-outsideCircle.getRadius() - 8, 0, outsideCircle.getRadius() + 8, 0);
            Line outerSecondaryLine = new Line(-outsideCircle.getRadius() - 4, 0, outsideCircle.getRadius() + 4, 0);
            innerLine.setRotate(i * 15);
            innerLine.setStroke(Paint.valueOf("#dadee5"));
            innerLine.setStyle("-fx-stroke-dash-array: 12 2 4 2;");

            outerPrimaryLine.setRotate(i * 15);
            outerPrimaryLine.setFill(Color.BLACK);
            baseCanvas.getChildren().add(outerPrimaryLine);

            outerSecondaryLine.setRotate(i * 15 + 7.5);
            outerSecondaryLine.setFill(Color.BLACK);
            baseCanvas.getChildren().add(outerSecondaryLine);

            getChildren().addAll(innerLine);
        }

        Circle circle = new Circle(outsideCircle.getRadius(), Color.WHITE);
        baseCanvas.getChildren().add(circle);

        gradationCanvas = new AnchorPane();
        gradationCanvas.setTranslateX(coreChart.getMinHeight() / 2 - 10);
        gradationCanvas.setTranslateY(coreChart.getMinHeight() / 2 - 10);

        dateLabelCanvas = new AnchorPane();
        dateLabelCanvas.setTranslateX(coreChart.getMinHeight() / 2 - 10);
        dateLabelCanvas.setTranslateY(coreChart.getMinHeight() / 2 - 10);
        getChildren().add(gradationCanvas);
        getChildren().add(coreChart);
        getChildren().add(dateLabelCanvas);
    }

    /**
     * Data를 세팅 하는 메소드
     * x축, y축 의 Axis 데이터는 동일 하기 때문에 한 축만 setting
     * @param lowerBound : 축의 시작값
     * @param upperBound : 축의  최댓값
     * @param tickUnit : 눈금의 값 단위
     */
    public void setAxisProperty(double lowerBound, double upperBound, double tickUnit){
        xAxis.setUpperBound(upperBound);
        xAxis.setLowerBound(0 - upperBound);
        xAxis.setTickUnit(tickUnit);
        yAxis.setUpperBound(upperBound);
        yAxis.setLowerBound(0 - upperBound);
        yAxis.setTickUnit(tickUnit);
<<<<<<< HEAD
        gradationCanvas.getChildren().removeAll();
        dateLabelCanvas.getChildren().removeAll();
        baseCanvas.getChildren().removeAll();
=======
>>>>>>> ddcddddbbdc8fc37f2706e2d3897010282422171
        doDraw();
    }

    /**
     * dateLabel을 그려주는 메소드
     * @param degree : circle 의 degree
     * @param date
     */
    public void drawDateLabel(double degree, ZonedDateTime date) {
        HBox hBox = new HBox();
        hBox.setPrefHeight(20);
        hBox.setPrefWidth(coreChart.getMinHeight() / 2 + 32);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        Label label = new Label(date.getMonth() + "/" + date.getDayOfMonth());
        label.setPadding(new Insets(2));
        label.setStyle("-fx-background-color:white; -fx-text-fill:blue; -fx-border-color:gray;");
        label.setRotate(degree);

        Circle circle = new Circle(4, Color.BLACK);
        circle.getStyleClass().add("date_label_circle");
        VBox box = new VBox();
        box.setAlignment(Pos.TOP_CENTER);
        box.getChildren().add(circle);

        hBox.getChildren().add(box);
        hBox.getChildren().add(label);

//        double regulateValue = (degree % 180) / 90 == 0 ? (2 * (degree % 90)) / 15 : (2 * (90 - degree % 90)) / 15;
        hBox.setMargin(label, new Insets(-10, 1, 0, -1));
        box.setMargin(circle, new Insets(-4, 0, 0, 0));
        Rotate rotate = new Rotate(-degree);
        hBox.getTransforms().add(rotate);
        dateLabelCanvas.getChildren().add(hBox);
    }

    /**
     * radius를 받아 내부에 연두색 circle을 그려주는 메소드
     * @param radiusCoordinate
     */
    public void drawObjectCircle(double radiusCoordinate) {
        Circle circle = new Circle((radiusCoordinate * outsideCircle.getRadius()) / xAxis.getUpperBound() , Paint.valueOf("#D5FEAD"));
        circle.setStroke(Color.RED);
        bottomCanvas.getChildren().add(circle);
    }

    /**
     * 원  내부의 눈금등 전체적인 contents들을 그려주는 메소드
     */
    public void doDraw() {
        boolean state = true;
        double upperBound = xAxis.getUpperBound();
        double tickUnit = xAxis.getTickUnit();
        for (double j = 0 + tickUnit; j <= upperBound; j += tickUnit) {
            Circle circle = new Circle();
            circle.setStroke(Paint.valueOf("#dadee5"));
            circle.setFill(Color.TRANSPARENT);
            circle.setRadius((j  * outsideCircle.getRadius()) / upperBound );
            gradationCanvas.getChildren().add(circle);

            Label markingLabel = new Label();
            markingLabel.setText(j + "");
            markingLabel.getStyleClass().add("marking-label");
            gradationCanvas.getChildren().add(markingLabel);
            markingLabel.setLayoutY(((j * outsideCircle.getRadius()) / upperBound * -1) - 8);
            markingLabel.setLayoutX(10);
        }

        for (int i = 1; i <= 4; i++) {
            state = !state;
            for (double j = 0 + tickUnit; j <= upperBound; j += tickUnit) {
                Line primaryLine = new Line(8, 0, -8, 0);
                primaryLine.setFill(Color.BLACK);
                Line secondaryLine = new Line(4, 0, -4, 0);
                secondaryLine.setFill(Color.BLACK);

                if (i <= 2) {
                    primaryLine.setRotate(90);
                    secondaryLine.setRotate(90);
                    primaryLine.setLayoutX((j * outsideCircle.getRadius()) / upperBound * (state ? -1 : 1));
                    secondaryLine.setLayoutX(((j   - tickUnit / 2) * outsideCircle.getRadius()) / upperBound * (state ? -1 : 1));
                } else {
                    primaryLine.setLayoutY((j * outsideCircle.getRadius()) / upperBound * (state ? -1 : 1));
                    secondaryLine.setLayoutY(((j- tickUnit / 2) * outsideCircle.getRadius()) / upperBound * (state ? -1 : 1));
                }

                gradationCanvas.getChildren().add(secondaryLine);
                gradationCanvas.getChildren().add(primaryLine);
            }
        }

        for (int i = 0; i < 12; i++) {
            BorderPane border = new BorderPane();
            border.setPrefHeight(20);
            border.setPrefWidth(coreChart.getMinHeight() + 20);
            border.setRotate(i * -15);
            border.setLayoutX((coreChart.getMinHeight() / 2 + 10 ) * -1);
            border.setLayoutY(-10);
            Label label1 = new Label(i * 15 + 180 + "");
            label1.setRotate(i * 15);
            Label label2 = new Label(i * 15 + "");
            label1.getStyleClass().add("degree-label");
            label2.getStyleClass().add("degree-label");
            VBox vBox = new VBox(label2);
            vBox.setAlignment(Pos.CENTER_LEFT);
            label2.setRotate(i * 15);
            border.setLeft(label1);
            border.setRight(vBox);
            gradationCanvas.getChildren().add(border);
        }
    }
}
