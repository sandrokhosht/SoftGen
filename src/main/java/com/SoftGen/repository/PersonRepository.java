package com.SoftGen.repository;

import com.SoftGen.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person,Integer> {

    List<Person> findByPid(String pid);
    List <Person> findByName(String name);
    List <Person> findBySurname(String surname);


}
