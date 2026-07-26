package com.yapzhenyie.GadgetsMenu.api.event.emotes;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.yapzhenyie.GadgetsMenu.cosmetics.emotes.EmoteType;

/**
 * Called when a player plays their equipped emote by clicking with it in hand.
 * <p>
 * The event is fired after the emote's cooldown, permission and state checks
 * have passed, but <b>before</b> the emote animation starts playing and before
 * its cooldown is applied. Cancelling it stops the emote from playing and
 * prevents the cooldown from starting, which lets plugins disable emotes in
 * particular areas or game states without modifying the emote implementation.
 *
 * @author Yap Zhen Yie
 * @since 5.30.0
 */
public class EmoteActivateEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private final Player player;
	private final EmoteType emoteType;

	private boolean cancelled = false;

	/**
	 * Constructs a new {@link EmoteActivateEvent}.
	 *
	 * @param player    The player playing the emote.
	 * @param emoteType The type of emote being played.
	 */
	public EmoteActivateEvent(Player player, EmoteType emoteType) {
		this.player = player;
		this.emoteType = emoteType;
	}

	/**
	 * Gets the player playing the emote.
	 *
	 * @return The player.
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Gets the type of emote being played.
	 *
	 * @return The {@link EmoteType} being played.
	 */
	public EmoteType getEmoteType() {
		return this.emoteType;
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
