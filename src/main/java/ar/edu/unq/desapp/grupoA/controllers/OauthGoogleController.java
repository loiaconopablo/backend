package ar.edu.unq.desapp.grupoA.controllers;

import ar.edu.unq.desapp.grupoA.controllers.requests.UserAuthorization;
import ar.edu.unq.desapp.grupoA.controllers.responses.UserTokenResponse;
import ar.edu.unq.desapp.grupoA.models.oauth.GoogleOauthCredential;
import ar.edu.unq.desapp.grupoA.services.GoogleCredentialsService;
import ar.edu.unq.desapp.grupoA.services.Login;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.oauth2.model.Userinfoplus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("oauth")
@Controller("oauthGoogleController")
public class OauthGoogleController {

    private Login login;
    private GoogleCredentialsService googleCredentialsService;

    @POST
    @Path("google")
    @Consumes("application/json")
    @Produces("application/json")
    public UserTokenResponse loginUser(UserAuthorization userAuthorization) {
        Credential credential = this.getGoogleCredentialsService().create(userAuthorization.getAuthorizationCode());
        Userinfoplus userinfoplus = this.getGoogleCredentialsService().getUserinfo(credential);
        GoogleOauthCredential googleOauthCredential = this.getGoogleCredentialsService().get(userinfoplus.getId());
        this.getLogin().signUpWithCredentials(userinfoplus, googleOauthCredential);
        return new UserTokenResponse(googleOauthCredential.getGoogleAccessToken());
    }

    public Login getLogin() {
        return login;
    }

    @Autowired
    public void setLogin(Login login) {
        this.login = login;
    }

    public GoogleCredentialsService getGoogleCredentialsService() {
        return googleCredentialsService;
    }

    @Autowired
    public void setGoogleCredentialsService(GoogleCredentialsService googleCredentialsService) {
        this.googleCredentialsService = googleCredentialsService;
    }
}
