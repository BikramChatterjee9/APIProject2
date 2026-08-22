package com.qa.gorest;

import com.qa.base.BaseTest;
import com.qa.constraints.AuthType;
import com.qa.pojo.userpojo;
import com.qa.utils.EmailUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateUserTest extends BaseTest {
    @Test
    public void m1()
    {
        userpojo user = new userpojo(null,"priyanka",EmailUtils.generateEmail(),"female","active");
        Response response = restClient.post(GO_REST_BASE_URL,GO_REST_END_POINT,user,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        String expectedName = response.jsonPath().getString("name");
        Assert.assertEquals(user.getName(),expectedName);
        int id= response.jsonPath().get("id");
        Assert.assertNotNull(id);
        System.out.println("User id is "+ id);

        Response responseGet = restClient.get(GO_REST_BASE_URL,GO_REST_END_POINT+"/"+id,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        System.out.println("==================== GET Response ===============");
        System.out.println(responseGet.prettyPrint());
        int responseUserId = responseGet.jsonPath().get("id");
        System.out.println("Response User id is "+ responseUserId);

        user.setName("Naveen");

        Response responsePut = restClient.put(GO_REST_BASE_URL,GO_REST_END_POINT+"/"+id,user,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        String responseName = responsePut.jsonPath().getString("name");
        Assert.assertEquals(responseName,"Naveen");

        Response responseDelete = restClient.delete(GO_REST_BASE_URL,GO_REST_END_POINT+"/"+id,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        System.out.println(responseDelete);
        Assert.assertTrue(responseDelete.statusLine().contains("No Content"));





    }
}
