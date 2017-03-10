package ui.screen.eventpredictionsetting;

import commons.ui.control.chart.EventPredictionChart;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Created by GSD on 2017-02-16.
 */
public class EventPredictionChartViewerEventHandler implements EventHandler<MouseEvent> {
  private Pane context;
  private Pane chartContainer;
  private EventPredictionChart chart;

  public EventPredictionChartViewerEventHandler(Pane context, Pane chartContainer, EventPredictionChart chart) {
    this.context = context;
    this.chartContainer = chartContainer;
    this.chart = chart;
  }

  @Override
  public void handle(MouseEvent event) {
    switch (event.getPickResult().getIntersectedNode().getId()) {
      case "chartViewerSaveButton":
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(context.getScene().getWindow());
        if (file != null) {
          saveFile(file);
        }
        break;
      case "chartViewerPrintButton":
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob.showPrintDialog(context.getScene().getWindow())) {
          boolean success = printerJob.printPage(chartContainer);
          if (success) {
            printerJob.endJob();
          }
        }
        break;
      case "chartViewerNewWindowButton":
        Stage stage = new Stage();
        stage.setTitle("Chart Viewer");
        EventPredictionChart copyChart = new EventPredictionChart();
        copyChart.setxAxis(chart.getxAxis());
        copyChart.setyAxis(chart.getyAxis());
        chart.getSunMoonData();
        copyChart.setSunMoonData(chart.getSunMoonData());
        copyChart.setOtherData(chart.getOtherData());
        EventPredictionNewWindowChartViewer eventPredictionNewWindowChartViewer = new EventPredictionNewWindowChartViewer(copyChart);
        Scene scene = new Scene(eventPredictionNewWindowChartViewer, 900, 500);
        stage.setScene(scene);
        stage.show();
        break;
    }
  }

  private void saveFile(File file){
    WritableImage image = new WritableImage(920, 440);
    chartContainer.snapshot(null, image);
    File imageFile = file;
    try{
      ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png",  imageFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
