package ui.screen.viewmodel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;

/**
 * Created by JinSeong on 2017-01-18.
 */
public class StationDataRowViewModel {
  private String id;
  private String name;
  private CheckBox checked = new CheckBox();

  public interface MyOnChecked{
    public void onChecked();
  }

  private MyOnChecked myOnChecked;

  public void setMyOnChecked(MyOnChecked onChecked){
    myOnChecked = onChecked;
  }

  public StationDataRowViewModel(String id, String name){
    this.id = id;
    this.name = name;
    this.checked.getStyleClass().add("gk2-check-box");
    checked.selectedProperty().addListener(new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        myOnChecked.onChecked();
      }
    });
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public CheckBox getChecked() {
    return checked;
  }


}
