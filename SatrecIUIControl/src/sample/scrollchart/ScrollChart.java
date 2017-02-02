package sample.scrollchart;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

/**
 * Created by GSD on 2017-02-01.
 */

public class ScrollChart extends StackPane {
  private final int[] RATIO_ARRAY = {1, 2, 5, 10};
  private final int SIX_DAYS_SECONDS = 518400;
  private final int ONE_DAYS_SECONDS = 86400;
  private ArrayList<ZonedDateTime> datas1;
  private int currentRatio;
  private ComboBox<String> comboBox;
  private ZonedDateTime startDateTime;
  private ZonedDateTime endDateTime;
  private ScrollPane coreChart;
  private SimpleIntegerProperty chartWidth;
  private SimpleIntegerProperty chartHeight;
  private int entireTime;
  private int entireWidth;
  private int entireBoxHeight;
  private ZonedDateTime EWStartDate;
  private ZonedDateTime NSStartDate;
  ObservableList<String> list = FXCollections.observableArrayList("1:1", "1:2", "1:5", "1:10");

  public SimpleIntegerProperty chartWidthProperty() {
    if (chartWidth == null) {
      chartWidth = new SimpleIntegerProperty(this, "width", 502);
    }
    return chartWidth;
  }

  public void setChartWidth(int width) {
    chartWidthProperty().setValue(width);
  }

  public int getChartWidth() {
    return chartWidth == null ? 502 : chartWidthProperty().getValue();
  }

  public SimpleIntegerProperty chartHeightProperty() {
    if (chartHeight == null) {
      chartHeight = new SimpleIntegerProperty(this, "height", 75);
    }
    return chartHeight;
  }

  public void setChartHeight(int height) {
    chartHeightProperty().setValue(height);
  }

  private int getChartHeight() {
    return chartHeight == null ? 75 : chartHeightProperty().getValue();
  }

  public ScrollChart() {
    ZoneId zoneId = ZoneId.of("UTC+1");
    //임시 데이터
    startDateTime = ZonedDateTime.of(2015, 11, 14, 23, 45, 59, 1234, zoneId);
    endDateTime = ZonedDateTime.of(2015, 11, 25, 0, 45, 59, 1234, zoneId);
    getStylesheets().add("/commons/ui/control/scrollchart/scrollchart.css");
    setPrefSize(getChartWidth(), getChartHeight());
    setMinSize(getChartWidth(), getChartHeight());
    setMaxSize(getChartWidth(), getChartHeight());
    coreChart = new ScrollPane();
    coreChart.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    coreChart.setPrefSize(getChartWidth(), getChartHeight());
    coreChart.setMinSize(getChartWidth(), getChartHeight());
    coreChart.setMaxSize(getChartWidth(), getChartHeight());

    comboBox = new ComboBox<>(list);
    comboBox.setValue("1:1");
    comboBox.getStyleClass().add("scroll-button");
    comboBox.setPrefSize(20, 10);
    comboBox.setMinSize(50, 15);
    comboBox.focusedProperty().addListener((observable, oldValue, newValue) -> {
      coreChart.setContent(null);
      doDraw(RATIO_ARRAY[comboBox.getSelectionModel().getSelectedIndex()]);
    });
    getChildren().addAll(coreChart, comboBox);
    doDraw(RATIO_ARRAY[comboBox.getSelectionModel().getSelectedIndex()]);
  }

  public void setDatas(ArrayList<ZonedDateTime> datas1, ZonedDateTime startDateTime, ZonedDateTime endDateTime, ZonedDateTime EWStartDate, ZonedDateTime NSStartDate) {
    this.datas1 = datas1;
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.EWStartDate = EWStartDate;
    this.NSStartDate = NSStartDate;
    doDraw(RATIO_ARRAY[comboBox.getSelectionModel().getSelectedIndex()]);
  }

  private void doDraw(int ratio) {
    int startDateInstantSecond = getInstantSeconds(startDateTime);
    int endDateInstantSecond = getInstantSeconds(endDateTime);
    entireTime = endDateInstantSecond - startDateInstantSecond;
    entireWidth = getChartWidth() * ratio;

    coreChart.setContent(drawEntireBox());
//    drawDatas(entireTime, entireTime);
    drawLineToChart(16754, 1417343800);
  }

