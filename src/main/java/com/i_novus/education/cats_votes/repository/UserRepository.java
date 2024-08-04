package com.i_novus.education.cats_votes.repository;

import com.i_novus.education.cats_votes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
