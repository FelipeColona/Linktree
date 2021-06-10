package br.com.linktree.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.linktree.domain.model.Shorturl;

public interface ShorturlRepository extends JpaRepository<Shorturl, Long> {
    Optional<Shorturl> findById(String uuid);
    List<Shorturl> findByTitle(String title);
}
