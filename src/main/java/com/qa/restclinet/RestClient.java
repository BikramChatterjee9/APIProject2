package com.qa.restclinet;

import com.qa.constraints.AuthType;
import com.qa.manager.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class RestClient {

    ResponseSpecification responseSpec200 = expect().statusCode(200);
    ResponseSpecification responseSpec201 = expect().statusCode(201);
    ResponseSpecification responseSpec200_201 = expect().statusCode(anyOf(equalTo(200),equalTo(201)));
    ResponseSpecification responseSpec200_404 = expect().statusCode(anyOf(equalTo(200),equalTo(404)));
    ResponseSpecification responseSpec204 = expect().statusCode(204);

    public RequestSpecification setupRequest(String baseURL, AuthType authType, ContentType contentType)
    {
        RequestSpecification request = given().log().all()
                .baseUri(baseURL)
                .contentType(contentType)
                .accept(contentType);

        switch (authType)
        {
            case BEARER_TOKEN:
                request.header("Authorization","Bearer "+ ConfigManager.get("bearerToken"));
                break;
            default:
                System.out.print("Invalid token");
                break;
        }
        return request;
    }

    public void applyParam(RequestSpecification request, Map<String,String> queryParams,Map<String,String> pathParams)
    {
        if(queryParams!=null)
        {
            request.queryParams(queryParams);
        }
        if(pathParams!=null)
        {
            request.pathParams(pathParams);
        }
    }

    public Response get(String baseURL, String endPoint, Map<String,String> queryParams, Map<String,String> pathParams,
                        AuthType authType, ContentType contentType)
    {
        RequestSpecification request = setupRequest(baseURL,authType,contentType);
        applyParam(request,queryParams,pathParams);
        return request.get(endPoint).then().log().all().spec(responseSpec200_404).extract().response();
    }

    public <T>Response post(String baseURL, String endPoint, T body, Map<String,String> queryParams, Map<String,String> pathParams,
                        AuthType authType, ContentType contentType)
    {
        return post(baseURL, endPoint, body, queryParams, pathParams, authType, contentType, 201);
    }

    public <T>Response post(String baseURL, String endPoint, T body, Map<String,String> queryParams, Map<String,String> pathParams,
                            AuthType authType, ContentType contentType, int expectedStatusCode)
    {
        RequestSpecification request = setupRequest(baseURL,authType,contentType);
        applyParam(request,queryParams,pathParams);
        return request.body(body).post(endPoint).then().log().all().statusCode(expectedStatusCode).extract().response();
    }

    public <T>Response put(String baseURL, String endPoint, T body, Map<String,String> queryParams, Map<String,String> pathParams,
                            AuthType authType, ContentType contentType)
    {
        RequestSpecification request = setupRequest(baseURL,authType,contentType);
        applyParam(request,queryParams,pathParams);
        return request.body(body).put(endPoint).then().log().all().spec(responseSpec200).extract().response();
    }

    public <T>Response patch(String baseURL, String endPoint, T body, Map<String,String> queryParams, Map<String,String> pathParams,
                           AuthType authType, ContentType contentType)
    {
        RequestSpecification request = setupRequest(baseURL,authType,contentType);
        applyParam(request,queryParams,pathParams);
        return request.body(body).patch(endPoint).then().log().all().spec(responseSpec200).extract().response();
    }

    public Response delete(String baseURL, String endPoint, Map<String,String> queryParams, Map<String,String> pathParams,
                             AuthType authType, ContentType contentType)
    {
        RequestSpecification request = setupRequest(baseURL,authType,contentType);
        applyParam(request,queryParams,pathParams);
        return request.delete(endPoint).then().log().all().spec(responseSpec204).extract().response();
    }


}
