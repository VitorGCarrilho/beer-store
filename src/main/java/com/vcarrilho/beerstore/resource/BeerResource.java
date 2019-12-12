package com.vcarrilho.beerstore.resource;

import com.vcarrilho.beerstore.dto.BeerDTO;
import com.vcarrilho.beerstore.model.Beer;
import com.vcarrilho.beerstore.service.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Vitor Carrilho
 * @since 29/04/19
 */
@RestController
@RequestMapping("/beers")
@RequiredArgsConstructor
public class BeerResource {

    private final BeerService beerService;

    @GetMapping
    public ResponseEntity<List<Beer>> all() {
        return ResponseEntity.ok(beerService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BeerDTO create(@Valid @RequestBody BeerDTO beer) {
        BeerDTO createdBeer = beerService.save(beer);
        return createdBeer;
    }

}
