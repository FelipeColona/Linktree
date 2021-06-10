package br.com.linktree.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;

import br.com.linktree.domain.model.Shorturl;
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

    @GetMapping
    public List<Shorturl> list(){
        return shorturlRepository.findAll(); 
    } 

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Shorturl create(@RequestBody Shorturl shorturl){
        String uuid = this.genUUID();
        shorturl.setId(uuid);

        return shorturlRepository.save(shorturl);
    }

    private String genUUID(){
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
        if(shorturlRepository.findById(uuid).isPresent()){
            this.genUUID();
        }
        return uuid;
    }
}
