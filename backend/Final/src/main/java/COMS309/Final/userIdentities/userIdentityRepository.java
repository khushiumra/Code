package COMS309.Final.userIdentities;

import COMS309.Final.groups.group;
import COMS309.Final.users.user;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


/**
 * @author Madhav Dhimal
 */
public interface userIdentityRepository extends JpaRepository<userIdentity, Long> {

}
