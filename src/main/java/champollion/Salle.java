package champollion;

/**
 * Classe représentant une Salle utilisée pour des interventions.
 */
public class Salle {
    private final String nom;  // Nom de la salle
    private final int capacite;  // Capacité maximale

    /**
     * Constructeur de la classe Salle.
     *
     * @param nom      Nom de la salle
     * @param capacite Capacité maximale
     */
    public Salle(String nom, int capacite) {
        this.nom = nom;
        this.capacite = capacite;
    }

    /**
     * Récupère le nom de la salle.
     *
     * @return Nom de la salle
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupère la capacité maximale de la salle.
     *
     * @return Capacité maximale
     */
    public int getCapacite() {
        return capacite;
    }
}
