🩺 Medflix — Gestão Médica e Autenticação Multi-Realm
O Medflix é uma API REST desenvolvida em Java com Spring Boot para o gerenciamento de clínicas e hospitais. O sistema lida com o cadastro de médicos, pacientes e administradores, garantindo que cada perfil tenha seu próprio escopo de permissões.

O grande destaque deste projeto é a sua arquitetura de segurança: em vez de misturar todos os usuários em uma única estrutura, o Medflix utiliza uma abordagem Multi-Realm. Isso significa que Administradores, Médicos e Pacientes possuem fluxos de autenticação, tabelas e filtros de segurança totalmente isolados, garantindo um sistema muito mais robusto e seguro.

🛠️ Tecnologias Utilizadas
Java 17+ e Spring Boot 3: Base sólida e moderna para a API.

Spring Security & JWT (Auth0): Controle de acesso stateless baseado em tokens.

Spring Data JPA / Hibernate: Para persistência de dados e comunicação com o banco.

BCrypt: Criptografia forte para senhas e chaves de acesso.

Jakarta Validation: Validação de dados de entrada (@NotBlank, @Email, etc.) direto nos DTOs.

Lombok: Para manter o código limpo e sem boilerplate (getters, setters e construtores).

🔐 Como funciona a Segurança (O diferencial do projeto)
Para evitar vazamento de privilégios, o sistema implementa múltiplos AuthenticationManager. O Spring Security foi configurado para interceptar requisições através de três filtros dedicados (SecurityFilterAdmin, SecurityFilterMedico e SecurityFilterUsuario).

Na prática, isso significa que um token gerado para um Paciente jamais será aceito em uma rota exclusiva para Médicos, e vice-versa. Cada perfil é validado contra seu próprio repositório no banco de dados.

👨‍⚕️ O Fluxo de Cadastro Médico
Para garantir que apenas profissionais autorizados atuem no sistema, o cadastro de um médico não é aberto ao público. Ele segue um fluxo de 4 etapas:

Pré-cadastro (Admin): O Administrador registra o CRM do médico e gera uma chave de acesso provisória criptografada.

Ativação: O médico consome um endpoint passando seu CRM e a chave de acesso para provar quem é.

Credenciais: Após a ativação, o médico define seu nome de usuário e uma senha segura.

Perfil Profissional: Já autenticado com seu Token JWT, o médico completa o perfil informando sua especialidade e se atua como cirurgião

Pacientes e Administradores possuem fluxos mais diretos de cadastro e login.
