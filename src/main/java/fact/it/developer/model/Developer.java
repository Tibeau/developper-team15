package fact.it.developer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Developer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;
    private int count_workers;
    private int founding_year;


    public Developer() {
    }


    public Developer(String name, int count_workers, int founding_year) {
        setName(name);
        setCount_workers(count_workers);
        setFounding_year(founding_year);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount_workers() {
        return count_workers;
    }

    public void setCount_workers(int count_workers) {
        this.count_workers = count_workers;
    }

    public int getFounding_year() {
        return founding_year;
    }

    public void setFounding_year(int founding_year) {
        this.founding_year = founding_year;
    }
}
