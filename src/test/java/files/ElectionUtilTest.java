package files;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ElectionUtilTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void testEmptyVotes() {
        String[] votes = {};
        String result = ElectionUtil.evaluate(votes);
        Assertions.assertEquals("none", result);
    }

    @Test
    void testSingleCandidate() {
        String[] votes = {"A"};
        String result = ElectionUtil.evaluate(votes);
        Assertions.assertEquals("A", result);
    }

    @Test
    void testMultipleCandidatesSingleWinner() {
        String[] votes = {"A", "B", "C", "A", "B", "A"};
        String result = ElectionUtil.evaluate(votes);
        Assertions.assertEquals("A", result);
    }

    @Test
    void testMultipleCandidatesDraw() {
        String[] votes = {"A", "B", "C", "A", "B", "C"};
        String result = ElectionUtil.evaluate(votes);
        Assertions.assertEquals("draw", result);
    }

    @Test
    void testTieForLastPlace() {
        String[] votes = {"A", "B", "C", "C"};
        String result = ElectionUtil.evaluate(votes);
        Assertions.assertEquals("C", result);
    }
    @Test
    void testHardCase() {
        String[] votes = new String[100000];
        for (int i = 0; i < 100000; i++) {
            int candidateNum = i % 100;
            if (candidateNum % 2 == 0) {
                votes[i] = "A" + candidateNum;
            } else {
                votes[i] = "B" + candidateNum;
            }
        }
        String result = ElectionUtil.evaluate(votes);
        Assertions.assertEquals("draw", result);
    }
}