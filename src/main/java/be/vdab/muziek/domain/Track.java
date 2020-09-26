package be.vdab.muziek.domain;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Objects;

// value object embedded by Entity Class Album (when Album object is deleted, the set of Track objects may vanish too)
@Embeddable
// JPA annotations will be placed before member vars (not before getters) > convention
@Access(AccessType.FIELD)
public class Track {

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn( name = "albumid")
//    private Album;

    private String naam;
    private LocalTime tijd;


    public String getNaam() {
        return naam;
    }

    public LocalTime getTijd() {
        return tijd;
    }

    // value objects need protected constructor
    protected Track() {
    }

    public Track(String naam, LocalTime tijd) {
        this.naam = naam;
        this.tijd = tijd;
    }

    // (Track) objects are stored in a Set (attribute of Album objects) >> override hash & equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Track)) return false;
        Track track = (Track) o;
        return naam.equalsIgnoreCase(track.naam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam);
    }
}
