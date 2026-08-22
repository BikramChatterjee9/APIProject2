package com.qa.gorest;

import com.qa.base.BaseTest;
import com.qa.constraints.AuthType;
import com.qa.pojo.userpojo;
import com.qa.utils.EmailUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUserTest extends BaseTest {

    @Test
    public void m1()
    {
        Response response = restClient.get(GO_REST_BASE_URL,GO_REST_END_POINT,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(response.statusLine().contains("OK"));
    }

    @Test
    public void m2()
    {
        HashMap<String,String> hm = new HashMap<>();
        hm.put("name","Shresthi Pilla CPA");
        hm.put("gender","male");
        Response response = restClient.get(GO_REST_BASE_URL,GO_REST_END_POINT,hm,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(response.statusLine().contains("OK"));
    }

    @Test
    public void m3()
    {
        userpojo user = new userpojo(null,"api-user", EmailUtils.generateEmail(), "male", "active");
        Response createResponse = restClient.post(GO_REST_BASE_URL, GO_REST_END_POINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        int userId = createResponse.jsonPath().getInt("id");

        Response response = restClient.get(GO_REST_BASE_URL, GO_REST_END_POINT + "/" + userId, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(response.statusLine().contains("OK"));
    }
}
