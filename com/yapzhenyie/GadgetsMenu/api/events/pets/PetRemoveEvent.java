package com.yapzhenyie.GadgetsMenu.api.events.pets;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.yapzhenyie.GadgetsMenu.cosmetics.pets.Pet;

public class PetRemoveEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private Pet pet;

	private boolean cancelled = false;

	public PetRemoveEvent(Pet pet) {
		this.pet = pet;
	}

	public Pet getPet() {
		return this.pet;
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
