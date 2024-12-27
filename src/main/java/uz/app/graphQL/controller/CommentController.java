package uz.app.graphQL.controller;

import org.springframework.web.bind.annotation.*;
import uz.app.graphQL.entity.Comment;
import uz.app.graphQL.payload.CommentDTO;
import uz.app.graphQL.repository.CommentRepository;
import uz.app.graphQL.repository.PostRepository;
import uz.app.graphQL.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    final CommentRepository commentRepository;
    final UserRepository userRepository;
    final PostRepository postRepository;

    public CommentController(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentDTO commentDTO) {
        Comment comment =new Comment();
        comment.setText(commentDTO.getText());
        comment.setPost(postRepository.findById(commentDTO.getPostId()).orElseThrow());
        comment.setUser(userRepository.findById(commentDTO.getUserId()).orElseThrow());
        return commentRepository.save(comment);
    }

    @GetMapping("/byPost/{id}")
    public List<Comment> getCommentsByPost(@PathVariable Long id) {
        return commentRepository.findAllByPost_Id(id);
    }
}
