package br.com.linktree.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.linktree.domain.model.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
    List<Link> findByShorturl_id(String uuid);
    boolean existsByShorturl_id(String uuid);

    @Modifying
    @Transactional
    @Query(
        value = "DELETE FROM link WHERE shorturl_id = ?1", 
        nativeQuery = true
    )
    Integer deleteByShorturl_id(String uuid);
}
