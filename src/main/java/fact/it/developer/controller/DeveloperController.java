package fact.it.developer.controller;

import ch.qos.logback.core.sift.AppenderTracker;
import fact.it.developer.model.Developer;
import fact.it.developer.repository.DeveloperRepository;
import org.hibernate.result.UpdateCountOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class DeveloperController {

    @Autowired
    private DeveloperRepository developerRepository;

    @PostConstruct
    public void fillDB(){
        if(developerRepository.count()==0){
            developerRepository.save(new Developer("687468435454",5,1));
            developerRepository.save(new Developer("687468435454",2,2));
            developerRepository.save(new Developer("687468434567",4,9));
        }

        System.out.println("Reviews test: " + developerRepository.findDeveloperByCount_workers(2));
    }

    @GetMapping("/books/title/{title}")
    public List<Developer> getBooksByTitle(@PathVariable String name){
        return developerRepository.findDevelopersByNameContaining(name);
    }

    @GetMapping("/books/{ISBN}")
    public Developer getBookByISBN(@PathVariable String ISBN){
        return developerRepository.findDeveloperByCount_workers(5);
    }

}
