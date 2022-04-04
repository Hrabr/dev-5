package goit.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Category {
    private long id;
    private String name;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
