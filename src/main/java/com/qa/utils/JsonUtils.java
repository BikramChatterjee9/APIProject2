package com.qa.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T deserelize(Response response,Class<T> targetClass) throws JsonProcessingException
    {
        return objectMapper.readValue(response.getBody().asString(),targetClass);
    }
}
