package com.example.backend.model.note.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetNoteResponse {
    private String title;
    private String content;
    private String authorName;
    private String createdAt;
}
