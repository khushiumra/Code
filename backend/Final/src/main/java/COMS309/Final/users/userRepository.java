package COMS309.Final.users;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Madhav Dhimal
 */
public interface userRepository extends JpaRepository<user, Long>{

    /**
     *  Finds a user given their email
     */
    user findByEmail(String Email);

    /**
     * Finds a user given their id
     */
    user findById(int id);

    /**
     *  Deletes a user given their email
     */
    @Transactional
    String deleteByEmail(String Email);

}
