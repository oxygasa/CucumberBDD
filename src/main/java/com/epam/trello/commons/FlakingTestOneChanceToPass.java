package com.epam.trello.commons;

import org.testng.*;
/***
 * If test flakes, this repeater makes 1 retry.
 * The 2-nd fail fall the test down.
 ***/
public class FlakingTestOneChanceToPass implements IRetryAnalyzer {
    private static final int MAX_RETRY = 1; //1 chance for Pass retry.
    private int actualRetry = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (actualRetry < MAX_RETRY) {
            actualRetry++;
            return true;
        } else {
            return false;
        }
    }
}
