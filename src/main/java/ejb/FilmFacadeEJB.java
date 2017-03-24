package ejb;

/**
 * Created by arturo on 23-03-17.
 */

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.FilmFacade;
import model.Film;

@Stateless
public class FilmFacadeEJB extends AbstractFacade<Film> implements FilmFacade {

    @PersistenceContext(unitName = "sakilaPU")
    private EntityManager em;

    public FilmFacadeEJB() {
        super(Film.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
}
