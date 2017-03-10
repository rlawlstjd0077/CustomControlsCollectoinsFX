package ui.screen.common;

import javafx.event.EventHandler;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by GSD on 2017-03-02.
 */
public class TextViewerEventHandler implements EventHandler<MouseEvent> {
  private TextArea textArea;
  private Pane context;
  private final String SAVE_BUTTON, PRINT_BUTTON, NEW_WINDOW_BUTTON;
  private String title;

  public TextViewerEventHandler(Pane context, TextArea textArea, String identifier, String title) {
    this.textArea = textArea;
    this.context = context;
    this.title = title;
    SAVE_BUTTON = identifier + "SaveButton";
    PRINT_BUTTON = identifier + "PrintButton";
    NEW_WINDOW_BUTTON = identifier + "NewWindowButton";
  }

  public TextViewerEventHandler(Pane context, TextArea textArea) {
    this(context, textArea, "textViewer", "textViewer");
  }

  @Override
  public void handle(MouseEvent event) {
    String clickResult = event.getPickResult().getIntersectedNode().getId();
    if (clickResult.equals(SAVE_BUTTON)) {
      FileChooser fileChooser = new FileChooser();
      File file = fileChooser.showSaveDialog(context.getScene().getWindow());
      if (file != null) {
        saveFile(file);
      }
      return;
    }
    if (clickResult.equals(PRINT_BUTTON)) {
      PrinterJob printerJob = PrinterJob.createPrinterJob();
      if (printerJob.showPrintDialog(context.getScene().getWindow())) {
        boolean success = printerJob.printPage(textArea);
        if (success) {
          printerJob.endJob();
        }
      }
      return;
    }
    if (clickResult.equals(NEW_WINDOW_BUTTON)) {
      Stage stage = new Stage();
      stage.setTitle(title);
      textArea.getStyleClass().add("gk2-text-area");
      TextArea newArea = new TextArea(textArea.getText());
      newArea.setEditable(false);
      stage.setScene(new Scene(newArea, 1000, 800));
      stage.show();
      return;
    }
  }

  /**
   * 지정된 경로에 현재 Focus 된 Tab의 Text file 저장
   *
   * @param file
   */
  private void saveFile(File file) {
    try {
      FileWriter writer = null;
      writer = new FileWriter(file);
      writer.write(textArea.getText().replaceAll("\n", "\r\n"));
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
