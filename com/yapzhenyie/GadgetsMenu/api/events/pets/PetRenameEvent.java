package com.yapzhenyie.GadgetsMenu.api.events.pets;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.yapzhenyie.GadgetsMenu.utils.cosmetics.pets.petdata.GPet;

public class PetRenameEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private Player owner;
	private GPet pet;
	private String petName;

	private boolean cancelled = false;

	public PetRenameEvent(Player owner, GPet pet, String petName) {
		this.owner = owner;
		this.petName = petName;
	}

	public Player getOwner() {
		return this.owner;
	}

	public GPet getPet() {
		return this.pet;
	}

	public String getPetName() {
		return this.petName;
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
