package help.security.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> 
{
	 
    Users findByUsername(String username);
}