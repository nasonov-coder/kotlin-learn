package dev.nasonov.neo4j;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.config.Configuration;
public class App {
	public static void main(String[] args) {
		Configuration configuration = new Configuration.Builder()
			.uri("bolt://localhost")
			.credentials("neo4j", "Eng7pt143?nc")
			.build();
		SessionFactory sessionFactory = new SessionFactory(configuration, "movies.domain");
		Session session = sessionFactory.openSession();

		Movie movie = new Movie("The Matrix", 1999);

		Actor keanu = new Actor("Keanu Reeves");
		keanu.actsIn(movie);

		Actor carrie = new Actor("Carrie-Ann Moss");
		carrie.actsIn(movie);

//Persist the movie. This persists the actors as well.
		session.save(movie);

//Load a movie
		Movie matrix = session.load(Movie.class, movie.getId());
		for(Actor actor : matrix.getActors()) {
			System.out.println("Actor: " + actor.getName());
		}
	}
}

