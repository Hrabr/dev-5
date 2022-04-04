package goit.model;

public enum PetStatus {
    AVAILABLE("available"), PENDING("pending"), SOLD("sold");
    private String name;

    PetStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
