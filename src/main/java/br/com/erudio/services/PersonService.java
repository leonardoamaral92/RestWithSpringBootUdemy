package br.com.erudio.services;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.data.model.Person;
import br.com.erudio.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public PersonVO create(PersonVO person){
        Person entity = DozerConverter.parseObject( person, Person.class );
        PersonVO vo = DozerConverter.parseObject( personRepository.save( entity ), PersonVO.class );
        return  vo;
    }

    public List<PersonVO> findAll(){
        return DozerConverter.parseListObjects( personRepository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id ){
        Person entity = personRepository.findById( id )
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        return DozerConverter.parseObject( entity, PersonVO.class );
    }

    public PersonVO update( PersonVO person){
        Person entity = personRepository.findById( person.getId() )
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));;

        entity.setFirstName( person.getFirstName() );
        entity.setLastName( person.getLastName() );
        entity.setAddress( person.getAddress() );
        entity.setGender( person.getGender() );

        return DozerConverter.parseObject( personRepository.save( entity ), PersonVO.class);
    }

    public void delete( Long id){

        Person entity = personRepository.findById( id )
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));;

        personRepository.delete( entity );
    }
}
