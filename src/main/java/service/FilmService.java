package service;

/**
 * Created by arturo on 23-03-17.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import facade.FilmFacade;
import jdk.nashorn.internal.objects.annotations.Getter;
import model.Actor;
import model.Film;
import model.data.FilmData;
import model.data.ActorData;

@Path("/films")
public class FilmService {

    @EJB
    FilmFacade filmFacadeEJB;

    Logger logger = Logger.getLogger(FilmService.class.getName());

    @GET
    @Produces({"application/xml", "application/json"})
    public List<FilmData> findAll(){
        logger.log(Level.INFO, "GET films");
        List<FilmData> films = new ArrayList<FilmData>();

        try {
            List<Film> filmsAux = filmFacadeEJB.findAll();


            for(int i = 0; i < filmsAux.size(); i++) {
                films.add(filmsAux.get(i).makeRedeable());
            }

        } catch(Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return films;
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public FilmData find(@PathParam("id") Integer id) {
        return filmFacadeEJB.find(id).makeRedeable();
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Film entity) {
        filmFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Film entity) {
        entity.setFilmId(id.intValue());
        filmFacadeEJB.edit(entity);
    }


    @GET
    @Path("{id}/actors")
    @Consumes({"application/xml", "application/json"})
    public List<ActorData> findActors(@PathParam("id") Integer id) {

        List<ActorData> actors = new ArrayList<ActorData>();

        List<Actor> actorsAux = filmFacadeEJB.find(id).getActors();
        int size = actorsAux.size();
        for(int i = 0; i < size; i++) {
            actors.add(actorsAux.get(i).makeRedeable());
        }

        return actors;

    }


    @GET
    @Path("/test")
    @Produces("text/plain")
    public String getClichedMessage() {
        logger.log(Level.INFO, "Done this correctly");
        return "Hello World";
    }
}
