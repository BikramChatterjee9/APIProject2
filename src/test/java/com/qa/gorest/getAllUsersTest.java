package com.qa.gorest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qa.base.BaseTest;
import com.qa.constraints.AuthType;
import com.qa.pojo.fakepojo;
import com.qa.pojo.userpojo;
import com.qa.utils.EmailUtils;
import com.qa.utils.JsonUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class getAllUsersTest extends BaseTest{

    @Test
    public void m1() throws JsonProcessingException {
        userpojo user = new userpojo(null,"priyanka",EmailUtils.generateEmail(),"female","active");

        Response responsePost = restClient.post(GO_REST_BASE_URL,GO_REST_END_POINT,user,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        String expectedName = responsePost.jsonPath().getString("name");
        Assert.assertEquals(user.getName(),expectedName);
        int id= responsePost.jsonPath().getInt("id");
        Assert.assertNotNull(id);

        Response response = restClient.get(GO_REST_BASE_URL,GO_REST_END_POINT+"/"+id,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        Assert.assertTrue(response.statusLine().contains("OK"));

        userpojo userpojo = JsonUtils.deserelize(response, userpojo.class);
    }
}
