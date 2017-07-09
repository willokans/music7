package model;

import java.io.Serializable;

/**
 * Created by willokans on 09/07/2017.
 */

public class Event implements Serializable{

    /**This will hold an instance of an event
     *
     * We will implement Serializable because we want to be able to freez the state of
     * this event and pass it to an instance
     */


    private static final long id = 1L;

    /**each event needs a title, artist and headLiner, venue, city, country,
     * street, postalcode, url, website, venue image, band Image, startData, Description, musicGenre
     */
    private String title;
    private String artist;
    private String headLiner;
    private String venueName;
    private String city;
    private String country;
    private String street;
    private String postalCode;
    private String url;
    private String website;
    private String venueImage;
    private String bandImage;
    private String startData;
    private String description;
    private String musicGenre;


    //create my getters and setters
    public static long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getHeadLiner() {
        return headLiner;
    }

    public void setHeadLiner(String headLiner) {
        this.headLiner = headLiner;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getVenueImage() {
        return venueImage;
    }

    public void setVenueImage(String venueImage) {
        this.venueImage = venueImage;
    }

    public String getBandImage() {
        return bandImage;
    }

    public void setBandImage(String bandImage) {
        this.bandImage = bandImage;
    }

    public String getStartData() {
        return startData;
    }

    public void setStartData(String startData) {
        this.startData = startData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }
}
