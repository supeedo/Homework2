/*
API
Задание 2.1
1.	Используя сервис https://reqres.in/ протестировать регистрацию пользователя в системе.
2.	Необходимо создание двух тестов на успешный логи и логин с ошибкой из-за не введённого пароля
Автотест необходимо написать, используя данный стек:
Java, JUnit

 */
package testAPI;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LoginTest {

    @Test //  test for sucessfully login
    public void sucessfullyLogin() {
        Map<String, String> data = new HashMap<>();  // мапа где будем хранить данные
        data.put("email", "eve.holt@reqres.in");  // логин пользователя
        data.put("password", "cityslicka");  // пароль пользователя

        Response response = given() // указываем оператор присваивания ответа сервера в response
                .contentType("application/json")  // формат отправляемой информации
                .body(data)  //  объект который будет отправляться
                .when()  // "когда"
                .post("https://reqres.in/api/login")  // ссылка на страницу логина
                .then()  // "тогда"
                .log().all()  // логируем все...пока непонятно зачем...
                .statusCode(200)  // статус-код??  Отправляем или получаем, опять же не совсем понятно
                .extract()   // выдераем
                .response();  // отправляем
        JsonPath jsonResponse = response.jsonPath();  // переводим возвращенную информацию в формат Json
        Assert.assertTrue(!jsonResponse.get("token").toString().isEmpty());  // сравниваем что ответ содержит токен и он не пустой
    }

    @Test  // test unsucessfully login
    public void unsucessfullyLogin() {
        Map <String, String> parameters = new HashMap<>();
        parameters.put("email", "peter@klaven");
        Response response = given()
                .contentType("application/json")
                .body(parameters)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(400)
                .extract().response();
        JsonPath jsonResponse = response.jsonPath();
        Assert.assertTrue(jsonResponse.get("error").toString().equals("Missing password"));


    }
}
