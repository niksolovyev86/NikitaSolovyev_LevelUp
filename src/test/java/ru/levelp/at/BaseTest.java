package ru.levelp.at;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    TicketNumberCompare ticketNumberCompare;

    @BeforeMethod
    public void setUp() {
        ticketNumberCompare = new TicketNumberCompare();
    }

    @AfterMethod
    public void tearDown() {
        ticketNumberCompare = null;
    }

}
