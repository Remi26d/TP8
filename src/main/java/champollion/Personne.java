package champollion;

/**
 * Classe abstraite représentant une Personne.
 * À spécifier dans les classes dérivées (comme Étudiant ou Enseignant).
 */
public abstract class Personne {
    private final String nom;  // Nom de la personne
    private final String email;  // Adresse email de la personne

    /**
     * Constructeur pour initialiser une Personne.
     *
     * @param nom   Nom de la personne
     * @param email Adresse email de la personne
     */
    protected Personne(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    /**
     * Obtient le nom de la personne.
     *
     * @return Le nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Obtient l'email de la personne.
     *
     * @return L'adresse email
     */
    public String getEmail() {
        return this.email;
    }
}
