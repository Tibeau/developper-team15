package fact.it.developer;

import fact.it.developer.model.Developer;
import fact.it.developer.repository.DeveloperRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc

public class DeveleporControllerUnitTests {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeveloperRepository developerRepository;

    private Developer developer1 = new Developer("Developer1");
    private Developer developer2 = new Developer("Developer2");

    private List<Developer> allDevelopers = Arrays.asList(developer1, developer2);

    @Test
    public void givenDeveloper_whenGetDeveloperByName_thenReturnJsonDeveloper() throws Exception {

        given(developerRepository.findDeveloperByName("Developer1")).willReturn(developer1);

        mockMvc.perform(get("/developers/{name}","Developer1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("Developer1")));
    }

    @Test
    public void givenDevelopers_whenGetDevelopersByName_thenReturnJsonDevelopers() throws Exception {

        given(developerRepository.findByNameContaining("Developer")).willReturn(allDevelopers);

        mockMvc.perform(get("/developers/name/{name}","Developer"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name",is("Developer1")))
                .andExpect(jsonPath("$[1].name",is("Developer2")));
    }

}

