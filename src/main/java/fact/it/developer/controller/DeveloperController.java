package fact.it.developer.controller;

import ch.qos.logback.core.sift.AppenderTracker;
import fact.it.developer.model.Developer;
import fact.it.developer.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            developerRepository.save(new Developer("Activision",5,1852));
            developerRepository.save(new Developer("Bethesda",2,1935));
            developerRepository.save(new Developer("Blizzard",4,2015));
        }

        System.out.println("Reviews test: ");
    }

    @GetMapping("/developers/{name}")
    public Developer getDeveloperByName(@PathVariable String name){
        return developerRepository.findDeveloperByName(name);

    }

    @GetMapping("/developers/name/{name}")
    public List<Developer> getDevelopersByName(@PathVariable String name){
        return developerRepository.findByNameContaining(name);
    }

    @GetMapping("/developers")
    public List<Developer> getDevelopers(){
        return developerRepository.findAll();
    }

}
