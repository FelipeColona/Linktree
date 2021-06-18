package br.com.linktree.api.controllers;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.linktree.Classes.LinksType;
import br.com.linktree.domain.model.Link;
import br.com.linktree.domain.model.Shorturl;
import br.com.linktree.repository.LinkRepository;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@RestController
public class LinkController {

    @Autowired
    private LinkRepository linkRepository;

    @GetMapping("/link")
    public List<Link> list(){
        return linkRepository.findAll();
    }

    @GetMapping("/{shorturlId}")
    public ResponseEntity<List<Link>> search(@PathVariable String shorturlId){
        List<Link> link = linkRepository.findByShorturl_id(shorturlId);
        if(!link.isEmpty()){
            return ResponseEntity.ok(link); 
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/link")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Link> create(@RequestBody LinksType body){
        body.getLinks().forEach(item -> {
            Link l = new Link();
            l.setRedirect(item);

            Shorturl s = new Shorturl();
            s.setId(body.getShorturl_id());

            l.setShorturl(s);

            linkRepository.save(l);
        });

        return linkRepository.findByShorturl_id(body.getShorturl_id());
    }

    @PutMapping("/link")
    public ResponseEntity<Link> update(@RequestBody LinksType body){
        List<Link> link = linkRepository.findByShorturl_id(body.getShorturl_id());

        if(link.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        linkRepository.deleteByShorturl_id(body.getShorturl_id());

        body.getLinks().forEach(item -> {
            Link l = new Link();
            l.setRedirect(item);

            Shorturl s = new Shorturl();
            s.setId(body.getShorturl_id());

            l.setShorturl(s);

            linkRepository.save(l);
        });

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/link/{shorturlId}")
    public ResponseEntity<Void> exclude(@PathVariable String shorturlId){

        linkRepository.deleteByShorturl_id(shorturlId);

        List<Link> link = linkRepository.findByShorturl_id(shorturlId);
        if(link.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build(); 
    }
}
