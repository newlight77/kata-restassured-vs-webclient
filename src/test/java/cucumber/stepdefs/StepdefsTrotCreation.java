package cucumber.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.wesquad.wetrot.model.Trot;
import io.wesquad.wetrot.model.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@Log
public class StepdefsTrotCreation {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webClient;

    private StepDefsContext context = StepDefsContext.CONTEXT;

    @Given("an admin user existing in the system")
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

    @Given("a Trot of brand Xiaomi")
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

    @When("he want to create a trot")
    public void he_want_to_create_a_trot() {
        WebTestClient.ResponseSpec response = webClient
                .post()
                .uri("/api/v1/trots")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(BodyInserters.fromObject(context.givenObject(Trot.class)))
                .exchange();
        context.thenObject(response, WebTestClient.ResponseSpec.class);

    }

    @Then("the trot is created")
    public void the_trot_is_created() {
        WebTestClient.ResponseSpec response = context.thenObject(WebTestClient.ResponseSpec.class);
        response.expectStatus()
                .isCreated()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(Trot.class).value(t -> t.getBrand().equals("Xiaomi"));

    }
}
