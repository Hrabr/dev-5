package goit.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Order {
  private   long id;
private  long petId;
private int quantity;
private String shipDate;
private String status;
private boolean complete;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", petId=" + petId +
                ", quantity=" + quantity +
                ", shipDate='" + shipDate + '\'' +
                ", status=" + status +
                ", complete=" + complete +
                '}'+"\n";
    }
}
