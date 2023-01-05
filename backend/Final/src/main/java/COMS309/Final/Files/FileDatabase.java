package COMS309.Final.Files;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Mac Whitney
 * 
 */ 
@Repository
public interface FileDatabase extends JpaRepository<File, String> {

	File findByFileName(String Filename);

	void deleteByFileName(String fileName);


}
