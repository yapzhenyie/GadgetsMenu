package com.yapzhenyie.GadgetsMenu.api.events.pets;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.yapzhenyie.GadgetsMenu.utils.cosmetics.pets.petdata.GPet;

public class SendPetForMissionEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private Player owner;
	private GPet pet;
	private int expObtained;

	public SendPetForMissionEvent(Player owner, GPet pet, int expObtained) {
		this.owner = owner;
		this.pet = pet;
		this.expObtained = expObtained;
	}

	public Player getOwner() {
		return this.owner;
	}
	
	public GPet getGPet() {
		return this.pet;
	}

	public int getEXPObtained() {
		return this.expObtained;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
