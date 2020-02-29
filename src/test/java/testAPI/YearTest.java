/*
Задание 2.3
Используя сервис https://reqres.in/
убедится что операция LIST <RESOURCE> возвращает данные отсортированные по годам
Автотест необходимо написать, используя данный стек:
Java, JUnit

 */
package testAPI;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class YearTest {

    @Test
    public void sortedTest() {
        Response response = given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .body("data.year", notNullValue())
                .log().all()
                .statusCode(200)
                .extract().response();
        JsonPath jsonResponse = response.jsonPath();
        List<Integer> years = jsonResponse.getList("data.year");
        boolean flag = true;
        if (years.size() > 2) {
            for (int i = 1; i < years.size(); i++) {
                if (years.get(i) < years.get(i - 1)) {
                    flag = false;
                    break;
                }
            }
        }
        Assert.assertTrue(flag);
    }
}
