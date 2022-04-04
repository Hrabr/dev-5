package goit.command.pet;

import com.google.gson.Gson;
import goit.command.Command;
import goit.command.EnterFromConsole;
import goit.command.answer.Answer;
import goit.model.ApiResponse;
import goit.requests.HttpRequests;
import goit.response.HttpResponses;
import goit.view.View;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Delete implements Command {
    private final View view;
    HttpResponses httpResponses;
    EnterFromConsole enterFromConsole;
    HttpRequests httpRequests;
    Answer answer;
    private final Gson MYGSON = new Gson();

    public Delete(View view) {
        this.view = view;
        enterFromConsole = new EnterFromConsole(view);
        httpRequests = new HttpRequests();
        httpResponses = new HttpResponses();
        answer = new Answer();
    }
    @SneakyThrows
    @Override
    public void process() {
        while (true) {
            view.write("Please enter what to delete:pet, order, user ");
            view.write("If you want to exit to the previous menu, press: exit");
            String read = view.read();
            if (read.equalsIgnoreCase("pet")) {
                 HttpResponse<String> httpResponse = deleteById("pet", "/pet/");
                answer.answerApiResponse(httpResponse,ApiResponse.class,MYGSON);
            }else if (read.equalsIgnoreCase("order")) {
                HttpResponse<String> httpResponse = deleteById("order", "/store/order/");
                answer.answerApiResponse(httpResponse,ApiResponse.class,MYGSON);
            }else if(read.equalsIgnoreCase("user")){
                 HttpResponse<String> httpResponse = deleteByName("username", "/user/");
                answer.answerApiResponse(httpResponse,ApiResponse.class,MYGSON);
            } else if (read.equalsIgnoreCase("exit")) {
                break;
            } else {
                view.write("You enter wrong command");

            }
        }
    }

    @Override
    public String commandName() {
        return "delete";
    }
    public HttpResponse<String> deleteById(String id, String url)throws IOException, InterruptedException{
        view.write("Enter "+id+" id");
        int i = enterFromConsole.CheckNumber();
        HttpRequest request = httpRequests.delete(url + i);
        return httpResponses.postResponse(request);
    }  public HttpResponse<String> deleteByName(String id, String url)throws IOException, InterruptedException{
        view.write("Enter "+id);
        String read = view.read();
        HttpRequest request = httpRequests.delete(url + read);
        return httpResponses.postResponse(request);
    }

}
