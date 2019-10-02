package com.yapzhenyie.GadgetsMenu.api.events.pets;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.yapzhenyie.GadgetsMenu.utils.cosmetics.pets.entity.IEntityPet;

public class PetMoveEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private IEntityPet entity;
	private Location targetLocation;
	private Cause cause;

	public PetMoveEvent(IEntityPet entity, Cause cause) {
		this.entity = entity;
		if (cause == Cause.RIDE) {
			this.targetLocation = entity.getEntity().getLocation();
		} else {
			this.targetLocation = entity.getPet().getOwner().getLocation();
		}
	}

	public PetMoveEvent(IEntityPet entity, Cause cause, boolean async) {
		super(async);
		this.entity = entity;
		this.cause = cause;
		if (cause == Cause.RIDE) {
			this.targetLocation = entity.getEntity().getLocation();
		} else {
			this.targetLocation = entity.getPet().getOwner().getLocation();
		}
	}

	public IEntityPet getEntity() {
		return this.entity;
	}

	public Location getTargetLocation() {
		return this.targetLocation;
	}

	public Cause getCause() {
		return this.cause;
	}

	public enum Cause {
		RIDE, WALK
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
