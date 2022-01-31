package br.com.vrbeneficios.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class TestUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String objectToJson(Object o){
        try {
            return mapper.writeValueAsString(o);
        } finally{
            return "";
        }
    }

    public static <T> T jsonToObject(String t, Class<T> tclass){
        try {
            return mapper.readValue(t, tclass);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
