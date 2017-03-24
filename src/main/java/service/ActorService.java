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
import facade.FilmFacade;
import jdk.nashorn.internal.objects.annotations.Getter;
import model.Actor;
import model.data.ActorData;

import model.Film;
import model.data.FilmData;

@Path("/actors")
public class ActorService {
	
	@EJB 
	ActorFacade actorFacadeEJB;

	@EJB
	FilmFacade filmFacadeEJB;
	
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

    @POST
    @Path("{actor_id}/films/{film_id}")
    @Consumes({"application/xml", "application/json"})
    public void addActor(@PathParam("actor_id") Integer actor_id, @PathParam("film_id") Integer film_id) {

        Actor actor = actorFacadeEJB.find(actor_id);

        List<Film> actorFilms = actor.getFilms();
        boolean notFound = true;
        int size = actorFilms.size();
        for(int i = 0; i < size; i++) {
            if (actorFilms.get(i).getFilmId() == film_id) {
                notFound = false;
                break;
            }
        }

        if(notFound) {
            Film film = filmFacadeEJB.find(film_id);
            film.addActor(actor);
            filmFacadeEJB.edit(film);
        }


    }
	

}
