package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PersonRepository {

    private final EntityManager em;

    public void save(Person person){
        em.persist(person);

    }

    public Person findOne(Long id){
        return em.find(Person.class, id);
    }

    public List<Person> findAll(){
        return em.createQuery("select p from Person p", Person.class)
                .getResultList();
    }

    public List<Person> findByName(String name){
        return em.createQuery("select p from Person p where p.name = :name", Person.class)
                .setParameter("name", name)
                .getResultList();
    }
}
