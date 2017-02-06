package sample.scrollchart;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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
  private final int ONE_DAYS_SECONDS = 86400;
  private ArrayList<ZonedDateTime> datas1;
  private ComboBox<String> comboBox;
  private ZonedDateTime startDateTime;
  private ZonedDateTime endDateTime;
  private ScrollPane coreChart;
  private SimpleIntegerProperty chartWidth;
  private SimpleIntegerProperty chartHeight;
  private int entireTime;
  private int entireWidth;
  private int entireBoxHeight;
  private StackPane entireContainer;
  private HBox entireBox;
  private AnchorPane labelBoard;
  private ZonedDateTime EWStartDate;
  private ZonedDateTime NSStartDate;
  private ObservableList<String> list = FXCollections.observableArrayList("1:1", "1:2", "1:5", "1:10");

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
      chartHeight = new SimpleIntegerProperty(this, "height", 90);
    }
    return chartHeight;
  }

  public void setChartHeight(int height) {
    chartHeightProperty().setValue(height);
  }

  private int getChartHeight() {
    return chartHeight == null ? 90 : chartHeightProperty().getValue();
  }

  public ScrollChart() {

    getStylesheets().add("/commons/ui/control/scrollchart/scrollchart.css");
    ZoneId zoneId = ZoneId.of("UTC+1");
    //임시 데이터
    startDateTime = ZonedDateTime.of(2015, 11, 14, 11, 45, 59, 1234, zoneId);
    endDateTime = ZonedDateTime.of(2015, 11, 20, 0, 45, 59, 1234, zoneId);
    getStylesheets().add("/sample/scrollchart/scrollchart.css");
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
    comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
      coreChart.setContent(null);
      doDraw(RATIO_ARRAY[comboBox.getSelectionModel().getSelectedIndex()]);
    });
    VBox comboBoxWrapper = new VBox(comboBox);
    comboBoxWrapper.setStyle("-fx-border-color:black");
    setMargin(comboBox, new Insets(0, 300, 70, 0));
    AnchorPane comboBoxContainer = new AnchorPane(comboBoxWrapper);
    getChildren().addAll(coreChart, comboBoxContainer);
    setMargin(comboBoxContainer, new Insets(0, 0, 20, 0));
  }

  /**
   * 초기 Data 설정 메소드
   * @param datas1        : Red line Data
   * @param startDateTime : 시작 날짜
   * @param endDateTime   : 종료 날짜
   * @param EWStartDate   : EW Date 시작 날짜
   * @param NSStartDate   : NS Date 시작 날짜
   */
  public void setDatas(ArrayList<ZonedDateTime> datas1, ZonedDateTime startDateTime, ZonedDateTime endDateTime, ZonedDateTime EWStartDate, ZonedDateTime NSStartDate) {
    this.datas1 = datas1;
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.EWStartDate = EWStartDate;
    this.NSStartDate = NSStartDate;
    doDraw(RATIO_ARRAY[comboBox.getSelectionModel().getSelectedIndex()]);
  }

  /**
   * Chart 의 Contents들을 그려주는 메소드
   * @param ratio
   */
  private void doDraw(int ratio) {
    int startDateInstantSecond = getInstantSeconds(startDateTime);
    int endDateInstantSecond = getInstantSeconds(endDateTime);
    entireTime = endDateInstantSecond - startDateInstantSecond;
    entireWidth = getChartWidth() * ratio;

    coreChart.setContent(drawEntireBox());
    drawStartEndDateBar();
    drawRefDateData();
    drawDatas();
//    drawDatas(entireTime, entireTime);
    ZoneId zoneId = ZoneId.of("UTC+1");

    ZonedDateTime TestTime = ZonedDateTime.of(2015, 11, 14, 23, 30, 59, 1234, zoneId);
    drawLineToChart(getEpochDay(TestTime), getSecondsOfDay(TestTime));
    ZonedDateTime TestTime2 = ZonedDateTime.of(2015, 11, 16, 23, 30, 59, 1234, zoneId);
    drawBarToChart(getEpochDay(TestTime2), getSecondsOfDay(TestTime2));
//    drawRefDateData();
//    drawDatas();
  }

  /**
   * Scroll Pane에 들어갈 전체 Box를 생성하는 메소드
   * @return EntireBox
   */
  private StackPane drawEntireBox() {
    ZonedDateTime tempDateTime = startDateTime;
    entireContainer = new StackPane();
    entireBox = new HBox();
    labelBoard = new AnchorPane();
    VBox cellBox;
    VBox dateBoard;
    entireBoxHeight = getChartHeight() - 20;
    entireBox.setPrefSize(entireWidth, entireBoxHeight);
    labelBoard.setPrefSize(entireWidth, entireBoxHeight);

    cellBox = new VBox();
    cellBox.setPrefSize((double) (entireWidth * (ONE_DAYS_SECONDS - getSecondsOfDay(startDateTime))) / entireTime, entireBoxHeight);
    entireBox.getChildren().add(cellBox);
    drawChartBoards(cellBox);
    dateBoard = new VBox();

    dateBoard.setPrefSize(cellBox.getPrefWidth(), 20);
    tempDateTime = tempDateTime.plusDays(1);
    dateBoard.getStyleClass().add("start-date-cell-box");
    cellBox.getChildren().add(dateBoard);

    for (int i = 0; i < getEpochDay(endDateTime) - getEpochDay(startDateTime) - 1; i++) {
      cellBox = new VBox();
      cellBox.setPrefSize((double) (entireWidth * ONE_DAYS_SECONDS) / entireTime, entireBoxHeight);
      entireBox.getChildren().add(cellBox);
      drawChartBoards(cellBox);
      dateBoard = new VBox();
      dateBoard.setPrefSize(cellBox.getPrefWidth(), 20);
      addDateLabel(dateBoard, tempDateTime);
      tempDateTime = tempDateTime.plusDays(1);
      dateBoard.getStyleClass().add("normal-date-cell-box");
      cellBox.getChildren().add(dateBoard);
    }
    cellBox = new VBox();
    cellBox.setPrefSize((double) (entireWidth * getSecondsOfDay(endDateTime)) / entireTime, entireBoxHeight);
    entireBox.getChildren().add(cellBox);
    drawChartBoards(cellBox);
    dateBoard = new VBox();
    dateBoard.setPrefSize(cellBox.getPrefWidth(), 20);
    dateBoard.getStyleClass().add("end-date-cell-box");
    cellBox.getChildren().add(dateBoard);

    entireContainer.getChildren().addAll(entireBox, labelBoard);
    return entireContainer;
  }

  private void drawStartEndDateBar() {
    Line startLine = drawBarToChart(getEpochDay(startDateTime),0);
    attachLabel((getXCoodinateFromBox(getEpochDay(startDateTime)) + 10), 15, "ST" );
    startLine.getStyleClass().add("bar-start-date");
    startLine.setLayoutX(4);
    Line endLine = drawBarToChart(getEpochDay(endDateTime), 0);
    attachLabel((getXCoodinateFromBox(getEpochDay(endDateTime)) - 14), 15, "ET" );
    endLine.getStyleClass().add("bar-end-date");
    endLine.setLayoutX(4);
  }

  private void drawChartBoards(VBox cellBox) {
    AnchorPane board = new AnchorPane();
    board.setPrefSize(cellBox.getPrefWidth(), cellBox.getPrefHeight() - 20);
    cellBox.getChildren().add(board);
    board.setTranslateY(cellBox.getPrefHeight() - 20);
  }

  private void addDateLabel(VBox vBox, ZonedDateTime dateTime) {
    vBox.setAlignment(Pos.CENTER_LEFT);
    DateTimeFormatter format = DateTimeFormatter
            .ofPattern("MM-dd");
    Label date = new Label(dateTime.format(format));
    date.setPrefSize(40, 20);
    vBox.getChildren().add(date);
    vBox.setMargin(date, new Insets(0, 0, 0, -date.getPrefWidth() / 2));
  }

  private void drawDatas() {
    if(datas1 != null) {
      for (ZonedDateTime time : datas1) {
        drawLineToChart(getEpochDay(time), getSecondsOfDay(time));
      }
    }
  }

  private void drawRefDateData() {
    if(EWStartDate != null && NSStartDate != null) {
      for (ZonedDateTime i = EWStartDate; endDateTime.compareTo(i) == 1; i = i.plusDays(6)) {
        Line line = drawBarToChart(getEpochDay(i), getSecondsOfDay(i));
        line.getStyleClass().add("bar-ew");
        attachLabel((getXCoodinateFromBox(getEpochDay(i)) + line.getLayoutX()), 15, "EW");
      }
      for (ZonedDateTime i = NSStartDate; endDateTime.compareTo(i) == 1; i = i.plusDays(6)) {
        Line line = drawBarToChart(getEpochDay(i), getSecondsOfDay(i));
        attachLabel((getXCoodinateFromBox(getEpochDay(i)) + line.getLayoutX()), 15, "NS");
        line.getStyleClass().add("bar-ns");
      }
    }
  }

  private void drawLineToChart(int epochDay, int secondOfDay) {
    VBox currentBox = getMatchBoxByCoordinate(epochDay);
    AnchorPane boxCell = (AnchorPane) currentBox.getChildren().get(0);
    Line line = new Line(0, -14, 0, 0);
    line.setLayoutX(epochDay - getEpochDay(startDateTime) == 0 ?
            getFirstBoxDataLayoutX(currentBox.getPrefWidth(), secondOfDay) : getDataLayoutX(currentBox.getPrefWidth(), secondOfDay));
    line.setStrokeWidth(2);
    line.setLayoutX(line.getLayoutX() - 1);
    line.setStroke(Color.RED);
    boxCell.getChildren().add(line);
  }

  private Line drawBarToChart(int epochDay, int secondOfDay) {
    VBox currentBox = getMatchBoxByCoordinate(epochDay);
    AnchorPane boxCell = (AnchorPane) currentBox.getChildren().get(0);
    Line line = new Line(0, -24, 0, 0);
    line.setLayoutX(epochDay - getEpochDay(startDateTime) == 0 ?
            getFirstBoxDataLayoutX(currentBox.getPrefWidth(), secondOfDay) - 3 : getDataLayoutX(currentBox.getPrefWidth(), secondOfDay) - 3);
    line.setStrokeWidth(6);
    boxCell.getChildren().add(line);
    return line;
  }

  private VBox getMatchBoxByCoordinate(int epoch_day) {
    return (VBox) entireBox.getChildren().get(epoch_day - getEpochDay(startDateTime));
  }

  private double getDataLayoutX(double width, int dataSecond) {
    double second = (width * entireTime) / entireWidth;
    return (width * dataSecond) / second;
  }

  private double getFirstBoxDataLayoutX(double width, int dataSecond) {
    double second = (width * entireTime) / entireWidth;
    return (width * (dataSecond - (ONE_DAYS_SECONDS - second))) / second;
  }

  private int getXCoodinateFromBox(int epoch_day){
    int x = 0;
    for(int i = 0; i < epoch_day - getEpochDay(startDateTime); i++){
      VBox vBox = (VBox)entireBox.getChildren().get(i);
      x += vBox.getPrefWidth();
    }
    return x;
  }

  private void attachLabel(double x, int y, String text){
    Label label = new Label(text);
    label.setLayoutX(x);
    label.setLayoutY(y);
    labelBoard.getChildren().add(label);
  }

  private int getEpochDay(ZonedDateTime time) {
    return (int) time.getLong(ChronoField.EPOCH_DAY);
  }

  private int getInstantSeconds(ZonedDateTime time) {
    return (int) time.getLong(ChronoField.INSTANT_SECONDS);
  }

  private int getSecondsOfDay(ZonedDateTime time){
    return (int) time.getLong(ChronoField.SECOND_OF_DAY);
  }
}
