package be.vdab.muziek.domain;

import org.hibernate.mapping.Collection;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity @Table( name = "albums" )
public class Album {
    @Id @GeneratedValue
    private long id;
    private String naam;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn( name = "artiestid")
    private Artiest artiest;

    @ElementCollection
    @CollectionTable(
            name = "tracks",
            joinColumns = @JoinColumn( name = "albumid")
    ) private Set<Track> getTracks(){
        return Collections.unmodifiableSet(tracks);
    }

}
