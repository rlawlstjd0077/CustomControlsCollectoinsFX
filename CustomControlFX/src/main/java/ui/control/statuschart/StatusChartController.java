package ui.control.statuschart;

import commons.ui.UiUtil;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Status 차트를 표시하는 컨트롤러.
 */
public class StatusChartController extends AnchorPane {
  @FXML
  private Label title;

  @FXML
  private PieChart chart;

  @FXML
  private Label error;

  @FXML
  private Label process;

  @FXML
  private Label plan;

  /**
   * Status 차트 컨트롤러 생성자.
   *
   * @throws IOException FXML 연결을 실패하였을 때 발생하는 예외.
   */
  public StatusChartController() throws IOException {
    UiUtil.loadFxml(this);
  }

  /**
   * ViewModel 객체와 바인딩.
   *
   * @param viewModel ViewModel,
   */
  public void bind(ViewModel viewModel) {
    title.textProperty().bind(viewModel.titleProperty());
    error.textProperty().bind(Bindings.convert(viewModel.errorProperty()));
    process.textProperty().bind(Bindings.convert(viewModel.processProperty()));
    plan.textProperty().bind(Bindings.convert(viewModel.planProperty()));
    chart.setData(viewModel.dataProperty());
  }

  /**
   * StatusChart를 위한 ViewModel.
   */
  public static class ViewModel {
    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleIntegerProperty error = new SimpleIntegerProperty(10);
    private SimpleIntegerProperty process = new SimpleIntegerProperty(20);
    private SimpleIntegerProperty plan = new SimpleIntegerProperty(100);

    private ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
      new PieChart.Data("Error", 10),
      new PieChart.Data("Process", 200),
      new PieChart.Data("Remain", 300)
    );

    /**
     * Title을 함께 생성하여 적용.
     *
     * @param title StatusChart 타이틀.
     */
    public ViewModel(String title) {
      this.title.set(title);
      error.addListener((observable, oldValue, newValue) -> data.get(0).pieValueProperty().setValue(newValue));
      process.addListener((observable, oldValue, newValue) -> data.get(1).pieValueProperty().setValue(newValue));
      plan.addListener((observable, oldValue, newValue) -> data.get(2).pieValueProperty().setValue(newValue.intValue() - process.get()));
    }

    public SimpleIntegerProperty errorProperty() {
      return error;
    }

    public SimpleIntegerProperty processProperty() {
      return process;
    }

    public SimpleIntegerProperty planProperty() {
      return plan;
    }

    public ObservableList<PieChart.Data> dataProperty() {
      return data;
    }

    public ObservableStringValue titleProperty() {
      return title;
    }
  }
}
