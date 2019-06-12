package rest;

import authentication.Secured;
import domain.Kweet;
import service.KweetService;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/kweet")
@Consumes({APPLICATION_JSON, APPLICATION_FORM_URLENCODED})
@Secured
public class KweetAPI extends Application {

    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;

    @GET
    @Path("/findAll")
    public Response findAll(){
        List<Kweet> kweets = kweetService.getAllKweets();

        if(kweets != null && !kweets.isEmpty()){
            return Response.ok().entity(kweets).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Path("/save")
    public Response save(@FormParam("content") String content, @FormParam("email") String email ){
        Kweet kweet = new Kweet(LocalDateTime.now(), content, email);
        if(kweetService.createKweet(kweet) != null){
            return Response.ok().entity(kweet).build();
        } else {
            return Response.status(409).build();
        }
    }

    @GET
    @Path("/findById/{id}")
    public Response findOneById(@PathParam("id") long id){
        Kweet kweet = kweetService.getKweetById(id);

        if(kweet != null ){
            return Response.ok().entity(kweet).build();
        } else {
            return Response.noContent().build();
        }
    }

    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") long id){
        Kweet kweet = kweetService.getKweetById(id);
        if(kweetService.removeKweet(kweet)){
            return Response.ok().entity("Kweet is removed.").build();
        } else {
            return Response.status(409).build();
        }
    }

    @POST
    @Path("findByEmail")
    public Response findByEmail(@FormParam("email") String email){
        List<Kweet> kweets = kweetService.getKweetsByEmail(email);
        if(kweets != null && !kweets.isEmpty()){
            return Response.ok(kweets).build();
        } else {
            return Response.noContent().build();
        }
    }
}