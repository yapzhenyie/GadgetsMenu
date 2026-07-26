package com.yapzhenyie.GadgetsMenu.api.event.gadgets;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.yapzhenyie.GadgetsMenu.cosmetics.gadgets.GadgetType;

/**
 * Called when a player activates their equipped gadget by clicking with it in
 * hand.
 * <p>
 * The event is fired after the gadget's cooldown, permission and requirement
 * checks have passed, but <b>before</b> the gadget's action is actually
 * executed and before its cooldown is applied. Cancelling it stops the gadget
 * from firing and prevents the cooldown from starting, making it a convenient
 * hook to disable gadget usage in certain areas or game states without touching
 * each individual gadget implementation.
 * <p>
 * Most gadgets are activated with a right click; some are configured to activate
 * on a left click instead. Use {@link #isLeftClick()} to tell the two apart.
 *
 * @author Yap Zhen Yie
 * @since 5.30.0
 */
public class GadgetActivateEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private final Player player;
	private final GadgetType gadgetType;
	private final boolean leftClick;

	private boolean cancelled = false;

	/**
	 * Constructs a new {@link GadgetActivateEvent}.
	 *
	 * @param player     The player activating the gadget.
	 * @param gadgetType The type of gadget being activated.
	 * @param leftClick  {@code true} if the gadget was activated with a left
	 *                   click, {@code false} if it was activated with a right
	 *                   click.
	 */
	public GadgetActivateEvent(Player player, GadgetType gadgetType, boolean leftClick) {
		this.player = player;
		this.gadgetType = gadgetType;
		this.leftClick = leftClick;
	}

	/**
	 * Gets the player activating the gadget.
	 *
	 * @return The player.
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Gets the type of gadget being activated.
	 *
	 * @return The {@link GadgetType} being activated.
	 */
	public GadgetType getGadgetType() {
		return this.gadgetType;
	}

	/**
	 * Determines whether the gadget was activated with a left click.
	 *
	 * @return {@code true} if activated with a left click, {@code false} if
	 *         activated with a right click.
	 */
	public boolean isLeftClick() {
		return this.leftClick;
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
