package fr.cnam.tp10.listing;

import fr.cnam.tp10.exceptions.PersonAlreadyHereException;
import fr.cnam.tp10.exceptions.UnknownPersonException;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Un listing permet d'enregistrer pour chaque membre du personnel le bureau qu'il occupe,
 * et inversement, pour chaque bureau l'ensemble des membres du personnel l'occupant.
 * <p>
 * Un membre du personnel ne peut occuper qu'un seul bureau,
 * mais un bureau peut accueillir plusieurs membres du personnel.
 * <p>
 * Chaque membre du personnel ainsi que chaque bureau est identifié de manière unique par son nom.
 *
 * @author Anaël Megna
 * @version 1.0
 */
public interface Listing {
    /**
     * Enregistre un nouveau membre du personnel en indiquant son bureau.
     *
     * @param staff  le membre du personnel
     * @param office le bureau occupé par ce membre
     */
    void recordArrival(String staff, String office) throws PersonAlreadyHereException;

    /**
     * Obtient le bureau occupé par un membre du personnel.
     *
     * @param staff le membre du personnel
     * @return Une chaine de caractère représentant le bureau occupé par le membre du personnel
     */
    String officeOf(String staff) throws UnknownPersonException;

    /**
     * Modifie le bureau d'un membre du personnel.
     *
     * @param staff  le membre du personnel
     * @param office le nouveau bureau du membre du personnel
     */
    void changeOfficeFor(String staff, String office) throws UnknownPersonException;

    /**
     * Enregistre le départ d'un membre du personnel.
     *
     * @param staff le membre du personnel
     */
    void recordDeparture(String staff) throws UnknownPersonException;

    /**
     * Obtient l'ensemble des membres du personnel.
     *
     * @return Une collection de chaine de caractères représentant l'ensemble des membres du personnel
     */
    Collection<String> staff();

    /**
     * Obtient l'ensemble des membres du personnel occupant un bureau particulier.
     *
     * @param office le bureau en question
     * @return Une collection de chaines de caractères représentant l'ensemble des membres du personnel occupant ce
     * bureau
     */
    Collection<String> staff(String office);

    /**
     * Obtient l'ensemble des bureaux.
     *
     * @return Une collection de chaines de caractères représentant l'ensemble des bureaux
     */
    Collection<String> offices();

    /**
     * Obtient l'occupation de l'ensemble des bureaux.
     *
     * @return Un mapping entre des bureaux représentés par une chaine de caractère et l'ensemble des membres du
     * personnel occupant chacun des bureaux, chaque membre étant représenté par une chaine de caractère
     */
    Map<String, Set<String>> staffByOffice();

    /**
     * Affiche l'ensemble des membres du personnel dans la console sous la forme :
     * <pre>
     * membre1 : bureau1
     * membre2 : bureau1
     * membre3 : bureau2
     * </pre>
     */
    void printStaff();

    /**
     * Affiche l'occupation de l'ensemble des bureaux dans la console sous la forme :
     * <pre>
     * bureau1 :
     *  - membre1
     *  - membre2
     * bureau2 :
     *  - membre3
     * </pre>
     */
    void printStaffByOffice();
}
