package entity.item;

import entity.Entity;
import entity.mob.Villager;
import graphics.Sprite;

public class Item extends Entity {
	protected String tooltip; // the item's tooltip
	private Villager reservedVil; // the villager that plans to pick the item
									// up, or is already holding it

	// basic constructors
	public Item(String name, int x, int y, Sprite sprite, String tooltip, boolean visible) {
		super(x, y);
		this.sprite = sprite;
		setVisible(visible);
		super.setName(name);
		this.tooltip = tooltip;
	}

	public boolean isSameType(Item item) {
		return item.getName().equals(getName());
	}

	public Item(String name, int x, int y, Sprite sprite, boolean visible) {
		this(name, x, y, sprite, "", visible);
	}

	public Item(String name, Sprite sprite, boolean visible) {
		super.setName(name);
		this.sprite = sprite;
		super.setVisible(visible);
	}

	public Item copy() {
		return new Item(this.getName(), this.x, this.y, this.sprite, this.getToolTip(), this.isVisible());
	}

	// getters and setters
	public String getToolTip() {
		return tooltip;
	}

	public boolean isReserved(Villager vil) {
		return reservedVil == null || reservedVil.equals(vil);
	}

	public void setReserved(Villager vil) {
		reservedVil = vil;
	}

	public void removeReserved() {
		reservedVil = null;
	}

}