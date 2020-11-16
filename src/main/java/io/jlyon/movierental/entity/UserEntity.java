package io.jlyon.movierental.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Users")
public class UserEntity {
	@Id
	@GeneratedValue
	@Column(updatable = false, nullable = false, unique=true, columnDefinition = "BINARY(16)")
	private UUID id;
	@Column(nullable = false, length = 30)
	private String username;
	@Column(nullable = false, length = 50, unique = true)
	private String email;
	@Column(nullable = false, length = 100)
	private String password;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserEntity{" +
			"id=" + id +
			", username='" + username + '\'' +
			", email='" + email + '\'' +
			'}';
	}
}
