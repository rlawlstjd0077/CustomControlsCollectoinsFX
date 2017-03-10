package ui.screen.fdsmain;


import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import ui.UiUtil;
import ui.control.datepicker.DatePickerControl;
import ui.screen.common.ScheduleDateTime;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GSD on 2017-03-03.
 */
public class FDSMainController extends GridPane {
  @FXML
  private VBox fdsMainDatePickerPane;
  @FXML
  private Button fdsMainDatePickerAddMonthButton;
  @FXML
  private Button fdsMainDatePickerMinusMonthButton;
  @FXML
  private AnchorPane firstTimeTablePane;
  @FXML
  private AnchorPane secondTimeTablePane;
  @FXML
  private HBox firstTimeTableHBox;
  @FXML
  private HBox secondTimeTableHBox;
  @FXML
  private AnchorPane sunEclipsePane;
  @FXML
  private AnchorPane moonEclipsePane;
  @FXML
  private AnchorPane sensorIntuslonPane;
  @FXML
  private AnchorPane orbitCrossingPane;
  @FXML
  private AnchorPane sunInterfacePane;
  @FXML
  private AnchorPane WOLPane;
  @FXML
  private AnchorPane SKPane;
  @FXML
  private Label firstTimeLabel;
  @FXML
  private Label secondTimeLabel;
  @FXML
  private AnchorPane firstTimeIndicator;
  @FXML
  private AnchorPane secondTimeIndicator;
  @FXML
  private Label firstListTitleLabel;
  @FXML
  private TableView<FDSMainTableRowViewModel> firstListTableView;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> firstListFirstColumn;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> firstListSecondColumn;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> firstListThirdColumn;
  @FXML
  private Label secondListTitleLabel;
  @FXML
  private TableView<FDSMainTableRowViewModel> secondListTableView;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> secondListFirstColumn;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> secondListSecondColumn;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> secondListThirdColumn;
  @FXML
  private Label thirdListTitleLabel;
  @FXML
  private TableView<FDSMainTableRowViewModel> thirdListTableView;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> thirdListFirstColumn;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> thirdListSecondColumn;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> thirdListThirdColumn;
  @FXML
  private Label fourthListTitleLabel;
  @FXML
  private TableView<FDSMainTableRowViewModel> fourthListTableView;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> fourthListFirstColumn;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> fourthListSecondColumn;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> fourthListThirdColumn;
  @FXML
  private Label fifthListTitleLabel;
  @FXML
  private TableView<FDSMainTableRowViewModel> fifthTopListTableView;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> fifthTopListFirstColumn;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> fifthTopListSecondColumn;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> fifthTopListThirdColumn;
  @FXML
  private TableView<FDSMainTableRowViewModel> fifthBottomListTableView;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> fifthBottomListFirstColumn;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> fifthBottomListSecondColumn;
  @FXML
  private TableColumn<FDSMainTableRowViewModel, String> fifthBottomListThirdColumn;
  @FXML
  private Pane firstGaugeBar;

  private ZonedDateTime firstStartTime;
  private ZonedDateTime startTime;
  private ZonedDateTime endTime;

  private final int DAY_INSTANT_SECOND = 86400;
  private final int TERM_HOUR = 6;
  private final double DAY_WIDTH = 378;
  private final double SIX_HOUR_WIDTH = 94.5;
  private final double CELL_SIZE = 31.5;

  private FDSMainViewModel viewModel;
  private ChangeListener<String> timeChangeListner;

  private DatePickerControl datePicker;

  public FDSMainController() throws IOException {
    UiUtil.loadFxml(this);

    firstTimeTablePane.setClip(createFirstBoxClipRectangle());
    secondTimeTablePane.setClip(createSecondBoxClipRectangle());
    sunEclipsePane.setClip(createFirstBoxClipRectangle());
    moonEclipsePane.setClip(createFirstBoxClipRectangle());
    sensorIntuslonPane.setClip(createFirstBoxClipRectangle());
    orbitCrossingPane.setClip(createFirstBoxClipRectangle());
    sunInterfacePane.setClip(createFirstBoxClipRectangle());
    WOLPane.setClip(createSecondBoxClipRectangle());
    SKPane.setClip(createSecondBoxClipRectangle());
  }

