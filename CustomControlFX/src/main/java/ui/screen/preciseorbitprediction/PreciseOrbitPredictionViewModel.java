package ui.screen.preciseorbitprediction;

import fds.coms.io.OpOperatorParameter;
import fds.coms.io.StationParameterHelper;
import fds.coms.util.Kepler;
import fds.ui.common.OutputListTableRowViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ui.screen.viewmodel.StationDataRowViewModel;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Created by GSD on 2017-01-13.
 */
public class PreciseOrbitPredictionViewModel {
  public ObservableList<OutputListTableRowViewModel> outputListTableRowViewModelList;
  public ObservableList<StationDataRowViewModel> stationDataTableRowViewModelList;
  public ZonedDateTime startTime;
  public ZonedDateTime endTime;
  public BooleanProperty preEarthGravityCheckBox;
  public BooleanProperty preSunMoonGravityCheckBox;
  public BooleanProperty preSolarRadiationPressure;
  public BooleanProperty maneuverStackFlag;
  public BooleanProperty wolFlag;
  public int earthDegree;
  public int earthOrder;
  public double outputInterval;
  public Kepler kepler = new Kepler();
  public OpOperatorParameter opOperatorParameter;
  public ObservableList<OutputListTableRowViewModel> orgOutputListTableRowViewModelList;

