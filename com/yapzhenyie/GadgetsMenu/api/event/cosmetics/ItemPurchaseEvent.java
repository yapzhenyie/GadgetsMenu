package com.yapzhenyie.GadgetsMenu.api.event.cosmetics;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.yapzhenyie.GadgetsMenu.cosmetics.Category;
import com.yapzhenyie.GadgetsMenu.utils.ItemPurchaseMetadata;
import com.yapzhenyie.GadgetsMenu.utils.discount.ItemCostDiscount;

/**
 * Called when a player attempts to purchase a cosmetic item with mystery dust
 * through the item purchase confirmation menu.
 * <p>
 * The event is fired for <b>both</b> outcomes of an attempt — a completed
 * purchase and a rejected one. Use {@link #getStatus()} (or the shorthand
 * {@link #isSuccessful()}) to tell them apart:
 * <ul>
 * <li>{@link PurchaseStatus#SUCCESS} — every requirement was met and the mystery
 * dust has just been deducted. The event is fired immediately before the
 * cosmetic is unlocked and equipped, so a listener may still
 * {@link #setCancelled(boolean) cancel} it to veto the purchase; the deducted
 * dust is automatically refunded when that happens.</li>
 * <li>{@link PurchaseStatus#FAILED} — the attempt was rejected (missing
 * permission, not enough mystery dust, an invalid selection or a failed
 * deduction). A failed event is purely informational and cancelling it has no
 * effect.</li>
 * </ul>
 * The reported {@link #getPrice() price} is the final price the player is (or
 * would have been) charged, i.e. the base price after any active
 * {@link ItemCostDiscount discount} has already been applied.
 *
 * @author Yap Zhen Yie
 * @since 5.30.0
 */
public class ItemPurchaseEvent extends Event implements Cancellable {

	/**
	 * Represents the outcome of a cosmetic item purchase attempt.
	 */
	public enum PurchaseStatus {

		/**
		 * The purchase met every requirement and the mystery dust has been
		 * deducted. The cosmetic has not been granted yet, so the event can still
		 * be cancelled to veto it.
		 */
		SUCCESS,

		/**
		 * The purchase attempt was rejected before completion, for example because
		 * the player lacked the required permission, did not have enough mystery
		 * dust, made an invalid selection or the mystery dust could not be
		 * deducted.
		 */
		FAILED;
	}

	private static final HandlerList handlers = new HandlerList();

	private final Player player;
	private final Category category;
	private final String cosmeticName;
	private final String displayName;
	private final ItemCostDiscount discount;
	private final int price;
	private final String permission;
	private final PurchaseStatus status;

	private boolean cancelled = false;

	/**
	 * Constructs a new {@link ItemPurchaseEvent}.
	 *
	 * @param player       The player who is purchasing the cosmetic item.
	 * @param category     The category the purchased cosmetic belongs to.
	 * @param cosmeticName The internal name of the cosmetic being purchased.
	 * @param displayName  The formatted display name of the cosmetic being
	 *                     purchased.
	 * @param discount     The discount applied to this purchase, or {@code null}
	 *                     if no discount is active.
	 * @param price        The final price (after discount) in mystery dust that
	 *                     the player is charged.
	 * @param permission   The permission granted to the player once the cosmetic
	 *                     is unlocked.
	 * @param status       The outcome of the purchase attempt.
	 */
	public ItemPurchaseEvent(Player player, Category category, String cosmeticName, String displayName,
			ItemCostDiscount discount, int price, String permission, PurchaseStatus status) {
		this.player = player;
		this.category = category;
		this.cosmeticName = cosmeticName;
		this.displayName = displayName;
		this.discount = discount;
		this.price = price;
		this.permission = permission;
		this.status = status;
	}

	/**
	 * Convenience constructor that snapshots the details of a purchase attempt
	 * from the given {@link ItemPurchaseMetadata}.
	 * <p>
	 * The relevant values are copied into this event's immutable fields at
	 * construction time; the {@link ItemPurchaseMetadata} instance itself is not
	 * retained and is never exposed through the API. This keeps the event a
	 * read-only data holder even though {@link ItemPurchaseMetadata} is a mutable,
	 * internally reused object.
	 *
	 * @param player               The player who is purchasing the cosmetic item.
	 * @param itemPurchaseMetadata The purchase attempt to snapshot. The reported
	 *                             price is the final price after any active
	 *                             discount.
	 * @param status               The outcome of the purchase attempt.
	 */
	public ItemPurchaseEvent(Player player, ItemPurchaseMetadata itemPurchaseMetadata, PurchaseStatus status) {
		this(player, itemPurchaseMetadata.getCategory(), itemPurchaseMetadata.getName(),
				itemPurchaseMetadata.getDisplayName(), itemPurchaseMetadata.getItemCostDiscount(),
				itemPurchaseMetadata.getPrice(), itemPurchaseMetadata.getPermission(), status);
	}

	/**
	 * Gets the player who is purchasing the cosmetic item.
	 *
	 * @return The purchasing player.
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Gets the {@link Category} the purchased cosmetic belongs to.
	 *
	 * @return The cosmetic category.
	 */
	public Category getCategory() {
		return this.category;
	}

	/**
	 * Gets the internal name of the cosmetic being purchased. This is the name
	 * that can be passed to {@code CosmeticType#valueOf(String, Category)} to
	 * resolve the underlying cosmetic type.
	 *
	 * @return The internal cosmetic name.
	 */
	public String getCosmeticName() {
		return this.cosmeticName;
	}

	/**
	 * Gets the formatted (colour translated) display name of the cosmetic being
	 * purchased.
	 *
	 * @return The cosmetic display name.
	 */
	public String getDisplayName() {
		return this.displayName;
	}

	/**
	 * Gets the discount applied to this purchase.
	 *
	 * @return The applied {@link ItemCostDiscount}, or {@code null} if no
	 *         discount is active for this purchase.
	 */
	public ItemCostDiscount getItemCostDiscount() {
		return this.discount;
	}

	/**
	 * Gets the final price, in mystery dust, that the player is charged for this
	 * purchase. Any active discount has already been applied to this value.
	 *
	 * @return The final mystery dust price.
	 */
	public int getPrice() {
		return this.price;
	}

	/**
	 * Gets the permission that will be granted to the player when the cosmetic is
	 * unlocked.
	 *
	 * @return The cosmetic permission.
	 */
	public String getPermission() {
		return this.permission;
	}

	/**
	 * Gets the outcome of this purchase attempt.
	 *
	 * @return The {@link PurchaseStatus} of the attempt.
	 */
	public PurchaseStatus getStatus() {
		return this.status;
	}

	/**
	 * Convenience shorthand for checking whether this purchase attempt succeeded.
	 *
	 * @return {@code true} if the status is {@link PurchaseStatus#SUCCESS},
	 *         otherwise {@code false}.
	 */
	public boolean isSuccessful() {
		return this.status == PurchaseStatus.SUCCESS;
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
