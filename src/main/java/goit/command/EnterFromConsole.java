package goit.command;

import goit.model.*;
import goit.view.View;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class EnterFromConsole {
    private View view;
    private Category category = new Category();

    public EnterFromConsole(View view) {
        this.view = view;
    }

    public Pet enterPetFromConsole(Pet pet) {
        view.write("Enter id pet");
        pet.setId(CheckNumber());
        pet.setCategory(enterCategoryFromConsole());
        view.write("Enter name pet");
        pet.setName(view.read());
        pet.setPhotoUrls(readPhotoUrlsFromConsole());
        pet.setTags(readTag());
        pet.setStatus(readStatusPet());
        return pet;
    }

    public Order enterOrderFromConsole(Order order) {
        view.write("Enter id of Order");
        order.setId(CheckNumber());
        view.write("Enter petId of Order");
        order.setPetId(CheckNumber());
        view.write("Enter quality of Order");
        order.setQuantity(CheckNumber());
        order.setShipDate(String.valueOf(LocalDateTime.now()));
        view.write("Enter status of Order");
        order.setStatus(readStatusOrder());
        view.write("Enter complete of Order");
        order.setComplete(readCompleteFromConsole());
        return order;
    }

    public User enterUserFromConsole(User user) {
        view.write("Enter id user");
        user.setId(CheckNumber());
        view.write("Enter username");
        user.setUsername(view.read());
        view.write("Enter firstname");
        user.setFirstName(view.read());
        view.write("Enter lastname");
        user.setLastName(view.read());
        view.write("Enter email");
        user.setEmail(view.read());
        view.write("Enter password");
        user.setPassword(view.read());
        view.write("Enter phone");
        user.setPhone(view.read());
        view.write("Enter userStatus");
        user.setUserStatus(CheckNumber());
        return user;
    }

    public Category enterCategoryFromConsole() {
        view.write("Enter id Category");
        category.setId(CheckNumber());
        view.write("Enter name Category");
        category.setName(view.read());
        return category;
    }

    public List<Tag> readTag() {
        List<Tag> list = new ArrayList<>();
        while (true) {
            Tag tag = new Tag();
            view.write("Enter id of Tag");
            tag.setId(CheckNumber());
            view.write("Enter name of Tag");
            String read = view.read();
            tag.setName(read);
            list.add(tag);
            view.write("Successfully added. Press 'enter' to add another photo url\nEnter 'exit' when finish");
            String read1;
            while (true) {
                read1 = view.read();
                if (read1.equals("exit") || read1.equals("enter")) {
                    break;
                } else {
                    view.write("You enter wrong command");
                }
            }
            if (read1.equalsIgnoreCase("exit")) {
                break;
            }

        }
        return list;
    }

    public boolean readCompleteFromConsole() {
        boolean complete;
        String string;
        while (true) {

            view.write("Enter complete: true, false ");
            string = view.read();
            if (string.equals("true") || string.equals("false")) {
                complete = Boolean.parseBoolean(string);
                break;
            } else {
                System.out.println("You enter wrong complete");
            }
        }
        return complete;
    }

    public String readStatusPet() {

        String status;
        while (true) {
            view.write("Please enter status: available, pending, sold");
            String read = view.read();
            if (read.equals(PetStatus.AVAILABLE.getName()) || read.equals(PetStatus.PENDING.getName()) || read.equals(PetStatus.SOLD.getName())) {
                status = read;
                break;
            } else {
                view.write("Wrong status");
            }
        }
        return status;
    }

    public String readStatusOrder() {

        String status;
        while (true) {
            view.write("Please enter status: placed, approved, delivered");
            String read = view.read();
            if (read.equals(OrderStatus.PLACED.getName()) || read.equals(OrderStatus.APPROVED.getName()) || read.equals(OrderStatus.DELIVERED.getName())) {
                status = read;
                break;
            } else {
                view.write("Wrong status");
            }
        }
        return status;
    }

    public List<String> readPhotoUrlsFromConsole() {
        List<String> list = new ArrayList<>();
        while (true) {
            view.write("Enter Photo Urls");
            String read = view.read();
            list.add(read);

            view.write("Successfully added. Press 'enter' to add another photo url\nEnter 'exit' when finish");
            String read1 = "";
            while (true) {
                read1 = view.read();
                if (read1.equals("exit") || read1.equals("enter")) {
                    break;
                } else {
                    view.write("You enter wrong command");
                }
            }
            if (read1.equalsIgnoreCase("exit")) {
                break;
            }
        }
        return list;
    }

    public int CheckNumber() {

        int number;
        while (true) {
            try {
                number = Math.abs(Integer.parseInt(view.read()));
                break;
            } catch (Exception e) {
                view.write("Please enter positive number");
            }
        }
        return number;
    }
}
