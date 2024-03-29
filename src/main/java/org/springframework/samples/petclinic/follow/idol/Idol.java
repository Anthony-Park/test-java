package org.springframework.samples.petclinic.follow.idol;

import lombok.Getter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.samples.petclinic.follow.user.User;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
//@Setter
@Node("Idol")
public class Idol {
	@Id
	@GeneratedValue
	private Long id;

	// @Property
	private Long idolId;
	private String name;

	public Idol(Long idolId, String name) {
		this.idolId = idolId;
		this.name = name;
	}

	public String toString() {
		return this.name + ", " + this.idolId;
/*		return this.name + this.idolid + " follows => "
			+ Optional.ofNullable(this.idols)
			.orElse(Collections.emptySet())
			.stream()
			.map(User::getName)
			.collect(Collectors.toList());
 */
	}
}
