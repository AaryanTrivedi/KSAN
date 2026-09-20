package in.ksan.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ksan.models.FarmingType;
import in.ksan.models.UserEntity;
import in.ksan.models.UserRole;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByIdAndStatusTrue(Long id);

	char[] existsByPassword(String string);

	boolean existsByEmail(String email);

	List<UserEntity> findByFarmingType(FarmingType type);

	List<UserEntity> findByRole(UserRole role);
	
	List<UserEntity> findByStatusTrue();


}
