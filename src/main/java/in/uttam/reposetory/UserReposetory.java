package in.uttam.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;

import in.uttam.entity.User;

public interface UserReposetory extends JpaRepository<User, Integer> 
{
	public User findByEmail(String email);
	public User findByUsername(String username);
}
