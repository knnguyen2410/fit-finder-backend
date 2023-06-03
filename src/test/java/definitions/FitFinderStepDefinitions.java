package definitions;

import com.example.fitfinder.FitFinderApplication;
import com.example.fitfinder.models.Gym;
import com.example.fitfinder.repository.GymRepository;
import com.example.fitfinder.repository.OwnerRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FitFinderApplication.class)
public class FitFinderStepDefinitions {

    private String BASE_URL = "http://localhost:";
    private Response response;
    private ResponseEntity<String> responseEntity;
    private RequestSpecification request;
    private List<?> list;

    @LocalServerPort
    String port;

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    public String getSecurityKey() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "kim@gmail.com");
        requestBody.put("password", "p");
        request.header("Content-Type", "application/json");

        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/owners/login");
        return response.jsonPath().getString("message");
    }

    public String getSecurityKeySam() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "sam@gmail.com");
        requestBody.put("password", "p");
        request.header("Content-Type", "application/json");

        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/owners/login");
        return response.jsonPath().getString("message");
    }

    // PUBLIC endpoints

    // Scenario: User (gym owner) can create an account
    // PUBLIC - POST /api/owners/register (owner user story)
    @Given("An email address is unused")
    public void anEmailAddressIsUnused() {
        String email = "dan@gmail.com";
        Assert.assertFalse(ownerRepository.existsByEmail(email));
    }

    @When("I create an account with the email address")
    public void iCreateAnAccountWithTheEmailAddress() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "dan");
        requestBody.put("email", "dan@gmail.com");
        requestBody.put("password", "p");
        request.header("Content-Type", "application/json");

        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/owners/register");
        Assert.assertEquals(201, response.getStatusCode());
    }

    @Then("The account is created")
    public void theAccountIsCreated() {
        String email = "dan@gmail.com";
        Assert.assertTrue(ownerRepository.existsByEmail(email));
    }

    // Scenario: User (gym owner) can log in
    // PUBLIC - GET /api/owners/{ownerId} (owner user story)
    @Given("A gym owner account is available")
    public void aGymOwnerAccountIsAvailable() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        response = request.get(BASE_URL + port + "/api/owners/1");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("I log in with valid credentials")
    public void iLogInWithValidCredentials() throws JSONException {
        Assert.assertNotEquals(getSecurityKey(), "Error : email or password is incorrect");
    }

    @Then("I see I am logged in")
    public void iSeeIAmLoggedIn() throws JSONException {
        Assert.assertNotNull(getSecurityKey());
    }

    // Scenario: User can see gym owner details
    // PUBLIC - GET /api/owners/{ownerId}/gyms (owner user story)
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

    // Scenario: User can see all gyms
    // PUBLIC - GET /api/gyms (gym user story)
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

    // PUBLIC - GET /api/gyms/{gymId} (gym user story)
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

    // PUBLIC - GET /api/gyms/{gymId}/equipment (equipment user story)
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

    // PUBLIC - GET /api/gyms/{gymId}/amenities (amenity user story)
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

    // PRIVATE endpoints

    @Given("I am logged into my account")
    public void iAmLoggedIntoMyAccount() throws JSONException {
        Assert.assertNotEquals(getSecurityKey(), "Error : email or password is incorrect");
        Assert.assertNotNull(getSecurityKey());
    }

    // Scenario: User (gym owner) can manage accounts details
    // PRIVATE - GET /api/owners/{ownerId} (owner user story)
    @When("I update my account details")
    public void iUpdateMyAccountDetails() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "updated kim");
        requestBody.put("email", "updated@gmail.com");
        requestBody.put("password", "up");
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + getSecurityKey());

        response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/owners/1");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("I see my profile is updated")
    public void iSeeMyProfileIsUpdated() {
        String updatedName = response.jsonPath().get("name");
        String updatedEmail = response.jsonPath().get("email");
        Assert.assertEquals("updated kim", updatedName);
        Assert.assertEquals("updated@gmail.com", updatedEmail);
    }

    // PRIVATE - DELETE /api/owners/{ownerId} (owner user story)
    @When("I delete my account")
    public void iDeleteMyAccount() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + getSecurityKeySam());
        response = request.delete(BASE_URL + port + "/api/owners/2");
    }

    @Then("I see my account is deleted")
    public void iSeeMyAccountIsDeleted() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertFalse(ownerRepository.existsById(2L));
    }
}