package com.qa.contacts;

import com.qa.base.BaseTest;
import com.qa.constraints.AuthType;
import com.qa.manager.ConfigManager;
import com.qa.pojo.contactspojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactsApiTest extends BaseTest {

    String tokenId;

    @BeforeMethod
    public void setup()
    {
        super.setup();

        contactspojo contacts = contactspojo.builder()
                .email("rapanomik@gmail.com")
                .password("vicky123")
                .build();

        Response response = restClient.post(BASE_URL_CONTACTS,CONTACTS_LOGIN_END_POINT,contacts,null,null, AuthType.NO_AUTH, ContentType.JSON, 200);

        tokenId = response.jsonPath().getString("token");

        System.out.println("token id is "+ tokenId);

        ConfigManager.set("bearerToken",tokenId);

    }

    @Test
    public void m1()
    {
        Response response = restClient.get(BASE_URL_CONTACTS,CONTACTS,null,null,AuthType.BEARER_TOKEN,ContentType.JSON);
        Assert.assertEquals(response.statusCode(),200);
    }

}
