# API REST com Spring Boot e Spring Security usando JWT

Este é um projeto de API REST em Java usando Spring Boot para demonstrar como implementar autenticação e autorização stateless usando Spring Security com JSON Web Tokens (JWT). Além disso, o projeto inclui classes para representar usuários do tipo Médico e Paciente, ambos capazes de fazer login e acessar funcionalidades específicas de acordo com o seu perfil.

## Funcionalidades

- Implementação de autenticação/autorização stateless usando Spring Security com JWT;
- Utilização de classes Medico e Paciente para representar diferentes tipos de usuários.

## Pré-requisitos

- Java 17 ou superior
- Maven
- MySQL (configurável no arquivo `application.properties`)
- Postman ou ferramenta similar para testar os endpoints

## Como executar

1. Clone o repositório:

    ```
    git clone https://github.com/rcaneppele/exemplo-clinica-spring-security.git
    ```

2. Navegue até o diretório do projeto:

    ```
    cd exemplo-clinica-spring-security
    ```

3. Execute o aplicativo usando Maven:

    ```
    mvn spring-boot:run
    ```

4. O aplicativo estará disponível em `http://localhost:8080`.

## Endpoints

### Login

- **POST** `/login`
    - Corpo da requisição:
      ```json
      {
        "login": "cpf do paciente ou crm do medico",
        "senha": "senha"
      }
      ```
    - Retorna um token JWT válido para autenticação futura.

### Acesso restrito para Médicos

- **GET** `/medicos`
    - Retorna informações específicas para usuários do tipo Médico.

### Acesso restrito para Pacientes

- **GET** `/pacientes`
    - Retorna informações específicas para usuários do tipo Paciente.

## Banco de dados

A estrutura do banco de dados será criada automaticamente pelo Flyway, de acordo com as migrations localizadas em `src/main/resources/db/migration`.

Para fins de testes, acessa o banco de dados e execute os seguintes comandos SQL:

```
insert into usuarios(id, login, senha) values(1, '00011122233', '$2a$10$1g.8vUZgxS10V0FcORrGyOUTFroyioIPHyGSZWSQSNfe.DNQdU19C');
insert into pacientes(id, nome, email, cpf) values(1, 'Paciente beltrana', 'paciente@email.com.br', '00011122233');

insert into usuarios(id, login, senha) values(2, '112233', '$2a$10$1g.8vUZgxS10V0FcORrGyOUTFroyioIPHyGSZWSQSNfe.DNQdU19C');
insert into medicos(id, nome, email, crm) values(2, 'Medico fulano', 'medico@email.com.br', '112233');
```

Será inserido um médico com CRM (login): **112233** e senha **123456**. Também será inserido um paciente com CPF (login): **00011122233** e senha **123456**
