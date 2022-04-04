package goit.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse {
private Integer code;
private String type;
private String message;

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
