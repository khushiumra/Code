package com.example.test.users;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<user, Long>{

    user findByEmail(String Email);

    @Transactional
    String deleteByEmail(String Email);

}
