package fr.xebia.devoxxfr.shootthexebians.business;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY
)
public class Rank {

    private Long rank;
    private String player;
    private Long score;

    @JsonCreator
    public Rank(@JsonProperty("rank") Long rank,
                @JsonProperty("player") String player,
                @JsonProperty("score") Long score) {
        this.rank = rank;
        this.player = player;
        this.score = score;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }
}
