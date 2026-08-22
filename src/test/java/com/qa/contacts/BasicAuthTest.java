package com.qa.contacts;

import com.qa.base.BaseTest;
import com.qa.constraints.AuthType;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class BasicAuthTest extends BaseTest {

    @Test
    public void m1()
    {
        restClient.get(BASE_URL_BASIC_AUTH,BASIC_AUTH_END_POINT,null,null, AuthType.NO_AUTH, ContentType.JSON);
    }
}
