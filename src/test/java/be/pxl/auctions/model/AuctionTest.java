package be.pxl.auctions.model;

import org.hibernate.id.GUIDGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Double.valueOf;
import static java.lang.Math.random;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class AuctionTest {
    private Auction auction;
    private final Random random = new Random();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    @BeforeEach
    public void init() {
        auction = new Auction();

    }

    @Test
    public void isFinishedReturnsTrueIfDateIsBeforeToday() {

        auction.setEndDate(LocalDate.now().minusDays(1), DATE_FORMAT);
        auction.setDescription(GUIDGenerator.GENERATOR_NAME);

        assertTrue(auction.isFinished());
    }

    @Test
    public void isFinishedReturnsFalseIfDateIsAfterToday() {

        auction.setEndDate(LocalDate.now().plusDays(1), DATE_FORMAT);
        auction.setDescription(GUIDGenerator.GENERATOR_NAME);

        assertFalse(auction.isFinished());
    }

    @Test
    public void findHighestBid() {

        auction.addBid(new Bid(generateRandomUser(), generateRandomDate(), 20000));
        auction.addBid(new Bid(generateRandomUser(), generateRandomDate(), 10000));
        auction.addBid(new Bid(generateRandomUser(), generateRandomDate(), 5));
        auction.addBid(new Bid(generateRandomUser(), generateRandomDate(), 10));
        assertEquals(20000, auction.findHighestBid().getAmount());
    }


    private User generateRandomUser() {
        User user = new User();
        user.setId(valueOf(1 - random() * (100 - 1)).intValue());
        user.setFirstName(GUIDGenerator.GENERATOR_NAME);
        user.setLastName(GUIDGenerator.GENERATOR_NAME);
        user.setEmail(GUIDGenerator.GENERATOR_NAME + "@" + "hotmail.com");
        user.setDateOfBirth(generateRandomDate());
        return user;
    }

    private LocalDate generateRandomDate() {
        double year = (double) (random.nextInt(2010 - 1900 + 1) + 1900);
        double month = (double) (random.nextInt(12 - 1 + 1) + 1);
        double day = (double) (random.nextInt(31 - 1 + 1) + 1);
        return LocalDate.of((int) year, (int) month, (int) day);
    }


}
