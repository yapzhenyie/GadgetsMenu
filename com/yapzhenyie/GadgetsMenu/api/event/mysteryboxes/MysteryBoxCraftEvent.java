package com.yapzhenyie.GadgetsMenu.api.event.mysteryboxes;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.yapzhenyie.GadgetsMenu.utils.mysteryboxes.CraftMysteryBoxType;

/**
 * Called when a player crafts a mystery box from mystery dust through the
 * mystery box crafting menu.
 * <p>
 * This event is fired <b>before</b> the mystery dust is deducted and before the
 * mystery box is given to the player. Cancelling it aborts the craft entirely:
 * no dust is spent and no box is created. This lets plugins gate crafting (for
 * example by region, rank or a custom requirement) or charge through a different
 * economy.
 * <p>
 * The {@link #getPrice() price} reported by this event is the final price the
 * player will be charged, i.e. after any active discount has been applied.
 *
 * @author Yap Zhen Yie
 * @since 5.30.0
 */
public class MysteryBoxCraftEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private final Player player;
	private final CraftMysteryBoxType craftMysteryBoxType;
	private final int price;

	private boolean cancelled = false;

	/**
	 * Constructs a new {@link MysteryBoxCraftEvent}.
	 *
	 * @param player              The player crafting the mystery box.
	 * @param craftMysteryBoxType The type of mystery box being crafted.
	 * @param price               The final mystery dust price (after discount)
	 *                            the player will be charged.
	 */
	public MysteryBoxCraftEvent(Player player, CraftMysteryBoxType craftMysteryBoxType, int price) {
		this.player = player;
		this.craftMysteryBoxType = craftMysteryBoxType;
		this.price = price;
	}

	/**
	 * Gets the player crafting the mystery box.
	 *
	 * @return The player.
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Gets the type of mystery box being crafted.
	 *
	 * @return The {@link CraftMysteryBoxType} being crafted.
	 */
	public CraftMysteryBoxType getCraftMysteryBoxType() {
		return this.craftMysteryBoxType;
	}

	/**
	 * Gets the final price, in mystery dust, that the player will be charged for
	 * crafting this mystery box. Any active discount has already been applied to
	 * this value.
	 *
	 * @return The final mystery dust price.
	 */
	public int getPrice() {
		return this.price;
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
