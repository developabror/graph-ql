package uz.app.graphQL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.graphQL.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByPost_Id(Long postId);
}
