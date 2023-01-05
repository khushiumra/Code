package COMS309.Final.Assignments;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Mac Whitney
 * 
 */ 
@Repository
public interface AssignmentsDatabase extends JpaRepository<Assignments, Long> {


	Assignments findByname(String name);
	
	@Transactional
    String deleteByname(String name);
}