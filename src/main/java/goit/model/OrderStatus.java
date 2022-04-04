package goit.model;

public enum OrderStatus {
    PLACED("placed"), APPROVED("approved"), DELIVERED("delivered");
    private String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
