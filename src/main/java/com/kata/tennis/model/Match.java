package com.kata.tennis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
public class Match {


    private Player player1;

    private Player player2;

    @JsonIgnore
    private Optional<String> winner = Optional.empty();

    @JsonProperty
    public String getFinalWinner() {
        return winner.orElseGet(() -> "");
    }

    private Integer setNumber = 1;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }


}
