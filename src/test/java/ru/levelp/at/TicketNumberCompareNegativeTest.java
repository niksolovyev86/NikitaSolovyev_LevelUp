package ru.levelp.at;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.levelp.at.TicketNumberCompare.ticketNumberCompare;

import org.testng.annotations.Test;

public class TicketNumberCompareNegativeTest extends BaseTest {
    @Test(dataProvider = "DataProviderTicketNegativeTest", dataProviderClass = DataProviderForTest.class)
    public void ticketNumberCompareNegativeTest(String ticketNumber, boolean expectedResult) {
        boolean result = ticketNumberCompare(ticketNumber);

        assertThat(result).as("Something wrong is going on")
                          .isEqualTo(expectedResult);
    }
}
