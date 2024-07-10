package resources;

import POJO_Serialiazation_MapsRequestPayload.Level1_addPlace_62;
import POJO_Serialiazation_MapsRequestPayload.Level2_location_62;

import java.util.Arrays;

public class BuildRequestPayload_79 {

    public Level1_addPlace_62 returnAddPlaceJavaObjAfterInitialization(String name, String phoneNumber, String address) {
        Level1_addPlace_62 addPlaceJavaObj = new Level1_addPlace_62();
        Level2_location_62 location = new Level2_location_62();
        location.setLat(27.055189);
        location.setLng(79.918083);
        addPlaceJavaObj.setAccuracy(100);
        addPlaceJavaObj.setName(name);
        addPlaceJavaObj.setPhone_number(phoneNumber);
        addPlaceJavaObj.setAddress(address);
        addPlaceJavaObj.setWebsite("https://google.com/MumVenuvan");
        addPlaceJavaObj.setLanguage("Sanskrit");
        addPlaceJavaObj.setTypes(Arrays.asList("HQ", "Office"));
        addPlaceJavaObj.setLocation(location);
        return addPlaceJavaObj;
    }

    public String returnDeletePlacePayload(String placeId)
    {
        return "{\n" +
                "    \"place_id\":\""+placeId+"\"\n" +
                "}";
    }
}
