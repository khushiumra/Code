package COMS309.Final.notes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * 
 * @author Mac Whitney
 * 
 */ 
@Repository
public interface NotesDatabase extends JpaRepository<Notes, Long> {


	Notes findByTitle(String Title);

	@Transactional
	void deleteByTitle(String notesTitle);
	
}