  public PreciseOrbitPredictionViewModel(OpOperatorParameter opOperatorParameter) {
    this.opOperatorParameter = opOperatorParameter; // TODO DB에서 읽어와야함
    StationParameterHelper stationParameterHelper = StationParameterHelper.getInstance();

    startTime = (ZonedDateTime.of(2018, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
    endTime = (ZonedDateTime.of(2018, 1, 2, 0, 0, 0, 0, ZoneOffset.UTC)); //TODO 임시 하드 코딩

    setInitialOutputListModel(startTime, endTime);

    preEarthGravityCheckBox = new SimpleBooleanProperty(opOperatorParameter.getPerturbationForceFlag(0));
    preSunMoonGravityCheckBox = new SimpleBooleanProperty(opOperatorParameter.getPerturbationForceFlag(1));
    preSolarRadiationPressure = new SimpleBooleanProperty(opOperatorParameter.getPerturbationForceFlag(2));
    maneuverStackFlag = new SimpleBooleanProperty(opOperatorParameter.getManeuverStackDataApplicationFlag());
    wolFlag = new SimpleBooleanProperty(opOperatorParameter.getWheelOffLoadingApplicationFlag());

    earthDegree = opOperatorParameter.getEarthGravityDegree();
    earthOrder = opOperatorParameter.getEarthGravityOrder();
    outputInterval = opOperatorParameter.getOutputTimeStepSec();


    stationDataTableRowViewModelList = FXCollections.observableArrayList();
    for (int i = 0; i < stationParameterHelper.getNmrStations(); i++) {
      stationDataTableRowViewModelList.add(new StationDataRowViewModel(stationParameterHelper.getStationId(i),
          stationParameterHelper.getStationName(i)));
    }

  }

  public PreciseOrbitPredictionViewModel(OpOperatorParameter opOperatorParameter, Kepler epochKepler) {
    kepler = epochKepler;
    this.opOperatorParameter = opOperatorParameter; // TODO DB에서 읽어와야함
    StationParameterHelper stationParameterHelper = StationParameterHelper.getInstance();

    startTime = (ZonedDateTime.of(2018, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC));
    endTime = (ZonedDateTime.of(2018, 1, 2, 0, 0, 0, 0, ZoneOffset.UTC)); //TODO 임시 하드 코딩

    setInitialOutputListModel(startTime, endTime);

    preEarthGravityCheckBox = new SimpleBooleanProperty(opOperatorParameter.getPerturbationForceFlag(0));
    preSunMoonGravityCheckBox = new SimpleBooleanProperty(opOperatorParameter.getPerturbationForceFlag(1));
    preSolarRadiationPressure = new SimpleBooleanProperty(opOperatorParameter.getPerturbationForceFlag(2));
    maneuverStackFlag = new SimpleBooleanProperty(opOperatorParameter.getManeuverStackDataApplicationFlag());
    wolFlag = new SimpleBooleanProperty(opOperatorParameter.getWheelOffLoadingApplicationFlag());

    earthDegree = opOperatorParameter.getEarthGravityDegree();
    earthOrder = opOperatorParameter.getEarthGravityOrder();
    outputInterval = opOperatorParameter.getOutputTimeStepSec();


    stationDataTableRowViewModelList = FXCollections.observableArrayList();
    for (int i = 0; i < stationParameterHelper.getNmrStations(); i++) {
      stationDataTableRowViewModelList.add(new StationDataRowViewModel(stationParameterHelper.getStationId(i),
          stationParameterHelper.getStationName(i)));
//      stationDataTableRowViewModelList.add(new StationDataRowViewModel(
//          new SimpleStringProperty(stationParameterHelper.getStationId(i)),
//          new SimpleStringProperty(stationParameterHelper.getStationName(i))));
    }

  }

  public void setOutputListModel(ZonedDateTime startTime, ZonedDateTime endTime) {


    this.startTime = startTime;
    this.endTime = endTime;

    String start = startTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    String generaton = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH-mm-ss"));
    long durationHr = ChronoUnit.HOURS.between(startTime, endTime);

    if(outputListTableRowViewModelList != null) {
      outputListTableRowViewModelList.clear();
    }

    String directory = "D:/test/";// TODO FDSdef 에서  경로 하드 코딩

    outputListTableRowViewModelList = FXCollections.observableArrayList(
        new OutputListTableRowViewModel(new SimpleStringProperty("Report"),
            orgOutputListTableRowViewModelList.get(0).generationProperty().isSelected(),
            new SimpleStringProperty("OPReport_" + start + "_" + durationHr + ".txt"), // TODO FDSdef 에서  파일 규칙 하드 코딩
            new SimpleStringProperty(directory + "OPReport_" + start + "_" + durationHr + ".txt")),
        new OutputListTableRowViewModel(new SimpleStringProperty("Orbit Prediction Data (Cartesian)"),
            orgOutputListTableRowViewModelList.get(1).generationProperty().isSelected(),
            new SimpleStringProperty("prediction_car_" + start + "_" + durationHr + ".dat"),
            new SimpleStringProperty(directory + "prediction_car_" + start + "_" + durationHr + ".dat")),
        new OutputListTableRowViewModel(new SimpleStringProperty("Orbit Prediction Data(keplerian)"),
            orgOutputListTableRowViewModelList.get(2).generationProperty().isSelected(),
            new SimpleStringProperty("prediction_kep_" + start + "_" + durationHr + ".dat"),
            new SimpleStringProperty(directory + "prediction_kep_" + start + "_" + durationHr + ".dat")),
        new OutputListTableRowViewModel(new SimpleStringProperty("Ground Track Data"),
            orgOutputListTableRowViewModelList.get(3).generationProperty().isSelected(),
            new SimpleStringProperty("GTD_" + start + "_" + durationHr + ".dat"),
            new SimpleStringProperty(directory + "GTD_" + start + "_" + durationHr + ".dat")),
        new OutputListTableRowViewModel(new SimpleStringProperty("TLE Data"),
            orgOutputListTableRowViewModelList.get(4).generationProperty().isSelected(),
            new SimpleStringProperty("ANT_SGCS_" + start + ".DAT"),
            new SimpleStringProperty(directory + "ANT_SGCS_" + start + ".DAT")),
        new OutputListTableRowViewModel(new SimpleStringProperty("ECI Ephemerides"),
            orgOutputListTableRowViewModelList.get(5).generationProperty().isSelected(),
            new SimpleStringProperty("GK2A_ECI_" + generaton + ".asc"),
            new SimpleStringProperty(directory + "GK2A_ECI_" + generaton + ".asc")),
        new OutputListTableRowViewModel(new SimpleStringProperty("ECEF Ephemerides"),
            orgOutputListTableRowViewModelList.get(6).generationProperty().isSelected(),
            new SimpleStringProperty("GK2A_ECEF_" + generaton + ".asc"),
            new SimpleStringProperty(directory + "GK2A_ECEF_" + generaton + ".asc")),
        new OutputListTableRowViewModel(new SimpleStringProperty("CCSDS"),
            orgOutputListTableRowViewModelList.get(7).generationProperty().isSelected(),
            new SimpleStringProperty("OPReport_" + start + "_" + durationHr + ".opm"),
            new SimpleStringProperty(directory + "OPReport_" + start + "_" + durationHr + ".opm")));

    if (orgOutputListTableRowViewModelList.size() > 8) {
      for(int i = 8; i < orgOutputListTableRowViewModelList.size(); i++) {
        String stationName = orgOutputListTableRowViewModelList.get(i).fileNameProperty().get().split("-")[1];
        outputListTableRowViewModelList.add(
            new OutputListTableRowViewModel(new SimpleStringProperty("Antenna Pointing Data(" + stationName + ")"),
                true,
                new SimpleStringProperty("ANT_" + stationName + "_" + start + "_" + durationHr + ".dat"),
                new SimpleStringProperty(directory + "ANT_" + stationName + "_" + start + "_" + durationHr + ".dat")));
      }
    }

    orgOutputListTableRowViewModelList = FXCollections.observableArrayList(outputListTableRowViewModelList);
  }


  public void setInitialOutputListModel(ZonedDateTime startTime, ZonedDateTime endTime) {


    this.startTime = startTime;
    this.endTime = endTime;

    String start = startTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    String generaton = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH-mm-ss"));
    long durationHr = ChronoUnit.HOURS.between(startTime, endTime);

    if (outputListTableRowViewModelList != null) {
      outputListTableRowViewModelList.clear();
    }

    String directory = "D:/test/";// TODO FDSdef 에서  경로 하드 코딩

    outputListTableRowViewModelList = FXCollections.observableArrayList(
        new OutputListTableRowViewModel(new SimpleStringProperty("Report"),
            opOperatorParameter.getReportGenerationFlag(),
            new SimpleStringProperty("OPReport_" + start + "_" + durationHr + ".txt"), // TODO FDSdef 에서  파일 규칙 하드 코딩
            new SimpleStringProperty(directory + "OPReport_" + start + "_" + durationHr + ".txt")),
        new OutputListTableRowViewModel(new SimpleStringProperty("Orbit Prediction Data (Cartesian)"),
            opOperatorParameter.getOpDataCartesianGenerationFlag(),
            new SimpleStringProperty("prediction_car_" + start + "_" + durationHr + ".dat"),
            new SimpleStringProperty(directory + "prediction_car_" + start + "_" + durationHr + ".dat")),
        new OutputListTableRowViewModel(new SimpleStringProperty("Orbit Prediction Data(keplerian)"),
            opOperatorParameter.getOpDataKeplerianGenerationFlag(),
            new SimpleStringProperty("prediction_kep_" + start + "_" + durationHr + ".dat"),
            new SimpleStringProperty(directory + "prediction_kep_" + start + "_" + durationHr + ".dat")),
        new OutputListTableRowViewModel(new SimpleStringProperty("Ground Track Data"),
            opOperatorParameter.getGroundTrackDataGenerationFlag(),
            new SimpleStringProperty("GTD_" + start + "_" + durationHr + ".dat"),
            new SimpleStringProperty(directory + "GTD_" + start + "_" + durationHr + ".dat")),
        new OutputListTableRowViewModel(new SimpleStringProperty("TLE Data"),
            opOperatorParameter.getTleDataGenerationFlag(),
            new SimpleStringProperty("ANT_SGCS_" + start + ".DAT"),
            new SimpleStringProperty(directory + "ANT_SGCS_" + start + ".DAT")),
        new OutputListTableRowViewModel(new SimpleStringProperty("ECI Ephemerides"),
            opOperatorParameter.getEphemerisEciDataGenerationFlag(),
            new SimpleStringProperty("GK2A_ECI_" + generaton + ".asc"),
            new SimpleStringProperty(directory + "GK2A_ECI_" + generaton + ".asc")),
        new OutputListTableRowViewModel(new SimpleStringProperty("ECEF Ephemerides"),
            opOperatorParameter.getEphemerisEcefDataGenerationFlag(),
            new SimpleStringProperty("GK2A_ECEF_" + generaton + ".asc"),
            new SimpleStringProperty(directory + "GK2A_ECEF_" + generaton + ".asc")),
        new OutputListTableRowViewModel(new SimpleStringProperty("CCSDS"),
            opOperatorParameter.getCcsdsReportGenerationFlag(),
            new SimpleStringProperty("OPReport_" + start + "_" + durationHr + ".opm"),
            new SimpleStringProperty(directory + "OPReport_" + start + "_" + durationHr + ".opm")));

    orgOutputListTableRowViewModelList = FXCollections.observableArrayList(outputListTableRowViewModelList);

  }

}
