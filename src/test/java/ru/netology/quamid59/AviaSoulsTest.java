package ru.netology.quamid59;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class AviaSoulsTest {
    Ticket ticket1 = new Ticket("Moscow", "St. Petersburg", 5000, 10, 13);
    Ticket ticket2 = new Ticket("Kazan", "Sochi", 10000, 14, 18);
    Ticket ticket3 = new Ticket("St. Petersburg", "Kazan", 12000, 12, 16);
    Ticket ticket4 = new Ticket("Sochi", "Moscow", 15000, 17, 20);
    Ticket ticket5 = new Ticket("St. Petersburg", "Moscow", 8000, 11, 14);
    Ticket ticket6 = new Ticket("Moscow", "St. Petersburg", 7000, 10, 13);
    AviaSouls manager = new AviaSouls();

    @BeforeEach
    public void setup() {
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
    }

    @Test
    public void testCompareToReturnsOneForHigherPriceTicket() {
        int expected = 1;
        int actual = ticket2.compareTo(ticket1);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testCompareToReturnsMinusOneForLowerPriceTicket() {
        int expected = -1;
        int actual = ticket1.compareTo(ticket3);

        Assertions.assertEquals(actual, expected);
    }

    @Test
    public void testCompareToReturnsZeroForEqualPriceTickets() {
        int expected = -1;
        int actual = ticket1.compareTo(ticket4);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSearchReturnsTicketsSortedByPrice() {
        Ticket[] expected = {ticket1, ticket6};
        Ticket[] actual = manager.search("Moscow", "St. Petersburg");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchReturnsSingleMatchingTicket() {
        Ticket[] expected = {ticket2};
        Ticket[] actual = manager.search("Kazan", "Sochi");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchReturnsEmptyArrayWhenNoMatchingTicketsFound() {
        Ticket[] expected = {};
        Ticket[] actual = manager.search("Sochi", "Kazan");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchAndSortByReturnsTicketsSortedByFlightTime() {
        Comparator<Ticket> comparator = new TicketTimeComparator();
        Ticket[] expected = {ticket1, ticket6};
        Ticket[] actual = manager.searchAndSortBy("Moscow", "St. Petersburg", comparator);
        Assertions.assertArrayEquals(expected, actual);
    }
}
