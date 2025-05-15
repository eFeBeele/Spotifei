# Spotifei

---

Bem-vindo ao **Spotifei**, uma plataforma de informações de áudios digitais inspirada no Spotify! Este projeto Java permite que usuários e administradores gerenciem e interajam com um catálogo de músicas e podcasts.

## 🎯 Objetivo

O objetivo principal do Spotifei é criar uma plataforma robusta para o compartilhamento de informações sobre áudios digitais, como músicas e podcasts. Embora não haja reprodução de áudio, o sistema foca na gestão e consulta de metadados, oferecendo uma experiência similar à de um serviço de streaming de áudio.

## 🚀 Tecnologias Utilizadas

* **Java Swing**: Para a construção da interface gráfica do usuário (GUI).
* **JDBC (Java Database Connectivity)**: Para a conexão e manipulação do banco de dados.
* **PostgreSQL**: Sistema de gerenciamento de banco de dados relacional.
* **MVC (Model-View-Controller)**: Arquitetura de software para separar a lógica de negócios da interface do usuário.

## ✨ Funcionalidades

### 👤 Usuário

* **Cadastro e Login**:
    * Cadastrar novo usuário.
    * Realizar login na plataforma.
* **Busca e Visualização de Músicas**:
    * Buscar músicas por nome, artista ou gênero.
    * Listar informações detalhadas das músicas buscadas.
* **Interação com Músicas**:
    * Curtir e descurtir músicas.
* **Gerenciamento de Playlists**:
    * Criar, editar e excluir playlists.
    * Adicionar e remover músicas de playlists existentes.
* **Visualização de Histórico**:
    * Visualizar as últimas 10 músicas buscadas.
    * Visualizar a lista de músicas curtidas.
    * Visualizar a lista de músicas descurtidas.

### 👑 Administrador

* **Login**:
    * Realizar login como administrador.
* **Gerenciamento de Conteúdo**:
    * Cadastrar e excluir músicas do sistema.
    * Cadastrar novos artistas.
* **Consulta de Usuários**:
    * Consultar informações sobre usuários cadastrados.
* **Estatísticas do Sistema**:
    * Visualizar as Top 5 músicas mais curtidas pelos usuários.
    * Visualizar as Top 5 músicas mais descurtidas pelos usuários.
    * Visualizar o total de usuários cadastrados.
    * Visualizar o total de músicas disponíveis no sistema.

---

## 👨‍💻 Alunos

* **Fernando Bordin Lopes** - RA: 24.124.003-5
* **Arthur Barbosa Zanvetor** - RA: 24.124.063-9

---

## 🛠️ Como Executar o Projeto

1.  **Configuração do Banco de Dados**:
    * Certifique-se de ter o PostgreSQL instalado e configurado.
    * Crie um banco de dados com o nome `spotifei` (ou o nome que preferir, ajustando nas configurações do projeto).
    * Execute os scripts SQL para criar as tabelas necessárias (usuários, músicas, artistas, playlists, etc.).
2.  **Configuração do Projeto Java**:
    * Clone este repositório para o seu ambiente local.
    * Importe o projeto em sua IDE Java (IntelliJ IDEA, Eclipse, NetBeans, etc.).
    * Adicione as dependências JDBC para PostgreSQL ao seu projeto (geralmente via Maven ou Gradle, ou adicionando o JAR manualmente).
    * Atualize as configurações de conexão com o banco de dados no código (usuário, senha, URL do banco de dados).
3.  **Execução**:
    * Compile e execute a aplicação principal a partir da sua IDE.

---

Sinta-se à vontade para explorar e contribuir com o projeto Spotifei! Se tiver alguma dúvida ou sugestão, entre em contato.
