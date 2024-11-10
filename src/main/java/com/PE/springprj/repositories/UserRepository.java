package com.PE.springprj.repositories;

import com.PE.springprj.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

	/*@Query("select u from User u where username = :username")*/
	public User getUserByUsername(@Param("username") String username);
}