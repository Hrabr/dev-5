package goit.command.pet;

import com.google.gson.Gson;
import goit.command.Command;
import goit.command.EnterFromConsole;
import goit.command.answer.Answer;
import goit.model.ApiResponse;
import goit.model.Pet;
import goit.model.User;
import goit.requests.HttpRequests;
import goit.response.HttpResponses;
import goit.view.View;
import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Update implements Command {
    private final View view;
    HttpResponses httpResponses;
    EnterFromConsole enterFromConsole;
    HttpRequests httpRequests;
    Answer answer;
    Get get;
    private final Gson MYGSON = new Gson();

    public Update(View view) {
        this.view = view;
        enterFromConsole = new EnterFromConsole(view);
        httpRequests = new HttpRequests();
        httpResponses = new HttpResponses();
        answer = new Answer();
        get = new Get(view);
    }
    @SneakyThrows
    @Override
    public void process() {
        while (true) {
            view.write("Please enter what to update:pet, user ");
            view.write("If you want to exit to the previous menu, press: exit");
            String read = view.read();
            if (read.equalsIgnoreCase("pet")) {
                updatePet();
            }if (read.equalsIgnoreCase("user")) {
                updateUser("/user/");
            } else if (read.equalsIgnoreCase("exit")) {
                break;
            } else {
                view.write("You enter wrong command");

            }
        }
    }

    @Override
    public String commandName() {
        return "update";
    }

    public void updatePet() throws IOException, InterruptedException {
        CloseableHttpClient DEFAULT_CLIENT = HttpClients.createDefault();
        Pet pet;
        HttpResponse<String> httpResponse = get.getWithId("pet", "/pet/");
        if (httpResponse.statusCode() == 200) {
            pet = MYGSON.fromJson(httpResponse.body(), Pet.class);
            System.out.println(pet);
        } else {
            System.out.println("Pet not found ");
            return;
        }
        view.write("Enter pet new name");
        String newName = view.read();
        String s = enterFromConsole.readStatusPet();
        HttpPost httpPost = httpRequests.updatePet(pet, newName, s);
        org.apache.http.HttpResponse response = DEFAULT_CLIENT.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();
        String sResponse = EntityUtils.toString(responseEntity, "UTF-8");
        System.out.println("Successfully \n" + sResponse);
    }

    public void updateUser(String url) throws IOException, InterruptedException {
        User user = new User();
        view.write("Enter username");
        String read = view.read();
        HttpRequest request = httpRequests.get(url + read);
        HttpResponse<String> httpResponse = httpResponses.postResponse(request);
        if (httpResponse.statusCode() == 200) {
            User user1 = enterFromConsole.enterUserFromConsole(user);
            HttpRequest put = httpRequests.put(user1, url+read, MYGSON);
            HttpResponse<String> httpResponse1 = httpResponses.postResponse(put);
            answer.answerApiResponse(httpResponse1,ApiResponse.class, MYGSON);
        } else {
            view.write("This user with this username not found");
        }
    }
}
