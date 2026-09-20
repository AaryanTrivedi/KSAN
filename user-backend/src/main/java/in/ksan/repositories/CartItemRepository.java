package in.ksan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ksan.models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

}
