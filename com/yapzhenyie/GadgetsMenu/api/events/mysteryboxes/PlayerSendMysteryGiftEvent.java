package com.yapzhenyie.GadgetsMenu.api.events.mysteryboxes;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerSendMysteryGiftEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private Player sender;
	private Player receiver;

	public PlayerSendMysteryGiftEvent(Player sender, Player receiver) {
		this.sender = sender;
		this.receiver = receiver;
	}

	public Player getSender() {
		return this.sender;
	}

	public Player getReceiver() {
		return this.receiver;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
