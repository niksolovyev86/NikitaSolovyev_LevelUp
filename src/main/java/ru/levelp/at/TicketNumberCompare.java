package ru.levelp.at;

public class TicketNumberCompare {

    public static boolean ticketNumberCompare(String ticketNumber) {

        int num1 = Integer.parseInt(ticketNumber, 0, 1, 10);
        int num2 = Integer.parseInt(ticketNumber, 1, 2, 10);
        int num3 = Integer.parseInt(ticketNumber, 2, 3, 10);
        int num4 = Integer.parseInt(ticketNumber, 3, 4, 10);
        int num5 = Integer.parseInt(ticketNumber, 4, 5, 10);
        int num6 = Integer.parseInt(ticketNumber, 5, 6, 10);

        return (num1 + num2 + num3 == num4 + num5 + num6);

    }


}
