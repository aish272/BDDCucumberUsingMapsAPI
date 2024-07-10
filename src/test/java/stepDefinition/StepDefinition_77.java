package stepDefinition;


import commonMethods.Utility;
import enumInput.ResourceAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.BuildRequestPayload_79;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition_77 extends Utility {
    /*
       keeping it static so that when delete scenario runs which is a new case and would turn all
       variables values null, then placeid would still retain its value from last execution
    */
    static String placeId;
    RequestSpecification request;
    Response response;
    BuildRequestPayload_79 getPayload = new BuildRequestPayload_79();

    @Given("Add place payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String phoneNumber, String address) throws IOException {
        /* **********Build request spec */
        // pass the request spec builder and store in a variable.
        request = given().spec(returnRequestSpecBuilder()).body(getPayload.returnAddPlaceJavaObjAfterInitialization(name, phoneNumber, address));

    }

    @When("user calls {string} api with {string} Http request")
    public void user_calls_api_with_post_http_request(String typeOfResource, String httpMethod) {
        ResourceAPI resourceAPI = ResourceAPI.valueOf(typeOfResource);
        //Call when() on the request spec obj
        switch (httpMethod) {
            case "POST" -> response = request.when().post(resourceAPI.getResource());
            case "GET" -> response = request.when().get(resourceAPI.getResource());
            case "DELETE" -> response = request.when().delete(resourceAPI.getResource());
        }
    }

    @Then("API call should return {int} success code")
    public void api_call_should_return_success_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(response.getStatusCode(), 200);
    }

    @Then("response body should contain {string} key with value {string}")
    public void response_body_should_contain_key_with_value(String key, String expectedVal) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(returnResponseAttributeValue(response, key), expectedVal);
    }

    @And("verify {string} attribute value in the created place using {string} http method")
    public void verifyAttributeValueInTheCreatedPlaceUsingHttpMethod(String value, String resource) throws IOException {
        placeId = returnResponseAttributeValue(response, "place_id");
        request = given().spec(returnRequestSpecBuilder()).queryParam("place_id", placeId);
        user_calls_api_with_post_http_request(resource, "GET");
        assertEquals(returnResponseAttributeValue(response, "name"), value);

    }

    @Given("Delete place payload")
    public void delete_place_payload() throws IOException {
        request = given().spec(returnRequestSpecBuilder()).body(getPayload.returnDeletePlacePayload(placeId));
    }
}
