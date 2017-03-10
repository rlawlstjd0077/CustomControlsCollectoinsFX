package ui.screen.preciseorbitprediction;

import fds.ui.common.FDSCheckBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * Created by GSD on 2017-01-18.
 */
public class StationDataRowViewModel {
  private String id;
  private String name;
  private FDSCheckBox checked = new FDSCheckBox();

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

  public FDSCheckBox getChecked() {
    return checked;
  }


}
