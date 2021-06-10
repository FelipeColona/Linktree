package br.com.linktree.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.linktree.domain.model.Shorturl;

public interface ShorturlRepository extends JpaRepository<Shorturl, Long> {
    
}
