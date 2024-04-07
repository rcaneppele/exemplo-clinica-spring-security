package br.com.rcaneppele.clinica.domain.usuario;

import br.com.rcaneppele.clinica.domain.medico.MedicoRepository;
import br.com.rcaneppele.clinica.domain.paciente.PacienteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public AutenticacaoService(MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 11 digitos = CPF, entao eh um paciente fazendo login
        if (username.length() == 11) {
            return pacienteRepository.findByLogin(username);
        }

        return medicoRepository.findByLogin(username);
    }

}
