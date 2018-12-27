package com.tb.bimo.model.persistance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Basket {

    @Id
    private String id;

    @JsonIgnore
    @NotNull
    private String userId;

    private String campaignId;

    private Map<Product,String> productList;
}
