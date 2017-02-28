package ui.common;

/**
 * Created by GSD on 2017-02-27.
 */
public class SelectedItemData {
  private int index;
  private DataSearchTableRowViewModel data;

  public SelectedItemData(int index, DataSearchTableRowViewModel data) {
    this.index = index;
    this.data = data;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public DataSearchTableRowViewModel getData() {
    return data;
  }

  public void setData(DataSearchTableRowViewModel data) {
    this.data = data;
  }
}
