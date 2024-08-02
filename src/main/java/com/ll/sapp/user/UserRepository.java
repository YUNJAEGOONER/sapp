package com.ll.sapp.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//user class의 id의 데이터 타입은 Long
public interface UserRepository extends JpaRepository<SiteUser, Long> {
    Optional<SiteUser> findByusername(String username);
}
