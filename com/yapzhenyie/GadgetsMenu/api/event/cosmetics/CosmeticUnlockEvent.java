package com.yapzhenyie.GadgetsMenu.api.event.cosmetics;

import java.sql.Timestamp;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.yapzhenyie.GadgetsMenu.cosmetics.Category;
import com.yapzhenyie.GadgetsMenu.cosmetics.type.CosmeticType;

/**
 * Called when a cosmetic item is unlocked (owned) for a player.
 * <p>
 * Unlike {@link ItemPurchaseEvent}, which only covers dust purchases through the
 * shop, this event is fired for <b>every</b> way a cosmetic can become unlocked:
 * <ul>
 * <li>buying it with mystery dust in the item purchase menu,</li>
 * <li>finding it as loot when opening a mystery box,</li>
 * <li>receiving it from an administrative command / API grant.</li>
 * </ul>
 * It is therefore the ideal single hook for developers who want to react to a
 * player gaining ownership of a cosmetic no matter the source.
 * <p>
 * The unlocked cosmetic is exposed as its {@link CosmeticType} through
 * {@link #getCosmeticType()}, giving access to its name, display name,
 * permission, rarity and other metadata.
 * <p>
 * This event is <b>informational and cannot be cancelled</b>; it is fired after
 * the unlock has already been recorded. To prevent a shop purchase, listen to
 * the cancellable {@link ItemPurchaseEvent} instead.
 * <p>
 * A cosmetic may be unlocked either permanently or temporarily. When
 * {@link #getExpiryTime()} returns {@code null} the cosmetic is unlocked
 * permanently; otherwise it will expire at the returned timestamp.
 *
 * @author Yap Zhen Yie
 * @since 5.30.0
 */
public class CosmeticUnlockEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	private final Player player;
	private final CosmeticType cosmeticType;
	private final Timestamp expiryTime;

	/**
	 * Constructs a new {@link CosmeticUnlockEvent}.
	 *
	 * @param player       The player the cosmetic is being unlocked for.
	 * @param cosmeticType The cosmetic being unlocked.
	 * @param expiryTime   The moment the unlock expires, or {@code null} if the
	 *                     cosmetic is unlocked permanently.
	 */
	public CosmeticUnlockEvent(Player player, CosmeticType cosmeticType, Timestamp expiryTime) {
		this.player = player;
		this.cosmeticType = cosmeticType;
		this.expiryTime = expiryTime;
	}

	/**
	 * Gets the player the cosmetic is being unlocked for.
	 *
	 * @return The player.
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Gets the {@link CosmeticType} being unlocked. This exposes the full metadata
	 * of the cosmetic, such as its name, display name, permission and rarity.
	 *
	 * @return The cosmetic being unlocked.
	 */
	public CosmeticType getCosmeticType() {
		return this.cosmeticType;
	}

	/**
	 * Gets the {@link Category} of the unlocked cosmetic. This is a shorthand for
	 * {@code getCosmeticType().getCategory()}.
	 *
	 * @return The cosmetic category.
	 */
	public Category getCategory() {
		return this.cosmeticType.getCategory();
	}

	/**
	 * Gets the time at which this unlock expires.
	 *
	 * @return The expiry timestamp, or {@code null} if the cosmetic is unlocked
	 *         permanently.
	 */
	public Timestamp getExpiryTime() {
		return this.expiryTime;
	}

	/**
	 * Determines whether this unlock is permanent (never expires).
	 *
	 * @return {@code true} if the cosmetic is unlocked permanently, otherwise
	 *         {@code false}.
	 */
	public boolean isPermanent() {
		return this.expiryTime == null;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
