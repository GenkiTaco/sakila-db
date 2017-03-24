package facade;

import java.util.List;

import javax.ejb.Local;

import model.Film;

/**
 * Created by arturo on 23-03-17.
 */

@Local
public interface FilmFacade {

    public void create(Film entity);

    public void edit(Film entity);

    public void remove(Film entity);

    public Film find(Object id);

    public List<Film> findAll();

    public List<Film> findRange(int[] range);

    public int count();

}
