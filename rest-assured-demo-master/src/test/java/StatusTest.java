import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

public class StatusTest {

    private final String STATUS = "Completed";
    private final String NAME = "Mock service implementation using Express";
    private RequestSpecification requestSpecification_maxSoft;


    @BeforeClass
    public void setup() {
        RequestSpecBuilder requestSpecBuilder_maxSoft = new RequestSpecBuilder();
        requestSpecBuilder_maxSoft
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader(Constants.AUTH_HEADER_NAME_maxSoft, "Bearer " + Token.getToken())
                .addHeader(Constants.APP_NAME,"IntelliAPI")
                .addHeader(Constants.ORGANIZATION,"MaxSoft");
        requestSpecification_maxSoft = requestSpecBuilder_maxSoft.build();
    }

    @Test()
    public void testStatusCode() {

       // System.out.println(requestSpecification_maxSoft);
        //Validate The response
     /*   given()
                .spec(requestSpecification_maxSoft)
                .when().log().all()
                .get(Constants.USERS_ENDPOINT_maxSoft)
                .then().log().all()
                .statusCode(SC_OK);
                //.body("data.status", equalTo(STATUS))
                //.body("data.name", equalTo(NAME));
*/
        //GET operation
        given() .spec(requestSpecification_maxSoft)
                .when().log().all()
                .get(Constants.USERS_ENDPOINT_maxSoft)
                .then()
                //.log().all()

                //verify status code as 200
                .assertThat().statusCode(200);

    }

    //Check for 200 and insert data to hashmap
    @Test()
    public void testOutputName() {

        boolean key_value_pair = false;

        // System.out.println(requestSpecification_maxSoft);
        //Validate The response
     /*   given()
                .spec(requestSpecification_maxSoft)
                .when().log().all().get("")
                .get(Constants.USERS_ENDPOINT_maxSoft)
                .then().assertThat()
                .body("['status']",equalTo("In Progress"));
*/

        //RestAssured.baseURI = "https://restapi.demoqa.com/utilities/weather/city";
        // requestSpecification_maxSoft.get("/tasks");
        //  int statusCode = response.getStatusCode();
        //  Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
/*
            given().spec(requestSpecification_maxSoft)
                    .when().log().all()
                    .get(Constants.USERS_ENDPOINT_maxSoft).then()
                .statusCode(200)
                .and()
                .body().as;

        JsonArray jsonArray = new JsonArray();
        //Get the response as jsonArray.
        jsonArray = given().baseUri("https://intelliapi-mockserver.herokuapp.com")
                .basePath("tasks")
                .get().as(JsonArray.class);
*/
        JsonArray jsonArray = given().spec(requestSpecification_maxSoft).get(Constants.USERS_ENDPOINT_maxSoft).as(JsonArray.class);

        //Loop through the array and get each element.


        HashMap<String, String> jsondata_map = new HashMap<String, String>();

        // Add keys and values (Country, City)



        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
            jsondata_map.put(jsonObject.get("status").getAsString(),jsonObject.get("name").getAsString());

            System.out.println(jsondata_map);
        }

        if (jsondata_map.containsKey("Completed")) {
            if(jsondata_map.containsValue("Mock service implementation using Express")){
                key_value_pair = true;
            }

        } else {
            key_value_pair = false;
        }


       System.out.println("Final test output >>>" + key_value_pair);
    }
}













