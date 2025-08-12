package com.example.backend.service;

import com.example.backend.model.author.Author;
import com.example.backend.model.note.Note;
import com.example.backend.model.note.request.CreateNoteRequest;
import com.example.backend.model.note.response.GetNoteResponse;
import com.example.backend.model.note.response.GetNotesResponse;
import com.example.backend.repository.AuthorRepository;
import com.example.backend.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public void createNote(CreateNoteRequest request) {
        Optional<Author> author = authorRepository.findById(request.getAuthorId());
        if(author.isEmpty()) {
            throw new IllegalArgumentException("Author not found with id: " + request.getAuthorId());
        }

        Note note = Note.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(author.get())
                .build();

        noteRepository.save(note);
    }

    public GetNotesResponse getAllNotes() {
        List<GetNoteResponse> notes = noteRepository.findAll().stream()
                .map(note -> GetNoteResponse.builder()
                        .title(note.getTitle())
                        .content(note.getContent())
                        .createdAt(note.getCreatedAt().toString())
                        .authorName(note.getAuthor().getName())
                        .build())
                .toList();

        return new GetNotesResponse(notes);
    }

    public GetNoteResponse getNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found with id: " + id));

        return GetNoteResponse.builder()
                .title(note.getTitle())
                .content(note.getContent())
                .createdAt(note.getCreatedAt().toString())
                .authorName(note.getAuthor().getName())
                .build();
    }

    public void deleteNote(Long id) {
        if(!noteRepository.existsById(id)) {
            throw new IllegalArgumentException("Note not found with id: " + id);
        }
        noteRepository.deleteById(id);
    }
}
