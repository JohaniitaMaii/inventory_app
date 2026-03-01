package com.portfolio.inventory_app.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    List<Token> findAllValidIsFalseOrRevokedIsFalseByUsuarioId(Long id);
    Optional<Token> findByToken(String token);


}
