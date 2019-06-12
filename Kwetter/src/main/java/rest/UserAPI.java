package rest;

import authentication.Secured;
import domain.*;
import org.springframework.web.bind.annotation.RequestBody;
import service.KweetService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/user")
@Stateless
@Produces(APPLICATION_JSON)
@Secured
public class UserAPI extends Application {

    @Inject
    private UserService userService;
    @Inject
    private KweetService kweetService;

    @GET
    @Path("/findAll")
    public Response findAll(){
        List<User> users = userService.getAllUsers();
        if(users != null){
            return Response.ok(users).build();
        } else {
            return Response.noContent().build();
        }
    }

    @DELETE
    @Path("/delete")
    public Response delete(@QueryParam("email") String email){
        User user = userService.findByEmail(email);
        if(userService.removeUser(user)){
            return Response.ok().entity("User is removed").build();
        } else {
            return Response.status(409).build();
        }
    }

    @POST
    @Path("/findByEmail")
    public Response findByEmail(@FormParam("email") String email){
        User user = userService.findByEmail(email);
        if(user != null){
            user.setKweets(kweetService.getKweetsByEmail(email));
            user.setFollowers(userService.getFollowers(user));
            user.setFollowings(userService.getFollowing(user));
            return Response.ok(user).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Path("/following")
    public Response getFollowing(@QueryParam("email") String email){
        User user = userService.findByEmail(email);
        List<User> followings = userService.getFollowing(user);
        if(followings != null && !followings.isEmpty()){
            return Response.ok().entity(followings).build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/followers")
    public Response getFollowers(@QueryParam("email") String email){
        User user = userService.findByEmail(email);
        List<User> followers =  userService.getFollowers(user);
        if(followers != null && !followers.isEmpty()){
            return Response.ok().entity(followers).build();
        } else {
            return Response.noContent().build();
        }
    }

    @DELETE
    @Path("/unfollow")
    public Response removeFollowing(@FormParam("follower") String emailFollower, @FormParam("following") String emailFollowing){
        if(userService.removeFollowing(userService.getFollowerByEmail(emailFollower), userService.getFollowingByEmail(emailFollowing))){
            return Response.ok().entity("Following is removed").build();
        } else {
            return Response.noContent().build();
        }
    }

    @DELETE
    @Path("/removeFollower")
    public Response removeFollower(@FormParam("follower") String emailFollower, @FormParam("following") String emailFollowing){
        if(userService.removeFollower(userService.getFollowerByEmail(emailFollower), userService.getFollowingByEmail(emailFollowing))){
            return Response.ok().entity("Follower is removed").build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Path("/addFollow")
    public Response addFollower(@FormParam("follower") String emailFollower, @FormParam("following") String emailFollowing){
        User follower = userService.findByEmail(emailFollower);
        User following = userService.findByEmail(emailFollowing);
        if(userService.followUser(follower, following) != null){
            return Response.ok().entity(follower + " is now following " + following).build();
        } else {
            return Response.status(409).build();
        }
    }

    @POST
    @Path("/changeDetails")
    public Response changeDetails(@FormParam("bio") String bio, @FormParam("website") String website, @FormParam("email") String email){
        Details details = new Details(bio, website);
        if(userService.editDetails(email, details) != null){
            return Response.ok().entity(details).build();
        } else {
            return Response.status(409).build();
        }
    }

    @POST
    @Path("/changeLocation")
    public Response changeLocation(@FormParam("country") String country, @FormParam("city") String city, @FormParam("Street") String street, @FormParam("housenumber") String housenumber, @FormParam("email") String email){
        Location location = new Location(country, city, street, housenumber);
        if(userService.editLocation(email, location) != null){
            return Response.ok().entity(location).build();
        } else {
            return Response.status(409).build();
        }
    }

    @POST
    @Consumes({APPLICATION_JSON, APPLICATION_FORM_URLENCODED})
    @Path("/editUser")
    public Response editUser(User user){
        if(userService.editUser(user) != null){
            return Response.ok(user).build();
        } else {
            return Response.status(409).build();
        }
    }

}
