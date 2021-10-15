package fact.it.developer.repository;


import fact.it.developer.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, String>{
    Developer findDeveloperByCount_workers(int Count_workers);
    List<Developer> findDevelopersByNameContaining(String Name);

}
