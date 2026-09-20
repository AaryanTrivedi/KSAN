package in.ksan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ksan.dto.CartItemDto;
import in.ksan.dto.OrderDto;
import in.ksan.models.CartItem;
import in.ksan.models.Orders;
import in.ksan.service.CartService;
import in.ksan.service.ProductService;
import in.ksan.service.UserService;


@RestController
@CrossOrigin
public class CartController {
    @Autowired private CartService cartService;
    @Autowired private ProductService orderService;
    @Autowired private UserService userService;
    
    @PostMapping("/order/checkout")
    public ResponseEntity<OrderDto> checkout() {
        Long userId = userService.getUserByJwt().id();
        Orders order;
        try {
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(null);
    }

    private OrderDto convertToDto(Orders order) {
        return null;
    }

    
    @PostMapping("/cart/add/{productId}/{quantity}")
    public ResponseEntity<?> addToCart(	@PathVariable Long productId,
    									@PathVariable int quantity) {
    	Long user = userService.getUserByJwt().id();
        cartService.addToCart(user, productId , quantity);
        return ResponseEntity.ok().build();
    }
    
    
    @GetMapping("/cart/view")
    public ResponseEntity<List<CartItemDto>> viewCart() {
        Long userId = userService.getUserByJwt().id();
        List<CartItemDto> cartItems = cartService.getCartItems(userId)
                .stream()
                .map(this::convertToDto)
                .toList();
        return ResponseEntity.ok(cartItems);
    }

    private CartItemDto convertToDto(CartItem cartItem) {
        return null;
    }
    
    
    
}