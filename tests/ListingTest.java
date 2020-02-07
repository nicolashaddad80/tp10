package fr.cnam.tp9;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Classe abstraite de test de l'interface Listing.
 * <p>
 * Cette classe n'est pas à lire pour éviter de vous biaiser, et ne doit en aucun cas être modifiée.
 * <p>
 * Pour tester votre classe, héritez de cette classe et initialisez l'attribut <code>listing</code> dans une
 * redéfinition de <code>setUp()</code>
 *
 * @author Anaël Megna
 * @version 1.0
 */
public abstract class ListingTest {
    protected Listing listing;

    @Before
    public void setUp() {
        this.listing.recordArrival("Xavier", "F305");
        this.listing.recordArrival("Marc", "F305");
        this.listing.recordArrival("Aurélie", "F307");
        this.listing.recordArrival("Christiane", "F309");
        this.listing.recordArrival("Marcel", "F309");
    }

    @Test
    public void testOfficeOf() {
        assertEquals("F305", this.listing.officeOf("Xavier"));
        assertEquals("F305", this.listing.officeOf("Marc"));
        assertEquals("F307", this.listing.officeOf("Aurélie"));
        assertEquals("F309", this.listing.officeOf("Christiane"));
        assertEquals("F309", this.listing.officeOf("Marcel"));
    }

    @Test
    public void testChangeOfficeFor() {
        this.listing.changeOfficeFor("Xavier", "F301");
        assertEquals("F301", this.listing.officeOf("Xavier"));
    }

    @Test
    public void testStaff() {
        assertEquals(5, this.listing.staff().size());
        assertTrue(this.listing.staff().contains("Xavier"));
        assertTrue(this.listing.staff().contains("Marc"));
        assertTrue(this.listing.staff().contains("Aurélie"));
        assertTrue(this.listing.staff().contains("Christiane"));
        assertTrue(this.listing.staff().contains("Marcel"));
    }

    @Test
    public void testStaffFor() {
        Collection<String> personnels = this.listing.staff("F307");
        assertEquals(1, personnels.size());
        assertTrue(personnels.contains("Aurélie"));

        personnels = this.listing.staff("F305");
        assertEquals(2, personnels.size());
        assertTrue(personnels.contains("Xavier"));
        assertTrue(personnels.contains("Marc"));

        personnels = this.listing.staff("Salle virtuelle");
        assertEquals(0, personnels.size());
    }

    @Test
    public void testOffices() {
        assertEquals(3, this.listing.offices().size());
        assertTrue(this.listing.offices().contains("F305"));
        assertTrue(this.listing.offices().contains("F307"));
        assertTrue(this.listing.offices().contains("F309"));
    }

    @Test
    public void testRecordDeparture() {
        this.listing.recordDeparture("Marcel");
        assertFalse(this.listing.staff().contains("Marcel"));
        assertEquals(1, this.listing.staff("F309").size());
        assertFalse(this.listing.staff().contains("Marcel"));
    }

    @Test
    public void testStaffByOffice() {
        assertEquals(1, this.listing.staffByOffice().get("F307").size());
        assertTrue(this.listing.staffByOffice().get("F307").contains("Aurélie"));

        assertEquals(2, this.listing.staffByOffice().get("F305").size());
        assertTrue(this.listing.staffByOffice().get("F305").contains("Marc"));
        assertTrue(this.listing.staffByOffice().get("F305").contains("Xavier"));

        assertEquals(2, this.listing.staffByOffice().get("F309").size());
        assertTrue(this.listing.staffByOffice().get("F309").contains("Christiane"));
        assertTrue(this.listing.staffByOffice().get("F309").contains("Marcel"));

        assertEquals(3, this.listing.staffByOffice().keySet().size());
    }

    @Test(expected = PersonAlreadyHereException.class)
    public void testRecordArrival() {
        this.listing.recordArrival("Xavier", "FFFF");
    }

    @Test(expected = NullPointerException.class)
    public void testRecordArrivalStaffNotNull() {
        this.listing.recordArrival(null, "FFFF");
    }

    @Test(expected = NullPointerException.class)
    public void testRecordArrivalOfficeNotNull() {
        this.listing.recordArrival("Xavier", null);
    }

    @Test(expected = UnknownPersonException.class)
    public void testChangeOfficeForUnknown() {
        this.listing.changeOfficeFor("Superman", "FFFF");
    }

    @Test(expected = NullPointerException.class)
    public void testChangeOfficeForStaffNotNull() {
        this.listing.changeOfficeFor(null, "FFFF");
    }

    @Test(expected = NullPointerException.class)
    public void testChangeOfficeForOfficeNotNull() {
        this.listing.changeOfficeFor("Xavier", null);
    }

    @Test(expected = UnknownPersonException.class)
    public void testOfficeOfUnknown() {
        this.listing.officeOf("Superman");
    }

    @Test(expected = NullPointerException.class)
    public void testOfficeForNotNull() {
        this.listing.officeOf(null);
    }

    @Test(expected = NullPointerException.class)
    public void testStaffForNotNull() {
        this.listing.staff(null);
    }

    @Test(expected = UnknownPersonException.class)
    public void testRecordDepartureUnknown() {
        this.listing.recordDeparture("Superman");
    }

    @Test(expected = NullPointerException.class)
    public void testRecordDepartureNotNull() {
        this.listing.recordDeparture(null);
    }
}
