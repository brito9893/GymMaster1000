package model.registry;

import model.User;

import java.util.*;

/**
 * Created by vitor on 04/07/2017.
 */
public class UserRegistry {
    private List<User> userRegistry = new ArrayList<>(Collections.singletonList(new User("Vítor Brito", "vitorbrito98@outlook.pt", "brito", "123", "4980-605", new Date(19980912), "Alameda 25 de Abril 301 RC/D")));
    private List<User> nonConfirmedUserRegistry = new ArrayList<>();

    public List getUserRegistry() {
        return userRegistry;
    }

    public List getNonConfirmedUserRegistry() {
        return nonConfirmedUserRegistry;
    }

    public void addNonConfirmedUser(User u) {
        nonConfirmedUserRegistry.add(u);
    }

    public void confirmUser(User u) {
        if (nonConfirmedUserRegistry.indexOf(u) != -1) {
            nonConfirmedUserRegistry.remove(u);
            confirmUser(u);
        }
    }

    public boolean getUser(String username, String password) {
        for (User u : userRegistry) {
            if (Objects.equals(u.getUsername().toLowerCase(), username.toLowerCase()) && Objects.equals(u.getPassword(), password)) {
                return true;
            }
        }
        return false;
    }
}
