package club.lazyzzz.covid.service;

import club.lazyzzz.covid.model.entity.User;

public interface UserService {
    /**
     * User Login Interface
     *
     * @param username username or email address of the account
     * @param plainPassword password of plain text
     * @return access token of the account
     */
    String login(String username, String plainPassword);

    /**
     * User Registration Interface
     * @param user User Object
     */
    void register(User user);
}
