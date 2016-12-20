package graphics.ui.icon;

import java.awt.Graphics;
import input.Mouse;

public class UiIcons {
	public static Icon[] icons;

	public static void init() {
		icons = new Icon[3];
		icons[0] = new Icon(30, 570, "/res/icons/wood-axe.png", 60, 60);
		icons[1] = new Icon(120, 570, "/res/icons/mining.png", 60, 60);
		icons[2] = new Icon(210, 570, "/res/icons/trowel.png", 60, 60);

	}

	public static void render(Graphics g) {
		for (Icon i : icons) {
			i.render(g);
		}

	}

	public static boolean hoverOnAnyIcon() {
		return isMiningHover() || isTrowelHover() || isWoodHover();
	}

	public static void update(Mouse mouse) {
		for (Icon i : icons) {
			i.setHoverOn((((mouse.getTrueXPixels()) >= i.getX())
					&& ((mouse.getTrueXPixels()) <= i.getX() + (i.getWidth())) && ((mouse.getTrueYPixels()) >= i.getY())
					&& ((mouse.getTrueYPixels()) <= i.getY() + (i.getHeight()))));
		}

	}

	public static boolean isWoodHover() {
		return icons[0].hoverOn();
	}

	public static boolean isMiningHover() {
		return icons[1].hoverOn();
	}

	public static boolean isTrowelHover() {
		return icons[2].hoverOn();
	}

	public static void select() {
		for (Icon i : icons) {
			i.setSelect(i.hoverOn());
		}
	}

	public static boolean isWoodSelected() {
		return icons[0].selected();
	}

	public static boolean isMiningSelected() {
		return icons[1].selected();
	}

	public static boolean isTrowelSelected() {
		return icons[2].selected();
	}

	public static void setMiningSelected(boolean select) {
		icons[1].setSelect(select);
	}

	public static void setWoodSelected(boolean select) {
		icons[0].setSelect(select);
	}

	public static void setTrowelSelected(boolean select) {
		icons[2].setSelect(select);
	}

}
