package com.tb.bimo.models.joinTables;

import com.tb.bimo.models.Basket;
import com.tb.bimo.models.Item;
import com.tb.bimo.models.embeddables.BasketItemId;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class BasketItem {

    @EmbeddedId
    private BasketItemId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("basketId")
    private Basket basket;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("itemId")
    private Item item;

    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BasketItem that = (BasketItem) o;
        return Objects.equals(basket, that.basket) &&
                Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basket, item);
    }
}
