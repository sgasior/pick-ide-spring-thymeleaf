package pl.edu.kopalniakodu.pickide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.kopalniakodu.pickide.domain.Alternative;

public interface AlternativeRepository extends JpaRepository<Alternative, Long> {
}
