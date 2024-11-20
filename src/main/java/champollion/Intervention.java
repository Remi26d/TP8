package champollion;

import java.util.Date;

/**
 * Classe représentant une intervention planifiée.
 */
public class Intervention {
    private final UE ue;  // L'unité d'enseignement concernée
    private final TypeIntervention type;  // Type d'intervention (CM, TD, TP)
    private final Date date;  // Date de l'intervention
    private final int heures;  // Durée en heures
    private final Salle salle;  // Salle où se déroule l'intervention

    public Intervention(UE ue, TypeIntervention type, Date date, int heures, Salle salle) {
        this.ue = ue;
        this.type = type;
        this.date = date;
        this.heures = heures;
        this.salle = salle;
    }

    public UE getUe() {
        return ue;
    }

    public TypeIntervention getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public int getHeures() {
        return heures;
    }

    public Salle getSalle() {
        return salle;
    }
}
