package com.casaSolaire.repository;

import com.casaSolaire.models.Category;
import com.casaSolaire.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
