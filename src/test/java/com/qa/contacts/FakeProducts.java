package com.qa.contacts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qa.base.BaseTest;
import com.qa.constraints.AuthType;
import com.qa.pojo.fakepojo;
import com.qa.utils.JsonUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class FakeProducts extends BaseTest {

    @Test
    public void m1() throws JsonProcessingException {
        Response response = restClient.get(BASE_URL_FAKE_STORE,END_POINT_FAKE_STORE,null,null, AuthType.NO_AUTH, ContentType.ANY);

        Assert.assertEquals(response.statusCode(),200);

        fakepojo[] fakepojos = JsonUtils.deserelize(response, fakepojo[].class);

        for(fakepojo fake : fakepojos)
        {
            System.out.println("Id is "+ fake.getId());
            System.out.println("Title is "+ fake.getTitle());
            System.out.println("Price is "+ fake.getPrice());
            System.out.println("Desription is "+ fake.getDescription());
            System.out.println("Category is"+ fake.getCategory());
            System.out.println("Image is "+ fake.getImage());
            System.out.println("Rate is "+ fake.getRating().getRate());
            System.out.println("Count is"+ fake.getRating().getCount());
        }
    }
}
