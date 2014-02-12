package fr.xebia.devoxxfr.shootthexebians.business;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE
)
public class Score {

    private String player;
    private String fondation;
    private Long score;

    @JsonCreator
    public Score(@JsonProperty("player") String player,
                 @JsonProperty("fondation") String fondation,
                 @JsonProperty("score") Long score) {
        this.player = player;
        this.fondation = fondation;
        this.score = score;
    }
}
