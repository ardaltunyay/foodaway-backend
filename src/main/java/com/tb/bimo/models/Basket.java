package com.tb.bimo.models;

import com.tb.bimo.models.joinTables.BasketItem;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Basket {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Campaign campaign;

    @NotNull
    private Long createdAt;

    @OneToMany(
            mappedBy = "basket",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<BasketItem> itemSet = new HashSet<>();

    public void addItem(Item item, Integer quantity) {
        BasketItem basketItem = BasketItem.builder()
                .basket(this)
                .item(item)
                .quantity(quantity)
                .build();
        itemSet.add(basketItem);
    }

    public void removeItem(Item item) {
        for (
                Iterator<BasketItem> iterator = itemSet.iterator();
                iterator.hasNext();
        ) {
            BasketItem basketItem = iterator.next();

            if (basketItem.getBasket().equals(this) && basketItem.getItem().equals(item)) {
                iterator.remove();
                basketItem.setBasket(null);
                basketItem.setItem(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Basket post = (Basket) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
