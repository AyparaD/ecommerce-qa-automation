package api;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Epic("API Automation")
@Feature("Post Management")

    public class PostApiTest {

        @BeforeClass
        public void setup() {

            RestAssured.baseURI =
                    "https://jsonplaceholder.typicode.com";

        }

        // GET Request
        @Description("Verify that user can retrieve a post successfully")
        @Test
        public void verifyGetPost() {

            given()

                    .when()
                    .get("/posts/1")

                    .then()
                    .statusCode(200)
                    .body("id", equalTo(1))
                    .body("title", not(emptyString()));

        }

        // POST Request
        @Test
        public void verifyCreatePost() {

            String requestBody = """
                {
                    "title": "QA Automation",
                    "body": "REST Assured API Testing",
                    "userId": 1
                }
                """;

            given()
                    .header("Content-Type", "application/json")
                    .body(requestBody)

                    .when()
                    .post("/posts")

                    .then()
                    .statusCode(201)
                    .body("title", equalTo("QA Automation"))
                    .body("userId", equalTo(1));

        }

        // PUT Request
        @Test
        public void verifyUpdatePost() {

            String requestBody = """
                {
                    "id": 1,
                    "title": "Updated QA Title",
                    "body": "Updated API Testing",
                    "userId": 1
                }
                """;

            given()
                    .header("Content-Type", "application/json")
                    .body(requestBody)

                    .when()
                    .put("/posts/1")

                    .then()
                    .statusCode(200)
                    .body("title", equalTo("Updated QA Title"));

        }

        // DELETE Request
        @Test
        public void verifyDeletePost() {

            given()

                    .when()
                    .delete("/posts/1")

                    .then()
                    .statusCode(200);

        }
    }