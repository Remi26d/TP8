package champollion;

/**
 * Classe représentant une Unité d'Enseignement (UE).
 */
public class UE {
    private final String intitule;  // Intitulé de l'UE

    /**
     * Constructeur pour créer une UE.
     *
     * @param intitule Intitulé de l'Unité d'Enseignement
     */
    public UE(String intitule) {
        this.intitule = intitule;
    }

    /**
     * Récupère l'intitulé de l'UE.
     *
     * @return L'intitulé
     */
    public String getIntitule() {
        return this.intitule;
    }
}
