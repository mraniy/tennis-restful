package com.kata.tennis.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamesAndMaybeTieBreakPoints {

    private Integer games;
    @JsonIgnore
    private Optional<Integer> tieBreakPoints = Optional.empty();




}
