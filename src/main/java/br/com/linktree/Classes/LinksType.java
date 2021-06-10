package br.com.linktree.Classes;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinksType {
    private Long id;
    private String shorturl_id;
    private List<String> links;
}
