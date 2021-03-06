package graphics.ui.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import entity.Entity;
import entity.mob.work.BuildingRecipe;
import entity.mob.work.ItemRecipe;
import entity.mob.work.Recipe;
import input.Mouse;

public class MenuItem {
	private String text; // text on the menuitem
	public int x, y; // x and y of top left corner
	private int width; // width of the menuitem
	private boolean hover; // is the mouse hovering over the item
	private static final Font font = new Font("Dialog", Font.LAYOUT_LEFT_TO_RIGHT, 15); // font
	// some static strings to use as menuitem texts
	public static final String CANCEL = "Cancel";
	public static final String MOVE = "Move";
	public static final String CHOP = "Chop";
	public static final String MINE = "Mine";
	public static final String BUILD = "Build";
	public static final String PICKUP = "Pick up";
	public static final String DROP = "Drop";
	public static final String FIGHT = "Fight";
	public static final String EQUIP = "Equip";
	public static final String WEAR = "Wear";
	public static final String CRAFT = "Craft";
	public static final String SMELT = "Smelt";
	public static final String SMITH = "Smith";
	public static final String IRON = "Iron";
	public static final String COPPER = "Copper";
	public static final String GOLD = "Gold";
	public static final String CRYSTAL = "Crystal";
	public static final String WOOD = "Wood";

	private Recipe recipe;
	private Entity entity;

	// constructor
	public MenuItem(String text) {
		this.text = text;

	}

	public MenuItem(String text, Entity e) {
		this(text + " " + e.getName());
		this.entity = e;

	}

	public MenuItem(ItemRecipe recipe) {
		this.text = CRAFT + " " + recipe.getRecipeName();
		this.recipe = recipe;
	}

	public MenuItem(BuildingRecipe recipe) {
		this.text = BUILD + " " + recipe.getRecipeName();
		this.recipe = recipe;
	}

	public void init(Menu menu) {
		x = menu.getX();
		width = menu.getWidth();
		y = menu.getYLocForMenuItem();
	}

	public Entity getEntity() {
		return entity;
	}

	// rendering the menuitem's text
	public void render(Graphics g) {
		g.setColor(hover ? Color.red : Color.black);
		g.setFont(font);
		g.drawString(text, x, y + 15);
	}

	// updating the mouse hover
	public void update(Mouse mouse) {
		hover = ((((mouse.getTrueXPixels()) >= x) && ((mouse.getTrueXPixels()) <= x + width)
				&& ((mouse.getTrueYPixels()) >= y) && ((mouse.getTrueYPixels()) <= y + 10)));
	}

	// getter
	public boolean clicked(Mouse mouse) {
		return hover && mouse.getClickedLeft();
	}

	public String getText() {
		return text;
	}

	@SuppressWarnings("unchecked") // again, shouldnt ever be a problem
	public <T extends Recipe> T getRecipe() {
		return (T) recipe;
	}

}
