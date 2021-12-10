package fact.it.developer.repository;


import fact.it.developer.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer>{
<<<<<<< HEAD
    Developer findDeveloperByName(String Name);
    List<Developer> findByNameContaining(String Name);
=======

    List<Developer> findDevelopersByNameContaining(String Name);
>>>>>>> 9a7a8d9174e4c871e0cf26181c38f0ec55c79305

}
