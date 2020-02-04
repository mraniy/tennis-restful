package com.kata.tennis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
public class Match {

    public Match deepCopy() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(objectMapper.writeValueAsString(this), Match.class);
    }

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
