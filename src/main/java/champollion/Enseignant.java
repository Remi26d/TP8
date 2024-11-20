package champollion;

import java.util.HashSet;
import java.util.Set;

public class Enseignant extends Personne {
    private final ServicePrevu serviceAttribue; // Service prévu pour cet enseignant
    private final Set<Intervention> interventionsPlanifiees; // Liste des interventions planifiées

    public Enseignant(String nom, String email) {
        super(nom, email);
        this.serviceAttribue = new ServicePrevu();
        this.interventionsPlanifiees = new HashSet<>();
    }

    /**
     * Ajoute un enseignement au service prévu de l'enseignant.
     *
     * @param ue        L'unité d'enseignement concernée
     * @param heuresCM  Nombre d'heures de CM
     * @param heuresTD  Nombre d'heures de TD
     * @param heuresTP  Nombre d'heures de TP
     */
    public void ajouterEnseignement(UE ue, int heuresCM, int heuresTD, int heuresTP) {
        serviceAttribue.ajouterEnseignement(ue, TypeIntervention.CM, heuresCM);
        serviceAttribue.ajouterEnseignement(ue, TypeIntervention.TD, heuresTD);
        serviceAttribue.ajouterEnseignement(ue, TypeIntervention.TP, heuresTP);
    }

    /**
     * Calcule le total des heures prévues en équivalent TD pour cet enseignant.
     *
     * @return Le total des heures prévues
     */
    public int calculerHeuresPrevues() {
        return serviceAttribue.heuresEquivalentes();
    }

    /**
     * Calcule le total des heures prévues pour une unité d'enseignement donnée.
     *
     * @param ue L'unité d'enseignement concernée
     * @return Le total des heures prévues pour cette UE
     */
    public int calculerHeuresPrevuesPourUE(UE ue) {
        return serviceAttribue.getHeuresEquivalentesPourUE(ue);
    }

    /**
     * Ajoute une intervention planifiée pour cet enseignant.
     *
     * @param intervention L'intervention à ajouter
     * @throws Exception Si l'intervention dépasse le volume horaire prévu
     */
    public void ajouterIntervention(Intervention intervention) throws Exception {
        int heuresPrevues = serviceAttribue.getHeuresPourUE(intervention.getUe(), intervention.getType());
        if (heuresPrevues == 0) {
            throw new Exception("Aucune heure prévue pour ce type d'intervention.");
        }

        int heuresRestantes = heuresPrevues - intervention.getHeures();
        if (heuresRestantes < 0) {
            throw new Exception("Le volume horaire prévu est dépassé.");
        }

        interventionsPlanifiees.add(intervention);
    }

    /**
     * Calcule les heures restantes à planifier pour une unité d'enseignement et un type d'intervention donnés.
     *
     * @param type Le type d'intervention (CM, TD, TP)
     * @param ue   L'unité d'enseignement concernée
     * @return Le nombre d'heures restantes à planifier
     */
    public int heuresRestantesAPlanifier(TypeIntervention type, UE ue) {
        int heuresPlanifiees = interventionsPlanifiees.stream()
                .filter(i -> i.getUe().equals(ue) && i.getType().equals(type))
                .mapToInt(Intervention::getHeures)
                .sum();

        return serviceAttribue.getHeuresPourUE(ue, type) - heuresPlanifiees;
    }
}
