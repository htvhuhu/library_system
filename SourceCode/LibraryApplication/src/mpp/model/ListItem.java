package mpp.model;

public class ListItem {
	private String itemName;
    private boolean selected = false;

    public ListItem(String itemName, boolean selected) {
        this.itemName = itemName;
        this.selected = selected;
    }

	public String getItemName() {
		return itemName;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
    
}
