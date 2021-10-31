package org.myorg.customer;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CustomerTest {

    @Test
    public void getCustomer() {

 /*   Response response =  RestAssured.get("http://localhost:8080/customer/2");
    System.out.println(response.getBody());
    response.body("id",equalTo(2));
        Assert.assertEquals(200,response.getStatusCode());*/

        when().
                get("http://localhost:8080/customer/{id}", 2).
                then().
                statusCode(200).
                body("id",equalTo(2),
                        "name", equalTo("chundru sateesh"));

    }
}
