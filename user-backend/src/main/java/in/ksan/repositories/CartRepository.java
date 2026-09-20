package in.ksan.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import in.ksan.models.Cart;
import in.ksan.models.UserEntity;

public interface CartRepository extends JpaRepository<Cart, Long>{

	Optional<Cart> findByUser(UserEntity user);

}
