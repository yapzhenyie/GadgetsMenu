package com.yapzhenyie.GadgetsMenu.api.events.mysteryvault;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.yapzhenyie.GadgetsMenu.utils.mysteryvault.MysteryVault;

public class MysteryVaultPreviewEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private Player player;
	private MysteryVault mysteryVault;

	private boolean cancelled = false;

	public MysteryVaultPreviewEvent(Player player, MysteryVault mysteryVault) {
		this.player = player;
		this.mysteryVault = mysteryVault;
	}

	public Player getPlayer() {
		return this.player;
	}
	
	public MysteryVault getClickedMysteryVault() {
		return this.mysteryVault;
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
