package be.vdab.muziek.domain;

import javax.persistence.*;
import java.time.LocalTime;

// value object  embedded by albums (when albums aborts, tracks may vanish too)
@Embeddable @Access(AccessType.FIELD)
public class Track {
    private String naam;
    private LocalTime tijd;

}
