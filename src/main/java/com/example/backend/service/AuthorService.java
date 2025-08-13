package com.example.backend.service;

import com.example.backend.model.author.Author;
import com.example.backend.model.author.request.CreateAuthorRequest;
import com.example.backend.model.author.response.GetAuthorResponse;
import com.example.backend.model.author.response.GetAuthorsResponse;
import com.example.backend.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public void createAuthor(CreateAuthorRequest request) {
        Author author = Author.builder()
                .name(request.getName())
                .build();
        authorRepository.save(author);
    }

    public GetAuthorsResponse getAllAuthors() {
        List<GetAuthorResponse> authors = authorRepository.findAll().stream()
                .map(author -> GetAuthorResponse.builder()
                        .name(author.getName())
                        .build())
                .toList();

        return GetAuthorsResponse.builder()
                .authors(authors)
                .build();
    }

    public GetAuthorResponse getAuthorById(Long id) throws IllegalArgumentException {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + id));

        return GetAuthorResponse.builder()
                .name(author.getName())
                .build();
    }
}
