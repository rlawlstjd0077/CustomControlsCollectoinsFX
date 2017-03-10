package ui.screen.fdsmain;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * Created by GSD on 2017-03-03.
 */
public class FDSMainViewModel {

  /**
   * 상단의 TableView 데이터 리스트 정의
   */
  public ObservableList<FDSMainTableRowViewModel> fdsMainTableRowViewModelList = FXCollections.observableArrayList(
    new FDSMainTableRowViewModel(new SimpleStringProperty("Report"), null, new SimpleStringProperty("Dsads"), new SimpleBooleanProperty(true)),
    new FDSMainTableRowViewModel(new SimpleStringProperty("Orbit Sample Data"), null, new SimpleStringProperty("Dsads"), new SimpleBooleanProperty(false)),
    new FDSMainTableRowViewModel(new SimpleStringProperty("Report"), null, new SimpleStringProperty("Dsads"), new SimpleBooleanProperty(false)),
    new FDSMainTableRowViewModel(new SimpleStringProperty("Report"), null, new SimpleStringProperty("Dsads"), new SimpleBooleanProperty(false))
  );

  /**
   * Schedule Item 들의 데이터 리스트 정의
   */
  public ObservableList<FDSMainScheduleItemViewModel> sunEclipseList = FXCollections.observableArrayList();
  public ObservableList<FDSMainScheduleItemViewModel> moonEclipseList = FXCollections.observableArrayList();
  public ObservableList<FDSMainScheduleItemViewModel> sensorIntuslonList = FXCollections.observableArrayList();
  public ObservableList<FDSMainScheduleItemViewModel> orbitCrossingList = FXCollections.observableArrayList();
  public ObservableList<FDSMainScheduleItemViewModel> sunInferfaceList = FXCollections.observableArrayList();

  public ObservableList<FDSMainScheduleItemViewModel> SKList = FXCollections.observableArrayList();
  public ObservableList<FDSMainScheduleItemViewModel> WOLList = FXCollections.observableArrayList();

  /**
   * Time, Name Fomatter 정의
   */
  public static final DateTimeFormatter DATE_FORMATER = new DateTimeFormatterBuilder()
    .appendValue(ChronoField.YEAR, 4)
    .appendLiteral("-")
    .appendValue(ChronoField.MONTH_OF_YEAR, 2)
    .optionalStart()
    .appendLiteral("-")
    .appendValue(ChronoField.DAY_OF_MONTH, 2)
    .toFormatter();
  public static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
    .appendValue(ChronoField.HOUR_OF_DAY, 2)
    .appendLiteral(':')
    .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
    .optionalStart()
    .appendLiteral(':')
    .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
    .toFormatter();

  /**
   * Indicator들의 % 정의
   */
  private SimpleIntegerProperty firstTimeIndicatorProperty = new SimpleIntegerProperty(70);
  private SimpleIntegerProperty secondTimeIndicatorProperty = new SimpleIntegerProperty(50);

  /**
   * start, end Date의 데이터 정의
   */
  private ObjectProperty<ZonedDateTime> startDateProperty = new SimpleObjectProperty<>(ZonedDateTime.parse("2017-03-06T12:30:40Z[GMT]"));
  private ObjectProperty<ZonedDateTime> endDateProperty = new SimpleObjectProperty<>(ZonedDateTime.parse("2017-03-30T12:30:40Z[GMT]"));

  /**
   * Getter 정의
   * @return
   */
  public SimpleIntegerProperty firstTimeIndicatorPropertyProperty() {
    return firstTimeIndicatorProperty;
  }

  public SimpleIntegerProperty secondTimeIndicatorPropertyProperty() {
    return secondTimeIndicatorProperty;
  }

  public ObjectProperty<ZonedDateTime> startDatePropertyProperty() {
    return startDateProperty;
  }
  public ObjectProperty<ZonedDateTime> endDatePropertyProperty() {
    return endDateProperty;
  }
}
