package com.vcarrilho.beerstore.dto;

import com.vcarrilho.beerstore.model.Beer;
import com.vcarrilho.beerstore.model.BeerType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Vitor Carrilho
 * @since 21/05/19
 */
@Data
@NoArgsConstructor
public class BeerDTO  implements Serializable {

    private Long id;

    @NotNull(message = "beers-1")
    private String name;

    @NotNull(message = "beers-2")
    private BeerType type;

    @NotNull(message = "beers-3")
    @DecimalMin(value = "0", message = "beers-4")
    private BigDecimal price;

    public BeerDTO(Beer beer) {
        this.id = beer.getId();
        this.name = beer.getName();
        this.type = beer.getType();
        this.price =beer.getPrice();
    }
}
