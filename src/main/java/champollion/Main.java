package champollion;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            // Création des objets principaux
            Enseignant professeur = new Enseignant("Alice Dupont", "alice.dupont@example.com");
            Salle sallePrincipale = new Salle("Salle Informatique", 40);
            UE uml = new UE("UML et conception logicielle");
            UE java = new UE("Programmation en Java");

            // Ajout des enseignements pour l'enseignant
            professeur.ajouterEnseignement(uml, 12, 8, 5);  // 12h CM, 8h TD, 5h TP
            professeur.ajouterEnseignement(java, 6, 10, 4);  // 6h CM, 10h TD, 4h TP

            // Création et ajout d'interventions
            Intervention intervention1 = new Intervention(uml, TypeIntervention.TD, new Date(), 4, sallePrincipale);
            Intervention intervention2 = new Intervention(java, TypeIntervention.CM, new Date(), 6, sallePrincipale);

            professeur.ajouterIntervention(intervention1);
            professeur.ajouterIntervention(intervention2);

            // Affichage des informations pour chaque UE
            System.out.println("=== Informations Enseignant ===");
            System.out.println("Total des heures prévues : " + professeur.calculerHeuresPrevues());
            System.out.println("Heures prévues pour 'UML' : " + professeur.calculerHeuresPrevuesPourUE(uml));
            System.out.println("Heures prévues pour 'Java' : " + professeur.calculerHeuresPrevuesPourUE(java));

            System.out.println("=== Heures Restantes à Planifier ===");
            System.out.println("UML, TD : " + professeur.heuresRestantesAPlanifier(TypeIntervention.TD, uml));
            System.out.println("Java, TP : " + professeur.heuresRestantesAPlanifier(TypeIntervention.TP, java));

        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}
