package fr.xebia.devoxxfr.shootthexebians.business.scores;

import static org.assertj.core.api.Assertions.assertThat;
import com.github.fakemongo.Fongo;
import com.mongodb.DB;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class ScoresResourceTest {

    private ScoresResource resource = new ScoresResource();
    private MongoCollection scoresCollection;

    @Before
    public void initDB() {
        DB db = new Fongo("Test").getDB("Database");
        Jongo jongo = new Jongo(db);
        scoresCollection = jongo.getCollection("scoresCollection");

        ReflectionTestUtils.setField(resource, "scoresCollection", scoresCollection);
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
        assertThat(scoresCollection.count()).isEqualTo(2);
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
        assertThat(scoresCollection.count()).isEqualTo(1);
        Score score = scoresCollection.findOne().as(Score.class);
        assertThat(score).isEqualTo(score2);
    }


}
