package com.yapzhenyie.GadgetsMenu.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RemoveMysteryDustEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private Player player;
	private int amount;
	private boolean cancelled = false;

	public RemoveMysteryDustEvent(Player player, int amount) {
		this.player = player;
		this.amount = amount;
	}

	public Player getPlayer() {
		return this.player;
	}

	public int getAmount() {
		return this.amount;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancelled = cancel;
	}
}