package com.yapzhenyie.GadgetsMenu.api.events.pets;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.yapzhenyie.GadgetsMenu.cosmetics.pets.Pet;

public class PetHatEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private Pet pet;
	private Type type;

	private boolean cancelled = false;

	public PetHatEvent(Pet pet, Type type) {
		this.pet = pet;
		this.type = type;
	}

	public Pet getPet() {
		return this.pet;
	}

	public Type getEventType() {
		return this.type;
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

	public enum Type {
		SET, REMOVE
	}
}
