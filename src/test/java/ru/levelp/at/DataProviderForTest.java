package ru.levelp.at;

import org.testng.annotations.DataProvider;

public class DataProviderForTest {

    public static final String DATA_PROVIDER_TICKET_NUMBER_POSITIVE_TEST = "DataProviderTicketPositiveTest";
    public static final String DATA_PROVIDER_TICKET_NUMBER_NEGATIVE_TEST = "DataProviderTicketNegativeTest";
    public static final String DATA_PROVIDER_TICKET_NUMBER_NEGATIVE_TEST_WRONG_INPUT_DATA = "DataProviderTicketNegativeTestWrongInputData";
    public static final String DATA_PROVIDER_TICKET_NUMBER_NEGATIVE_TEST_EMPTY = "DataProviderTicketNegativeTestEmpty";
    public static final String DATA_PROVIDER_TICKET_NUMBER_NEGATIVE_TEST_NULL = "DataProviderTicketNegativeTestNull";

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

    @DataProvider(name = DATA_PROVIDER_TICKET_NUMBER_NEGATIVE_TEST_WRONG_INPUT_DATA)
    public static Object[][] supplyTicketNumbersFalseWrongInputData() {
        return new Object[][]{
            new Object[]{"asfgfr", false},
            new Object[]{"qwrvsd", false},
            new Object[]{"dfbfgr", false},
            new Object[]{"kdpe[,", false},
            new Object[]{"erl;w/", false},
            new Object[]{"rfrrfr", false}
        };
    }

    @DataProvider(name = DATA_PROVIDER_TICKET_NUMBER_NEGATIVE_TEST_EMPTY)
    public static Object[][] supplyTicketNumbersFalseEmpty() {
        return new Object[][]{
            new Object[]{"", false}
        };
    }

    @DataProvider(name = DATA_PROVIDER_TICKET_NUMBER_NEGATIVE_TEST_NULL)
    public static Object[][] supplyTicketNumbersFalseNull() {
        return new Object[][]{
            new Object[]{null, false}
        };
    }

}
