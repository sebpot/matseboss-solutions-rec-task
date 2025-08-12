package com.example.backend.controller;

import com.example.backend.model.author.request.CreateAuthorRequest;
import com.example.backend.model.author.response.GetAuthorResponse;
import com.example.backend.model.author.response.GetAuthorsResponse;
import com.example.backend.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("")
    public ResponseEntity<?> createAuthor(@RequestBody CreateAuthorRequest request) {
        authorService.createAuthor(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<GetAuthorsResponse> getAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAuthorResponse> getAuthor(@PathVariable Long id) throws IllegalArgumentException {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }
}
