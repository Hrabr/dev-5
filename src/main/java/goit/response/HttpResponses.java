package goit.response;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class HttpResponses {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    public HttpResponse<String> postResponse(HttpRequest httpRequests) throws IOException, InterruptedException {
        HttpResponse<String> send = CLIENT.send(httpRequests, HttpResponse.BodyHandlers.ofString());
        return send;
    }
}
