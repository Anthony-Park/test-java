package org.springframework.samples.petclinic.follow;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.AbstractReactiveNeo4jConfig;
import org.springframework.data.neo4j.core.DatabaseSelection;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.ReactiveNeo4jClient;
import org.springframework.data.neo4j.core.transaction.ReactiveNeo4jTransactionManager;
import org.springframework.data.neo4j.repository.config.EnableReactiveNeo4jRepositories;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
@Configuration
@EnableReactiveNeo4jRepositories
@EnableTransactionManagement
public class FollowConfig extends AbstractReactiveNeo4jConfig {

	@Value("${spring.data.neo4j.uri}")
	private String uri;

	@Value("${spring.data.neo4j.username}")
	private String user;

	@Value("${spring.data.neo4j.password}")
	private String password;

//	private final ReactiveNeo4jClient client;
//
//	@Autowired
//	public FollowConfig(ReactiveNeo4jClient neo4jClient) {
//		this.client = neo4jClient;
//	}
//
//	@Bean
//	public DatabaseSelectionProvider databaseSelectionProvider() {
////		return () -> "neo4j"; // 기본 데이터베이스 이름을 여기에 지정하세요.
//		return () -> DatabaseSelection.byName("neo4j");
////		return () -> DatabaseSelection.byDefault();
//	}

//	@Bean
//	public ReactiveNeo4jTransactionManager reactiveTransactionManager(DatabaseSelectionProvider databaseSelectionProvider) {
//		return new ReactiveNeo4jTransactionManager(client, databaseSelectionProvider);
//	}
//
//	@Bean
//	public ReactiveNeo4jClient reactiveNeo4jClient(Driver driver, DatabaseSelectionProvider databaseSelectionProvider) {
//		return ReactiveNeo4jClient.create(driver, databaseSelectionProvider);
//	}

//	@Override
	@Bean
	public Driver driver() {
		return GraphDatabase.driver(uri, AuthTokens.basic(user, password));
//		return GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "neo4jneo4j"));
	}
}
*/
