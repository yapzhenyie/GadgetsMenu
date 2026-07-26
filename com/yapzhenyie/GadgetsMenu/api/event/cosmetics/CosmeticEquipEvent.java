package com.yapzhenyie.GadgetsMenu.api.event.cosmetics;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.yapzhenyie.GadgetsMenu.cosmetics.Category;
import com.yapzhenyie.GadgetsMenu.cosmetics.type.CosmeticType;

/**
 * Called when a player equips a cosmetic, regardless of its {@link Category}
 * (hats, animated hats, particles, suits, gadgets, pets, miniatures, morphs,
 * banners, emotes or cloaks).
 * <p>
 * This is a single, unified event fired for every cosmetic category. The cosmetic
 * being equipped is exposed as its {@link CosmeticType} through
 * {@link #getCosmeticType()}, giving access to its name, display name,
 * permission, rarity, mystery dust price and other metadata. Use
 * {@link #getCategory()} to determine which type of cosmetic is being equipped.
 * The event is fired for both player-initiated equips (through the menus) and
 * automatic equips (auto-equip after purchase, auto-equip on loot found and
 * cosmetic restoration when a player joins).
 * <p>
 * The event is fired <b>before</b> the cosmetic is actually applied to the
 * player. Cancelling it prevents the cosmetic from being equipped, which allows
 * plugins to block cosmetics in specific contexts (arenas, combat, minigames,
 * restricted worlds, etc.) without having to hook every individual cosmetic
 * type.
 * <p>
 * A suit set is made up of four {@code SuitEquipmentType} armour pieces. Equipping
 * a whole suit therefore fires this event once per armour piece, mirroring
 * {@link CosmeticUnequipEvent}; cancelling the event for a single piece blocks
 * only that piece.
 *
 * @author Yap Zhen Yie
 * @since 5.30.0
 */
public class CosmeticEquipEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();

	private final Player player;
	private final CosmeticType cosmeticType;

	private boolean cancelled = false;

	/**
	 * Constructs a new {@link CosmeticEquipEvent}.
	 *
	 * @param player       The player equipping the cosmetic.
	 * @param cosmeticType The cosmetic being equipped.
	 */
	public CosmeticEquipEvent(Player player, CosmeticType cosmeticType) {
		this.player = player;
		this.cosmeticType = cosmeticType;
	}

	/**
	 * Gets the player equipping the cosmetic.
	 *
	 * @return The player.
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Gets the {@link CosmeticType} being equipped. This exposes the full metadata
	 * of the cosmetic, such as its name, display name, permission, rarity and
	 * mystery dust price.
	 *
	 * @return The cosmetic being equipped.
	 */
	public CosmeticType getCosmeticType() {
		return this.cosmeticType;
	}

	/**
	 * Gets the {@link Category} of the cosmetic being equipped. This is a shorthand
	 * for {@code getCosmeticType().getCategory()}.
	 *
	 * @return The cosmetic category.
	 */
	public Category getCategory() {
		return this.cosmeticType.getCategory();
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