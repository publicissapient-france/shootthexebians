package fr.xebia.devoxxfr.shootthexebians.business;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY // mandatory for serialization
)
public class Score {

    @Email
    @NotBlank
    private String player;
    @NotBlank
    private String fondation;
    @Min(0)
    private Long score;

    @JsonCreator
    public Score(@JsonProperty("player") String player,
                 @JsonProperty("fondation") String fondation,
                 @JsonProperty("score") Long score) {
        this.player = player;
        this.fondation = fondation;
        this.score = score;
    }

    protected String getPlayer() {
        return player;
    }
}
