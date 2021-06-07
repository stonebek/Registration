package org.example.registraion.form.dao.repository;

import org.example.registraion.form.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
