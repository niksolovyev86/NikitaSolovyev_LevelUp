package ru.levelp.at;

import org.testng.annotations.DataProvider;

public class DataProviderForTest {

    public static final String DATA_PROVIDER_TICKET_NUMBER_POSITIVE_TEST = "DataProviderTicketPositiveTest";
    public static final String DATA_PROVIDER_TICKET_NUMBER_NEGATIVE_TEST = "DataProviderTicketNegativeTest";

    @DataProvider(name = DATA_PROVIDER_TICKET_NUMBER_POSITIVE_TEST)
    public static Object[][] supplyTicketNumbersTrue() {
        return new Object[][]{
            new Object[]{"001001", true},
            new Object[]{"563455", true},
            new Object[]{"327066", true},
            new Object[]{"999999", true},
            new Object[]{"236029", true}
        };
    }

    @DataProvider(name = DATA_PROVIDER_TICKET_NUMBER_NEGATIVE_TEST)
    public static Object[][] supplyTicketNumbersFalse() {
        return new Object[][]{
            new Object[]{"001000", false},
            new Object[]{"563415", false},
            new Object[]{"307066", false},
            new Object[]{"990999", false},
            new Object[]{"230029", false},
            new Object[]{"000001", false}
        };
    }
}
