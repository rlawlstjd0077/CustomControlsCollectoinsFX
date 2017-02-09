package sample.ui.control.textviewer;


import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

/**
 * Created by GSD on 2017-01-25.
 */
public class TextViewerEventHandler implements EventHandler<MouseEvent> {
  private TabPane tabPane;
  private Pane context;

  public TextViewerEventHandler(Pane context, TabPane tabPane) {
    this.tabPane = tabPane;
    this.context = context;
  }

  @Override
  public void handle(MouseEvent event) {
    switch (event.getPickResult().getIntersectedNode().getId()) {
      case "newFileButton":
        FileChooser newFileChooser = new FileChooser();
        File newFile = newFileChooser .showOpenDialog(context.getScene().getWindow());
        openNewTab(newFile.getPath());
        break;
      case "saveButton":
        if (isTabExist()) {
          if (isTabExist()) {
            FileChooser saveFileChooser = new FileChooser();
            File saveFile = saveFileChooser.showSaveDialog(context.getScene().getWindow());
            if (saveFile != null) {
              saveFile(saveFile);
            }
          }
        }
        break;
      case "printButton":
        if (isTabExist()) {
          PrinterJob printerJob = PrinterJob.createPrinterJob();
          if (printerJob.showPrintDialog(context.getScene().getWindow())) {
            boolean success = printerJob.printPage(tabPane.getSelectionModel().getSelectedItem().getContent());
            if (success) {
              printerJob.endJob();
            }
          }
        }
        break;
      case "newWindowButton":
        if (isTabExist()) {
          Stage stage = new Stage();
          stage.setTitle(tabPane.getSelectionModel().getSelectedItem().getText());
          HBox hBox = (HBox) tabPane.getSelectionModel().getSelectedItem().getContent();
          TextArea textArea = (TextArea) hBox.getChildren().get(0);
          TextArea newArea = new TextArea(textArea.getText());
          newArea.setEditable(false);
          stage.setScene(new Scene(newArea, 1000, 800));
          stage.show();
        }
        break;
    }
  }

  /**
   * TabPane에 Tab이 존재하는지 검사
   * @return
   */
  private boolean isTabExist() {
    return tabPane.getSelectionModel().getSelectedItem() != null;
  }

  /**
   * 지정된 경로에 현재 Focus 된 Tab의 Text file 저장
   * @param file
   */
  private void saveFile(File file){
    HBox hBox = (HBox) tabPane.getSelectionModel().getSelectedItem().getContent();
    TextArea textArea = (TextArea) hBox.getChildren().get(0);
    try{
      FileWriter writer = null;
      writer = new FileWriter(file);
      writer.write(textArea.getText().replaceAll("\n", "\r\n"));
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Txt File을 읽어 Tab으로 생성
   * @param txtFile
   * @return
   */
  private Tab addTabFromFile(File txtFile){
    Tab tab = new Tab();
    tab.setText(txtFile.getName());
    TextArea textArea = new TextArea();
    textArea.setEditable(false);

    HBox hBox = new HBox();
    hBox.setStyle("-fx-background-color:white");
    HBox.setHgrow(textArea, Priority.ALWAYS);

    //상단의 메뉴 바 하단에 배치
    hBox.setMargin(textArea, new Insets(35, 3, 3, 3));

    BufferedReader br = null;
    try{
      br = new BufferedReader(new InputStreamReader(new FileInputStream(txtFile)));
      String line;
      while((line = br.readLine()) != null){
        textArea.appendText(line + "\n");
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    hBox.getChildren().add(textArea);
    tab.setContent(hBox);
    return tab;
  }

  public void openNewTab(String path){
    tabPane.getTabs().add(addTabFromFile(new File(path)));
  }
}