  private Rectangle createFirstBoxClipRectangle() {
    Rectangle clipRect = new Rectangle();
    clipRect.widthProperty().bind(firstTimeTablePane.widthProperty());
    clipRect.heightProperty().bind(firstTimeTablePane.heightProperty());
    return clipRect;
  }

  private Rectangle createSecondBoxClipRectangle() {
    Rectangle clipRect = new Rectangle();
    clipRect.widthProperty().bind(secondTimeTablePane.widthProperty());
    clipRect.heightProperty().bind(secondTimeTablePane.heightProperty());
    return clipRect;
  }

  /**
   * FDSMain 화면과 ViewModel을 바인딩
   */
  public void bind(FDSMainViewModel viewModel) {
    this.viewModel = viewModel;

    refresh();
    timeChangeListner = (observable, oldValue, newValue) -> drawElement();
    ChangeListener<String> widthChangeListener = (observable, oldValue, newValue) -> {
      firstListTitleLabel.setTextFill(Color.WHITE);
      if(firstGaugeBar.getPrefWidth() < 373) {
        firstGaugeBar.setPrefWidth(firstGaugeBar.getPrefWidth() + 1);
      }
    };

    FacadeService.ui.viewModel.utcProperty().addListener(timeChangeListner);
    FacadeService.ui.viewModel.utcProperty().addListener(widthChangeListener);

    datePicker = new DatePickerControl(160, 40, 18, "form-text-dark", "calendar-form-button-dark", false);
    fdsMainDatePickerPane.getChildren().add(datePicker);

    fdsMainDatePickerAddMonthButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("arrow_add.png"))));
    fdsMainDatePickerAddMonthButton.setOnMouseClicked(event -> {
      datePicker.addMonth();
    });
    fdsMainDatePickerMinusMonthButton.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("arrow_minus.png"))));
    fdsMainDatePickerMinusMonthButton.setOnMouseClicked(event -> {
      datePicker.minusMonth();
    });


    firstListFirstColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    firstListFirstColumn.setCellFactory(tv -> new NameCellUpdater());
    firstListSecondColumn.setCellValueFactory(cellData -> cellData.getValue().firstIconProperty());
    firstListSecondColumn.setCellFactory(tv -> new FirstIconUpdater());
    firstListThirdColumn.setCellValueFactory(cellData -> cellData.getValue().secondIconProperty());
    firstListThirdColumn.setCellFactory(tv -> new SecondIconUpdater());
    firstListTableView.setItems(viewModel.fdsMainTableRowViewModelList);
    firstListTableView.setSelectionModel(null);
    removeTableHeader(firstListTableView);

    secondListFirstColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    secondListFirstColumn.setCellFactory(tv -> new NameCellUpdater());
    secondListSecondColumn.setCellValueFactory(cellData -> cellData.getValue().firstIconProperty());
    secondListSecondColumn.setCellFactory(tv -> new FirstIconUpdater());
    secondListThirdColumn.setCellValueFactory(cellData -> cellData.getValue().secondIconProperty());
    secondListThirdColumn.setCellFactory(tv -> new SecondIconUpdater());
    secondListTableView.setItems(viewModel.fdsMainTableRowViewModelList);
    secondListTableView.setSelectionModel(null);
    removeTableHeader(secondListTableView);

  }

  public void removeTableHeader(TableView<FDSMainTableRowViewModel> tableView ){
    tableView.widthProperty().addListener((source, oldWidth, newWidth) -> {
      Pane header = (Pane) tableView.lookup("TableHeaderRow");
      if (header.isVisible()) {
        header.setPrefHeight(0);
        header.setVisible(false);
      }
    });
  }

  private void refresh() {
    if (firstStartTime == null) {
      firstStartTime = viewModel.startDatePropertyProperty().get();
      startTime = firstStartTime;
      endTime = viewModel.endDatePropertyProperty().get();
    } else {
      startTime = endTime;
      endTime = viewModel.endDatePropertyProperty().get();
    }
    drawEntireContainer();
  }

  private void drawEntireContainer() {
    ZonedDateTime tempDate = startTime;
    for (int i = 0; i < endTime.getLong(ChronoField.EPOCH_DAY) - startTime.getLong(ChronoField.EPOCH_DAY); i++) {
      LocalTime tempTime = LocalTime.of(6, 0);
      AnchorPane firstTimeTableDayBox = new AnchorPane();
      AnchorPane secondTimeTableDayBox = new AnchorPane();
      firstTimeTableDayBox.setMinWidth(DAY_WIDTH);
      secondTimeTableDayBox.setMinWidth(DAY_WIDTH);
      firstTimeTableDayBox.setMaxWidth(DAY_WIDTH);
      secondTimeTableDayBox.setMaxWidth(DAY_WIDTH);
      firstTimeTableDayBox.setPrefWidth(DAY_WIDTH);
      secondTimeTableDayBox.setPrefWidth(DAY_WIDTH);
      for (int j = 0; j < 4; j++) {
        AnchorPane firstTimeTableDataBox = new AnchorPane();
        AnchorPane secondTimeTableDataBox = new AnchorPane();
        firstTimeTableDataBox.setPrefWidth(SIX_HOUR_WIDTH);
        firstTimeTableDataBox.setLayoutX(SIX_HOUR_WIDTH * j);
        firstTimeTableDayBox.setBottomAnchor(firstTimeTableDataBox, 0.0);
        firstTimeTableDayBox.setTopAnchor(firstTimeTableDataBox, 8.0);
        secondTimeTableDataBox.setPrefWidth(SIX_HOUR_WIDTH);
        secondTimeTableDataBox.setLayoutX(SIX_HOUR_WIDTH * j);
        secondTimeTableDayBox.setBottomAnchor(secondTimeTableDataBox, 0.0);
        secondTimeTableDayBox.setTopAnchor(secondTimeTableDataBox, 8.0);

        firstTimeTableDataBox.getChildren().add(setFirstCell(new Pane()));
        firstTimeTableDataBox.getChildren().add(setSecondCell(new Pane()));
        firstTimeTableDataBox.getChildren().add(setThirdCell(new Pane()));
        firstTimeTableDataBox.getChildren().add(setTimeLabel(new Label(), tempTime));
        firstTimeTableDayBox.getChildren().add(firstTimeTableDataBox);

        secondTimeTableDataBox.getChildren().add(setFirstCell(new Pane()));
        secondTimeTableDataBox.getChildren().add(setSecondCell(new Pane()));
        secondTimeTableDataBox.getChildren().add(setThirdCell(new Pane()));
        secondTimeTableDataBox.getChildren().add(setTimeLabel(new Label(), tempTime));
        tempTime = tempTime.plusHours(6);
        secondTimeTableDayBox.getChildren().add(secondTimeTableDataBox);
      }
      firstTimeTableDayBox.getChildren().add(setDayLabel(new HBox(), firstTimeTableDayBox, tempDate.format(FDSMainViewModel.DATE_FORMATER)));
      secondTimeTableDayBox.getChildren().add(setDayLabel(new HBox(), secondTimeTableDayBox, tempDate.format(FDSMainViewModel.DATE_FORMATER)));
      tempDate = tempDate.plusDays(1);

      firstTimeTableHBox.getChildren().add(firstTimeTableDayBox);
      secondTimeTableHBox.getChildren().add(secondTimeTableDayBox);
    }
  }

  private HBox setDayLabel(HBox hBox, AnchorPane parent, String text) {
    hBox.setPrefSize(100, 50);
    hBox.setAlignment(Pos.TOP_LEFT);
    parent.setLeftAnchor(hBox, 0.0);
    parent.setTopAnchor(hBox, -22.0);
    Line line = new Line(0, 0, 0, 48);
    line.setStrokeWidth(1);
    line.setStroke(Paint.valueOf("#8491A6"));
    Line symbol = new Line(0, 0, 0, 12);
    symbol.setStrokeWidth(6);
    symbol.setStroke(Paint.valueOf("#435575"));
    Label date = new Label(text);
    hBox.setMargin(date, new Insets(0, 0, 0, 6));
    hBox.getChildren().addAll(line, symbol, date);
    return hBox;
  }

  private Pane setFirstCell(Pane pane) {
    pane.setPrefWidth(CELL_SIZE);
    AnchorPane.setTopAnchor(pane, 6.0);
    AnchorPane.setBottomAnchor(pane, 0.0);
    pane.getStyleClass().add("primary-branch-line");
    return pane;
  }

  private Pane setSecondCell(Pane pane) {
    pane.setPrefWidth(CELL_SIZE);
    pane.setLayoutX(CELL_SIZE);
    AnchorPane.setTopAnchor(pane, 20.0);
    AnchorPane.setBottomAnchor(pane, 0.0);
    pane.getStyleClass().add("normal-branch-line");
    return pane;
  }

  private Pane setThirdCell(Pane pane) {
    pane.setPrefWidth(CELL_SIZE);
    pane.setLayoutX(CELL_SIZE * 2);
    AnchorPane.setTopAnchor(pane, 20.0);
    AnchorPane.setBottomAnchor(pane, 0.0);
    pane.getStyleClass().add("normal-branch-line");
    return pane;
  }

  private Label setTimeLabel(Label label, LocalTime time) {
    label.setText(time.format(FDSMainViewModel.TIME_FORMATTER));
    label.setTranslateX(20.0);
    AnchorPane.setRightAnchor(label, 0.0);
    AnchorPane.setTopAnchor(label, -10.0);
    label.getStyleClass().add("timeLabel");
    return label;
  }

  private void drawElement() {
    final ZonedDateTime now = ZonedDateTime.now();
    final double currentPosition = getElementOffsetX(now);
    final double firstRemainDisplayWidth = (viewModel.firstTimeIndicatorPropertyProperty().get() * (DAY_WIDTH * 3)) / 100;
    final double secondRemainDisplayWidth = (viewModel.secondTimeIndicatorPropertyProperty().get() * (DAY_WIDTH * 3)) / 100;

    firstTimeLabel.setText(now.format(FDSMainViewModel.TIME_FORMATTER));
    secondTimeLabel.setText(now.format(FDSMainViewModel.TIME_FORMATTER));

    firstTimeTableHBox.setLayoutX(-(currentPosition - firstRemainDisplayWidth));
    secondTimeTableHBox.setLayoutX(-(currentPosition - secondRemainDisplayWidth));

    firstTimeIndicator.setLayoutX(firstRemainDisplayWidth);
    secondTimeIndicator.setLayoutX(secondRemainDisplayWidth);
    drawScheduleItem();
  }

  private void drawScheduleItem() {
    final List<FDSMainScheduleItemController> sunEclipseList = createScheduleIItemAsList(viewModel.sunEclipseList);
    final List<FDSMainScheduleItemController> moonEclipseList = createScheduleIItemAsList(viewModel.moonEclipseList);
    final List<FDSMainScheduleItemController> sensorIntuslonList = createScheduleIItemAsList(viewModel.sensorIntuslonList);
    final List<FDSMainScheduleItemController> orbitCrossingList = createScheduleIItemAsList(viewModel.orbitCrossingList);
    final List<FDSMainScheduleItemController> sunInterfaceList = createScheduleIItemAsList(viewModel.sunInferfaceList);

    final List<FDSMainScheduleItemController> SKList = createScheduleIItemAsList(viewModel.SKList);
    final List<FDSMainScheduleItemController> WOLList = createScheduleIItemAsList(viewModel.WOLList);

    Platform.runLater(() -> {
      sunEclipsePane.getChildren().setAll(sunEclipseList);
      moonEclipsePane.getChildren().setAll(moonEclipseList);
      sensorIntuslonPane.getChildren().setAll(sensorIntuslonList);
      orbitCrossingPane.getChildren().setAll(orbitCrossingList);
      sunInterfacePane.getChildren().setAll(sunInterfaceList);
      SKPane.getChildren().setAll(SKList);
      WOLPane.getChildren().setAll(WOLList);
    });
  }

  private List<FDSMainScheduleItemController> createScheduleIItemAsList(List<FDSMainScheduleItemViewModel> dataList) {
    ArrayList<FDSMainScheduleItemController> list = new ArrayList<>();
    for (FDSMainScheduleItemViewModel data : dataList) {
      FDSMainScheduleItemController controller = createEventItem(data.titleProperty().get(), data.itemTypeProperty().get(), data.scheduleProperty().get());
      list.add(controller);
    }
    return list;
  }

  private FDSMainScheduleItemController createEventItem(String title,
                                                        FDSMainScheduleItemController.ItemType itemType,
                                                        ScheduleDateTime schedule) {
    try {
      final double offsetX = getElementOffsetX(schedule.getStart());
      final double width = getElementOffsetX(schedule.getEnd()) - offsetX;

      if (Math.abs(offsetX) < DAY_WIDTH * 3) {
        final FDSMainScheduleItemController controller = new FDSMainScheduleItemController();
        controller.setLayoutX(offsetX);
        controller.set(title);
        controller.setPrefWidth(width);
        controller.setMode(itemType);

        AnchorPane.setTopAnchor(controller, 7.0);
        AnchorPane.setBottomAnchor(controller, 7.0);
        return controller;
      } else {
        return null;
      }
    } catch (IOException e) {
      logger.error("Fail to display satellite event item - " + e.getMessage());
      return null;
    }
  }

  /**
   * time 값에 해당하는 TimeTable 상의 좌표를 반환
   * @param elementTime
   * @return
   */
  private double getElementOffsetX(ZonedDateTime elementTime) {
    return firstTimeTableHBox.getChildren().size() * DAY_WIDTH * (elementTime.getLong(ChronoField.INSTANT_SECONDS) -
      ZonedDateTime.of(LocalDate.of(firstStartTime.getYear(), firstStartTime.getMonth(), firstStartTime.getDayOfMonth()), LocalTime.of(0, 0, 0), ZoneId.of("Asia/Seoul")).getLong(ChronoField.INSTANT_SECONDS))
      / (firstTimeTableHBox.getChildren().size() * DAY_INSTANT_SECOND);
  }

  /**
   * TableView에서 1번째 컬럼의 TableCell로 CheckIcon 을 포함한다.
   */
  class NameCellUpdater extends TableCell<FDSMainTableRowViewModel, String>{
    String iconPath = "complete.png";
    @Override
    protected void updateItem(String item, boolean empty) {
      FDSMainTableRowViewModel row = (FDSMainTableRowViewModel) getTableRow().getItem();
      if (!empty) {
        if (row.visibleProperty().get()) {
          setGraphic(new ImageView(new Image(getClass().getResourceAsStream(iconPath))));
          getStyleClass().remove("table-cell-uncomplete");
          getStyleClass().add("table-cell-complete");
          setText(item);
        } else {
          getStyleClass().remove("table-cell-complete");
          getStyleClass().add("table-cell-uncomplete");
          setText(item);
        }
      }
    }
  }

  /**
   * TableView에서 2번째 컬럼의 TableCell
   */
  class FirstIconUpdater extends TableCell<FDSMainTableRowViewModel, String>{
    String iconPath;

    public FirstIconUpdater(){
      this("graph_white.png");
    }

    public FirstIconUpdater(String path){
      iconPath = path;
    }

    @Override
    protected void updateItem(String item, boolean empty) {
      FDSMainTableRowViewModel row = (FDSMainTableRowViewModel) getTableRow().getItem();
      if(item != null && row.visibleProperty().get() && !empty){
        setGraphic(new ImageView(new Image(getClass().getResourceAsStream(iconPath))));
        getGraphic().focusedProperty().addListener((observable, oldValue, newValue) -> {
          //icon 리스너 등록
        });
      }
    }
  }

  /**
   * TableView에서 3번째 컬럼의 TableCell 클래스
   */
  class SecondIconUpdater extends TableCell<FDSMainTableRowViewModel, String>{
    String iconPath = "memo.png";

    public SecondIconUpdater(){
      this("memo.png");
    }

    public SecondIconUpdater(String path){
      iconPath = path;
    }

    @Override
    protected void updateItem(String item, boolean empty) {
      FDSMainTableRowViewModel row = (FDSMainTableRowViewModel) getTableRow().getItem();
      if(item != null && row.visibleProperty().get() && !empty ){
        setGraphic(new ImageView(new Image(getClass().getResourceAsStream(iconPath))));
        getGraphic().focusedProperty().addListener((observable, oldValue, newValue) -> {
          //icon 리스너 등록
        });
      }
    }
  }
}
