package wevioz.social_network.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wevioz.social_network.entity.Group;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {

}