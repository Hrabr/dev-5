package goit.requests;

import com.google.gson.Gson;
import goit.model.Pet;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

public class HttpRequests<T> {

    private final String MYURI = "https://petstore.swagger.io/v2";

    public HttpRequest post(T type, String string,Gson gson) {
        String toJson = gson.toJson(type);
        return HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(toJson))
                .uri(URI.create(MYURI + string))
                .header("Content-type", "application/json")
                .build();
    }
    public HttpRequest postWithArray(List<T> list, String string,Gson gson) {

        return HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(list)))
                .uri(URI.create(MYURI + string))
                .header("Content-type", "application/json")
                .build();
    }
    public HttpRequest get(String string) {
        return HttpRequest.newBuilder()
                .uri(URI.create(MYURI+string))
                .header("Content-type", "application/json")
                .GET()
                .build();

    }
   public HttpRequest delete(String string){
       return  HttpRequest.newBuilder()
               .uri(URI.create(MYURI+string))
               .header("Content-type", "application/json")
               .DELETE()
               .build();
   }
    public  HttpPost prepareUploadAnImageForPet(String endpoint, String additionalMetaData, String filePath) throws FileNotFoundException {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        StringBody stringBody = new StringBody(additionalMetaData, ContentType.MULTIPART_FORM_DATA);
        HttpPost uploadFileRequest = new HttpPost(URI.create(MYURI + endpoint));
        File file = new File(filePath);
        builder.addPart("additionalMetadata", stringBody);
        builder.addBinaryBody("file",
                file,
                ContentType.MULTIPART_FORM_DATA,
                file.getName());
        uploadFileRequest.setEntity(builder.build());
        return uploadFileRequest;
    }
    public  HttpPost updatePet( Pet pet,String name,String status)  {

        List<NameValuePair> form = new ArrayList<>();
        form.add(new BasicNameValuePair("name", name));
        form.add(new BasicNameValuePair("status", status));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, Consts.UTF_8);
        HttpPost post = new HttpPost(URI.create(MYURI + "/pet/"+pet.getId()));
        post.setEntity(entity);
      return post;
    }
    public HttpRequest put(T type, String string,Gson gson) {
        String toJson = gson.toJson(type);
        return HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(toJson))
                .uri(URI.create(MYURI + string))
                .header("Content-type", "application/json")
                .build();
    }
}
