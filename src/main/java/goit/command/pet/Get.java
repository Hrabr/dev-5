package goit.command.pet;

import com.google.gson.Gson;
import goit.command.Command;
import goit.command.EnterFromConsole;
import goit.command.answer.Answer;
import goit.model.ApiResponse;
import goit.model.Order;
import goit.model.Pet;
import goit.model.User;
import goit.requests.HttpRequests;
import goit.response.HttpResponses;
import goit.view.View;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class Get implements Command {
    private final View view;
    HttpResponses httpResponses;
    EnterFromConsole enterFromConsole;
    HttpRequests httpRequests;
    Answer answer;
    private final Gson MYGSON = new Gson();

    public Get(View view) {
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
            view.write("Please enter what to get:pet by id, pet by status, order by id, order inventory, user by username," +
                    " user login, user logout");
            view.write("If you want to exit to the previous menu, press: exit");
            String read = view.read();
            if (read.equalsIgnoreCase("pet by id")) {
                HttpResponse<String> httpResponse = getWithId("pet", "/pet/");
                answer.answerObject(httpResponse, Pet.class, MYGSON);
            } else if (read.equalsIgnoreCase("pet by status")) {
                view.write("Enter pet status");
                String status = enterFromConsole.readStatusPet();
                HttpRequest request = httpRequests.get("/pet/findByStatus?status=" + status);
                HttpResponse<String> httpResponse = httpResponses.postResponse(request);
                answer.answerStatus(httpResponse, MYGSON);
            } else if (read.equalsIgnoreCase("order by id")) {
                HttpResponse<String> httpResponse = getWithId("order", "/store/order/");
                answer.answerObject(httpResponse, Order.class, MYGSON);
            } else if (read.equalsIgnoreCase("order inventory")) {
                HttpResponse<String> withoutId = getWithoutId("/store/inventory");
                 Map map = MYGSON.fromJson(withoutId.body(), Map.class);
                map.forEach((key, value) -> {
                    System.out.println(key+"  "+value);
                });

            } else if (read.equalsIgnoreCase("user by username")) {
                HttpResponse<String> withName = getWithName("/user/");
                answer.answerObject(withName, User.class, MYGSON);
            } else if (read.equalsIgnoreCase("user login")) {
                HttpResponse<String> httpResponse = logIn("/user/login?username=", "&password=");
                answer.answerApiResponse(httpResponse, ApiResponse.class, MYGSON);
            } else if (read.equalsIgnoreCase("user logout")) {
                 HttpResponse<String> httpResponse = logOut("/user/logout");
                answer.answerApiResponse(httpResponse, ApiResponse.class, MYGSON);
            } else if (read.equalsIgnoreCase("exit")) {
                break;
            } else {
                view.write("You enter wrong command");

            }
        }
    }

    public HttpResponse<String> getWithId(String id, String url) throws IOException, InterruptedException {
        view.write("Enter " + id + " id");
        int i = enterFromConsole.CheckNumber();
        HttpRequest request = httpRequests.get(url + i);
        return httpResponses.postResponse(request);
    }

    public HttpResponse<String> getWithoutId(String url) throws IOException, InterruptedException {
        HttpRequest request = httpRequests.get(url);
        return httpResponses.postResponse(request);
    }

    public HttpResponse<String> getWithName(String url) throws IOException, InterruptedException {
        view.write("Enter username");
        String read = view.read();
        HttpRequest request = httpRequests.get(url + read);
        return httpResponses.postResponse(request);
    }

    public HttpResponse<String> logIn(String userNameUri, String passwordUri) throws IOException, InterruptedException {
        view.write("Enter username");
        String userName = view.read();
        view.write(("Enter password"));
        String password = view.read();
        HttpRequest request = httpRequests.get(userNameUri + userName + passwordUri + password);
        return httpResponses.postResponse(request);
    }

    public HttpResponse<String> logOut(String url) throws IOException, InterruptedException {
        HttpRequest request = httpRequests.get(url);
        return httpResponses.postResponse(request);
    }

    @Override
    public String commandName() {
        return "get";
    }
}
