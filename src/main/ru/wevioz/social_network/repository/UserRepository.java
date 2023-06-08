package wevioz.social_network.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wevioz.social_network.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}