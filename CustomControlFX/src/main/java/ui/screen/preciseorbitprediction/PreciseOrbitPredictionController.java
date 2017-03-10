package ui.screen.preciseorbitprediction;

import commons.log.LogUtil;
import commons.log.Logger;
import commons.ui.UiUtil;
import commons.ui.control.datetimepicker.DateTimePicker;
import fds.FacadeService;
import fds.common.FdsDef;
import fds.coms.astro.AstroDataHelper;
import fds.coms.io.OpOperatorParameter;
import fds.coms.util.C3DVector;
import fds.coms.util.Kepler;
import fds.coms.util.PreciseJd;
import fds.coms.util.TimeConverter;
import fds.orbitprediction.OpRequest;
import fds.ui.CommonParameterTemp;
import fds.ui.EpochKeplerSearch;
import fds.ui.common.*;
import fds.util.KeplerOrbitState;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by GSD on 2017-01-13.
 */
public class PreciseOrbitPredictionController extends BorderPane implements Initializable {
  private final static Logger logger = LogUtil.getLogger(FacadeService.class.getName());
  @FXML
  private Pane preStartDateTimePickerPane;
  @FXML
  private Pane preEndDateTimePickerPane;
  @FXML
  private Spinner<Double> preOutputIntervalSpinner;
  @FXML
  private Pane preEpochDateTimePickerPane;
  @FXML
  private Button preSelectEpochButton;
  @FXML
  private ComboBox<String> preStackSourceTypeComboBox;
  @FXML
  private ComboBox<String> preDisplayTypeComboBox;
  @FXML
  private ComboBox<String> preCoordinateFrameComboBox;
  @FXML
  private CheckBox preEarthGravityCheckBox;
  @FXML
  private CheckBox preSunMoonGravityCheckBox;
  @FXML
  private CheckBox preSolarRadiationPressure;
  @FXML
  private CheckBox preAtmosphericDragCheckBox;
  @FXML
  private Spinner<Integer> preDegreeSpinner;
  @FXML
  private Spinner<Integer> preOrderSpinner;
  @FXML
  private CheckBox preApplyWheelCheckBox;
  @FXML
  private CheckBox preApplyStationCheckBox;
  @FXML
  private Button preSaveParameterButton;
  @FXML
  private Button preExcuteButton;
  @FXML
  private Button textViewerNewWindowButton;
  @FXML
  private Button textViewerSaveButton;
  @FXML
  private Button textViewerPrintButton;
  @FXML
  private TabPane preMainTab;
  @FXML
  private GridPane stationDataGridPane;
  @FXML
  private GridPane outputListGridPane;
  @FXML
  private GridPane preEpochGridPane;

  private DateTimePicker preStartDateTimePicker;
  private DateTimePicker preEndDateTimePicker;
  private DateTimePicker preEpochDateTimePicker;
  private TabTextViewerEventHandler handler;

