package fr.cnam.tp10.tests;

import fr.cnam.tp10.listing.ListingImpl;
import org.junit.Before;

public class concertListingTest extends ListingTest {

    @Override
    @Before
    public void setUp() {

        this.listing = new ListingImpl();
        super.setUp();
    }
}
