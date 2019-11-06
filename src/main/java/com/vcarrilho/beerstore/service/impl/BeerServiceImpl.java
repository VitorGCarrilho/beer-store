package com.vcarrilho.beerstore.service.impl;

import com.vcarrilho.beerstore.dto.BeerDTO;
import com.vcarrilho.beerstore.model.Beer;
import com.vcarrilho.beerstore.repository.BeerRepository;
import com.vcarrilho.beerstore.service.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Vitor Carrilho
 * @since 07/05/19
 */
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;

    public BeerDTO save(final BeerDTO beer){
        Beer createdBeer = beerRepository.save(new Beer(beer));
        return new BeerDTO(createdBeer);
    }

    public List<Beer> findAll() {
        return beerRepository.findAll();
    }

    @Autowired
    public BeerServiceImpl(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }
}
