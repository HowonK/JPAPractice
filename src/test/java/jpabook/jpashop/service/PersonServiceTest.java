package jpabook.jpashop.service;

import jpabook.jpashop.domain.Person;
import jpabook.jpashop.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class PersonServiceTest {

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepository personRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        Person person = new Person();
        person.setName("Kwak");

        //when
        Long savedId = personService.join(person);

        //then
        assertEquals(person, personRepository.findOne(savedId));

    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Person person1 = new Person();
        person1.setName("kwak1");

        Person person2 = new Person();
        person2.setName("kwak1");

        //when
        personService.join(person1);
        try {
            personService.join(person2); //에외가 발생해야 한다!!!
        }catch (IllegalStateException e){
            return;
        }


        //then
        fail("예외가 발생해야 한다");
    }
}