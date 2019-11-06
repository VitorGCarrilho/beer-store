package com.vcarrilho.beerstore.model;

import com.vcarrilho.beerstore.dto.BeerDTO;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Vitor Carrilho
 * @since 07/05/19
 */
@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Beer {

    @Id
    @Getter
    @EqualsAndHashCode.Include
    @SequenceGenerator(name = "beer_seq", sequenceName = "beer_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "beer_seq")
    private Long id;

    @Getter
    private String name;

    @Getter
    private BeerType type;

    @Getter
    private BigDecimal price;

    public Beer(BeerDTO beerDTO) {
        this.id = beerDTO.getId();
        this.name = beerDTO.getName();
        this.price = beerDTO.getPrice();
        this.type = beerDTO.getType();
    }
}
