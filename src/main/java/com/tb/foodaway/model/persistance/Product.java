package com.tb.foodaway.model.persistance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Product {

    @Id
    private String id;

    @JsonIgnore
    @NotNull
    private String companyId;

    @NotNull
    private String title;

    private String description;

    private String logoUrl;

    @NotNull
    private Double price;
}
