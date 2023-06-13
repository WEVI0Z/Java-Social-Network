package wevioz.social_network.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wevioz.social_network.entity.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

}