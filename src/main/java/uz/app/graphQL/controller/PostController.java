package uz.app.graphQL.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.app.graphQL.entity.Post;
import uz.app.graphQL.repository.PostRepository;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    List<Post> getPostList(){
       return postRepository.findAll();
    }
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id){
        return postRepository.findById(id).orElseThrow();
    }
}
