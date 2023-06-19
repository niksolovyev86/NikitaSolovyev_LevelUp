package ru.levelp.at;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.levelp.at.TicketNumberCompare.ticketNumberCompare;

import org.testng.annotations.Test;

public class TicketNumberCompareNegativeTest extends BaseTest {
    @Test(dataProvider = "DataProviderTicketNegativeTest", dataProviderClass = DataProviderForTest.class)
    public void ticketNumberCompareNegativeTest(String ticketNumber, boolean expectedResult) {
        boolean result = ticketNumberCompare(ticketNumber);

        assertThat(result).as("Ticket isn't happy")
                          .isEqualTo(expectedResult);
    }

    @Test(dataProvider = "DataProviderTicketNegativeTestEmpty", dataProviderClass = DataProviderForTest.class,
    expectedExceptions = {IndexOutOfBoundsException.class})
    public void ticketNumberCompareNegativeTestEmptyInputArray(String ticketNumber, boolean expectedResult) {
        boolean result = ticketNumberCompare(ticketNumber);

        assertThat(result).as("Ticket isn't empty")
                          .isEqualTo(expectedResult);
    }

    @Test(dataProvider = "DataProviderTicketNegativeTestWrongInputData", dataProviderClass = DataProviderForTest.class,
          expectedExceptions = {NumberFormatException.class})
    public void ticketNumberCompareNegativeTestWrongInputData(String ticketNumber, boolean expectedResult) {
        boolean result = ticketNumberCompare(ticketNumber);

        assertThat(result).as("Ticket input data is wrong")
                          .isEqualTo(expectedResult);
    }

}
