package cucumber.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.wesquad.wetrot.model.Trot;
import io.wesquad.wetrot.model.User;
import lombok.extern.java.Log;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.equalTo;

@Log
public class StepdefsTrotCreationRestassured {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost:" + port;
    private StepDefsContext context = StepDefsContext.CONTEXT;

    @Given("an admin user existing in the system - REST-assured")
    public void an_admin_user_existing_in_the_system() {
        User user = User.builder()
                .username("tatata")
                .firstname("Kong")
                .lastname("To")
                .email("newlight77@gmail.com")
                .age(42)
                .build();
        context.givenObject(user, User.class);
    }

    @Given("a Trot of brand Xiaomi - REST-assured")
    public void a_Trot_of_brand_Xiaomi() {
        Trot trot = Trot.builder()
                .name("first trot")
                .brand("Xiaomi")
                .batteryCapacity(5500)
                .currentBatteryLevel(2000)
                .wearLevel(0)
                .wearLife(990)
                .build();

        context.givenObject(trot, Trot.class);
    }

    @When("he want to create a trot - REST-assured")
    public void he_want_to_create_a_trot() {

        final String url = baseUrl + port + "/api/v1/trots";

        Response response = RestAssured.given()
                .log()
                .all()
                .when()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(context.givenObject(Trot.class))
                .post(url);

        context.thenObject(response, Response.class);
    }

    @Then("the trot is created - REST-assured")
    public void the_trot_is_created() {
        Response response = context.thenObject(Response.class);

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.CREATED.value())
                .body("brand", equalTo("Xiaomi"));
    }


}
