package commonMethods;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;

public class Utility {

    PrintStream requestStream;
    PrintStream responseStream;
    public RequestSpecification returnRequestSpecBuilder() throws IOException {
        String timeStamp = returnTS();
        requestStream = new PrintStream(new FileOutputStream("RequestLog"+timeStamp+".txt"));
        responseStream = new PrintStream(new FileOutputStream("ResponseLog"+timeStamp+".txt"));
        return new RequestSpecBuilder().setBaseUri(returnGlobalVal("BaseURI"))
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(requestStream)).
                addFilter(ResponseLoggingFilter.logResponseTo(responseStream)).build();
    }

    public ResponseSpecification returnResponseSpecBuilder() {
        return  new ResponseSpecBuilder().expectStatusCode(200).
                expectContentType(ContentType.JSON).build();
    }

    public String returnGlobalVal(String key) throws IOException {
        Properties pro = new Properties();
        FileInputStream inputStream = new FileInputStream("/Users/aish/Documents/BDDCucumberUsingMapsAPI/globalValues.properties");
        pro.load(inputStream);
        return pro.getProperty(key);
    }

    public String returnTS()
    {
        Locale locale = new Locale("English", "IN");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss", locale);
        return LocalDateTime.now().format(formatter);
    }

    public String returnResponseAttributeValue(Response response, String key)
    {
        JsonPath js = new JsonPath(response.asString());
        return js.get(key).toString();
    }


}
