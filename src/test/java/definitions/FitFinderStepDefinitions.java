package definitions;

import com.example.fitfinder.FitFinderApplication;
import com.example.fitfinder.models.Gym;
import com.example.fitfinder.repository.GymRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FitFinderApplication.class)
public class FitFinderStepDefinitions {

    private final String BASE_URL = "http://localhost:";
    private Response response;
    private ResponseEntity<String> responseEntity;
    private RequestSpecification request;
    private List<?> list;

    @LocalServerPort
    String port;

    @Autowired
    private GymRepository gymRepository;

    // PUBLIC - GET /api/owners/{ownerId}/gyms
    // Scenario: User can see all gyms belonging to an owner
    @Given("A gym owner account is available")
    public void aGymOwnerAccountIsAvailable(){
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        response = request.get(BASE_URL + port + "/api/owners/1");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("A list of gyms is available for the owner")
    public void aListOfGymsIsAvailableForTheOwner() {
        responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/owners/1/gyms", HttpMethod.GET, null, String.class);
        list = JsonPath.from(String.valueOf(responseEntity.getBody())).get();
        Assert.assertFalse(list.isEmpty());
    }

    @Then("I see a list of gyms belonging to the owner")
    public void iSeeAListOfGymsBelongingToTheOwner() {
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    // PUBLIC - GET /api/gyms
    // Scenario: User can see all gyms
    @Given("A list of gyms is available")
    public void aListOfGymsIsAvailable() {
        responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/gyms", HttpMethod.GET, null, String.class);
        list = JsonPath.from(String.valueOf(responseEntity.getBody())).get();
        Assert.assertFalse(list.isEmpty());
    }

    @When("I search for gyms")
    public void iSearchForGyms() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        response = request.get(BASE_URL + port + "/api/gyms");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("I see a list of all gyms")
    public void iSeeAListOfAllGyms() {
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    // Scenario: User can retrieve gym details
    @Given("A gym is available")
    public void aGymIsAvailable() {
        Gym gym = gymRepository.findById(1L).get();
        Assert.assertNotNull(gym);
    }

    // PUBLIC - GET /api/gyms/{gymId}
    @When("I search for the gym")
    public void iSearchForTheGym() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        response = request.get(BASE_URL + port + "/api/gyms/1");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("I see the details of gym")
    public void iSeeTheDetailsOfGym() {
        Assert.assertNotNull(String.valueOf(response));
    }

    // PUBLIC - GET /api/gyms/{gymId}/equipment
    @When("I search for the gym equipment")
    public void iSearchForTheGymEquipment() {
        responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/gyms/1/equipment", HttpMethod.GET, null, String.class);
        list = JsonPath.from(String.valueOf(responseEntity.getBody())).get();
        Assert.assertFalse(list.isEmpty());
    }

    @Then("I see a list of all equipment for the gym")
    public void iSeeAListOfAllEquipmentForTheGym() {
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @When("I search for the gym amenities")
    public void iSearchForTheGymAmenities() {
        responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/gyms/1/amenities", HttpMethod.GET, null, String.class);
        list = JsonPath.from(String.valueOf(responseEntity.getBody())).get();
        Assert.assertFalse(list.isEmpty());
    }

    @Then("I see a list of all amenities for the gym")
    public void iSeeAListOfAllAmenitiesForTheGym() {
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}