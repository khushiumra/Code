package com.example.test.groups;

import com.example.test.users.user;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface groupRepository extends JpaRepository<group, Long> {

    List<user> findByName(String name);

    @Transactional
    void deleteByName(String name);
}
