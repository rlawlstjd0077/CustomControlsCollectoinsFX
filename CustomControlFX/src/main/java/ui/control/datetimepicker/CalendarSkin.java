package ui.control.datetimepicker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.*;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * DatePicker의 Calendar View 구현 부분
 * Created by GSD
 */
public class CalendarSkin extends SkinBase<CalendarControl> {
  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMMM yyyy", Locale.ENGLISH);
  private final Date currentDate;
  private final Label month;
  private final BorderPane content;
  private int selectedYear;
  private int selectedMonth;
  private int selectedDay;
  private String selectedTime;
  private CalendarCell selectedDayCellTemp;
  private CalendarCell selectedDayCell;
  private CalendarCell todayCell;
  private Date selectedDate;
  private Date selectedDateTemp;
  private DateTimeInfo dateTimeInfo;
  private String timeTemp;

  /**
   * Calendar Control을 parameter로 받아 기능 사용
   *
   * @param calendarControl
   */
  public CalendarSkin(CalendarControl calendarControl) {
    super(calendarControl);
    this.dateTimeInfo = new DateTimeInfo();
    this.currentDate = calendarControl.getCurrentDate();          // this currentDate is the selected currentDate

    // content the part of BorderPane
    HBox topBar = new HBox();
    final DatePickerPane calendarPane = new DatePickerPane(currentDate);               // create center content
    VBox bottomBar = new VBox();

        /* create top content */
    {
      month = new Label(simpleDateFormat.format(calendarPane.getShownMonth()));

      Button monthBack = new Button();
      monthBack.getStyleClass().add("left-button");
      monthBack.addEventHandler(ActionEvent.ACTION, event -> calendarPane.forward(-1));

      Button monthForward = new Button();
      monthForward.getStyleClass().add("right-button");
      monthForward.addEventHandler(ActionEvent.ACTION, event -> calendarPane.forward(1));
      topBar.setHgrow(month, Priority.ALWAYS);
      month.setMaxWidth(Double.MAX_VALUE);
      month.setAlignment(Pos.BOTTOM_CENTER);
      topBar.setMargin(month, new Insets(11, 0, 0, 0));
      topBar.setMargin(monthBack, new Insets(7, 0, 0, 0));
      topBar.setMargin(monthForward, new Insets(7, 0, 0, 0));
      topBar.getChildren().addAll(monthBack, month, monthForward);
      topBar.getStyleClass().setAll("top-bar");
      topBar.setPrefHeight(40);
    }
        /*create bottom box content*/
    {
      bottomBar.getStyleClass().setAll("bottom-bar");
      bottomBar.setPrefHeight(124);

      HBox topBox = new HBox();
      HBox bottomBox = new HBox();

      topBox.setPrefHeight(74);
      bottomBox.setPrefHeight(50);

      Label time = new Label("Time : ");
      ComboBox<String> timeCombo = new ComboBox<>();

      time.setPrefWidth(75);
      time.setPrefHeight(16);
      timeCombo.setPrefWidth(167);
      timeCombo.setPrefHeight(36);

      ObservableList<String> observableList = FXCollections.observableArrayList(getTimeList(calendarControl.getTimeIntervalMin()));
      timeCombo.setItems(observableList);
      this.timeTemp = getTimeFromForm(currentDate);
      timeCombo.setVisibleRowCount(5);
      timeCombo.setValue(timeTemp);
      selectedTime = timeTemp;
      topBox.setMargin(time, new Insets(30, 0, 0, 20));
      topBox.setMargin(timeCombo, new Insets(24, 0, 0, -10));
      timeCombo.setOnAction(event -> selectedTime = timeCombo.getValue().substring(0, 13));
      topBox.getChildren().addAll(time, timeCombo);

      Button okButton = new Button("OK");
      Button cancelButton = new Button("CANCEL");
      okButton.setPrefWidth(80);
      cancelButton.setPrefWidth(80);
      bottomBox.setMargin(okButton, new Insets(5, 0, 0, 30));
      bottomBox.setMargin(cancelButton, new Insets(5, 0, 0, 50));

      okButton.setOnAction(event -> {
        dateTimeInfo.setDate(selectedYear, selectedMonth, selectedDay, selectedTime);
        calendarControl.onSelectDate(dateTimeInfo);
        selectedDayCellTemp = selectedDayCell;
        selectedDateTemp = selectedDate;
      });
      cancelButton.setOnAction(event -> {
        Date temp = calendarControl.getDateFromField();
        timeCombo.setValue(getTimeFromForm(temp));
        calendarControl.closeCalendar();
        if (isSameMonth(selectedDate, currentDate)) {
          calendarControl.closeCalendar();
          selectedDayCell.getStyleClass().add("calendar-cell");
          selectedDayCell.getStyleClass().remove("calendar-cell-selected");
          selectedDayCellTemp.getStyleClass().remove("calendar-cell");
          selectedDayCellTemp.getStyleClass().add("calendar-cell-selected");
          selectedDate = selectedDateTemp;
          selectedDayCell = selectedDayCellTemp;
        } else {
          selectedDayCell.getStyleClass().add("calendar-cell");
          selectedDayCell.getStyleClass().remove("calendar-cell-selected");
          selectedDate = currentDate;
        }
      });
      bottomBox.getChildren().addAll(okButton, cancelButton);

      bottomBar.getChildren().addAll(topBox, bottomBox);
    }
    // use a BorderPane to Layout the view
    content = new BorderPane();
    content.setTop(topBar);
    content.setCenter(calendarPane);
    content.setBottom(bottomBar);
    getChildren().add(content);

  }