  public PreciseOrbitPredictionController() throws IOException {
    final FXMLLoader loader = UiUtil.getFxmlLoader(this.getClass());
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  /**
   * 우측 TextViewer 레이아웃 setting
   *
   * @param location
   * @param resources
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    handler = new TabTextViewerEventHandler(this, preMainTab);
    preStartDateTimePicker = new DateTimePicker(205, 28, 12);
    preStartDateTimePickerPane.getChildren().add(preStartDateTimePicker);
    preEndDateTimePicker = new DateTimePicker(205, 28, 12);
    preEndDateTimePickerPane.getChildren().add(preEndDateTimePicker);
    preEpochDateTimePicker = new DateTimePicker(200, 28, 12);
    preEpochDateTimePickerPane.getChildren().add(preEpochDateTimePicker);
    // Header 삭제
//    preEpochElementTableView.widthProperty().addListener(new ChangeListener<Number>() {
//      @Override
//      public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth) {
//
//        //Don't show header
//        Pane header = (Pane) preEpochElementTableView.lookup("TableHeaderRow");
//        if (header.isVisible()) {
//          header.setPrefHeight(0);
//          header.setVisible(false);
//        }
//      }
//    });

    // TODO -> 초기select 설정 화면 저장시 보관 되어야함....
    preStackSourceTypeComboBox.getItems().addAll("BLSE", "EKF", "TLE");
    preStackSourceTypeComboBox.getSelectionModel().select(0);
    preDisplayTypeComboBox.getItems().addAll("Keplerian", "Cartesian");
    preDisplayTypeComboBox.getSelectionModel().select(0);
    preCoordinateFrameComboBox.getItems().addAll("TOD", "J2000");
    preCoordinateFrameComboBox.getSelectionModel().select(0);

    textViewerNewWindowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
    textViewerSaveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
    textViewerPrintButton.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
  }


  /**
   * Precise OrbitPrediction 화면과 ViewModel을 바인딩.
   */
  public void bind(PreciseOrbitPredictionViewModel viewModel) {

    try {


      // 초기 화면 데이터 세팅
      setInitialDisplay(viewModel);

      // DISPLAY 되는 Kepler Element, 좌표계에 따라 값 변화
      viewModel.kepler.setKepler(loadNearestEpochKepler());
      Kepler kepler = showPreCoordinateCombo(viewModel.kepler);
      changePreDisplayCombo(kepler);


      //TODO START-END 캘린더 선택 시 name 수정/epoch search 기능 추가 필요함


      //TDO Start Time 변경에 따른 Epoch Time Search / OuputList name 수정
      preStartDateTimePicker.setOnKeyReleased(new EventHandler<KeyEvent>() {
        public void handle(KeyEvent event) {
          if (preStartDateTimePicker.isValidDateType()) {
            try {
              handleStartTime(viewModel, kepler);
            } catch (Exception e) {
            }
          }
        }
      });

      preStackSourceTypeComboBox.setOnAction(event -> {
        try {
          // Kepler 업데이트 후 (epoch kepler 수정 후) kepler 다시 display
          viewModel.kepler.setKepler(loadNearestEpochKepler()); // View Model Kepler는 무조건 ECI 좌표계

          // 업데이트된 epoch kepler display
          kepler.setKepler(showPreCoordinateCombo(viewModel.kepler));
          changePreDisplayCombo(kepler);

        } catch (Exception e) {
          e.printStackTrace();
        }
      });


      // End Time 변경에 따른 OutputList name 데이터 변경
      preEndDateTimePicker.setOnKeyReleased(new EventHandler<KeyEvent>() {
        public void handle(KeyEvent event) {
          if (preEndDateTimePicker.isValidDateType()) {
            viewModel.setOutputListModel(preStartDateTimePicker.getDatetime(), preEndDateTimePicker.getDatetime());
            setOutputList(viewModel);
          }
        }
      });

      //Earth Gravity Perturbation 비활성화
      preEarthGravityCheckBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          if (!preEarthGravityCheckBox.isSelected()) {
            preDegreeSpinner.setDisable(true);
            preOrderSpinner.setDisable(true);
          } else {
            preDegreeSpinner.setDisable(false);
            preOrderSpinner.setDisable(false);
          }
        }
      });

      // Display Type 변경에 따른 Epoch Element 출력 화면 변경
      preDisplayTypeComboBox.setOnAction(event -> {
        try {
          changePreDisplayCombo(kepler);
        } catch (Exception e) {
          e.printStackTrace();
        }
      });

      //Coordinate 변경에 따른 Epoch Element 출력 화면 변경
      preCoordinateFrameComboBox.setOnAction(event -> {
        try {
          Kepler changedKepler = showPreCoordinateCombo(viewModel.kepler);
          kepler.setKepler(changedKepler);
          changePreDisplayCombo(changedKepler);
        } catch (Exception e) {
          e.printStackTrace();
        }
      });

