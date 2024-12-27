package uz.app.graphQL.command;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.app.graphQL.entity.Post;
import uz.app.graphQL.entity.User;
import uz.app.graphQL.repository.PostRepository;
import uz.app.graphQL.repository.UserRepository;

@Component
public class BaseParams implements CommandLineRunner {
    final UserRepository userRepository;
    final PostRepository postRepository;
    @Value("${spring.sql.init.mode}")
    String mode;

    public BaseParams(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!mode.equals("always")){
            return;
        }
        User user = new User();
        user.setEmail("user@example.com");
        user.setBirthDate("01.01.2001");
        user.setName("Ali");
        user.setPassword("root123");
        userRepository.save(user);

        Faker faker = new Faker();
        Book book = faker.book();
        for (int i = 0; i < 5; i++) {
            Post post = new Post();
            post.setTitle(book.title());
            post.setContent(book.publisher());
            post.setUser(user);
            postRepository.save(post);
        }

    }
}
