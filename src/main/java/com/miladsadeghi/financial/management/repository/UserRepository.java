package com.miladsadeghi.financial.management.repository;

import com.miladsadeghi.financial.management.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User getByUsername(String userName);
}
