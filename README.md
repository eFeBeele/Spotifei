# 🎧 Spotifei - Plataforma de Informações de Áudios Digitais

Bem-vindo ao **Spotifei**, seu catálogo digital de músicas e podcasts! Inspirado no Spotify, este projeto em Java permite que usuários e administradores naveguem e gerenciem informações detalhadas sobre seus áudios favoritos.
![feium título](https://github.com/user-attachments/assets/f7d54106-2e6b-459e-a448-873c2f821e6d)
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

## 🚀 Como Executar e Utilizar o Spotifei

Para começar a usar o **Spotifei**, siga estes passos detalhados.

### 1. Primeiros Passos - Como Executar

1.  **Banco de Dados (Configuração Existente)**:
    * Certifique-se de que você tenha acesso ao servidor **PostgreSQL** com as credenciais fornecidas para o projeto.
    * O banco de dados necessário já deve estar configurado e populado com as tabelas.
2.  **Projeto Java (NetBeans)**:
    * Clone este repositório para o seu computador.
    * Abra o projeto no **NetBeans IDE**.
    * Verifique se a biblioteca **JDBC do PostgreSQL** está adicionada ao projeto. Caso contrário, adicione o JAR correspondente nas configurações de bibliotecas do projeto.
    * **Importante:** As configurações de conexão com o banco de dados (URL, usuário, senha) estão definidas diretamente no código-fonte Java. Certifique-se de que essas informações estejam corretas para o seu ambiente de acesso ao banco de dados.
3.  **Execução (NetBeans)**:
    * No NetBeans, localize a classe principal da aplicação (provavelmente `Spotifei.java` ou `Administrador.java`).
    * Clique com o botão direito sobre essa classe e selecione "**Executar Arquivo**" para iniciar a aplicação.

---

### 2. Cadastrar e Acessar a Plataforma

Ao iniciar a aplicação, você verá a tela de login. Você tem duas opções para começar:

* **Criar uma Nova Conta (Usuário Comum):**
    *  Na tela inicial, clique no botão "**Cadastrar**".
    *  Preencha todos os campos solicitados, como seu nome, e-mail e senha.
    *  Após preencher, clique novamente no botão "**Cadastrar**" para finalizar o registro.
    *  Você será redirecionado para a tela inicial. Clique no botão "**Login**" e utilize suas credenciais recém-criadas para acessar o Spotifei.

* **Acessar como Administrador (Perfil Pré-existente):**
    *  Na tela inicial, clique no botão "**Login**".
    *  Insira as seguintes credenciais:
        * **E-mail:** `adm@email.com`
        * **Senha:** `123`
    *  Clique em "**Login**" para entrar na plataforma com acesso de administrador.

---

### 3. Funcionalidades Detalhadas (Modo Usuário)

Após fazer login como usuário, você poderá:

* **Exploração de Conteúdo Musical:**
    * Use a **barra de busca** para encontrar músicas digitando o **título**, o nome do **artista** ou o **gênero**.
    * Os resultados da busca exibirão os detalhes das músicas encontradas.
* **Interação Musical e Navegação:**
    * Para adicionar uma música à sua lista de favoritos, clique no botão "**Curtir**".
    * Para remover uma música dos seus favoritos, clique em "**Descurtir**".
    * Para navegar entre as músicas (seja nos resultados de uma busca ou dentro de uma playlist), utilize os botões "**Anterior**" e "**Próxima**".
* **Gerenciamento de Playlists:**
    * Acesse a seção de **Playlists**.
    * Você pode "**Criar Nova Playlist**", "**Editar**" as playlists existentes ou "**Excluir**" aquelas que não deseja mais.
    * Para adicionar ou remover músicas de uma playlist, selecione a playlist desejada e utilize as opções de "**Adicionar Música**" ou "**Remover Música**".
* **Histórico e Preferências:**
    * Consulte a seção de "**Histórico**" para ver as últimas 10 músicas que você pesquisou.
    * Acesse "**Músicas Curtidas**" para visualizar sua lista de músicas favoritas.
    * Acesse "**Músicas Descurtidas**" para ver as músicas que você removeu dos seus favoritos.

---

### 4. Funcionalidades Detalhadas (Modo Administrador)

Ao acessar o Spotifei com as credenciais de administrador (`adm@email.com` / `123`), você terá acesso a recursos adicionais para gerenciar o sistema:

* **Gestão de Conteúdo:**
    * **Adicionar Músicas:** Encontre a opção para "**Adicionar Nova Música**" e preencha os detalhes como título, artista, gênero, etc.
    * **Remover Músicas:** Na lista de músicas, selecione a música que deseja excluir e clique em "**Remover Música**".
    * **Registrar Artistas:** Utilize a seção designada (pode ser "Gerenciar Artistas") para adicionar novos artistas ao catálogo da plataforma.
* **Consulta de Usuários:**
    * Acesse a seção de "**Usuários Cadastrados**" para visualizar uma lista completa de todos os usuários registrados no Spotifei, juntamente com suas informações básicas.
* **Estatísticas do Sistema:**
    * Procure por um painel ou seção de "**Estatísticas**" onde você poderá acompanhar dados importantes, como:
        * As 5 músicas mais curtidas pelos usuários.
        * As 5 músicas mais descurtidas.
        * O número total de usuários registrados na plataforma.
        * O número total de músicas disponíveis no catálogo.

---

## 📹 Vídeos

* **SPOTIFEI (USUÁRIO)** - https://youtu.be/Ueet8l9WAmE?si=AJEJlkRJ3QVzF34_
* **ADMINISTRADOR** - https://youtu.be/77ldfoq4z8Q

---

## 👨‍💻 Desenvolvedores

* **Fernando Bordin Lopes** - RA: 24.124.003-5
* **Arthur Barbosa Zanvetor** - RA: 24.124.063-9

Esperamos que você aproveite o Spotifei! Se tiver alguma dúvida ou sugestão, não hesite em nos contatar.
