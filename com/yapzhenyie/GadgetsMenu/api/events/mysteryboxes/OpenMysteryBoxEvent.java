package com.yapzhenyie.GadgetsMenu.api.events.mysteryboxes;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.yapzhenyie.GadgetsMenu.mysteryboxes.MysteryBoxes;

public class OpenMysteryBoxEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private Player player;
	private MysteryBoxes mysteryBox;

	private boolean cancelled = false;

	public OpenMysteryBoxEvent(Player player, MysteryBoxes mysteryBox) {
		this.player = player;
		this.mysteryBox = mysteryBox;
	}

	public Player getPlayer() {
		return this.player;
	}

	public MysteryBoxes getSelectedMysteryBox() {
		return this.mysteryBox;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
