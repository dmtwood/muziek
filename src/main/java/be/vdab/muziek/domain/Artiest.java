package be.vdab.muziek.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity @Table( name = "artiest")
public class Artiest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;

    @OneToMany ( mappedBy = "tracks")
    private Set<Album> albumSet;

    protected Artiest() {
    }
    public Artiest( String naam) {
        this.naam = naam;
        this.albumSet = new LinkedHashSet<>();
    }


    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Set<Album> getAlbumSet() {
        return Collections.unmodifiableSet(albumSet);
    }
}
