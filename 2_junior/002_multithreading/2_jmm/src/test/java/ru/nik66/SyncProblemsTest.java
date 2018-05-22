package ru.nik66;

import org.junit.Test;

public class SyncProblemsTest {

    @Test
    public void whenFieldIsNotVolatileThenVisibilityProblem() {
        SyncProblems syncProblems = new SyncProblems();
        System.out.printf("Visibility: 2000000 = %d", syncProblems.doVisCount());
        System.out.println();
        System.out.printf("Race Condition: 2000000 = %d", syncProblems.doRaceCount());
        /*
         * Result 1:
         * Visibility: 2000000 = 1953722
         * Race Condition: 2000000 = 1341261
         * Process finished with exit code 0
         * Result 2:
         * Visibility: 2000000 = 1171322
         * Race Condition: 2000000 = 1280211
         * Process finished with exit code 0
         */
    }

}
