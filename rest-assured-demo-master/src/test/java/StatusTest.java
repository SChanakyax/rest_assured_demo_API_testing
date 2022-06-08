import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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

        System.out.println(requestSpecification_maxSoft);
        //Validate The response
        given()
                .spec(requestSpecification_maxSoft)
                .when().log().all()
                .get(Constants.USERS_ENDPOINT_maxSoft)
                .then().log().all()
                .statusCode(SC_OK);
                //.body("data.status", equalTo(STATUS))
                //.body("data.name", equalTo(NAME));


    }
}
