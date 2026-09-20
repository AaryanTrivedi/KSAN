package in.ksan.service;

import java.util.Collection;

import in.ksan.models.CartItem;

public interface CartService {
	void addToCart(Long userId, Long productId, int quantity);

	Collection<CartItem> getCartItems(Long userId);
}
