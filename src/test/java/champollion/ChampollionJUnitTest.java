package champollion;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class ChampollionJUnitTest {
	private Enseignant enseignant;
	private UE uml, java;

	@BeforeEach
	public void setUp() {
		enseignant = new Enseignant("Jean Dupont", "jean.dupont@gmail.com");
		uml = new UE("Modélisation UML");
		java = new UE("Programmation Java");
	}

	@Test
	public void unNouvelEnseignantDevraitAvoirZeroHeures() {
		assertEquals(0, enseignant.calculerHeuresPrevues(),
				"Un nouvel enseignant doit avoir 0 heures prévues.");
	}

	@Test
	public void ajouterDesHeuresAugmenteLeService() {
		enseignant.ajouterEnseignement(uml, 10, 10, 0); // 10h CM, 10h TD
		assertEquals(25, enseignant.calculerHeuresPrevuesPourUE(uml),
				"Les heures prévues pour UML devraient être 25.");
	}

	@Test
	public void ajoutDInterventionAvecDepassement() {
		enseignant.ajouterEnseignement(uml, 0, 0, 5); // 5h TP
		Intervention intervention = new Intervention(uml, TypeIntervention.TP, new Date(), 6, new Salle("Salle TP", 30));
		Exception exception = assertThrows(Exception.class, () -> enseignant.ajouterIntervention(intervention),
				"Une exception doit être levée pour une intervention dépassant les heures prévues.");
		assertEquals("Le volume horaire prévu est dépassé.", exception.getMessage());
	}

	@Test
	public void ajoutDInterventionSansHeurePrevue() {
		Intervention interventionNonPrevue = new Intervention(uml, TypeIntervention.TD, new Date(), 4, new Salle("Salle TD", 40));
		Exception exception = assertThrows(Exception.class, () -> enseignant.ajouterIntervention(interventionNonPrevue),
				"Une exception doit être levée si aucune heure n'est prévue pour ce type d'intervention.");
		assertEquals("Aucune heure prévue pour ce type d'intervention.", exception.getMessage());
	}

	@Test
	public void heuresRestantesSansAucuneIntervention() {
		enseignant.ajouterEnseignement(uml, 8, 0, 0); // 8h CM
		int heuresRestantes = enseignant.heuresRestantesAPlanifier(TypeIntervention.CM, uml);
		assertEquals(8, heuresRestantes, "Toutes les heures prévues doivent rester disponibles.");
	}

	@Test
	public void heuresRestantesAvecInterventionCorrespondante() {
		enseignant.ajouterEnseignement(uml, 0, 8, 0); // 8h TD
		Intervention interventionTD = new Intervention(uml, TypeIntervention.TD, new Date(), 3, new Salle("Salle TD", 40));
		try {
			enseignant.ajouterIntervention(interventionTD);
		} catch (Exception e) {
			fail("Aucune exception ne doit être levée pour une intervention valide.");
		}
		int heuresRestantes = enseignant.heuresRestantesAPlanifier(TypeIntervention.TD, uml);
		assertEquals(5, heuresRestantes, "Il doit rester 5 heures de TD à planifier pour UML.");
	}

	@Test
	public void casMixteAvecInterventionsCorrespondantesEtNonCorrespondantes() {
		enseignant.ajouterEnseignement(uml, 5, 5, 0); // 10h pour UML
		enseignant.ajouterEnseignement(java, 10, 0, 0); // 10h pour Java
		Intervention interventionUMLTD = new Intervention(uml, TypeIntervention.TD, new Date(), 3, new Salle("Salle UML", 30));
		Intervention interventionJavaCM = new Intervention(java, TypeIntervention.CM, new Date(), 5, new Salle("Salle Java", 40));
		try {
			enseignant.ajouterIntervention(interventionUMLTD);
			enseignant.ajouterIntervention(interventionJavaCM);
		} catch (Exception e) {
			fail("Aucune exception ne doit être levée pour des interventions valides.");
		}
		int heuresRestantesUMLTD = enseignant.heuresRestantesAPlanifier(TypeIntervention.TD, uml);
		assertEquals(2, heuresRestantesUMLTD, "Il doit rester 2 heures de TD à planifier pour UML.");
		int heuresRestantesJavaCM = enseignant.heuresRestantesAPlanifier(TypeIntervention.CM, java);
		assertEquals(5, heuresRestantesJavaCM, "Il doit rester 5 heures de CM à planifier pour Java.");
	}
}
