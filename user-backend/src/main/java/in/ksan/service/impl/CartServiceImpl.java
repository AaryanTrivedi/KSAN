package in.ksan.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ksan.exceptions.ResourceNotFoundException;
import in.ksan.repositories.CartRepository;
import in.ksan.repositories.ProductDao;
import in.ksan.repositories.UserDao;
import in.ksan.models.Cart;
import in.ksan.models.CartItem;
import in.ksan.models.Products;
import in.ksan.models.UserEntity;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired private CartRepository cartRepository;
    @Autowired private ProductDao productDao;
    @Autowired private UserDao userDao;

    public void addToCart(Long userId, Long productId, int quantity) {
        Products product = productDao.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        
        if (!product.isMarkedForSale() || product.getStockToSell() < quantity) {
            throw new IllegalArgumentException("Product not available for sale in the requested quantity");
        }
        UserEntity user = userDao.findById(userId).get();
        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cart.addItem(newItem);
        }

        cartRepository.save(cart);
    }

	@Override
	public List<CartItem> getCartItems(Long userId) {
        UserEntity user = userDao.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getActiveCart().getItems();
    }
}