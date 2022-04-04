package goit.command.pet;

import goit.command.Command;
import goit.command.EnterFromConsole;
import goit.requests.HttpRequests;
import goit.view.View;
import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.HttpClient;

public class Upload  implements Command {
    View view;
    HttpClient httpClient;
    HttpRequests httpRequests;
    EnterFromConsole enterFromConsole;

    public Upload(View view) {
        this.view = view;
        httpClient = HttpClient.newBuilder().build();
        enterFromConsole = new EnterFromConsole(view);
        httpRequests = new HttpRequests();

    }
    @SneakyThrows
    @Override
    public void process() {
        view.write("Enter id of pet, which should be updated");
        view.write("If you want to exit to the previous menu, press: exit");
        int number = enterFromConsole.CheckNumber();
        view.write("Enter additionalData:");
        String tag = view.read();
        view.write("Enter image path:");
        String imagePath = view.read();
        uploadImage(number,tag,imagePath);
    }

    @Override
    public String commandName() {
        return "upload";
    }

    public void uploadImage(int id, String additionalData, String imagePath) {
        CloseableHttpClient DEFAULT_CLIENT = HttpClients.createDefault();
        String endpoint = "/pet" + "/" + id + "/uploadImage";
        try {
            HttpResponse response =  DEFAULT_CLIENT.execute(httpRequests.prepareUploadAnImageForPet(endpoint,
                    additionalData, imagePath));
            HttpEntity responseEntity = response.getEntity();
            String sResponse = EntityUtils.toString(responseEntity, "UTF-8");
            System.out.println("Successfully \n"+sResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}