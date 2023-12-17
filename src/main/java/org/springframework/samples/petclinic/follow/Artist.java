package org.springframework.samples.petclinic.follow;

import lombok.Getter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@Node("Artist")
public class Artist {

	@Id
	private Integer idolid;

	private String name;

	public Artist(Integer idoleid, String name) {
		this.idolid = idolid;
		this.name = name;
	}

}
