package service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import facade.ActorFacade;
import jdk.nashorn.internal.objects.annotations.Getter;
import model.Actor;
import model.data.ActorData;

import model.Film;
import model.data.FilmData;

@Path("/actors")
public class ActorService {
	
	@EJB 
	ActorFacade actorFacadeEJB;
	
	Logger logger = Logger.getLogger(ActorService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<ActorData> findAll(){

        logger.log(Level.INFO, "GET films");
        List<ActorData> actors = new ArrayList<ActorData>();

        try {
            List<Actor> actorsAux = actorFacadeEJB.findAll();


            for(int i = 0; i < actorsAux.size(); i++) {
                actors.add(actorsAux.get(i).makeRedeable());
            }

        } catch(Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return actors;

	}
	
	@GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public ActorData find(@PathParam("id") Integer id) {
        return actorFacadeEJB.find(id).makeRedeable();
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(Actor entity) {
        actorFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Actor entity) {
    	entity.setActorId(id.intValue());
        actorFacadeEJB.edit(entity);
    }

    @GET
    @Path("{id}/films")
    @Consumes({"application/xml", "application/json"})
    public List<FilmData> findFilms(@PathParam("id") Integer id) {

	    List<FilmData> films = new ArrayList<FilmData>();

	    List<Film> filmsAux = actorFacadeEJB.find(id).getFilms();
        int size = filmsAux.size();
        for(int i = 0; i < size; i++) {
            films.add(filmsAux.get(i).makeRedeable());
        }

        return films;
    }
	

}
