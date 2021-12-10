package fact.it.developer;

import fact.it.developer.model.Developer;
import fact.it.developer.repository.DeveloperRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc

public class DeveleporControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DeveloperRepository developerRepository;

    private Developer developer1 = new Developer("Developer1");
    private Developer developer2 = new Developer("Developer2");

    @BeforeEach
    public void beforeAllTests() {
        developerRepository.deleteAll();
        developerRepository.save(developer1);
        developerRepository.save(developer2);
    }

    @AfterEach
    public void afterAllTests() {
        //Watch out with deleteAll() methods when you have other data in the test database!
        developerRepository.deleteAll();
    }

    @Test
    public void givenDeveloper_whenGetDeveloperByName_thenReturnJsonDeveloper() throws Exception {

        mockMvc.perform(get("/developers/{name}","Developer1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("Developer1")));
    }

    @Test
    public void givenDevelopers_whenGetDevelopersByName_thenReturnJsonDevelopers() throws Exception {

        mockMvc.perform(get("/developers/name/{name}","Developer"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name",is("Developer1")))
                .andExpect(jsonPath("$[1].name",is("Developer2")));
    }

}
