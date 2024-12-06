package com.example.usersservice.web.repository;

import com.example.usersservice.web.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByIdentifier(String identifier);
    Page<Users> findAllByNameLikeOrIdentifierLike(String name, String identifier, Pageable pageable);
}
