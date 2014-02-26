package fr.xebia.devoxxfr.shootthexebians.business.scores;

import static org.assertj.core.api.Assertions.assertThat;
import fr.xebia.devoxxfr.shootthexebians.business.FongoJongo;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class ScoresResourceTest {

    @Rule
    public FongoJongo fongoJongo = new FongoJongo();

    private ScoresResource resource = new ScoresResource();
    
    @Before
    public void initCollection() {
        ReflectionTestUtils.setField(resource, "scoresCollection", fongoJongo.getCollection("scores"));
    }

    @Test
    public void should_insert_two_new_score() {
        // given
        Score score1 = new Score("player1@xebia.fr", "craft", 12345L);
        Score score2 = new Score("player2@xebia.fr", "agile", 6789L);

        // when
        resource.createScore(score1);
        resource.createScore(score2);

        // then
        assertThat(fongoJongo.getCollection("scores").count()).isEqualTo(2);
    }

    @Test
    public void should_update_one_existing_score() {
        // given
        Score score1 = new Score("player3@xebia.fr", "craft", 12345L);
        Score score2 = new Score("player3@xebia.fr", "agile", 6789L);
        resource.createScore(score1);

        // when
        resource.createScore(score2).getEntity();

        // then
        assertThat(fongoJongo.getCollection("scores").count()).isEqualTo(1);
        Score score = fongoJongo.getCollection("scores").findOne().as(Score.class);
        assertThat(score).isEqualTo(score2);
    }


}
