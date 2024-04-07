package br.com.rcaneppele.clinica.domain.paciente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Paciente findByLogin(String login);

}
