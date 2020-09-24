package be.vdab.muziek.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Table( name = "artiest")
public class Artiest {
    @Id @GeneratedValue
    private long id;
    private String naam;

    protected Artiest() {
    }
    public Artiest( String naam) {
        this.naam = naam;
    }
}
