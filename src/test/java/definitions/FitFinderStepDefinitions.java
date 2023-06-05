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

    public String getSecurityKeyKim() throws JSONException {
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

    public String getSecurityKeyAsh() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "ash@gmail.com");
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
        Assert.assertNotEquals(getSecurityKeyKim(), "Error : email or password is incorrect");
    }

    @Then("I see I am logged in")
    public void iSeeIAmLoggedIn() throws JSONException {
        Assert.assertNotNull(getSecurityKeyKim());
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

    @Given("I am logged into my account - kim")
    public void iAmLoggedIntoMyAccountKim() throws JSONException {
        Assert.assertNotEquals(getSecurityKeyKim(), "Error : email or password is incorrect");
        Assert.assertNotNull(getSecurityKeyKim());
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
        request.header("Authorization", "Bearer " + getSecurityKeyKim());

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
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("I see my account is deleted")
    public void iSeeMyAccountIsDeleted() {
        Assert.assertFalse(ownerRepository.existsById(2L));
    }

    // Scenario: User (gym owner) can create a gym
    // PRIVATE - POST /api/gyms (gym user story)
    @Given("I am logged into my account - ash")
    public void iAmLoggedIntoMyAccountAsh() throws JSONException {
        Assert.assertNotEquals(getSecurityKeyAsh(), "Error : email or password is incorrect");
        Assert.assertNotNull(getSecurityKeyAsh());
    }

    @When("I create a gym")
    public void iCreateAGym() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Mindful Matter");
        requestBody.put("category", "Yoga Studio");
        requestBody.put("addressStreet", "789 N. Street St.");
        requestBody.put("addressCity", "Chicago");
        requestBody.put("addressState", "IL");
        requestBody.put("addressZip", "60654");
        requestBody.put("hours", "Weekdays 5am - 10pm, Weekends 8am - 8pm");
        requestBody.put("phone", "(123) 123-1234");
        requestBody.put("details", "New yoga studio in River North");
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + getSecurityKeyAsh());

        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/gyms");
    }

    @Then("I see the gym is created")
    public void iSeeTheGymIsCreated() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    // PRIVATE - PUT /api/gyms/{gymId} (gym user story)
    @When("I update the details for gym")
    public void iUpdateTheDetailsForGym() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Updated Mindful Matter");
        requestBody.put("category", "Updated Yoga Studio");
        requestBody.put("addressStreet", "Updated 789 N. Street St.");
        requestBody.put("addressCity", "Updated Chicago");
        requestBody.put("addressState", "Updated IL");
        requestBody.put("addressZip", "60655");
        requestBody.put("hours", "Updated Weekdays 5am - 10pm, Weekends 8am - 8pm");
        requestBody.put("phone", "Updated (123) 123-1234");
        requestBody.put("details", "Updated New yoga studio in River North");
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + getSecurityKeyAsh());

        response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/gyms/4");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("I see the gym is updated")
    public void iSeeTheGymIsUpdated() {
        String updatedName = response.jsonPath().get("name");
        String updatedCategory = response.jsonPath().get("category");
        String updatedAddressStreet = response.jsonPath().get("addressStreet");
        String updatedAddressCity = response.jsonPath().get("addressCity");
        String updatedAddressState = response.jsonPath().get("addressState");
        String updatedAddressZip = response.jsonPath().get("addressZip").toString();
        String updatedHours = response.jsonPath().get("hours");
        String updatedPhone = response.jsonPath().get("phone");
        String updatedDetails = response.jsonPath().get("details");
        Assert.assertEquals("Updated Mindful Matter", updatedName);
        Assert.assertEquals("Updated Yoga Studio", updatedCategory);
        Assert.assertEquals("Updated 789 N. Street St.", updatedAddressStreet);
        Assert.assertEquals("Updated Chicago", updatedAddressCity);
        Assert.assertEquals("Updated IL", updatedAddressState);
        Assert.assertEquals("60655", updatedAddressZip);
        Assert.assertEquals("Updated Weekdays 5am - 10pm, Weekends 8am - 8pm", updatedHours);
        Assert.assertEquals("Updated (123) 123-1234", updatedPhone);
        Assert.assertEquals("Updated New yoga studio in River North", updatedDetails);
    }

    // PRIVATE - DELETE /api/gyms/{gymId} (gym user story)
    @When("I delete gym")
    public void iDeleteGym() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + getSecurityKeyAsh());

        response = request.delete(BASE_URL + port + "/api/gyms/4");
    }

    @Then("I see the gym is deleted")
    public void iSeeTheGymIsDeleted() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    // Scenario: User (gym owner) can manage equipment for a gym
    // PRIVATE - POST /api/gyms/{gymId}/equipment (equipment user story)
    @When("I create equipment for the gym")
    public void iCreateEquipmentForTheGym() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        requestBody.put("category", "Weight");
        requestBody.put("brand", "Trustworthy Brand");
        requestBody.put("name", "10lb dumbbell (single)");
        requestBody.put("quantity", "10");
        requestBody.put("details", "One (1) 10lb dumbbell");
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + getSecurityKeyAsh());

        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/gyms/3/equipment");
    }

    @Then("I see the equipment is created for the gym")
    public void iSeeTheEquipmentIsCreatedForTheGym() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    // PRIVATE - PUT /api/gyms/{gymId}/equipment/{equipmentId} (equipment user story)
    @When("I update the equipment details for the gym")
    public void iUpdateTheEquipmentDetailsForTheGym() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        requestBody.put("category", "Updated Weight");
        requestBody.put("brand", "Updated Trustworthy Brand");
        requestBody.put("name", "Updated 10lb dumbbell (single)");
        requestBody.put("quantity", "20");
        requestBody.put("details", "Updated One (1) 10lb dumbbell");
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + getSecurityKeyAsh());

        response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/gyms/3/equipment/4");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("I see the equipment is updated")
    public void iSeeTheEquipmentIsUpdated() {
        String updatedCategory = response.jsonPath().get("category");
        String updatedBrand = response.jsonPath().get("brand");
        String updatedName = response.jsonPath().get("name");
        String updatedQuantity = response.jsonPath().get("quantity").toString();
        String updatedDetails = response.jsonPath().get("details");
        Assert.assertEquals("Updated Weight", updatedCategory);
        Assert.assertEquals("Updated Trustworthy Brand", updatedBrand);
        Assert.assertEquals("Updated 10lb dumbbell (single)", updatedName);
        Assert.assertEquals("20", updatedQuantity);
        Assert.assertEquals("Updated One (1) 10lb dumbbell", updatedDetails);
    }

    // PRIVATE - DELETE /api/gyms/{gymId}/equipment/{equipmentId} (equipment user story)
    @When("I delete the equipment from the gym")
    public void iDeleteTheEquipmentFromTheGym() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + getSecurityKeyAsh());

        response = request.delete(BASE_URL + port + "/api/gyms/3/equipment/4");
    }

    @Then("I see the equipment is deleted")
    public void iSeeTheEquipmentIsDeleted() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    // PRIVATE - POST /api/gyms/{gymId}/amenities (amenity user story)
    @When("I create an amenity for the gym")
    public void iCreateAnAmenityForTheGym() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        requestBody.put("category", "Spa");
        requestBody.put("subcategory", "Sauna");
        requestBody.put("name", "Infrared Sauna");
        requestBody.put("quantity", "1");
        requestBody.put("details", "New infrared sauna");
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + getSecurityKeyAsh());

        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/gyms/3/amenities");
    }

    @Then("I see the amenity is created for the gym")
    public void iSeeTheAmenityIsCreatedForTheGym() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    // PRIVATE - PUT /api/gyms/{gymId}/amenities/{amenityId} (amenity user story)
    @When("I update the amenity details for the gym")
    public void iUpdateTheAmenityDetailsForTheGym() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        JSONObject requestBody = new JSONObject();
        requestBody.put("category", "Updated Spa");
        requestBody.put("subcategory", "Updated Sauna");
        requestBody.put("name", "Updated Infrared Sauna");
        requestBody.put("quantity", "5");
        requestBody.put("details", "Updated New infrared sauna");
        request.header("Content-Type", "application/json");
        request.header("Authorization", "Bearer " + getSecurityKeyAsh());

        response = request.body(requestBody.toString()).put(BASE_URL + port + "/api/gyms/3/amenities/2");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("I see the amenity is updated")
    public void iSeeTheAmenityIsUpdated() {
        String updatedCategory = response.jsonPath().get("category");
        String updatedSubcategory = response.jsonPath().get("subcategory");
        String updatedName = response.jsonPath().get("name");
        String updatedQuantity = response.jsonPath().get("quantity").toString();
        String updatedDetails = response.jsonPath().get("details");
        Assert.assertEquals("Updated Spa", updatedCategory);
        Assert.assertEquals("Updated Sauna", updatedSubcategory);
        Assert.assertEquals("Updated Infrared Sauna", updatedName);
        Assert.assertEquals("5", updatedQuantity);
        Assert.assertEquals("Updated New infrared sauna", updatedDetails);
    }
}