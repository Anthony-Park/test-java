/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import org.neo4j.cypherdsl.core.renderer.Configuration;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.samples.petclinic.follow.FollowService;
import org.springframework.samples.petclinic.follow.idol.Idol;
import org.springframework.samples.petclinic.follow.idol.IdolRepository;
import org.springframework.samples.petclinic.follow.user.User;
import org.springframework.samples.petclinic.follow.user.UserRepository;
//import org.springframework.samples.petclinic.follow.FollowService;

import java.util.Arrays;
import java.util.List;

/**
 * PetClinic Spring Boot Application.
 *
 * @author Dave Syer
 *
 */
@SpringBootApplication
@EnableNeo4jRepositories // for neo4j, tpk
@ImportRuntimeHints(PetClinicRuntimeHints.class)
public class PetClinicApplication {

	private final static Logger log = LoggerFactory.getLogger(PetClinicApplication.class);

//	public static void main(String[] args) {
//		SpringApplication.run(PetClinicApplication.class, args);
//	}
//}
	public static void main(String[] args) throws Exception {
		SpringApplication.run(PetClinicApplication.class, args);
//		System.exit(0);
	}

	@Bean
	Configuration cypherDslConfiguration() {
		return Configuration.newConfig()
			.withDialect(Dialect.NEO4J_5).build();
	}

	@Bean
//	@Bean (name = "transactionManager")
//	@Transactional
	CommandLineRunner demo(UserRepository userRepository, IdolRepository idolRepository, FollowService follow) {
		return args -> {
			int step = 2;
		//	userRepository.deleteAll();
			follow.deleteAll();

			if (step == 1) {
				User sam = new User(9999L, "Sam99", "sam99@g-mail.com");
				User tony = new User(9998L, "Tony99", "tony99@g-mail.com");

				List<User> team = Arrays.asList(sam, tony);
				log.info("Before linking up with Neo4j...");
				team.stream().forEach(user -> log.info("\t" + user.toString()));
				userRepository.save(sam);
				userRepository.save(tony);

				tony = userRepository.findByName(tony.getName());
				tony.followWith(sam);
				userRepository.save(tony);

				idolRepository.deleteAll();

				Idol pink = new Idol(9999L, "Black Pink99", true, true);
				Idol bts = new Idol(9998L, "BTS99", true, false);

				List<Idol> idols = Arrays.asList(pink, bts);
				log.info("Before linking up with Neo4j...");
				idols.stream().forEach(idol -> log.info("\t" + idol.toString()));
				idolRepository.save(pink);
				idolRepository.save(bts);

				pink = idolRepository.findByName(pink.getName());
				tony.followWith(pink);
				tony.followWith(bts);
				sam.followWith(pink);

				userRepository.save(tony);
				userRepository.save(sam);

				Long userId;
				userId = tony.getUserId();
				log.info("following {}, follower {}",
					userRepository.getFollowingCount(userId), userRepository.getFollowerCount(userId));

				userId = sam.getUserId();
				log.info("following {}, follower {}",
					userRepository.getFollowingCount(userId), userRepository.getFollowerCount(userId));
			}

			if (step == 2) {
//			FollowService follow;
				follow.addUser(9999L, "Tony99", "tony99@g-mail.com");
				follow.addUser(9998L, "Sam98", "sam99@g-mail.com");
//				follow.addUser(9997L, "Sun97", "sun97@g-mail.com");

				follow.addIdol(9999L, "Black Pink99", true, true);
				follow.addIdol(9998L, "BTS98", true, false);

				follow.followIdol(9999L, 9999L); // Tony99 follows Black Pink99
				follow.followIdol(9998L, 9999L); // Sam99 follows Black Pink99
				follow.followIdol(9998L, 9998L); // Sam99 follows BTS98

				follow.addPlace(9999L, "Popup99", "Seoul", 9998L);
				follow.followPlace(9999L, 9999L); // Tony99 follows Popup99
				follow.followPlace(9998L, 9999L); // Sam99 follows Popup99

				follow.addItem(9999L,"Picture99", 1, 9998L, 9999L);
				follow.followItem(9998L, 9999L); // Sam99 follows Picture99

				follow.addMeet(9999L, "FanMeet99", 9999L, 9999L);
				follow.followMeet(9999L, 9999L); // Tony99 follows FanMeet99
				follow.followMeet(9998L, 9999L); // Sam99 follows FanMeet99
			}

/*			User roy = new Person("Roy");
			User craig = new Person("Craig");
			List<Person> team = Arrays.asList(greg, roy, craig);

			log.info("Before linking up with Neo4j...");

			team.stream().forEach(person -> log.info("\t" + person.toString()));

			personRepository.save(greg);
			personRepository.save(roy);
			personRepository.save(craig);

			greg = personRepository.findByName(greg.getName());
			greg.worksWith(roy);
			greg.worksWith(craig);
			personRepository.save(greg);

			roy = personRepository.findByName(roy.getName());
			roy.worksWith(craig);

			// We already know that roy works with greg
			personRepository.save(roy);

			// We already know craig works with roy and greg
			log.info("Lookup each person by name...");
			team.stream().forEach(person -> log.info(
				"\t" + personRepository.findByName(person.getName()).toString()));

			List<Person> teammates = personRepository.findByTeammatesName(greg.getName());
			log.info("The following have Greg as a teammate...");
			teammates.stream().forEach(person -> log.info("\t" + person.getName()));
 */
		};
	}
}
