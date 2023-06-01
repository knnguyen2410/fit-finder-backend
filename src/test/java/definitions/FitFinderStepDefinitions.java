package definitions;

import com.example.fitfinder.FitFinderApplication;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.restassured.response.Response;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FitFinderApplication.class)
public class FitFinderStepDefinitions {

    private static final String BASE_URL = "http://localhost:";
    private static Response response;

    @LocalServerPort
    String port;

    @Given("A gym is available")
    public void aGymIsAvailable() {
    }

}
