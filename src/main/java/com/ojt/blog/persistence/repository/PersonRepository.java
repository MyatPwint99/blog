package com.ojt.blog.persistence.repository;

import com.ojt.blog.persistence.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
//    public void updatePersonByTime();

    @Query(value = "SELECT p FROM Person p WHERE p.name LIKE %?1%")
    public List<Person> findAll(String keyword);
}
