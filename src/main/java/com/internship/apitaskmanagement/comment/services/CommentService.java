package com.internship.apitaskmanagement.comment.services;

import com.internship.apitaskmanagement.comment.dto.CommentCreateDto;
import com.internship.apitaskmanagement.comment.dto.CommentDto;
import com.internship.apitaskmanagement.comment.dto.CommentUpdateDto;

public interface CommentService {

    CommentDto create(String username, CommentCreateDto newComment);

    CommentDto change(String username, Long commentId, CommentUpdateDto commentDto);

    void delete(String username, Long commentId);
}
