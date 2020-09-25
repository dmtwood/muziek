package be.vdab.muziek.services;

import be.vdab.muziek.domain.Album;

import java.util.Optional;

public interface AlbumService {
    Optional<Album> findById(long id);
}
