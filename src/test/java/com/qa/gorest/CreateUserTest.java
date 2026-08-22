package com.qa.gorest;

import com.qa.base.BaseTest;
import com.qa.constraints.AuthType;
import com.qa.pojo.UserPojoNew;
import com.qa.pojo.userpojo;
import com.qa.utils.EmailUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateUserTest extends BaseTest{

    @Test
    public void m1()
    {
        userpojo user = new userpojo(null,"priyanka",EmailUtils.generateEmail(),"female","active");
        Response response = restClient.post(GO_REST_BASE_URL,GO_REST_END_POINT,user,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        String expectedName = response.jsonPath().getString("name");
        Assert.assertEquals(user.getName(),expectedName);
        int id= response.jsonPath().getInt("id");
        Assert.assertNotNull(id);
        System.out.println("User id is "+ id);
    }

    @Test
    public void m2()
    {
        String emailId = EmailUtils.generateEmail();

        String userJson = "{\n" +
                "    \"name\": \"Jagmeet Guneta\",\n" +
                "    \"email\": \""+emailId+"\",\n" +
                "    \"gender\": \"female\",\n" +
                "    \"status\": \"active\"\n" +
                "}";
        Response response = restClient.post(GO_REST_BASE_URL,GO_REST_END_POINT,userJson,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        String expectedName = response.jsonPath().getString("name");
        int id= response.jsonPath().getInt("id");
        Assert.assertNotNull(id);
    }

    @DataProvider
    public Object[][] setData()
    {
        Object[][] data={{"priyanka","female","active"},{"priyanka","female","inactive"}};
        return data;
    }

    @Test(dataProvider = "setData")
    public void m3(String name,String gender,String status)
    {
        String emailId = EmailUtils.generateEmail();

        UserPojoNew user = new UserPojoNew(name,emailId,gender,status);

        Response response = restClient.post(GO_REST_BASE_URL,GO_REST_END_POINT,user,null,null, AuthType.BEARER_TOKEN, ContentType.JSON);
        String expectedName = response.jsonPath().getString("name");
        int id= response.jsonPath().getInt("id");
        Assert.assertNotNull(id);
    }


}
