package rest;

import authentication.RandomToken;
import domain.Token;
import domain.User;
import service.KweetService;
import service.UserService;
import utils.AuthenticationUtils;
import utils.EmailSender;
import utils.LoginResponse;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/auth")
@Consumes({APPLICATION_JSON, APPLICATION_FORM_URLENCODED})
public class AuthAPI extends Application {

    @Context
    private UriInfo uriInfo;

    @Inject
    private UserService userService;

    @Inject
    private KweetService kweetService;

    @Inject
    private EmailSender emailSender;

    @POST
    @Path("token")
    @Produces(APPLICATION_JSON)
    public Response authorize(@FormParam("email") String email, @FormParam("password") String password){

        try {
            User user = authenticate(email, password);

            String token = getToken();

            userService.addToken(new Token(token, LocalDateTime.now().plusMinutes(60)));

            return Response.ok(new LoginResponse(token, user)).build();
        } catch (Exception e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }

    private User authenticate(String email, String password){
        User user = userService.login(email, AuthenticationUtils.encodeSHA256(password));

        if(user == null){
            throw new SecurityException("Invalid email or password");
        }
        user.setKweets(kweetService.getKweetsByEmail(email));
        user.setFollowers(userService.getFollowers(user));
        user.setFollowings(userService.getFollowing(user));
        return user;
    }

    private String getToken(){
        RandomToken token = new RandomToken();
        return token.nextString();
    }

    @POST
    @Path("/createUser")
    public Response save(User user){
        if(userService.createUser(user) != null){
//            try {
//                emailSender.sendMail(user);
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
            return Response.ok().entity(user).build();
        } else {
            return Response.status(409).build();
        }
    }
}
