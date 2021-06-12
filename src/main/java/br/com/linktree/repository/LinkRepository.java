package br.com.linktree.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.linktree.domain.model.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {
    List<Link> findByShorturl_id(String uuid);
    boolean existsByShorturl_id(String uuid);
}
