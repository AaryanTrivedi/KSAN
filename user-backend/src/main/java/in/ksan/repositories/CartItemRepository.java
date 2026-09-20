package in.ksan.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ksan.models.CartItem;

public interface CartItemDao extends JpaRepository<CartItem, Long>{

}
