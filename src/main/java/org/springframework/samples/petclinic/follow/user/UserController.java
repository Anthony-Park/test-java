package org.springframework.samples.petclinic.follow.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/follow/users")
public class UserController {

	@Autowired
	private UserService userService;

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@PostMapping("/new")
//	public Mono<User> createUser(@RequestBody User user) {
	public User createUser(@RequestBody User user) {
		// return userRepository.save(user);
		return userService.createUser(user);
	}

	@GetMapping("/find")
	public User findAmud() {
		return userService.findAmud();
	}

	@GetMapping("/{userid}")
//	public Mono<User> getUserByUserid(@PathVariable Long userid) { // reactive
	public User getUserByUserid(@PathVariable Long userid) {
		System.out.println(userid);
		return userService.getUserByUserid(userid);
	}
	// public User getUserById(@PathVariable Long userid) {
	// //return userService.getUserById(userid);
	// System.out.println(userid);
	// return null;
	// }

	@GetMapping("/")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PutMapping("/{userid}")
//	public Mono<User> updateUser(@PathVariable Long userid, @RequestBody User updatedUser) {
	public Optional<User> updateUser(@PathVariable Long userid, @RequestBody User updatedUser) {
		return userService.updateUser(userid, updatedUser);
	}

	@DeleteMapping("/{userid}")
	public void deleteUser(@PathVariable Long userid) {
		userService.deleteUser(userid);
	}

}

// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.ResponseStatus;
//
// @ResponseStatus(HttpStatus.NOT_FOUND)
// public class NotFoundException extends RuntimeException {
// public NotFoundException(String message) {
// super(message);
// }
// }
