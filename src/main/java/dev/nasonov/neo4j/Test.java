package dev.nasonov.neo4j;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Test {
	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}
}