  private static boolean isSameDay(Calendar cal1, Calendar cal2) {
    if (cal1 == null || cal2 == null) {
      throw new IllegalArgumentException("The dates must not be null");
    }
    return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
      && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
      && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
  }

  private static boolean isToday(Calendar cal) {
    return isSameDay(cal, Calendar.getInstance());
  }

  private static boolean isSameMonth(Date date1, Date date2) {
    Calendar cal1 = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();
    cal1.setTime(date1);
    cal2.setTime(date2);

    return cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) ? true : false;
  }

  public ArrayList<String> getTimeList(int minute) {
    ArrayList<String> timeList = new ArrayList<>();
    int divideVal = 1440 / minute;
    for (int i = 0; i < divideVal; i++) {
      int time = i * minute;
      timeList.add(getTimeFromForm(time / 60, time % 60, 00));
    }
    return timeList;
  }

  public String getTimeFromForm(int hour, int minute, int second) {
    return String.format("%02d", hour) + " : " + String.format("%02d", minute) + " : " + String.format("%02d ", second) + (hour <= 12 ? "AM" : "PM");
  }

  public String getTimeFromForm(Date date) {
    Calendar cal = GregorianCalendar.getInstance();
    cal.setTime(date);

    int hour = cal.get(Calendar.HOUR);
    int minute = cal.get(Calendar.MINUTE);
    int second = cal.get(Calendar.SECOND);

    return String.format("%02d", hour) + " : " + String.format("%02d", minute) + " : " + String.format("%02d ", second) + (hour <= 12 ? "AM" : "PM");
  }

  /**
   * 요일, 일자의 Cell
   */
  private static class CalendarCell extends StackPane {

    private final Date date;
    private Label day;

    public CalendarCell(Date day, String text) {
      this.date = day;
      this.day = new Label(text);
    }

    public Date getDate() {
      return date;
    }

    public void setDay(String text) {
      day = new Label(text);
    }

    public void addCell() {
      getChildren().add(day);
    }
  }

  class DatePickerPane extends Region {
    private final Calendar cal;
    // this is used to format the day cells
    private final SimpleDateFormat sdf = new SimpleDateFormat("d");
    private int rows, columns;

    public DatePickerPane(Date date) {
      setPrefSize(271, 236);

      this.columns = 7;
      this.rows = 5;

      cal = Calendar.getInstance();
      Date helperDate = new Date(date.getTime());
      cal.setTime(helperDate);

      selectedDate = date;
      selectedDateTemp = selectedDate;
      refresh();
    }

    public void forward(int i) {
      cal.add(Calendar.MONTH, i);
      month.setText(simpleDateFormat.format(cal.getTime()));
      refresh();
    }

    /**
     * 처음 Load 되거나 Month 가 바뀌었을 때 refresh() 동작
     */
    private void refresh() {
      super.getChildren().clear();
      this.rows = 6;
      selectedYear = cal.get(Calendar.YEAR);
      selectedMonth = cal.get(Calendar.MONTH) + 1;
      selectedDay = cal.get(Calendar.DAY_OF_MONTH);
      Date copy = new Date(cal.getTime().getTime());

      // Display a styleable row of localized weekday symbols
      DateFormatSymbols symbols = new DateFormatSymbols(Locale.ENGLISH);
      String[] dayNames = symbols.getShortWeekdays();

      for (int i = 1; i < 8; i++) { // array starts with an empty field
        CalendarCell calendarCell = new CalendarCell(cal.getTime(), dayNames[i].substring(0, 2));
        calendarCell.addCell();
        calendarCell.getStyleClass().add("weekday-cell");
        super.getChildren().add(calendarCell);
      }

      cal.set(Calendar.DAY_OF_MONTH, 1);
      final int month = cal.get(Calendar.MONTH) + 1;

      int weekday = cal.get(Calendar.DAY_OF_WEEK);

      if (weekday >= Calendar.FRIDAY) {
        Calendar check = Calendar.getInstance();
        check.setTime(new Date(cal.getTime().getTime()));
        int lastDate = check.getActualMaximum(Calendar.DATE);
        if ((weekday + lastDate) >= 37) {
          rows = 7;
        }
      }

      //The first of DAY_OF_WEEK is Sunday
      cal.set(Calendar.DAY_OF_WEEK, 1);

      // used to identify and style the cell with the selected currentDate;
      Calendar testSelected = Calendar.getInstance();
      testSelected.setTime(selectedDate);

      for (int i = 0; i < (rows); i++) {
        for (int j = 0; j < columns; j++) {
          String formatted = sdf.format(cal.getTime());
          final CalendarCell dayCell = new CalendarCell(cal.getTime(), formatted);
          dayCell.getStyleClass().add("calendar-cell");
          if (cal.get(Calendar.MONTH) + 1 != month) {
            dayCell.setDay("");
            dayCell.addCell();
            dayCell.getStyleClass().setAll("calendar-cell-inactive");
          } else {
            dayCell.addCell();
            if (isSameDay(testSelected, cal)) {
              dayCell.getStyleClass().add("calendar-cell-selected");
              selectedDayCell = dayCell;
              selectedDayCellTemp = selectedDayCell;
            }
//                        if (isToday(cal)) {
//                            dayCell.getStyleClass().add("calendar-cell-today");
//                            todayCell = dayCell;
//                        }
          }

          dayCell.setOnMouseClicked(arg0 -> {
            Calendar currentCal = Calendar.getInstance();
            currentCal.setTime(dayCell.getDate());
            selectedYear = currentCal.get(Calendar.YEAR);
            selectedMonth = currentCal.get(Calendar.MONTH) + 1;
            selectedDay = currentCal.get(Calendar.DAY_OF_MONTH);
            if (selectedDayCell != null) {
              selectedDayCell.getStyleClass().add("calendar-cell");
              selectedDayCell.getStyleClass().remove("calendar-cell-selected");
            }
            selectedDate.setTime(dayCell.getDate().getTime());
            dayCell.getStyleClass().remove("calendar-cell");
            dayCell.getStyleClass().add("calendar-cell-selected");
            selectedDayCell = dayCell;
          });

          super.getChildren().add(dayCell);
          cal.add(Calendar.DATE, 1);
        }
      }
      cal.setTime(copy);
    }

    @Override
    protected ObservableList<Node> getChildren() {
      return FXCollections.unmodifiableObservableList(super.getChildren());
    }

    public Date getShownMonth() {
      return cal.getTime();
    }

    @Override
    protected void layoutChildren() {
      ObservableList<Node> children = getChildren();
      double width = getWidth();
      double height = getHeight();

      double cellWidth = (width / (columns));
      double cellHeight = height / (rows);

      for (int i = 0; i < (rows); i++) {
        for (int j = 0; j < (columns); j++) {
          if (children.size() <= ((i * (columns)) + j)) {
            break;
          }
          Node get = children.get((i * (columns)) + j);
          layoutInArea(get, j * cellWidth, i * cellHeight, cellWidth, cellHeight, 0.0d, HPos.LEFT, VPos.TOP);
        }
      }
    }
  }
}
