package in.uttam.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.uttam.entity.User;
import in.uttam.passwordEncryDecry.PasswordEncryDecry;
import in.uttam.reposetory.UserReposetory;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	private UserReposetory userReposetory;
	@Autowired
	private PasswordEncryDecry passwordEncryDecry;

//....................................................................

	@Override
	public String regUser(User user) {
		System.out.println("user recive at registration........mail : " + user.getEmail());
		user.setEmail(user.getEmail().toLowerCase());
		System.out.println("after lowercase : " + user.getEmail());
		System.out.println("sending to encryption...");
		user.setPassword(passwordEncryDecry.passwordEncrypt(user.getPassword()));

		if (!isContainEmail(user.getEmail())) {

			if (!isContainUsername(user.getUsername())) {

				System.out.println("is username already exist in DB : " + isContainUsername(user.getUsername()));
				try {
					userReposetory.save(user);
					return "success";
				}

				catch (Exception e) {
					System.out.println("error is ->" + e.toString());
					return "internal error";
				}
			}
			return "username alredy used";
		}
		return "email is already register";

	}

//...............................................................
	@Override
	public String updateUser(User user) {
		user.setPassword(passwordEncryDecry.passwordEncrypt(user.getPassword()));
		userReposetory.save(user);
		return "success";
	}

	// ...............................................................
	@Override
	public User loginUser(String email, String password) {
		getAllPassword();
		User details = userReposetory.findByEmail(email);
		String encryPass = passwordEncryDecry.passwordEncrypt(password);
		if (details != null && details.getPassword().equals(encryPass)) {
			return details;
		}

		return null;
	}

//...........................
	@Override
	public User uLoginUser(String username, String password) {

		User details = userReposetory.findByUsername(username);
		String encryPass = passwordEncryDecry.passwordEncrypt(password);
		if (details != null && details.getPassword().equals(encryPass)) {
			return details;
		}
		return null;
	}

//.....................
	private Optional<List<String>> getAllEmail() {
		List<String> list = userReposetory.findAll().stream().map(user -> user.getEmail()).collect(Collectors.toList());
		return Optional.of(list);
	}

//......................
	private Optional<List<String>> getAllUsername() {

		List<String> list = userReposetory.findAll().stream().map(user -> user.getUsername())
				.collect(Collectors.toList());
		return Optional.of(list);
	}

//...............................................
	private Optional<List<String>> getAllPassword() {

		List<String> list = userReposetory.findAll().stream().map(user -> user.getPassword())
				.map(a -> passwordEncryDecry.passwordDecrypt(a)).collect(Collectors.toList());
		System.out.println(list.toString());
		return Optional.of(list);
	}

//...............................................
	private boolean isContainEmail(String email) {
		List<String> list = getAllEmail().orElseGet(null);
		if (list.contains(email)) {
			return true;
		}
		return false;
	}

//.................................................
	private boolean isContainUsername(String username) {
		List<String> list = getAllUsername().orElseGet(null);
		if (list.contains(username)) {
			return true;
		}
		return false;
	}

//......................................................
	@Override
	public String passwordLength(String password) {
		if (password.length() >= 4) {
			return "success";
		}
		return "Password must be between 4 and 10 characters long.";
	}

//................
	@Override
	public String encPass(String password) {
		return passwordEncryDecry.passwordEncrypt(password);
	}

	@Override
	public User getuser(int id) {
		Optional<User> user;
		try {
			user = userReposetory.findById(id);
		} catch (Exception e) {
			user = null;
		}
		return user.orElseGet(null);
	}

	@Override
	public boolean deleteAccount(int id) {
		try {
			userReposetory.deleteById(id);
			return true;
		}

		catch (Exception e) {
			return false;
		}
	}
}
