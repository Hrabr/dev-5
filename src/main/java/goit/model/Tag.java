package goit.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tag {
    private long id;
    private String name;

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
