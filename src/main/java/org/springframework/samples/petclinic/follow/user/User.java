package org.springframework.samples.petclinic.follow.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.samples.petclinic.follow.idol.Idol;
import org.springframework.samples.petclinic.follow.place.Place;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Node("User")
public class User {
	@Id
	@GeneratedValue
	private Long id;

	// @Property
	private Long userid;
	private String name;
	private String email;

	public User(Long userid, String name, String email) {
		this.userid = userid;
		this.name = name;
		this.email = email;
	}

	@Relationship(type = "FOLLOW_USER")
	public Set<User> users;

	public void followWith(User user) {
		if (user == null) {
			users = new HashSet<>();
		}
		users.add(user);
	}

	@Relationship(type = "FOLLOW_IDOL")
	public Set<Idol> idols;

	public void followWith(Idol idol) {
		if (idols == null) {
			idols = new HashSet<>();
		}
		idols.add(idol);
	}

	@Relationship(type = "FOLLOW_PLACE", direction = Relationship.Direction.OUTGOING)
	//private Set<Place> follows = new HashSet<>();
	public Set<Place> places;

	public void followWith(Place place) {
		if (place == null) places = new HashSet<>();

		places.add(place);
	}
	/*
	 * public String getName() { return name; } public void setName(String name) {
	 * this.name = name; }
	 */
	public String toString() {
		return this.name + ", " + this.userid + " follows => "
			+ Optional.ofNullable(this.users)
			.orElse(Collections.emptySet())
			.stream()
			.map(User::getName)
			.collect(Collectors.toList());
	}

}
