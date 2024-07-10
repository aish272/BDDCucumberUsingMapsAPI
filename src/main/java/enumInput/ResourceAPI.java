package enumInput;

public enum ResourceAPI {

    ADDPLACEAPI("maps/api/place/add/json"),
    DELETEPLACEAPI("maps/api/place/delete/json"),
    GETPLACEAPI("maps/api/place/get/json");
    private String resource;

    ResourceAPI(String resource)
    {
        this.resource = resource;
    }

    public String getResource()
    {
        return resource;
    }
}
