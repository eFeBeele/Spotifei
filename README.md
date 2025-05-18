# 🎧 Spotifei - Plataforma de Informações de Áudios Digitais

Bem-vindo ao **Spotifei**, seu catálogo digital de músicas e podcasts! Inspirado no Spotify, este projeto em Java permite que usuários e administradores naveguem e gerenciem informações detalhadas sobre seus áudios favoritos.

## 🎯 Nosso Objetivo

O principal objetivo do Spotifei é oferecer uma plataforma eficiente para gerenciar e consultar metadados de áudios digitais, como músicas e podcasts. Focamos na organização e apresentação de informações relevantes, proporcionando uma experiência informativa similar à de um serviço de streaming.

## 🛠️ Tecnologias Empregadas

* **Interface Gráfica**: Java Swing
* **Banco de Dados**: JDBC (Java Database Connectivity)
* **SGBD**: PostgreSQL
* **Arquitetura**: MVC (Model-View-Controller)

## ✨ Funcionalidades Detalhadas

### 👤 Usuário

#### 1. Cadastro e Login
* Crie sua conta no Spotifei.
* Acesse a plataforma com suas credenciais.

#### 2. Exploração de Conteúdo Musical
* Busque músicas por título, artista ou gênero.
* Visualize os detalhes de cada música encontrada.

#### 3. Interação Musical
* Marque suas músicas favoritas com um "curtir".
* Remova músicas da sua lista de favoritos com "descurtir".

#### 4. Gerenciamento de Playlists
* Crie, edite e exclua suas playlists personalizadas.
* Adicione e remova músicas de suas playlists.

#### 5. Histórico e Preferências
* Veja as últimas 10 músicas que você buscou.
* Consulte sua lista de músicas curtidas.
* Acesse sua lista de músicas descurtidas.

### 👑 Administrador

#### 1. Acesso Administrativo
* Faça login com suas credenciais de administrador.

#### 2. Gestão de Conteúdo
* Adicione novas músicas ao sistema.
* Remova músicas existentes do catálogo.
* Registre novos artistas na plataforma.

#### 3. Consulta de Usuários
* Visualize informações sobre os usuários cadastrados.

#### 4. Estatísticas do Sistema
* Descubra as 5 músicas mais curtidas pelos usuários.
* Identifique as 5 músicas mais descurtidas.
* Acompanhe o número total de usuários registrados.
* Verifique o número total de músicas disponíveis.

---

## ⚙️ Primeiros Passos - Como Executar

1.  **Banco de Dados**:
    * Certifique-se de que o PostgreSQL esteja instalado e configurado em sua máquina.
    * Crie um banco de dados chamado `spotifei` (ou outro nome, ajustando a configuração no projeto).
    * Execute os scripts SQL fornecidos para criar as tabelas necessárias (usuários, músicas, artistas, playlists, etc.).
2.  **Projeto Java**:
    * Clone este repositório para o seu computador.
    * Abra o projeto em sua IDE Java preferida (IntelliJ IDEA, Eclipse, NetBeans).
    * Adicione a dependência JDBC do PostgreSQL ao seu projeto (via Maven, Gradle ou adicionando o JAR).
    * Modifique as configurações de conexão com o banco de dados no código-fonte (URL, usuário, senha).
3.  **Execução**:
    * Compile e execute a classe principal da aplicação através da sua IDE.

---

## 👨‍💻 Desenvolvedores

* **Fernando Bordin Lopes** - RA: 24.124.003-5
* **Arthur Barbosa Zanvetor** - RA: 24.124.063-9

---

Esperamos que você aproveite o Spotifei! Se tiver alguma dúvida ou sugestão, não hesite em nos contatar.
