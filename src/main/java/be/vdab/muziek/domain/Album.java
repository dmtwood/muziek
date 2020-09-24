package be.vdab.muziek.domain;

import javax.persistence.*;

@Entity
@Table( name = "albums" )
public class Album {
    @Id @GeneratedValue
    private long id;
    private String naam;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Artiest artiest;

}
