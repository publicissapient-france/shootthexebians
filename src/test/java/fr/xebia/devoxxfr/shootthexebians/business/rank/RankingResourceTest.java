package fr.xebia.devoxxfr.shootthexebians.business.rank;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import fr.xebia.devoxxfr.shootthexebians.business.FongoJongo;
import fr.xebia.devoxxfr.shootthexebians.business.scores.Score;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class RankingResourceTest {

    @Rule
    public FongoJongo fongoJongo = FongoJongo.getInstance();

    private RankingResource resource = new RankingResource();

    @Before
    public void initCollection() {
        ReflectionTestUtils.setField(resource, "scoresCollection", fongoJongo.getCollection("scores"));
    }

    @Test
    public void should_build_ranking() {
        // given
        Score score1 = new Score("player1@xebia.fr", "craft", 20L);
        Score score2 = new Score("player2@xebia.fr", "agile", 60L);
        Score score3 = new Score("player3@xebia.fr", "back", 30L);
        fongoJongo.getCollection("scores").insert(score1, score2, score3);

        // when
        List<Rank> ranking = resource.buildRanking();

        // then
        List<Rank> expectedRanking = newArrayList(
                new Rank(1L, "player2@xebia.fr", 60L),
                new Rank(2L, "player3@xebia.fr", 30L),
                new Rank(3L, "player1@xebia.fr", 20L)
        );
        assertThat(ranking).isEqualTo(expectedRanking);
    }
}
