package service;

import model.User;

public class LoginServiceImpl implements LoginService{

    private UserService service;

    public LoginServiceImpl(UserService service) {
        this.service = service;
    }

    @Override
    public void save(User user) {
        service.save(user);
    }

    @Override
    public boolean isAuth(String username, String password) {
        return false;
    }

    @Override
    public void logout(String username) {

    }
}
