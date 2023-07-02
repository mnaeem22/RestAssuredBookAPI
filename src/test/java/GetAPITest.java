import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class GetAPITest {

    @Test
    void getAPITest(){
        RestAssured.baseURI="https://demoqa.com/BookStore/v1/Books";
        RequestSpecification requestSpecification = RestAssured.given();
        Response response = requestSpecification.get();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode,200);
        ValidatableResponse validatableResponse = response.then();
        validatableResponse.time(Matchers.lessThan(2000L));
        validatableResponse.contentType(ContentType.JSON);
        validatableResponse.body(matchesJsonSchemaInClasspath("json-schema.json"));
    }


}
