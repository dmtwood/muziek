package be.vdab.muziek.controllers;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.dto.AlbumArtiest;
import be.vdab.muziek.exceptions.AlbumNietGevondenException;
import be.vdab.muziek.services.AlbumService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;

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
    EntityModel<AlbumArtiest> getAlbum(long id) {
        var optionalAlbum = albumService.findById(id);
        if( optionalAlbum.isPresent() ) {
            var album = optionalAlbum.get();
            var albumArtiest = new AlbumArtiest(album);
            var model = new EntityModel<>(albumArtiest);
            model.add(entityLinks.linkToItemResource(Album.class, id));
            model.add(entityLinks.linkForItemResource(Album.class, id).slash("tracks").withRel("tracks"));
        }
        throw new AlbumNietGevondenException();
    }
}
