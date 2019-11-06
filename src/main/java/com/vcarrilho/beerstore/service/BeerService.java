package com.vcarrilho.beerstore.service;

import com.vcarrilho.beerstore.dto.BeerDTO;
import com.vcarrilho.beerstore.model.Beer;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Vitor Carrilho
 * @since 07/05/19
 */
public interface BeerService {

    BeerDTO save(final BeerDTO beer);

    List<Beer> findAll();
}
