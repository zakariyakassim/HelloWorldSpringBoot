package com.qa.HelloWord.repository;

import com.qa.HelloWord.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
