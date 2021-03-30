package br.com.erudio.controller;

import br.com.erudio.data.vo.PersonVO;
import br.com.erudio.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<PersonVO> findAll() {
        return personService.findAll( );
    }

    @GetMapping("/{id}")
    public PersonVO findById(@PathVariable("id") Long id) {
        return personService.findById( id );
    }

    @PostMapping
    public PersonVO create(@RequestBody PersonVO person) {
        return personService.create( person );
    }

    @PutMapping
    public PersonVO update(@RequestBody PersonVO person) {
        return personService.update( person );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        personService.delete( id );
        return ResponseEntity.ok().build();
    }
}
