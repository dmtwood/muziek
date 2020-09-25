package be.vdab.muziek.services;

import be.vdab.muziek.domain.Album;
import be.vdab.muziek.repositories.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service @Transactional class DefaultAlbumService implements AlbumService {

    private final AlbumRepository albumRepository;


    public DefaultAlbumService(AlbumRepository a) {
        this.albumRepository=a;
    }


    @Override
    @Transactional( readOnly = true )
    public Optional<Album> findById(long id) {
        return albumRepository.findById(id);
    }


}
