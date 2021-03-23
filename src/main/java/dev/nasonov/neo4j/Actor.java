package dev.nasonov.neo4j;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Actor {

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	@Id
	@GeneratedValue
	private Long id;
	private String name;

	@Relationship(type = "ACTS_IN", direction = Relationship.OUTGOING)
	private Set<Movie> movies = new HashSet<>();

	public Actor() {
	}

	public Actor(String name) {
		this.name = name;
	}

	public void actsIn(Movie movie) {
		movies.add(movie);
		movie.getActors().add(this);
	}
}
