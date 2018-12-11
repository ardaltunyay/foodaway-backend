package com.tb.bimo.models.embeddables;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class BasketItemId implements Serializable {

    @Column(name = "basket_id")
    private Long basketId;

    @Column(name = "item_id")
    private Long itemId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        BasketItemId that = (BasketItemId) o;
        return Objects.equals(basketId, that.basketId) &&
                Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketId, itemId);
    }
}