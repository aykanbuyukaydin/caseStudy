package caseStudy.stepdefinitions;

import caseStudy.pojos.Grocery;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.Argument;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class APIStepDefs {
    Response response;

    @Then("Kullanici butun stok ve fiyat bilgisini okumak icin {string} endpointe gider")
    public void kullanici_butun_stok_ve_fiyat_bilgisini_okumak_icin_endpointe_gider(String endpoint) {
        response = given()
                .accept("application/json")
                .when()
                .get(endpoint)
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        response.prettyPrint();

        System.out.println("-----------------------------------------------------------------------------------");
    }

    @Then("Status kodunun {int} oldugunu ve gelen response degerlerini kontrol eder")
    public void status_kodunun_oldugunu_ve_gelen_response_degerlerini_kontrol_eder(Integer statusKod, DataTable dataTable) {
        response.then().assertThat().
                statusCode(statusKod).
                contentType(ContentType.JSON);

        System.out.println(dataTable);

        List<Map<String, Object>> expectedList = dataTable.asMaps(String.class, Object.class);
        List<Grocery.Item> actualGroceryList = Arrays.asList(response.getBody().as(Grocery.Item[].class));

        for (int i = 0; i < expectedList.size(); i++) {
            Assert.assertEquals(Integer.parseInt((String) expectedList.get(i).get("id")), actualGroceryList.get(i).getId());
            Assert.assertEquals(expectedList.get(i).get("name"), actualGroceryList.get(i).getName());
            Assert.assertEquals(Double.parseDouble((String) expectedList.get(i).get("price")), actualGroceryList.get(i).getPrice(), 0.001);
            Assert.assertEquals(Integer.parseInt((String) expectedList.get(i).get("stock")), actualGroceryList.get(i).getStock());
        }

        System.out.println("***********************************************************************************");
    }

    @Then("Kullanici stok ve fiyat bilgisini okumak icin {string} endpointe gider")
    public void kullanici_stok_ve_fiyat_bilgisini_okumak_icin_endpointe_gider(String endpoint) {
        response = given()
                .accept("application/json")
                .when()
                .get(endpoint)
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        response.prettyPrint();

        System.out.println("-----------------------------------------------------------------------------------");
    }

    @Then("Status kodunun {int} oldugunu ve gelen response degerini kontrol eder")
    public void status_kodunun_oldugunu_ve_gelen_response_degerini_kontrol_eder(Integer statusKod, io.cucumber.datatable.DataTable dataTable) {
        response.then().assertThat().
                statusCode(statusKod).
                contentType(ContentType.JSON);

        System.out.println(dataTable);

        Map<String, Object> expected = dataTable.asMap(String.class, Object.class);
        Grocery.Item actualGrocery = response.getBody().as(Grocery.Item.class);

        Assert.assertEquals(Integer.parseInt((String) expected.get("id")), actualGrocery.getId());
        Assert.assertEquals(expected.get("name"), actualGrocery.getName());
        Assert.assertEquals(Double.parseDouble((String) expected.get("price")), actualGrocery.getPrice(), 0.001);
        Assert.assertEquals(Integer.parseInt((String) expected.get("stock")), actualGrocery.getStock());

        System.out.println("***********************************************************************************");
    }

    @Then("Status kodunun {int} oldugunu kontrol eder")
    public void status_kodunun_oldugunu_kontrol_eder(Integer statusKod) {
        System.out.println(response.getStatusCode());
        if(response.getStatusCode()==statusKod) {
            response.then().assertThat().
                    statusCode(statusKod).
                    contentType(ContentType.JSON);
            System.out.println("Status Kod Basarili");
        }else {
            System.out.println("Status Kod Basarisiz");
        }

        System.out.println("***********************************************************************************");
    }

    @Then("Kullanici meyve yada sebze eklemek icin {string} endpointe gider")
    public void kullanici_meyve_yada_sebze_eklemek_icin_endpointe_gider(String endpoint, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, Object>> data = dataTable.asMaps(String.class, Object.class);
        for (Map<String, Object> row : data) {
            int id = Integer.parseInt((String) row.get("id"));
            String name = (String) row.get("name");
            float price = Float.parseFloat((String) row.get("price"));
            int stock = Integer.parseInt((String) row.get("stock"));

            response = given()
                    .contentType("application/json")
                    .body(new Grocery.Item(id, name, price, stock))
                    .when()
                    .post(endpoint)
                    .then()
                    .extract()
                    .response();

            response.prettyPrint();

            System.out.println("-----------------------------------------------------------------------------------");
        }
    }

    @Then("Status kodunun {int} oldugunu ve meyve yada sebzenin eklemdigini kontrol eder")
    public void status_kodunun_oldugunu_ve_meyve_yada_sebzenin_eklemdigini_kontrol_eder(Integer statusKod) {
        response.prettyPrint();

        response.then().assertThat().
                statusCode(statusKod).
                contentType(ContentType.JSON);

        given()
                .when()
                .get("http://mockservice.com/allGrocery")
                .then()
                .body("data.name", hasItems("string"))
                .body("data.price", hasItems(12.3f))
                .body("data.stock", hasItems(3));

        System.out.println("***********************************************************************************");
    }
}