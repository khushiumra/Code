package COMS309.Final.groups;

import COMS309.Final.users.user;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;


/**
 * @author Madhav Dhimal
 */
public interface groupRepository extends JpaRepository<group, Long> {

    /**
     * Finds the group by name
     */
    group findByName(String name);

    /**
     * Finds the group by id
     */
    group findById(int id);

    /**
     * Deletes group given its name
     */
    @Transactional
    void deleteByName(String name);
}
