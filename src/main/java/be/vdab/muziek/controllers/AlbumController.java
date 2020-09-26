package be.vdab.muziek.controllers;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.domain.Track;
import be.vdab.muziek.dto.AlbumArtiest;
import be.vdab.muziek.exceptions.AlbumNietGevondenException;
import be.vdab.muziek.services.AlbumService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.Set;

// for handling requests with a Response Body (JSON/XML)
@RestController
@RequestMapping("albums")
@ExposesResourceFor(Album.class)
class AlbumController {
    private final AlbumService albumService;
    private final EntityLinks entityLinks;

    public AlbumController(AlbumService albumService, EntityLinks entityLinks) {
        this.albumService = albumService;
        this.entityLinks = entityLinks;
    }

    @GetMapping("{id}")
    EntityModel<AlbumArtiest> getAlbum(@PathVariable long id) {

        var optionalAlbum = albumService.findById(id);

        if( optionalAlbum.isPresent() ) {

//            var album = optionalAlbum.get();
            var albumArtiest = new AlbumArtiest( optionalAlbum.get() );
            var entityModel = EntityModel.of(albumArtiest); // new EntityModel<>(albumArtiest);

            entityModel.add(
                    entityLinks.linkToItemResource(Album.class, id)
            );
            entityModel.add(
                    entityLinks.linkForItemResource(Album.class, id)
                    .slash("tracks")
                    .withRel("tracks")
            );
            return entityModel;
        }
        throw new AlbumNietGevondenException();
    }

    @GetMapping("{id}/tracks")
    Set<Track> getTracks(@PathVariable long id){
        var optionalAlbum = albumService.findById(id);
        if ( optionalAlbum.isPresent() ) {
            return optionalAlbum.get().getTracks();
        }
        throw new AlbumNietGevondenException();
    }
    @ExceptionHandler(AlbumNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void albumNietGevonden(){
    }
}
