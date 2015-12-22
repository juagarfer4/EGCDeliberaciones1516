package repositories;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select c from User c where c.userAccount.id= ?1")
	User findOneByPrincipal(int id);

	@Query("select c from User c where c.userAccount.username= ?1")
	User findByUsername(String username);
	
	@Query("select c from User c where c.comments.size=0")
	Collection<User> findUserWithZeroComments();
	
	@Query("select c from User c where c.comments.size=(select max(u.comments.size) from User u)")
	Collection<User> findUserWithMoreComments();
	
	@Query("select c from User c where c.threads.size=(select max(u.threads.size) from User u)")
	Collection<User> findUserWithMoreThreads();

}