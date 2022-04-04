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
import java.util.ArrayList;
import java.util.List;


public class Create implements Command {
    private final View view;
    HttpResponses httpResponses;
    EnterFromConsole enterFromConsole;
    HttpRequests httpRequests;
    Answer answer;
    private Pet pet = new Pet();
    private Order order = new Order();
    private User user = new User();
    Gson MYGSON = new Gson();


    public Create(View view) {

        this.view = view;
        enterFromConsole = new EnterFromConsole(view);
        httpRequests = new HttpRequests();
        httpResponses = new HttpResponses();
        answer = new Answer();
    }

    @Override
    @SneakyThrows  public void process(){

        while (true) {
            view.write("Please enter what to create: pet, order, user,user with Array ");
            view.write("If you want to exit to the previous menu, press: exit");
            String read = view.read();
            if (read.equalsIgnoreCase("pet")) {
                HttpResponse<String> httpResponse = createPet();
                answer.answerObject(httpResponse, Pet.class, MYGSON);
            } else if (read.equalsIgnoreCase("order")) {
                HttpResponse<String> httpResponse = createOrder();
                answer.answerObject(httpResponse, Order.class, MYGSON);
            } else if (read.equalsIgnoreCase("user")) {
                HttpResponse<String> user = createUser();
                answer.answerApiResponse(user, ApiResponse.class, MYGSON);
            } else if (read.equalsIgnoreCase("user with Array")) {
                 HttpResponse<String> userWithArray = createUserWithArray();
                answer.answerApiResponse(userWithArray, ApiResponse.class, MYGSON);
            } else if (read.equalsIgnoreCase("exit")) {
                break;
            } else {
                view.write("You enter wrong command");

            }
        }
    }

    @Override
    public String commandName() {
        return "create";
    }

    public HttpResponse<String> createPet() throws IOException, InterruptedException {
        Pet pet1 = enterFromConsole.enterPetFromConsole(pet);
        HttpRequest post = httpRequests.post(pet1, "/pet", MYGSON);
        return httpResponses.postResponse(post);
    }

    public HttpResponse<String> createOrder() throws IOException, InterruptedException {
        Order order1 = enterFromConsole.enterOrderFromConsole(order);
        HttpRequest post = httpRequests.post(order1, "/store/order", MYGSON);
        return httpResponses.postResponse(post);
    }

    public HttpResponse<String> createUser() throws IOException, InterruptedException {
        User user1 = enterFromConsole.enterUserFromConsole(user);
        HttpRequest post = httpRequests.post(user1, "/user", MYGSON);
        return httpResponses.postResponse(post);
    }

    public HttpResponse<String> createUserWithArray() throws IOException, InterruptedException {
        List<User> list = new ArrayList<>();
        while (true) {
            User user3 = new User();
            User user1 = enterFromConsole.enterUserFromConsole(user3);
            list.add(user1);
            view.write("Successfully added. Press 'enter' to add another user\nEnter 'exit' when finish");
            String read1;
            while (true) {
                read1 = view.read();
                if (read1.equals("exit") || read1.equals("enter")) {
                    break;
                } else {
                    view.write("You enter wrong command");
                }
            }
            if (read1.equalsIgnoreCase("exit")) {
                break;
            }
        }
        HttpRequest request = httpRequests.postWithArray(list, "/user/createWithList", MYGSON);
        return httpResponses.postResponse(request);
    }
}
