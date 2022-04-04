package goit.command.answer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import goit.model.ApiResponse;
import goit.model.Pet;

import java.net.http.HttpResponse;
import java.util.List;

public class Answer <T>{


    public void answerObject(HttpResponse<String> response, Class<T> t, Gson gson){
        if(response.statusCode()==200){
            System.out.println("Successfully \n"+gson.fromJson(response.body(), t));
        }else {
            System.out.println("No successfully \n"+gson.fromJson(response.body(), ApiResponse.class));
        }
    }
    public void answerApiResponse(HttpResponse<String> response, Class<T> t, Gson gson){
        if(response.statusCode()==200){
            System.out.println("Successfully \n"+gson.fromJson(response.body(),  t));
        }else {
            System.out.println("No successfully \n");
        }
    }
    public void answerStatus(HttpResponse<String> response,Gson gson){
        if (response.statusCode()==200){
            System.out.println("Successfully \n"+gson.fromJson(response.body(),new TypeToken<List<Pet>>() {}.getType()));
        }else {
            System.out.println("No successfully \n");
        }
    }
}
