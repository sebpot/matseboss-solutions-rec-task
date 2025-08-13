package com.example.backend.controller;

import com.example.backend.model.note.request.CreateNoteRequest;
import com.example.backend.model.note.response.GetNoteResponse;
import com.example.backend.model.note.response.GetNotesResponse;
import com.example.backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("")
    public ResponseEntity<?> createNote(CreateNoteRequest request) throws IllegalArgumentException {
        noteService.createNote(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<GetNotesResponse> getNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetNoteResponse> getNote(Long id) throws IllegalArgumentException {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) throws IllegalArgumentException {
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }
}
