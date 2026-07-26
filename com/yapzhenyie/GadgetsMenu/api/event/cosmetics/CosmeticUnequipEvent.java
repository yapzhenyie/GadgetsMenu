package com.yapzhenyie.GadgetsMenu.api.event.cosmetics;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.yapzhenyie.GadgetsMenu.cosmetics.Category;
import com.yapzhenyie.GadgetsMenu.cosmetics.type.CosmeticType;

/**
 * Called when a player unequips a currently equipped cosmetic, regardless of
 * its {@link Category} (hats, animated hats, particles, suits, gadgets, pets,
 * miniatures, morphs, banners, emotes or cloaks).
 * <p>
 * This is a single, unified event fired for every cosmetic category. The cosmetic
 * being removed is exposed as its {@link CosmeticType} through
 * {@link #getCosmeticType()}. Use {@link #getCategory()} to determine which type
 * of cosmetic was removed. The event only fires when there was actually an active
 * cosmetic of that category to remove; it is not fired when an unequip call is a
 * no-op.
 * <p>
 * A suit set is made up of four {@code SuitEquipmentType} armour pieces, so
 * unequipping a whole suit fires this event once per armour piece.
 * <p>
 * This event is <b>purely informational and cannot be cancelled</b>. It is
 * fired in many contexts (manually switching cosmetics, logging out, entering a
 * blacklisted region, cleanup, etc.), and allowing a listener to keep a
 * cosmetic equipped in those situations could leave the player in an
 * inconsistent state. Use it for logging, statistics or synchronising external
 * systems rather than for control flow.
 *
 * @author Yap Zhen Yie
 * @since 5.30.0
 */
public class CosmeticUnequipEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private final Player player;
	private final CosmeticType cosmeticType;

	/**
	 * Constructs a new {@link CosmeticUnequipEvent}.
	 *
	 * @param player       The player unequipping the cosmetic.
	 * @param cosmeticType The cosmetic being unequipped.
	 */
	public CosmeticUnequipEvent(Player player, CosmeticType cosmeticType) {
		this.player = player;
		this.cosmeticType = cosmeticType;
	}

	/**
	 * Gets the player unequipping the cosmetic.
	 *
	 * @return The player.
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Gets the {@link CosmeticType} being unequipped. This exposes the full metadata
	 * of the cosmetic, such as its name, display name, permission, rarity and
	 * mystery dust price.
	 *
	 * @return The cosmetic being unequipped.
	 */
	public CosmeticType getCosmeticType() {
		return this.cosmeticType;
	}

	/**
	 * Gets the {@link Category} of the cosmetic being unequipped. This is a
	 * shorthand for {@code getCosmeticType().getCategory()}.
	 *
	 * @return The cosmetic category.
	 */
	public Category getCategory() {
		return this.cosmeticType.getCategory();
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
