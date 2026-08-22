package com.qa.base;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.restclinet.RestClient;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

@Listeners(ChainTestListener.class)
public class BaseTest {

    public static RestClient restClient;

    public final static String GO_REST_BASE_URL = "https://gorest.co.in";
    public final static String BASE_URL_CONTACTS = "https://thinking-tester-contact-list.herokuapp.com";
    public final static String BASE_URL_BASIC_AUTH = "https://the-internet.herokuapp.com";
    public final static String BASE_URL_FAKE_STORE = "https://fakestoreapi.com";

    public final static String GO_REST_END_POINT = "/public/v2/users";
    public final static String CONTACTS_LOGIN_END_POINT = "/users/login";
    public final static String CONTACTS = "/contacts";
    public final static String BASIC_AUTH_END_POINT = "/basic_auth";
    public final static String END_POINT_FAKE_STORE = "/products";


    @BeforeSuite
    public void setupAllure()
    {
        RestAssured.filters(new AllureRestAssured());
    }

    @BeforeTest
    public void setup()
    {
        restClient = new RestClient();
    }
}
