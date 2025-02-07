package sn.l3l2i.gestionEnseignants.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.l3l2i.gestionEnseignants.models.Enseignant;
import sn.l3l2i.gestionEnseignants.service.EnseignantService;

import java.util.List;

@RestController
@RequestMapping("/enseignants")
public class EnseignantController {

	@Autowired
	private EnseignantService enseignantService;

	@PostMapping
	public ResponseEntity<Enseignant> ajouterEnseignant(@RequestBody Enseignant enseignant) {
		Enseignant nouvelEnseignant = enseignantService.ajouterEnseignant(enseignant);
		return ResponseEntity.status(HttpStatus.CREATED).body(nouvelEnseignant);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Enseignant> trouverEnseignantParId(@PathVariable Long id) {
		Enseignant enseignant = enseignantService.trouverEnseignantParId(id);
		return ResponseEntity.ok(enseignant);

	}

	@GetMapping
	public ResponseEntity<List<Enseignant>> trouverTousLesEnseignants() {
		List<Enseignant> enseignants = enseignantService.trouverTousLesEnseignants();
		return ResponseEntity.ok(enseignants);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> supprimerEnseignant(@PathVariable Long id) {
		enseignantService.supprimerEnseignant(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}