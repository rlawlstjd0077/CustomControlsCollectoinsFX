package sample.scrollchart;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

/**
 * Created by GSD on 2017-02-01.
 */
public class ScrollChart extends StackPane {
  private ArrayList<ZonedDateTime> datas1;
  private int currentRatio;
  private ComboBox<String> comboBox;
  private ZonedDateTime startDateTime;
  private ZonedDateTime endDateTime;
  private HBox cellBox;
  private ScrollPane coreChart;
  private final int[] RATIO_ARRAY = {1, 2, 5, 10};
  private SimpleIntegerProperty chartWidth;
  private SimpleIntegerProperty chartHeight;
  private int entireTime;
  private int entireWidth;
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
      chartHeight = new SimpleIntegerProperty(this, "height", 70);
    }
    return chartHeight;
  }

  public void setChartHeight(int height) {
    chartHeightProperty().setValue(height);
  }

  private int getChartHeight() {
    return chartHeight == null ? 70 : chartHeightProperty().getValue();
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
    doDraw(comboBox.getSelectionModel().getSelectedIndex());
  }

  public void setDatas(ArrayList<ZonedDateTime> datas1, ZonedDateTime startDateTime, ZonedDateTime endDateTime, ZonedDateTime EWStartDate, ZonedDateTime NSStartDate) {
    this.datas1 = datas1;
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
    this.EWStartDate = EWStartDate;
    this.NSStartDate = NSStartDate;
    doDraw(comboBox.getSelectionModel().getSelectedIndex());
    startDateTime.getMinute();
  }

  private void doDraw(int ratio) {
    int startDateInstantSecond = (int) startDateTime.getLong(ChronoField.INSTANT_SECONDS);
    int endDateInstantSecond = (int) endDateTime.getLong(ChronoField.INSTANT_SECONDS);
    entireTime = endDateInstantSecond - startDateInstantSecond;
    entireWidth = getChartWidth() * ratio;

    coreChart.setContent(drawEntireBox());
//    drawDatas(entireTime, entireTime);
  }

  private HBox drawEntireBox() {
    HBox entireBox = new HBox();
    entireBox.setPrefSize(entireWidth, getChartHeight());

    cellBox = new HBox();
    cellBox.setPrefSize((entireWidth * startDateTime.getLong(ChronoField.SECOND_OF_DAY)) / entireTime, getChartHeight());
    entireBox.getChildren().add(cellBox);

    for (int i = 0; i < endDateTime.getLong(ChronoField.EPOCH_DAY) - startDateTime.getLong(ChronoField.EPOCH_DAY) - 2; i++) {
      cellBox = new HBox();
      cellBox.setPrefSize((entireWidth * 86400) / entireTime, getChartHeight());
      entireBox.getChildren().add(cellBox);
    }
    cellBox = new HBox();
    cellBox.setPrefSize((entireWidth * endDateTime.getLong(ChronoField.SECOND_OF_DAY)) / entireTime, getChartHeight());
    entireBox.getChildren().add(cellBox);

    return entireBox;
  }

  public void drawDatas() {
    for (ZonedDateTime time : datas1) {
      drawLineToChart((int)time.getLong(ChronoField.EPOCH_DAY));
    }
  }

  public void drawRefDateData() {
    for(int i = 0; i < 2; i++){

    }
  }

  public void drawLineToChart(int epoch_day){
    HBox entireBox = (HBox) coreChart.getContent();
    HBox currentBox = (HBox) entireBox.getChildren().get((int)(epoch_day - startDateTime.getLong(ChronoField.EPOCH_DAY)));
    AnchorPane boxCell = (AnchorPane) currentBox.getChildren().get(0);
    Line line = new Line(0, -5, 5, 0);
    line.setLayoutX(getDataXCoordinate((int)currentBox.getPrefWidth()));
    line.setLayoutY(5);
    boxCell.getChildren().add(line);
  }

  public int getDataXCoordinate(int width){
    int second = (width * entireTime) / entireWidth;
    return (width * second) / entireTime;
  }
}
