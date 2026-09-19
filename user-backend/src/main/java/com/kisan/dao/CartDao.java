package com.kisan.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.kisan.models.Cart;
import com.kisan.models.UserEntity;

public interface CartDao extends JpaRepository<Cart, Long>{

	Optional<Cart> findByUser(UserEntity user);

}
