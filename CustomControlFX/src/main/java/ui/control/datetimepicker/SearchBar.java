package ui.control.datetimepicker;

import commons.ui.UiUtil;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.converter.NumberStringConverter;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 검색 조건(시간, 페이지 사이즈)를 제공하는 컨트롤.
 */
public class SearchBar extends GridPane {
  public static final String DAY = "1 Day";
  public static final String WEEK = "1 Week";
  public static final String MONTH = "1 Month";
  public static final String YEAR = "1 Year";
  private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd    HH : mm : ss", Locale.KOREA);

  @FXML
  private ImageView configButton;

  @FXML
  private ComboBox<String> periodComboBox;

  @FXML
  private DateTimePicker startTimePicker;

  @FXML
  private DateTimePicker endTimePicker;

  @FXML
  private Button searchButton;

  private String selectedValue;
  private Date startDate;
  private ObservableList<String> list = FXCollections.observableArrayList(DAY, WEEK, MONTH, YEAR);
  private SimpleIntegerProperty pageSize = new SimpleIntegerProperty(50);

  /**
   * 생성자.
   *
   * @throws IOException FXML 오류.
   */
  public SearchBar() throws IOException {
    UiUtil.loadFxml(this);

    ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC)
      .withHour(0)
      .withMinute(0)
      .withSecond(0)
      .withNano(0);

    startTimePicker.setDatetime(now);
    endTimePicker.setDatetime(now.plusDays(1));
    periodComboBox.setItems(list);
    periodComboBox.setValue(DAY);

    configButton.setOnMouseClicked(event -> handleShowConfig());
    searchButton.setOnMouseClicked(event -> handleSearch());
    periodComboBox.setOnAction(event -> handlePeriodChanged());
  }

  private void handleSearch() {
    if (isInvalidDateInterval(startTimePicker.getDate(), endTimePicker.getDate())) {
      //정상적인 처리
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error Dialog");
      alert.setHeaderText(null);
      alert.setContentText("Date Interval is invalid.");
      alert.showAndWait();
    }
  }

  public boolean isInvalidDateInterval(Date start, Date end) {
    Calendar cal1 = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();
    cal1.setTime(start);
    cal2.setTime(end);
    return cal1.compareTo(cal2) < 0;
  }

  private void handlePeriodChanged() {
    startDate = startTimePicker.getDate();
    selectedValue = periodComboBox.getValue().toString();
    Calendar cal = modifyDate();
    String modifiedDate = FORMAT.format(cal.getTime());
    DateTimeInfo endDateTimeInfo = new DateTimeInfo();
    endDateTimeInfo.setDate(modifiedDate);
    endTimePicker.setComboBoxText(endDateTimeInfo);
  }

  private Calendar modifyDate() {
    Calendar cal = new GregorianCalendar();
    cal.setTime(startDate);
    switch (selectedValue) {
      case DAY:
        cal.add(Calendar.DATE, 1);
        break;
      case WEEK:
        cal.add(Calendar.DATE, 7);
        break;
      case MONTH:
        cal.add(Calendar.MONTH, 1);
        break;
      case YEAR:
        cal.add(Calendar.YEAR, 1);
        break;
      default:
        break;
    }
    return cal;
  }

  private void handleShowConfig() {
    TextField pageSizeText = new TextField();
    pageSizeText.setPrefWidth(70);
    pageSizeText.setEditable(true);
    Bindings.bindBidirectional(pageSizeText.textProperty(),
      pageSize,
      new NumberStringConverter());

    GridPane pane = new GridPane();
    pane.setPadding(new Insets(10, 10, 10, 10));
    pane.setVgap(5.0);
    pane.setHgap(5.0);
    pane.add(new Label("Paging Size"), 0, 0);
    pane.add(pageSizeText, 0, 1);

    PopOver popOver = new PopOver();
    popOver.setArrowLocation(PopOver.ArrowLocation.TOP_CENTER);
    popOver.setDetachable(false);
    popOver.setContentNode(pane);
    popOver.show(configButton);
  }

  public DateTimePicker getStartTimePicker() {
    return startTimePicker;
  }

  public DateTimePicker getEndTimePicker() {
    return endTimePicker;
  }

  public Button getSearchButton() {
    return searchButton;
  }

  public ComboBox getPeriodComboBox() {
    return periodComboBox;
  }

  public int getPageSize() {
    return pageSize.get();
  }
}
