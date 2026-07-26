package com.yapzhenyie.GadgetsMenu.api.event.mysteryboxes;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.yapzhenyie.GadgetsMenu.mysteryboxes.MysteryBoxes;
import com.yapzhenyie.GadgetsMenu.mysteryboxes.MysteryBoxesLoot;

/**
 * Called when a player is awarded the loot from an opened mystery box, once the
 * mystery vault animation has finished and the reward is handed out.
 * <p>
 * Whereas {@link OpenMysteryBoxEvent} fires <i>before</i> a box is opened (and
 * can be cancelled to prevent opening), this event fires <i>after</i> the loot
 * has been rolled and reveals exactly what the player received. It is the right
 * place to log drops, feed custom reward tracking or trigger extra rewards.
 * <p>
 * Use {@link #isDuplicate()} to distinguish between a brand-new cosmetic
 * (unlocked for the player) and a duplicate the player already owned (typically
 * converted into mystery dust by the plugin).
 * <p>
 * This event is <b>informational and cannot be cancelled</b>: at the point it is
 * fired the box has already been consumed and the loot already determined.
 *
 * @author Yap Zhen Yie
 * @since 5.30.0
 */
public class MysteryBoxRewardEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private final Player player;
	private final MysteryBoxes mysteryBox;
	private final MysteryBoxesLoot loot;
	private final boolean duplicate;

	/**
	 * Constructs a new {@link MysteryBoxRewardEvent}.
	 *
	 * @param player     The player receiving the reward.
	 * @param mysteryBox The mystery box that was opened.
	 * @param loot       The loot that was rolled from the mystery box.
	 * @param duplicate  {@code true} if the player already owned this cosmetic
	 *                   (a duplicate), {@code false} if it is a new unlock.
	 */
	public MysteryBoxRewardEvent(Player player, MysteryBoxes mysteryBox, MysteryBoxesLoot loot, boolean duplicate) {
		this.player = player;
		this.mysteryBox = mysteryBox;
		this.loot = loot;
		this.duplicate = duplicate;
	}

	/**
	 * Gets the player receiving the reward.
	 *
	 * @return The player.
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Gets the mystery box that was opened to produce this reward.
	 *
	 * @return The opened {@link MysteryBoxes}.
	 */
	public MysteryBoxes getMysteryBox() {
		return this.mysteryBox;
	}

	/**
	 * Gets the loot that was rolled from the mystery box. The loot carries the
	 * rewarded cosmetic's category, name, rarity and display name.
	 *
	 * @return The rolled {@link MysteryBoxesLoot}.
	 */
	public MysteryBoxesLoot getLoot() {
		return this.loot;
	}

	/**
	 * Determines whether the rewarded cosmetic was already owned by the player.
	 * <p>
	 * When {@code true}, the player already had this cosmetic unlocked and the
	 * drop is typically compensated with mystery dust instead. When
	 * {@code false}, the cosmetic is a new unlock for the player.
	 *
	 * @return {@code true} if this is a duplicate reward, otherwise {@code false}.
	 */
	public boolean isDuplicate() {
		return this.duplicate;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
