package br.com.linktree.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.linktree.domain.model.Shorturl;

public interface ShorturlRepository extends JpaRepository<Shorturl, Long> {
    Optional<Shorturl> findById(String uuid);
    
    @Modifying
    @Transactional
    @Query(
        value = "DELETE FROM shorturl WHERE id = ?1", 
        nativeQuery = true
    )
    Integer deleteByShorturl_id(String uuid);
}
