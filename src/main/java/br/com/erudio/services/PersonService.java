package br.com.erudio.services;

import br.com.erudio.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    public Person create( Person person){
        return person;
    }

    public Person update( Person person){
        return person;
    }

    public void delete( String id){
    }

    public Person findById( String id ){
        Person person = new Person();
        person.setId( counter.incrementAndGet() );
        person.setFirstName("Leandro");
        person.setLastName("Costa");
        person.setAddress("Uberlandia - Minas Gerais - Brasil");
        person.setGenrer("Male");
        return person;
    }

    public List<Person> findAll(){
        List<Person> people = new ArrayList<>();

        for( int i = 0; i < 8; i ++ ){
            Person person = mockPerson(i);
            people.add( person );
        }


        return people;
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId( counter.incrementAndGet() );
        person.setFirstName("Person name" + i);
        person.setLastName("Last Name" + i);
        person.setAddress("Some address in Brazil");
        person.setGenrer( i % 2 == 0 ? "Male" : "Female");

        return person;
    }

}
