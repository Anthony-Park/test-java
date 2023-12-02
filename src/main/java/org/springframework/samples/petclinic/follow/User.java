package org.springframework.samples.petclinic.follow;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Node
public class User {

	@Id
	@GeneratedValue
	private Long id;

	// @Property
	private Integer userid;

	private String email;

	private String name;

	public User(Integer userid, String email, String name) {
		this.userid = userid;
		this.email = email;
		this.name = name;
	}

	@Relationship(type = "FOLLOW_USER")
	public Set<User> users;

	public void followWith(User user) {
		if (user == null) {
			users = new HashSet<>();
		}
		users.add(user);
	}

	public String toString() {
		return this.name + " follows => "
				+ Optional.ofNullable(this.users)
					.orElse(Collections.emptySet())
					.stream()
					.map(User::getName)
					.collect(Collectors.toList());
	}

	/*
	 * public String getName() { return name; } public void setName(String name) {
	 * this.name = name; }
	 */

}
