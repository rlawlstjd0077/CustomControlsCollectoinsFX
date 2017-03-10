package ui.screen.common;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by GSD on 2017-02-20.
 */
public class ChartViewerEventHandler implements EventHandler<MouseEvent>{
  private Pane context;
  private Pane chartContainer;
  private ScatterChart<Date, Number> chart;
  private DateAxis xAxis;
  private NumberAxis yAxis;

  public ChartViewerEventHandler(Pane context, Pane chartContainer, ScatterChart<Date, Number> chart) {
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
        DateAxis tempXAxis = (DateAxis)chart.getXAxis();
        NumberAxis tempYAxis = (NumberAxis) chart.getYAxis();
        xAxis = new DateAxis(tempXAxis.getLowerBound(), tempXAxis.getUpperBound());
        xAxis.setTickLabelFormatter(tempXAxis.getTickLabelFormatter());
        yAxis = new NumberAxis(tempYAxis.getLowerBound(), tempYAxis.getUpperBound(), tempYAxis.getTickUnit());
        ScatterChart<Date, Number> copyChart = new ScatterChart<>(xAxis, yAxis);

        VBox entireBox = (VBox) chartContainer.getChildren().get(1);
        HBox firstLabelBox = (HBox) entireBox.getChildren().get(0);
        HBox secondLabelBox = (HBox) entireBox.getChildren().get(1);
        Label firstLabel = (Label) firstLabelBox.getChildren().get(2);
        Label secondLabel = (Label) secondLabelBox.getChildren().get(2);
        ChartNewWindowViewer eventPredictionChartNewWindowViewer = new ChartNewWindowViewer(copyChart, firstLabel.getText(), secondLabel.getText());
        Scene scene = new Scene(eventPredictionChartNewWindowViewer, 900, 500);
        stage.setScene(scene);
        stage.show();
        break;
    }
  }
  private void saveFile(File file){
    WritableImage image = new WritableImage(700, 400);
    chartContainer.snapshot(null, image);
    File imageFile = file;
    try{
      ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png",  imageFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
