package io.jlyon.movierental.controller;

import com.sun.istack.NotNull;
import io.jlyon.movierental.composer.UserComposer;
import io.jlyon.movierental.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserComposer composer;

	@GetMapping("/{id}")
	@Transactional
	public UserView getUserById(@PathVariable("id") String userId) {
		log.info("Get User: {}", userId);
		return composer.getUserById(userId);
	}
}
