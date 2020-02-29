/*
Задание 2.1
1.	Используя сервис https://reqres.in/ получить список пользователей со второй страницы.
2.	Убедится что аватары пользователей совпадают
3.	Автотесты необходимо написать, используя данный стек:
4.	Java, testNG, restAssured.

 */

package testAPI;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class APItest {

    @Test
    public void firstTest() {
        Response response = given()  //  "получить" с оператором присваивания в response
                .when() // логическое "когда"
                .get("https://reqres.in/api/users?page=2")  //откуда берем данные
                .then()   // логическое "когда
                .body("data.avatar", notNullValue())  //  "тело" ключа не имеет пустых значений
                .log().all()  //логируем  (нужно ли? )
                .statusCode(200)  // проверяем статус-код. Вроде это обязательно
                .extract()  //  "дергаем" данные
                .response();  // записываем в R

        JsonPath jsonResponse = response.jsonPath(); //  переводим Response в формат Json
        List<String> list = jsonResponse.getList("data.avatar");
        boolean flag = true;   // булевый флаг на тест
        for (int i = 0; i < list.size(); i++) {
            String temp = list.get(i).substring(list.get(i).lastIndexOf("/"));
            for (int j = i + 1; j < list.size(); j++) {
                if (!temp.equals(list.get(j).substring(list.get(j).lastIndexOf("/")))) {
                    flag = false;
                    break;
                }
            }
        }
        Assert.assertTrue(flag); // проверка флага


    }
}
