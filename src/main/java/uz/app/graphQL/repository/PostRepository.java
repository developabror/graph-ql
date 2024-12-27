package uz.app.graphQL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.graphQL.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long> {
}
