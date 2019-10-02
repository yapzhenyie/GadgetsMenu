package com.yapzhenyie.GadgetsMenu.api.events.pets;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.yapzhenyie.GadgetsMenu.cosmetics.pets.PetType;

public class PetPreSpawnEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private Player player;
	private Location spawnLocation;
	private PetType petType;

	public PetPreSpawnEvent(Player player, Location spawnLocation, PetType type) {
		this.player = player;
		this.spawnLocation = spawnLocation;
		this.petType = type;
	}

	public Player getPlayer() {
		return this.player;
	}

	public Location getSpawnLocation() {
		return this.spawnLocation;
	}

	public PetType getPetType() {
		return this.petType;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
