package stepDefinition;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeDeleteScenario() throws IOException {
        //In case add place method is not executed, this code
        // will create place id which will be used in delete API method
        if(StepDefinition_77.placeId==null) {
            StepDefinition_77 sd = new StepDefinition_77();
            sd.add_place_payload_with("Aish","Italian","Europe");
            sd.user_calls_api_with_post_http_request("ADDPLACEAPI","POST");
            sd.verifyAttributeValueInTheCreatedPlaceUsingHttpMethod("Aish","GETPLACEAPI");
        }

    }
}
