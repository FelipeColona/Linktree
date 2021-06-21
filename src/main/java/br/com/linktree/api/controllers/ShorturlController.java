package br.com.linktree.api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.linktree.domain.model.Shorturl;
import br.com.linktree.repository.LinkRepository;
import br.com.linktree.repository.ShorturlRepository;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@RestController
@RequestMapping("/shorturl")
public class ShorturlController {
    @Autowired
    private ShorturlRepository shorturlRepository;

    @Autowired
    private LinkRepository linkRepository;

    @GetMapping
    public List<Shorturl> list(){
        return shorturlRepository.findAll(); 
    } 

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Shorturl create(){
        Shorturl shorturl = new Shorturl();
        String uuid = this.genUUID();
        shorturl.setId(uuid);

        return shorturlRepository.save(shorturl);
    }

    @DeleteMapping("/{shorturlId}")
    public ResponseEntity<Void> exclude(@PathVariable String shorturlId){
        linkRepository.deleteByShorturl_id(shorturlId);

        shorturlRepository.deleteByShorturl_id(shorturlId);

        return ResponseEntity.noContent().build();   
    }

    private String genUUID(){
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
        if(shorturlRepository.findById(uuid).isPresent()){
            this.genUUID();
        }
        return uuid;
    }
}
