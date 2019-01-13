package com.tb.bimo.model.persistance;

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
public class Campaign {

    @Id
    private String id;

    @NotNull
    private String description;

    @NotNull
    private String imageUrl;

    @NotNull
    private String companyId;

    @NotNull
    private String companyName;

    private Double discountRate;
}