      //Execute 실행
      preExcuteButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
          try {

            //TODO OPOPERATION PARAMETER , MANEUVER STACK , WOL STACK은 DB 호출 -> Op requster에는 maneuver /wol stack 제외 하는게 맞는듯함
            setOpParameter(viewModel.opOperatorParameter, viewModel);
            CommonParameterTemp temp = new CommonParameterTemp();

            OpRequest opRequest = new OpRequest(setEpochKepler(kepler), viewModel.opOperatorParameter,
                temp.setWheelOffLoadingStackData(), temp.setManeuverStackDataOpDs());

            FacadeService.executeOp(opRequest);
          } catch (Exception e) {
            e.printStackTrace();
          }
//        FacadeService.executeOp();
        }
      });


      //Save Parameter 실행
      preSaveParameterButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

          // OP OPERATION PARAMETER 및 VIEW MODEL DB 저장
        }
      });


    } catch (Exception e) {
      e.printStackTrace();
    }

    preSelectEpochButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      EpochSelectorPopup popup = new EpochSelectorPopup();
      Scene scene = new Scene(popup);
      Stage newStage = new Stage();
      newStage.setScene(scene);
      newStage.initModality(Modality.APPLICATION_MODAL);
      newStage.setTitle("Epoch Selector");
      newStage.showAndWait();
    });
  }


  private void handleStartTime(PreciseOrbitPredictionViewModel viewModel, Kepler kepler) throws Exception {

    viewModel.setOutputListModel(preStartDateTimePicker.getDatetime(), preEndDateTimePicker.getDatetime());
    setOutputList(viewModel);
//    outputListTableView.setItems(viewModel.outputListTableRowViewModelList);

    // Kepler 업데이트 후 (epoch kepler 수정 후) kepler 다시 display
    viewModel.kepler.setKepler(loadNearestEpochKepler());

    // 업데이트된 epoch kepler display
    kepler.setKepler(showPreCoordinateCombo(viewModel.kepler));
    changePreDisplayCombo(kepler);
  }

  private Kepler loadNearestEpochKepler() throws Exception {

    // nearest stack data로 업데이트
    EpochKeplerSearch epochKeplerSearch = new EpochKeplerSearch();

    Kepler epochKepler;
    if (preStackSourceTypeComboBox.getSelectionModel().isSelected(0)) { // BLSE
      List<KeplerOrbitState> stackList = new CommonParameterTemp().setKeplerStack(); //임시
      epochKepler = epochKeplerSearch.getEpochOrbitFromKeplerStackList(stackList, preStartDateTimePicker.getDatetime());

    } else if (preStackSourceTypeComboBox.getSelectionModel().isSelected(1)) {

      //STACK DATA DB 호출
      List<KeplerOrbitState> stackList = new CommonParameterTemp().setKeplerStack2(); //임시
      epochKepler = epochKeplerSearch.getEpochOrbitFromKeplerStackList(stackList, preStartDateTimePicker.getDatetime());
    } else {
      List<KeplerOrbitState> stackList = new ArrayList<>(); //임시
      epochKepler = epochKeplerSearch.getEpochOrbitFromKeplerStackList(stackList, preStartDateTimePicker.getDatetime());

    }
    preEpochDateTimePicker.setDatetime(TimeConverter.convertJdToZoneDateTime(epochKepler.getJd(), 2));

    return epochKepler;
  }

  private void setInitialDisplay(PreciseOrbitPredictionViewModel viewModel) throws Exception {

    setOutputList(viewModel);
    // GS 설정

    for (int i = 0; i < viewModel.stationDataTableRowViewModelList.size(); i++) {
      StationDataRowViewModel model = viewModel.stationDataTableRowViewModelList.get(i);
      model.setMyOnChecked(new StationDataRowViewModel.MyOnChecked() {
        //GS선택에 따른 Output data 추가
        @Override
        public void onChecked() {
//          System.out.println(model.getId());
          if(viewModel.outputListTableRowViewModelList.size() > 8) {
            viewModel.outputListTableRowViewModelList.remove(8, viewModel.outputListTableRowViewModelList.size()); //antenna
          }

          for (int j = 0; j < viewModel.stationDataTableRowViewModelList.size(); j++) {
            StationDataRowViewModel modelTemp = viewModel.stationDataTableRowViewModelList.get(j);
            if(modelTemp.getChecked().isSelected())
            updateList(viewModel, modelTemp.getName());
            setOutputList(viewModel);
          }
        }
      });
      stationDataGridPane.add(model.getChecked(), 0, i);
      stationDataGridPane.add(new Label(model.getId()), 1, i);
      stationDataGridPane.add(new Label(model.getName()), 2, i);
    }


    //start - end 설정
    preStartDateTimePicker.setDatetime(viewModel.startTime);
    preEndDateTimePicker.setDatetime(viewModel.endTime);

    //Perturbation 설정
    preAtmosphericDragCheckBox.setDisable(true);
    preDegreeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, viewModel.earthDegree));
    preOrderSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, viewModel.earthOrder));
    preEarthGravityCheckBox.selectedProperty().bindBidirectional(viewModel.preEarthGravityCheckBox);
    preSunMoonGravityCheckBox.selectedProperty().bindBidirectional(viewModel.preSunMoonGravityCheckBox);
    preSolarRadiationPressure.selectedProperty().bindBidirectional(viewModel.preSolarRadiationPressure);

    //Maneuver 설정
    preApplyStationCheckBox.selectedProperty().bindBidirectional(viewModel.maneuverStackFlag);
    preApplyWheelCheckBox.selectedProperty().bindBidirectional(viewModel.wolFlag);

    preOutputIntervalSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 100, viewModel.outputInterval));

    //    start - end 초기설정
    preStartDateTimePicker.setDatetime(viewModel.startTime);
    preEndDateTimePicker.setDatetime(viewModel.endTime);

  }

  private void setOutputList(PreciseOrbitPredictionViewModel viewModel) {
    if (outputListGridPane.getChildren() != null)
      outputListGridPane.getChildren().clear();
    for (int i = 0; i < viewModel.outputListTableRowViewModelList.size(); i++) {

      OutputListTableRowViewModel model = viewModel.outputListTableRowViewModelList.get(i);
      OutputListViewCellController controller = new OutputListViewCellController(this, model.fileLocationProperty().get());

      outputListGridPane.add(new Label(model.itemProperty().get()), 0, i);
      outputListGridPane.add(model.generationProperty(), 1, i);
      outputListGridPane.add(new Label(model.fileNameProperty().get()), 2, i);
      outputListGridPane.add(controller.getViewButton(), 3, i);
    }
  }


  private Kepler setEpochKepler(Kepler kepler) throws Exception {

    Kepler epochKepler = new Kepler(kepler);

    AstroDataHelper astroDataHelper = AstroDataHelper.getInstance();

    double jd = epochKepler.getJd();
    if (FdsDef.ASTRO_TIME_REFERENCE_TDB) {
      jd = astroDataHelper.astroTime.convertUtcJdToTdbJd(jd);
    }

    if (preDisplayTypeComboBox.getSelectionModel().isSelected(0)) { //TOD

      C3DVector posTod = new C3DVector();
      C3DVector velTod = new C3DVector();

      kepler.getEciPosVel(posTod, velTod);

      C3DVector posEci = astroDataHelper.iers.convertTodToEci(posTod, jd);
      C3DVector velEci = astroDataHelper.iers.convertTodToEci(velTod, jd);

      epochKepler.setEciPosVel(posEci, velEci);
    }

    epochKepler.setJd(jd);

    return epochKepler;

  }

  private void setOpParameter(OpOperatorParameter opOperatorParameter, PreciseOrbitPredictionViewModel viewModel) {

    AstroDataHelper astroDataHelper = AstroDataHelper.getInstance();

    double startJd = TimeConverter.convertZoneDateTimeToJd(preStartDateTimePicker.getDatetime());
    double endJd = TimeConverter.convertZoneDateTimeToJd(preEndDateTimePicker.getDatetime());

    if (FdsDef.ASTRO_TIME_REFERENCE_TDB) {
      try {
        startJd = astroDataHelper.astroTime.convertUtcJdToTdbJd(startJd);
        endJd = astroDataHelper.astroTime.convertUtcJdToTdbJd(endJd);
      } catch (Exception e) {
        e.printStackTrace(); // Error log
      }
    }

    opOperatorParameter.setOutputStartTimeJd(new PreciseJd(startJd));
    opOperatorParameter.setOutputEndTimeJd(new PreciseJd(endJd));

    opOperatorParameter.setPerturbationForceFlag(0, preEarthGravityCheckBox.selectedProperty().getValue());
    opOperatorParameter.setPerturbationForceFlag(1, preSunMoonGravityCheckBox.selectedProperty().getValue());
    opOperatorParameter.setPerturbationForceFlag(2, preSolarRadiationPressure.selectedProperty().getValue());
    opOperatorParameter.setPerturbationForceFlag(3, preAtmosphericDragCheckBox.selectedProperty().getValue());
    opOperatorParameter.setEarthGravityOrder((int) preOrderSpinner.getValueFactory().valueProperty().getValue());
    opOperatorParameter.setEarthGravityDegree((int) preDegreeSpinner.getValueFactory().valueProperty().getValue());
    opOperatorParameter.setOutputTimeStepSec((double) preOutputIntervalSpinner.getValueFactory().valueProperty().getValue());

    opOperatorParameter.setManeuverStackDataApplicationFlag(preApplyStationCheckBox.selectedProperty().getValue());
    opOperatorParameter.setWheelOffLoadingApplicationFlag(preApplyWheelCheckBox.selectedProperty().getValue());

    List<String> antennaStation = new ArrayList<>();
    List<Boolean> antennaStationFlag = new ArrayList<>();
    List<String> antennaStationLocation = new ArrayList<>();
    for (int i = 0; i < viewModel.stationDataTableRowViewModelList.size(); i++) {

      StationDataRowViewModel model = viewModel.stationDataTableRowViewModelList.get(i);
      if (model.getChecked().isSelected()) {
        antennaStation.add(model.getId());
        antennaStationFlag.add(true);
        antennaStationLocation.add("D://test/" + model.getName() + "_test.dat");
      }
    }
    opOperatorParameter.setAntennaPointingDataStationId(antennaStation);
    opOperatorParameter.setAntennaPointingDataGenerationFlag(true);
    opOperatorParameter.setAntennaPointingDataLocation(antennaStationLocation);

    opOperatorParameter.setReportGenerationFlag(viewModel.outputListTableRowViewModelList.get(0).generationProperty().isSelected());
    opOperatorParameter.setOpDataCartesianGenerationFlag(viewModel.outputListTableRowViewModelList.get(1).generationProperty().isSelected());
    opOperatorParameter.setOpDataKeplerianGenerationFlag(viewModel.outputListTableRowViewModelList.get(2).generationProperty().isSelected());
    opOperatorParameter.setGroundTrackDataGenerationFlag(viewModel.outputListTableRowViewModelList.get(3).generationProperty().isSelected());
    opOperatorParameter.setTleDataGenerationFlag(viewModel.outputListTableRowViewModelList.get(4).generationProperty().isSelected());
    opOperatorParameter.setEphemerisEcefDataGenerationFlag(viewModel.outputListTableRowViewModelList.get(5).generationProperty().isSelected());
    opOperatorParameter.setEphemerisEciDataGenerationFlag(viewModel.outputListTableRowViewModelList.get(6).generationProperty().isSelected());
    opOperatorParameter.setCcsdsReportGenerationFlag(viewModel.outputListTableRowViewModelList.get(7).generationProperty().isSelected());

    opOperatorParameter.setReportLocation("D://test/" + viewModel.outputListTableRowViewModelList.get(0).fileNameProperty().getValue());
    opOperatorParameter.setOpDataCartesianLocation("D://test/" + viewModel.outputListTableRowViewModelList.get(1).fileNameProperty().getValue());
    opOperatorParameter.setOpDataKeplerianLocation("D://test/" + viewModel.outputListTableRowViewModelList.get(2).fileNameProperty().getValue());
    opOperatorParameter.setGroundTrackDataLocation("D://test/" + viewModel.outputListTableRowViewModelList.get(3).fileNameProperty().getValue());
    opOperatorParameter.setTleDataLocation("D://test/" + viewModel.outputListTableRowViewModelList.get(4).fileNameProperty().getValue());
    opOperatorParameter.setEphemerisEciLocation("D://test/" + viewModel.outputListTableRowViewModelList.get(5).fileNameProperty().getValue());
    opOperatorParameter.setEphemerisEcefLocation("D://test/" + viewModel.outputListTableRowViewModelList.get(6).fileNameProperty().getValue());
    opOperatorParameter.setCcsdsReportLocation("D://test/" + viewModel.outputListTableRowViewModelList.get(7).fileNameProperty().getValue());

  }


  private void changePreDisplayCombo(Kepler epochKepler) throws Exception {
    ObservableList<EpochListTableRowViewModel> epochListTableRowViewModelList;
    //Epoch 초기 설정

    if (preDisplayTypeComboBox.getSelectionModel().isSelected(0)) { // Keplerian

      epochListTableRowViewModelList = FXCollections.observableArrayList(
          new EpochListTableRowViewModel(new SimpleStringProperty("Semi-Major Axis"),
              new SimpleStringProperty(String.format("%.10f", epochKepler.getSemiMajor() / 1000.0)),
              new SimpleStringProperty("km")),
          new EpochListTableRowViewModel(new SimpleStringProperty("Eccentricity"),
              new SimpleStringProperty(String.format("%.10f", epochKepler.getEccentricity())),
              new SimpleStringProperty("")),
          new EpochListTableRowViewModel(new SimpleStringProperty("Inclination"),
              new SimpleStringProperty(String.format("%.10f", epochKepler.getInclination() * FdsDef.TO_DEGREE)),
              new SimpleStringProperty("deg")),
          new EpochListTableRowViewModel(new SimpleStringProperty("R.A.A.N"),
              new SimpleStringProperty(String.format("%.10f", epochKepler.getRaan() * FdsDef.TO_DEGREE)),
              new SimpleStringProperty("deg")),
          new EpochListTableRowViewModel(new SimpleStringProperty("Arg. of Perigee"),
              new SimpleStringProperty(String.format("%.10f", epochKepler.getArgPerigee() * FdsDef.TO_DEGREE)),
              new SimpleStringProperty("deg")),
          new EpochListTableRowViewModel(new SimpleStringProperty("Mean Anomaly"),
              new SimpleStringProperty(String.format("%.10f", epochKepler.getMeanAnomaly() * FdsDef.TO_DEGREE)),
              new SimpleStringProperty("deg")));


    } else {

      C3DVector pos = new C3DVector();
      C3DVector vel = new C3DVector();
      epochKepler.getEciPosVel(pos, vel);

      epochListTableRowViewModelList = FXCollections.observableArrayList(
          new EpochListTableRowViewModel(new SimpleStringProperty("X"),
              new SimpleStringProperty(String.format("%.10f", pos.getX() / 1000.0)),
              new SimpleStringProperty("km")),
          new EpochListTableRowViewModel(new SimpleStringProperty("Y"),
              new SimpleStringProperty(String.format("%.10f", pos.getY() / 1000.0)),
              new SimpleStringProperty("km")),
          new EpochListTableRowViewModel(new SimpleStringProperty("Z"),
              new SimpleStringProperty(String.format("%.10f", pos.getZ() / 1000.0)),
              new SimpleStringProperty("km")),
          new EpochListTableRowViewModel(new SimpleStringProperty("Vx"),
              new SimpleStringProperty(String.format("%.10f", vel.getX() / 1000.0)),
              new SimpleStringProperty("km/s")),
          new EpochListTableRowViewModel(new SimpleStringProperty("Vy"),
              new SimpleStringProperty(String.format("%.10f", vel.getY() / 1000.0)),
              new SimpleStringProperty("km/s")),
          new EpochListTableRowViewModel(new SimpleStringProperty("Vz"),
              new SimpleStringProperty(String.format("%.10f", vel.getZ() / 1000.0)),
              new SimpleStringProperty("km/s")));
    }

//    epochElementColumn1.setCellValueFactory(cellData -> cellData.getValue().itemProperty());
//    epochElementColumn2.setCellValueFactory(cellData -> cellData.getValue().valueProperty());
//    epochElementColumn3.setCellValueFactory(cellData -> cellData.getValue().unitProperty());
//    preEpochElementTableView.setItems(epochListTableRowViewModelList);
  }


  private Kepler showPreCoordinateCombo(Kepler epochKepler) throws Exception {
    AstroDataHelper astroDataHelper = AstroDataHelper.getInstance();
    Kepler kepler = new Kepler(epochKepler);

    double tdbjd = kepler.getJd();
    if (FdsDef.ASTRO_TIME_REFERENCE_TDB) {
      tdbjd = astroDataHelper.astroTime.convertUtcJdToTdbJd(tdbjd);
    }

    C3DVector pos = new C3DVector();
    C3DVector vel = new C3DVector();
    epochKepler.getEciPosVel(pos, vel);

    if (preCoordinateFrameComboBox.getSelectionModel().isSelected(0)) { // TOD

      C3DVector todPos = astroDataHelper.iers.convertEciToTod(pos, tdbjd);
      C3DVector todVel = astroDataHelper.iers.convertEciToTod(vel, tdbjd);

      kepler.setEciPosVel(todPos, todVel);

    } else { // ECI

      C3DVector todPos = astroDataHelper.iers.convertTodToEci(pos, tdbjd);
      C3DVector todVel = astroDataHelper.iers.convertTodToEci(vel, tdbjd);

      kepler.setEciPosVel(todPos, todVel);

    }

    return kepler;
  }

  public void openNewFile(String path) {
    handler.openNewTab(path);
  }


  public void updateList(PreciseOrbitPredictionViewModel viewModel, String stationName) {

    ZonedDateTime startTime = preStartDateTimePicker.getDatetime();
    ZonedDateTime endTime = preEndDateTimePicker.getDatetime();
    long durationHr = ChronoUnit.HOURS.between(startTime, endTime);
    String start = startTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    viewModel.outputListTableRowViewModelList.add(
        new OutputListTableRowViewModel(new SimpleStringProperty("Antenna Pointing Data(" + stationName + ")"),
            true,
            new SimpleStringProperty("ANT_" + stationName + "_" + start + "_" + durationHr + ".dat"),
            new SimpleStringProperty("D:/Users/GSD/Desktop/c.txt")));

  }
}
