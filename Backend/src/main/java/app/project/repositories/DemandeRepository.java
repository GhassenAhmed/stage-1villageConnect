package app.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.project.entities.Demande;

public interface DemandeRepository extends JpaRepository<Demande, Long>{

}
