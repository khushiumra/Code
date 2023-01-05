package COMS309.Final.planners;

import COMS309.Final.groups.group;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Madhav Dhimal
 */
public interface plannerRepository extends JpaRepository<planner, Long> {

    planner findByName(String name);

}
