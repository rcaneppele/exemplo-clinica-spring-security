package br.com.rcaneppele.clinica.domain.medico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Medico findByLogin(String login);

}
