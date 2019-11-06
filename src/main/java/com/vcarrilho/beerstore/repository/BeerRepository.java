package com.vcarrilho.beerstore.repository;

import com.vcarrilho.beerstore.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Vitor Carrilho
 * @since 07/05/19
 */
public interface BeerRepository extends JpaRepository<Beer, Long> {
}
