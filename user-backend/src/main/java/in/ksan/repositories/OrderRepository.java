package in.ksan.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ksan.models.Orders;
import in.ksan.models.UserEntity;

public interface OrderDao extends JpaRepository<Orders, Long>{
	List<Orders> findByUser(UserEntity user);
}
