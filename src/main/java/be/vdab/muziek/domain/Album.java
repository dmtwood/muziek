package be.vdab.muziek.domain;

import org.hibernate.mapping.Collection;

import javax.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity @Table( name = "albums")   //@NamedAttributeNode("artiest") )
public class Album {

    @Id @GeneratedValue
    private long id;
    private String naam;

    // many Album EC-objects can belong to one Artiest >> joined on own column 'Album.artiestid'
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn( name = "artiestid")
    private Artiest artiest;

    // value objects Entity Class 'tracks' >> joined on foreign column 'Track.albumid'
    @ElementCollection @CollectionTable( name = "tracks", joinColumns = @JoinColumn( name = "albumid") )
    private Set<Track> tracks = new LinkedHashSet<>();



    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Artiest getArtiest() {
        return artiest;
    }

    public Set<Track> getTracks(){
        return Collections.unmodifiableSet(tracks);
    }

    // (Album) objects are stored in a Set (attribute of Artiest objects) >> override hash & equals

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;
        Album album = (Album) o;
        return
                naam.equalsIgnoreCase(album.naam) && artiest.equals(album.artiest) ;

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naam, artiest, tracks);
    }
}
