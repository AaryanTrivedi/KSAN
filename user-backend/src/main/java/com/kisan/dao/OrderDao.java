package com.kisan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kisan.models.Orders;
import com.kisan.models.UserEntity;

public interface OrderDao extends JpaRepository<Orders, Long>{
	List<Orders> findByUser(UserEntity user);
}
