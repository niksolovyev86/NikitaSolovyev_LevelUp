package ru.levelp.at;

public class TicketNumberCompare {

    public static boolean ticketNumberCompare(String ticketNumber) {

        int num1;
        int num2;
        int num3;
        int num4;
        int num5;
        int num6;

        try {
            num1 = Integer.parseInt(ticketNumber, 0, 1, 10);
            num2 = Integer.parseInt(ticketNumber, 1, 2, 10);
            num3 = Integer.parseInt(ticketNumber, 2, 3, 10);
            num4 = Integer.parseInt(ticketNumber, 3, 4, 10);
            num5 = Integer.parseInt(ticketNumber, 4, 5, 10);
            num6 = Integer.parseInt(ticketNumber, 5, 6, 10);

        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }

        return (num1 + num2 + num3 == num4 + num5 + num6);

    }


}
