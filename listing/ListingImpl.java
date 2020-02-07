package fr.cnam.tp10.listing;

import fr.cnam.tp10.exceptions.PersonAlreadyHereException;
import fr.cnam.tp10.exceptions.UnknownPersonException;

import java.util.*;
import java.util.stream.Collectors;

public class ListingImpl extends HashMap<String, String> implements Listing {

    @Override
    public void recordArrival(String staff, String office) throws PersonAlreadyHereException {
        if (staff != null && office != null) {
            if (this.containsKey(staff))
                throw new PersonAlreadyHereException();
            else
                this.put(staff, office);
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public String officeOf(String staff) {
        if (staff != null) {
            if (!this.containsKey(staff)) {
                /* throw Exception */
                throw new UnknownPersonException();
            } else
                return this.get(staff);
        } else {
            /* throw Exception */
            throw new NullPointerException();
        }
    }

    @Override
    public void changeOfficeFor(String staff, String office) {
        if (staff != null && office != null) {
            if (!this.containsKey(staff)) {
                /* throw Exception */
                throw new UnknownPersonException();
            } else
                this.replace(staff, office);
        } else {
            /* throw Exception */
            throw new NullPointerException();
        }
    }

    @Override
    public void recordDeparture(String staff) {
        if (staff != null) {
            if (!this.containsKey(staff)) {
                /* throw Exception */
                throw new UnknownPersonException();
            } else
                this.remove(staff);
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public Collection<String> staff() {

        return this.keySet();
    }

    @Override
    public Map<String, Set<String>> staffByOffice() {

        HashMap<String, Set<String>> mapInverted = new HashMap<>();

        for (Map.Entry<String, String> entry : this.entrySet()) {
            String bureau = entry.getValue();
            String person = entry.getKey();
            if (mapInverted.containsKey(bureau)) {

                mapInverted.get(bureau).add(person);

            } else {
                Set<String> staff = new HashSet<>();
                staff.add(person);
                mapInverted.put(entry.getValue(), staff);
            }
        }
        return mapInverted;
    }

    @Override
    public Collection<String> staff(String office) {
        if (office != null) {
            Set<String> staffSet = new HashSet<>();
            staffSet = this.entrySet().stream()
                    .filter(x -> x.getValue().equals(office))
                    .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue())).keySet();
            return staffSet;
        } else
            throw new NullPointerException();
    }

    @Override
    public Collection<String> offices() {

        Set<String> existing = new HashSet<>();
        Map<String, String> map = new HashMap<>(this);
        map = map.entrySet()
                .stream()
                .filter(entry -> existing.add(entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return map.values();
    }

    @Override
    public void printStaff() {
        for (String staff : this.keySet())
            System.out.println(staff + " : " + this.get(staff));
    }

    @Override
    public void printStaffByOffice() {

    }
}
