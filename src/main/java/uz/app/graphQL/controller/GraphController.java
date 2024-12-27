package uz.app.graphQL.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import uz.app.graphQL.entity.Comment;
import uz.app.graphQL.entity.Post;
import uz.app.graphQL.payload.PostDTO;
import uz.app.graphQL.repository.CommentRepository;
import uz.app.graphQL.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {
    final PostRepository postRepository;
    final CommentRepository commentRepository;

    public GraphController(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @QueryMapping(value = "getPosts")
    public Post getPostById(@Argument Long id){
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElseThrow();
    }


    @QueryMapping
    public List<Comment> getCommentByPostId(@Argument Long id){
        return commentRepository.findAllByPost_Id(id);
    }

    @MutationMapping
    public Post updatePost(@Argument(name = "post") PostDTO postDTO){
        Post post = postRepository.findById(postDTO.getId()).orElseThrow();
        //if else
        post.setContent(postDTO.getContent());
        post.setTitle(postDTO.getTitle());
        return postRepository.save(post);

    }
}
