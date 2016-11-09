package com.OnlyNoobDied.GadgetsMenu.Utils;

import org.bukkit.Color;

public enum EnumColor {

	AQUA(Color.AQUA), 
	BLACK(Color.BLACK), 
	BLUE(Color.BLUE), 
	FUCHSIA(Color.FUCHSIA), 
	GRAY(Color.GRAY), 
	GREEN(Color.GREEN), 
	LIME(Color.LIME), 
	MAROON(Color.MAROON),
	NAVY(Color.NAVY),
	OLIVE(Color.OLIVE),
	ORANGE(Color.ORANGE),
	PURPLE(Color.PURPLE),
	RED(Color.RED),
	SILVER(Color.SILVER), 
	TEAL(Color.TEAL), 
	WHITE(Color.WHITE), 
	YELLOW(Color.YELLOW);

	private Color color;

	private EnumColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}
}
