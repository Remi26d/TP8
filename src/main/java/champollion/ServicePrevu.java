package champollion;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe gérant les services prévus pour un enseignant.
 */
public class ServicePrevu {
    private final Map<UE, Map<TypeIntervention, Integer>> enseignements = new HashMap<>();

    // Ajoute un enseignement pour une UE et un type d'intervention
    public void ajouterEnseignement(UE ue, TypeIntervention type, int heures) {
        if (heures < 0) {
            throw new IllegalArgumentException("Le volume horaire ne peut pas être négatif.");
        }
        enseignements.computeIfAbsent(ue, k -> new HashMap<>())
                .merge(type, heures, Integer::sum);
    }


    public int getHeuresPourUE(UE ue, TypeIntervention type) {
        return enseignements.getOrDefault(ue, new HashMap<>()).getOrDefault(type, 0);
    }

    public int getHeuresEquivalentesPourUE(UE ue) {
        return enseignements.getOrDefault(ue, Map.of()).entrySet().stream()
                .mapToInt(e -> {
                    int heures = e.getValue();
                    return switch (e.getKey()) {
                        case CM -> (int) Math.round(heures * 1.5);  // Arrondi par type
                        case TD -> heures;
                        case TP -> (int) Math.round(heures * 0.75); // Arrondi par type
                    };
                })
                .sum();
    }

    public int heuresEquivalentes() {
        return enseignements.values().stream()
                .flatMap(m -> m.entrySet().stream())
                .mapToInt(e -> {
                    int heures = e.getValue();
                    return switch (e.getKey()) {
                        case CM -> (int) Math.round(heures * 1.5);  // Arrondi par type
                        case TD -> heures;
                        case TP -> (int) Math.round(heures * 0.75); // Arrondi par type
                    };
                })
                .sum();
    }

}
