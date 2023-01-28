package jpabook.jpashop.service;

import jpabook.jpashop.domain.Person;
import jpabook.jpashop.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    //회원 가입
    @Transactional
    public Long join(Person person){
        validateDuplicatePerson(person); //중복 회원 검증
        personRepository.save(person);
        return person.getId();
    }

    private void validateDuplicatePerson(Person person) {
        //EXCEPTION
        List<Person> findPersons = personRepository.findByName(person.getName());
        if(!findPersons.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    //회원 전체 조회
    public List<Person> findPersons(){
        return personRepository.findAll();
    }

    public Person findOne(Long personId){
        return personRepository.findOne(personId);
    }

}
