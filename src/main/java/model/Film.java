package model;

import model.data.FilmData;
import java.io.Serializable;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


/**
 * Created by arturo on 23-03-17.
 */

@Entity
@Table(name="film")
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film implements Serializable {

    @Id
    @Column(name="film_id", unique=true, nullable=false)
    private int filmId;

    @Column(name="title", nullable=false, length=255)
    private String title;

    @Column(name="description", nullable=true)
    private String description;

    @Column(name="release_year", nullable=true)
    private Date releaseYear;

    @Column(name="language_id", nullable=false)
    private int languageId;

    @Column(name="original_language_id", nullable=true)
    private int originalLanguageId;

    @Column(name="rental_duration", nullable=false)
    private int rentalDuration;

    @Column(name="rental_rate", nullable=false)
    private float rentalRate;

    @Column(name="length", nullable=true)
    private int length;

    @Column(name="replacement_cost", nullable=false)
    private float replacementCost;

    @Column(name="rating", nullable=true)
    private String rating;

    @Column(name="special_features", nullable=true)
    private String specialFeatures;

    @Column(name="last_update", nullable=false)
    private Timestamp lastUpdate;

    @ManyToMany
    @JoinTable(
            name="film_actor",
            joinColumns=@JoinColumn(name="film_id", referencedColumnName="film_id"),
            inverseJoinColumns=@JoinColumn(name="actor_id", referencedColumnName="actor_id")
    )
    private List<Actor> actors;

    public Film() {

    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public int getOriginalLanguageId() {
        return originalLanguageId;
    }

    public void setOriginalLanguageId(int originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
    }

    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public float getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(float rentalRate) {
        this.rentalRate = rentalRate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public float getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(float replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public void addActor(Actor actor) {
        this.actors.add(actor);
    }

    public FilmData makeRedeable() {

        FilmData f = new FilmData();

        f.setFilmId(this.getFilmId());

        f.setTitle(this.getTitle());

        f.setDescription(this.getDescription());

        f.setReleaseYear(this.getReleaseYear());

        f.setLanguageId(this.getLanguageId());

        f.setOriginalLanguageId(this.getOriginalLanguageId());

        f.setRentalDuration(this.getRentalDuration());
        f.setRentalRate(this.getRentalRate());

        f.setLength(this.getLength());

        f.setReplacementCost(this.getReplacementCost());

        f.setRating(this.getRating());

        f.setSpecialFeatures(this.getSpecialFeatures());

        f.setLastUpdate(this.getLastUpdate());

        return f;
    }

}
