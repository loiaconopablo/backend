package ar.edu.unq.desapp.grupoA.services;

import ar.edu.unq.desapp.grupoA.models.UserModel;
import ar.edu.unq.desapp.grupoA.models.UserToken;
import ar.edu.unq.desapp.grupoA.repositories.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userTokenService")
public class UserTokenService {

    private UserTokenRepository userTokenRepository;

    @Transactional
    public UserToken create(UserModel userModel){
        UserToken token = new UserToken();
        token.setUserModel(userModel);
        token.generateToken();
        this.getUserTokenRepository().save(token);
        return token;
    }

    public UserTokenRepository getUserTokenRepository() {
        return userTokenRepository;
    }

    @Autowired
    public void setUserTokenRepository(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }
}
