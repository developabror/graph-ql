package uz.app.graphQL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.app.graphQL.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
