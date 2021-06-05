package br.com.erudio.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonService personService;
    
    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public List<PersonVO> findAll() {
    	List<PersonVO> persons = personService.findAll( );
    	persons.stream().forEach(p -> { p.add( linkTo( methodOn( PersonController.class ).findById( p.getKey() ) ).withSelfRel( ) ); });
    	return persons;
    }

    @GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO findById(@PathVariable("id") Long id) {
        PersonVO personVO =  personService.findById( id );
        personVO.add( linkTo( methodOn( PersonController.class ).findById( id ) ).withSelfRel( ) );
        return personVO;
    }

    @PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO create(@RequestBody PersonVO person) {
    	PersonVO personVO =  personService.create( person );
        personVO.add( linkTo( methodOn( PersonController.class ).findById( personVO.getKey() ) ).withSelfRel( ) );
        return personVO;
    }

    @PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public PersonVO update(@RequestBody PersonVO person) {
    	PersonVO personVO =  personService.update( person );
        personVO.add( linkTo( methodOn( PersonController.class ).findById( personVO.getKey() ) ).withSelfRel( ) );
        return personVO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        personService.delete( id );
        return ResponseEntity.ok().build();
    }
}