  private HBox drawEntireBox() {
    ZonedDateTime tempDateTime = startDateTime;
    HBox entireBox = new HBox();
    VBox cellBox;
    VBox dateBoard;
    entireBoxHeight = getChartHeight() - 20;
    entireBox.setPrefSize(entireWidth, entireBoxHeight);

    cellBox = new VBox();
    cellBox.setPrefSize((double)(entireWidth * startDateTime.getLong(ChronoField.SECOND_OF_DAY)) / entireTime, entireBoxHeight);
    entireBox.getChildren().add(cellBox);
    drawChartBoards(cellBox);
    dateBoard = new VBox();

    dateBoard.setPrefSize(cellBox.getPrefWidth(), 20);
    addDateLabel(dateBoard, tempDateTime);
    tempDateTime = tempDateTime.plusDays(1);
    dateBoard.setStyle("-fx-background-color:#F2F3F5; -fx-border-color:#9AB3CA; -fx-border-width:1 0 0 1");
    cellBox.getChildren().add(dateBoard);

    for (int i = 0; i < getEpochDay(endDateTime) - getEpochDay(startDateTime) - 2; i++) {
      cellBox = new VBox();
      cellBox.setPrefSize((double)(entireWidth * 86400) / entireTime, entireBoxHeight);
      entireBox.getChildren().add(cellBox);
      drawChartBoards(cellBox);
      dateBoard = new VBox();
      dateBoard.setPrefSize(cellBox.getPrefWidth(), 20);
      addDateLabel(dateBoard, tempDateTime);
      tempDateTime = tempDateTime.plusDays(1);
      dateBoard.setStyle("-fx-background-color:#F2F3F5; -fx-border-color:#9AB3CA; -fx-border-width:1 0 0 0");
      cellBox.getChildren().add(dateBoard);
    }
    cellBox = new VBox();
    cellBox.setPrefSize((double)(entireWidth * endDateTime.getLong(ChronoField.SECOND_OF_DAY)) / entireTime, entireBoxHeight);
    entireBox.getChildren().add(cellBox);
    drawChartBoards(cellBox);
    dateBoard = new VBox();
    dateBoard.setPrefSize(cellBox.getPrefWidth(), 20);
    addDateLabel(dateBoard, tempDateTime);
    dateBoard.setStyle("-fx-background-color:#F2F3F5; -fx-border-color:#9AB3CA; -fx-border-width:1 1 0 0");
    cellBox.getChildren().add(dateBoard);
    return entireBox;
  }
  private void drawChartBoards(VBox cellBox){
    AnchorPane board = new AnchorPane();
    board.setPrefSize(cellBox.getPrefWidth(), cellBox.getPrefHeight() - 20);
    cellBox.getChildren().add(board);
    board.setTranslateY(cellBox.getPrefHeight() - 20);
  }

  private void addDateLabel(VBox vBox, ZonedDateTime dateTime){
    vBox.setAlignment(Pos.CENTER);
    DateTimeFormatter format = DateTimeFormatter
            .ofPattern("MM-dd");
    vBox.getChildren().add(new Label(dateTime.format(format)));
  }

  public void drawDatas() {
    for (ZonedDateTime time : datas1) {
      drawLineToChart(getEpochDay(time), getInstantSeconds(time));
    }
  }

  public void drawRefDateData() {
    for(int i = getInstantSeconds(EWStartDate); i < getInstantSeconds(endDateTime); i += SIX_DAYS_SECONDS){
      Line line  = drawBarToChart(i / ONE_DAYS_SECONDS, i);
      line.getStyleClass().add("bar-ew");
    }
    for(int i = getInstantSeconds(NSStartDate); i < getInstantSeconds(endDateTime); i += SIX_DAYS_SECONDS){
      Line line  = drawBarToChart(i / ONE_DAYS_SECONDS, i);
      line.getStyleClass().add("bar-ns");
    }
  }

  public void drawLineToChart(int epochDay, int instantSecond){
    VBox currentBox = getMatchBoxByCoordinate(epochDay);
    AnchorPane boxCell = (AnchorPane) currentBox.getChildren().get(0);
    Line line = new Line(0, -20, 0, 0);
    line.setLayoutX(getDataXCoordinate(currentBox.getPrefWidth(), instantSecond));
    line.getStyleClass().add("bar-ew");
    line.setStrokeWidth(4);
    line.setStroke(Color.RED);
    boxCell.getChildren().add(line);
  }

  public Line drawBarToChart(int epochDay, int instantSecond){
    VBox currentBox = getMatchBoxByCoordinate(epochDay);
    AnchorPane boxCell = (AnchorPane) currentBox.getChildren().get(0);
    Line line = new Line(0, -8, 0, 0);
    line.setLayoutX(getDataXCoordinate((int)currentBox.getPrefWidth(), instantSecond));
    line.setStrokeWidth(15);
    boxCell.getChildren().add(line);
    return line;
  }

  public VBox getMatchBoxByCoordinate(int epoch_day){
    HBox entireBox = (HBox) coreChart.getContent();
    return (VBox) entireBox.getChildren().get(epoch_day - getEpochDay(startDateTime));
  }

  public double getDataXCoordinate(double width, int dataSecond){
    double second = (width * entireTime) / entireWidth;
    return (width * (dataSecond / ONE_DAYS_SECONDS - (ONE_DAYS_SECONDS - second))) / second;
  }

  public int getEpochDay(ZonedDateTime time){
    return (int)time.getLong(ChronoField.EPOCH_DAY);
  }

  public int getInstantSeconds(ZonedDateTime time){
    return (int)time.getLong(ChronoField.INSTANT_SECONDS);
  }
}
