package com.ojt.blog.persistence.repository;

import com.ojt.blog.persistence.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
//    public void updatePersonByTime();

    @Modifying
    @Query(value = "update person set updated_at=CURRENT_TIMESTAMP,name =:name where id =:id",nativeQuery = true)
    @Transactional
    void updatePersonByQuery( Long id,String name);

    @Query(value = "SELECT p FROM Person p WHERE p.name LIKE %?1%")
    public List<Person> findAll(String keyword);

//    @Query(value = "SELECT p FROM Person p WHERE name=:name")
    public Person findByName(String name);
}
