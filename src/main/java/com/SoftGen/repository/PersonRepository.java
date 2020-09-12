package com.SoftGen.repository;

import com.SoftGen.domain.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PersonRepository extends CrudRepository<Person,Integer> {

    List <Person> findByPid(String pid);
    List <Person> findByName(String name);
    List <Person> findBySurname(String surname);

    @Modifying      // to mark delete or update query
    @Query(value = "DELETE FROM Person e WHERE e.pid = :pid")       // it will delete all the record with specific name
    void deleteByPid(@Param("pid") String pid);


}
