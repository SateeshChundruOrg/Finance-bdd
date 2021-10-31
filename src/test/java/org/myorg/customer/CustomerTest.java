package org.myorg.customer;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class CustomerTest {

    @Test
    public void getCustomer() {

    Response response =  RestAssured.get("http://localhost:8082/customer/3");

        ResponseBody responseBody = response.getBody();

      JsonPath jsonPath  = responseBody.jsonPath();
       Integer id= jsonPath.get("id");
        String name= jsonPath.get("name");
        String address= jsonPath.get("address");
        String phoneNumber= jsonPath.get("phoneNumber");

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(id.intValue(),3);
        Assert.assertEquals(name,"Geetha");
        Assert.assertEquals(address,"vadlamuru");
        Assert.assertEquals(phoneNumber,"8374428448");




    }


    @Test
    public void saveCustomer() {


        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Virender");
        requestParams.put("address", "Singh");
        requestParams.put("phoneNumber", "123455756734");

        request.header("Content-Type", "application/json");

        request.body(requestParams.toString());
        Response response = request.post("http://localhost:8082/customer");

        JsonPath jsonPath  = response.getBody().jsonPath();
        Assert.assertEquals(response.getStatusCode(),201);

        Assert.assertNotNull(jsonPath.get("customerId").toString());



    }
}
