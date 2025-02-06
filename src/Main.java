import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Genero controlandoGenero = new Genero(0, "", "");
        Filme controlandoFilme = new Filme(0, "", "", new Genero(0, "", ""), 0);
        Ator controlandoAtor = new Ator("", "", "", 0);
        Funcionario controlandoFuncionario = new Funcionario("", "", "", 0, null);
        TipoAssento controlandoTipoAssento = new TipoAssento(0, "", "");
        FilmeAtor controlandoFilmeAtor = new FilmeAtor(0, new Ator("", "", "", 0), new Filme(0, "", "", controlandoGenero, 0), "", true);
        Assento controlandoAssento = new Assento(0, controlandoTipoAssento);
        Sala controlandoSala = new Sala(0, 0, "", "");
        SalaAssento controlandoSalaAssento = new SalaAssento(0, controlandoAssento, controlandoSala);
        Sessao controlandoSessao = new Sessao(0, new Date(), controlandoFilme, controlandoSala, controlandoFuncionario, "");
        Ingresso controlandoIngresso = new Ingresso(0, 0.0, null, null);

        ArrayList<Genero> generos = controlandoGenero.listarGeneros(new ArrayList<>());
        ArrayList<Filme> filmes = controlandoFilme.listarFilmes(new ArrayList<>(), generos);
        ArrayList<Ator> atores = controlandoAtor.listarAtores(new ArrayList<>());
        ArrayList<Funcionario> funcionarios = controlandoFuncionario.listarFuncionarios(new ArrayList<>());
        ArrayList<TipoAssento> tiposAssentos = controlandoTipoAssento.listarTiposDeAssentos(new ArrayList<>());
        ArrayList<FilmeAtor> filmesAtores = controlandoFilmeAtor.listarFilmeAtor(new ArrayList<>(), atores, filmes);
        ArrayList<Assento> assentos = controlandoAssento.listarAssentos(new ArrayList<>(), tiposAssentos);
        ArrayList<Sala> salas = controlandoSala.listarSalas(new ArrayList<>());
        ArrayList<SalaAssento> salaAssentos = controlandoSalaAssento.listarSalaAssento(new ArrayList<>(), assentos,salas);
        ArrayList<Sessao> sessoes = controlandoSessao.listarSessoes(new ArrayList<>(), filmes, salas, funcionarios);
        ArrayList<Ingresso> ingressos = controlandoIngresso.listarIngressos(new ArrayList<>(), salaAssentos, sessoes);

        int menuDeOpcoesParaControlar;

        do {
            System.out.println("---------------------------------------------------------------");
            System.out.println("SISTEMA DE CINEMA COM ESCRITA E LEITURA DE ARQUIVOS.TXT");
            System.out.println("-------------------MENU DE GENEROS-------------------");
            System.out.println("1 - Cadastrar Dados De Gênero");
            System.out.println("2 - Listar Gêneros");
            System.out.println("3 - Consultar Gênero Pelo ID");
            System.out.println("4 - Editar Gêneros");

            System.out.println("-------------------MENU DE FILMES-------------------");
            System.out.println("5 - Cadastrar Dados De Filme");
            System.out.println("6 - Listar Filmes");
            System.out.println("7 - Consultar Filme Pelo ID");
            System.out.println("8 - Editar Filmes");

            System.out.println("-------------------MENU DE ATORES-------------------");
            System.out.println("9 - Cadastrar Dados De Atores");
            System.out.println("10 - Listar Atores");
            System.out.println("11 - Consultar Ator Pelo Registro");
            System.out.println("12 - Editar Atores");

            System.out.println("-------------------MENU DE FUNCIONARIOS-------------------");
            System.out.println("13 - Cadastrar Dados De Funcionário");
            System.out.println("14 - Listar Funcionários");
            System.out.println("15 - Consultar Funcionário Pela Matrícula");
            System.out.println("16 - Editar Funcionários");

            System.out.println("-------------------MENU DE TIPOS DE ASSENTOS-------------------");
            System.out.println("17 - Cadastrar Dados De Tipo De Assento");
            System.out.println("18 - Listar Tipo De Assento");
            System.out.println("19 - Consultar Tipo De Assento Pelo ID");
            System.out.println("20 - Editar Tipo De Assento");

            System.out.println("-------------------MENU DE FILME-ATOR-------------------");
            System.out.println("21 - Cadastrar Dados De Filme Ator");
            System.out.println("22 - Listar Filme-Ator");
            System.out.println("23 - Consultar Filme-Ator");
            System.out.println("24 - Editar Filme-Ator");

            System.out.println("-------------------MENU DE ASSENTOS-------------------");
            System.out.println("25 - Cadastrar Dados De Assentos");
            System.out.println("26 - Listar Assentos");
            System.out.println("27 - Consultar Assentos");
            System.out.println("28 - Editar Assentos");

            System.out.println("-------------------MENU DE SALAS-------------------");
            System.out.println("29 - Cadastrar Dados De Salas");
            System.out.println("30 - Listar Salas");
            System.out.println("31 - Consultar Salas");
            System.out.println("32 - Editar Salas");

            System.out.println("-------------------MENU DE SALA-ASSENTO-------------------");
            System.out.println("33 - Cadastrar Dados De Sala-Assento");
            System.out.println("34 - Listar Sala-Assento");
            System.out.println("35 - Consultar Sala-Assento");
            System.out.println("36 - Editar Sala-Assento");

            System.out.println("-------------------MENU DE SESSÕES-------------------");
            System.out.println("37 - Cadastrar Dados de Sessão");
            System.out.println("38 - Listar Sessões");
            System.out.println("39 - Consultar Sessão");
            System.out.println("40 - Editar Sessão");

            System.out.println("-------------------MENU DE INGRESSOS-------------------");
            System.out.println("41 - Cadastrar Dados De Ingresso");
            System.out.println("42 - Listar Ingressos");
            System.out.println("43 - Consultar Ingresso");
            System.out.println("44 - Editar Ingresso");

            System.out.println("--------------------------OUTRO-------------------------------");
            System.out.println("0 - Sair do menu de opções");
            System.out.println("---------------------------------------------------------------");

            menuDeOpcoesParaControlar = scanner.nextInt();
            scanner.nextLine();

            switch (menuDeOpcoesParaControlar) {
                case 1:
                    System.out.println("ID do Gênero: ");
                    int idGenero = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Descrição do Gênero: ");
                    String descricaoGenero = scanner.nextLine();
                    System.out.println("Status do Gênero: ");
                    String statusGenero = scanner.nextLine();

                    Genero novoGenero = new Genero(idGenero, descricaoGenero, statusGenero);
                    if (novoGenero.cadastrarGeneros(novoGenero)) {
                        generos.add(novoGenero);
                        System.out.println("Gênero cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar o gênero.");
                    }
                    break;

                case 2:
                    for (Genero genero : generos) {
                        System.out.println(genero.toString());
                    }
                    break;

                case 3:
                    System.out.println("Digite o ID do Gênero para consulta: ");
                    int idConsultaGenero = scanner.nextInt();
                    Genero generoConsultado = controlandoGenero.consultarGeneros(generos, idConsultaGenero);
                    if (generoConsultado != null) {
                        System.out.println("ID: " + generoConsultado.getId() + "; Descrição: "
                                + generoConsultado.getDescricao() + "; Status: " + generoConsultado.getStatus());
                    } else {
                        System.out.println("Gênero não encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("Digite o ID do Gênero para editar: ");
                    int idEditarGenero = scanner.nextInt();
                    scanner.nextLine();

                    Genero generoParaEditar = controlandoGenero.consultarGeneros(generos, idEditarGenero);
                    if (generoParaEditar != null) {
                        System.out.println("Nova Descrição: ");
                        String novaDescricao = scanner.nextLine();
                        System.out.println("Novo Status: ");
                        String novoStatus = scanner.nextLine();

                        generoParaEditar.setDescricao(novaDescricao);
                        generoParaEditar.setStatus(novoStatus);

                        if (controlandoGenero.editarGeneros(generoParaEditar)) {
                            System.out.println("Gênero editado com sucesso!");
                        } else {
                            System.out.println("Erro ao editar o gênero.");
                        }
                    } else {
                        System.out.println("Gênero não encontrado.");
                    }
                    break;

                case 5:
                    System.out.println("ID do Filme: ");
                    int idFilme = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Título do Filme: ");
                    String tituloFilme = scanner.nextLine();
                    System.out.println("Status do Filme: ");
                    String statusFilme = scanner.nextLine();
                    System.out.println("ID do Gênero do Filme: ");
                    int idGeneroFilme = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Classificação do Filme: ");
                    int classificacaoFilme = scanner.nextInt();
                    scanner.nextLine();

                    Genero generoDoFilme = controlandoGenero.consultarGeneros(generos, idGeneroFilme);
                    if (generoDoFilme != null) {
                        Filme novoFilme = new Filme(idFilme, tituloFilme, statusFilme, generoDoFilme,classificacaoFilme);
                        if (novoFilme.cadastrarFilmes(novoFilme)) {
                            filmes.add(novoFilme);
                            System.out.println("Filme cadastrado com sucesso!");
                        } else {
                            System.out.println("Erro ao cadastrar o filme.");
                        }
                    } else {
                        System.out.println("Gênero informado não encontrado.");
                    }
                    break;

                case 6:
                    for (Filme filme : filmes) {
                        System.out.println(filme.toString());
                    }
                    break;

                case 7:
                    System.out.println("Digite o ID do Filme para consulta: ");
                    int idConsultaFilme = scanner.nextInt();
                    Filme filmeConsultado = controlandoFilme.consultarFilmes(filmes, idConsultaFilme);
                    if (filmeConsultado != null) {
                        System.out.println(
                                "ID: " + filmeConsultado.getIdFilme() + "; Título: " + filmeConsultado.getTitulo()
                                        + "; Status: " + filmeConsultado.getStatus() + "; Gênero: "
                                        + filmeConsultado.getGenero().getId()
                                        + "; Classificação: " + filmeConsultado.getClassificacao());
                    } else {
                        System.out.println("Filme não encontrado.");
                    }
                    break;

                case 8:
                    System.out.println("Digite o ID do Filme para editar: ");
                    int idEditarFilme = scanner.nextInt();
                    scanner.nextLine();

                    Filme filmeParaEditar = controlandoFilme.consultarFilmes(filmes, idEditarFilme);
                    if (filmeParaEditar != null) {
                        System.out.println("Novo Título: ");
                        String novoTitulo = scanner.nextLine();
                        System.out.println("Novo Status: ");
                        String novoStatusFilme = scanner.nextLine();
                        System.out.println("ID do Novo Gênero: ");
                        int novoIdGeneroFilme = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Nova Classificação: ");
                        int novaClassificacao = scanner.nextInt();
                        scanner.nextLine();

                        Genero novoGeneroFilme = controlandoGenero.consultarGeneros(generos, novoIdGeneroFilme);
                        if (novoGeneroFilme != null) {
                            filmeParaEditar.setTitulo(novoTitulo);
                            filmeParaEditar.setStatus(novoStatusFilme);
                            filmeParaEditar.setGenero(novoGeneroFilme);
                            filmeParaEditar.setClassificacao(novaClassificacao);

                            if (controlandoFilme.editarFilmes(filmeParaEditar, generos)) {
                                System.out.println("Filme editado com sucesso!");
                            } else {
                                System.out.println("Erro ao editar o filme.");
                            }
                        } else {
                            System.out.println("Gênero informado não encontrado.");
                        }
                    } else {
                        System.out.println("Filme não encontrado.");
                    }
                    break;

                case 9:
                    System.out.println("CPF do Ator: ");
                    String cpfAtor = scanner.nextLine();
                    System.out.println("Nome do Ator: ");
                    String nomeDoAtor = scanner.nextLine();
                    System.out.println("Email do Ator: ");
                    String emailDoAtor = scanner.nextLine();
                    System.out.println("Registro do Ator: ");
                    int registroDoAtor = scanner.nextInt();
                    scanner.nextLine();

                    Ator novoAtor = new Ator(cpfAtor, nomeDoAtor, emailDoAtor, registroDoAtor);
                    if (novoAtor.cadastrarAtores(novoAtor)) {
                        atores.add(novoAtor);
                        System.out.println("Ator cadastrado com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar o ator.");
                    }
                    break;

                case 10:
                    for (Ator ator : atores) {
                        System.out.println(ator.toString());
                    }
                    break;

                case 11:
                    System.out.println("Digite o Registro do Ator para consultar: ");
                    int registroConsultaAtor = scanner.nextInt();
                    Ator atorConsultado = controlandoAtor.consultarAtores(atores, registroConsultaAtor);
                    if (atorConsultado != null) {
                        System.out.println(
                                "CPF: " + atorConsultado.getCpf() + "; Nome: " + atorConsultado.getNome() + "; Email: "
                                        + atorConsultado.getEmail() + "; Registro: " + atorConsultado.getRegistro());
                    } else {
                        System.out.println("Ator não encontrado.");
                    }
                    break;
                case 12:
                    System.out.println("Digite o Registro do Ator para editar: ");
                    int registroEditarAtor = scanner.nextInt();
                    scanner.nextLine();

                    Ator atorParaEditar = controlandoAtor.consultarAtores(atores, registroEditarAtor);
                    if (atorParaEditar != null) {
                        System.out.println("Novo CPF: ");
                        String novoCpf = scanner.nextLine();
                        System.out.println("Novo Nome: ");
                        String novoNome = scanner.nextLine();
                        System.out.println("Novo Email: ");
                        String novoEmail = scanner.nextLine();

                        atorParaEditar.setCpf(novoCpf);
                        atorParaEditar.setNome(novoNome);
                        atorParaEditar.setEmail(novoEmail);

                        if (controlandoAtor.editarAtores(atorParaEditar)) {
                            System.out.println("Ator editado com sucesso!");
                        } else {
                            System.out.println("Erro ao editar o ator.");
                        }
                    } else {
                        System.out.println("Ator não encontrado.");
                    }
                    break;

                case 13:
                    System.out.println("CPF do Funcionário: ");
                    String cpfFuncionario = scanner.nextLine();
                    System.out.println("Nome do Funcionário: ");
                    String nomeFuncionario = scanner.nextLine();
                    System.out.println("Email do Funcionário: ");
                    String emailFuncionario = scanner.nextLine();
                    System.out.println("Matrícula do Funcionário: ");
                    int matriculaFuncionario = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Horário de Trabalho: ");
                    String horarioTrabalhoString = scanner.nextLine();

                    try {
                        SimpleDateFormat formatoHorario = new SimpleDateFormat("HH:mm");
                        Date horarioTrabalho = formatoHorario.parse(horarioTrabalhoString);
                        Funcionario novoFuncionario = new Funcionario(cpfFuncionario, nomeFuncionario, emailFuncionario, matriculaFuncionario, horarioTrabalho);
                        if (novoFuncionario.cadastrarFuncionario(novoFuncionario)) {
                            funcionarios.add(novoFuncionario);
                            System.out.println("Funcionário cadastrado com sucesso!");
                        } else {
                            System.out.println("Erro ao cadastrar o funcionário.");
                        }
                    } catch (ParseException e) {
                        System.out.println("Erro: Formato de horário inválido. Use HH:mm.");
                    }
                    break;

                case 14:
                    for (Funcionario funcionariop : funcionarios) {
                        System.out.println(funcionariop.toString());
                    }
                    break;

                case 15:
                    System.out.println("Digite a Matrícula do Funcionário para consulta: ");
                    int matriculaConsultaFuncionario = scanner.nextInt();
                    Funcionario funcionarioConsultado = controlandoFuncionario.consultarFuncionario(funcionarios,
                            matriculaConsultaFuncionario);
                    if (funcionarioConsultado != null) {
                        System.out.println("CPF: " + funcionarioConsultado.getCpf() + "; Nome: "
                                + funcionarioConsultado.getNome()
                                + "; Email: " + funcionarioConsultado.getEmail() + "; Matrícula: "
                                + funcionarioConsultado.getMatricula()
                                + "; Horário de Trabalho: " + funcionarioConsultado.getHorarioTrabalho());
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    break;

                case 16:
                    System.out.println("Digite a Matrícula do Funcionário para editar: ");
                    int matriculaEditarFuncionario = scanner.nextInt();
                    scanner.nextLine();

                    Funcionario funcionarioParaEditar = controlandoFuncionario.consultarFuncionario(funcionarios,
                            matriculaEditarFuncionario);
                    if (funcionarioParaEditar != null) {
                        System.out.println("Novo CPF: ");
                        String novoCpfFuncionario = scanner.nextLine();
                        System.out.println("Novo Nome: ");
                        String novoNomeFuncionario = scanner.nextLine();
                        System.out.println("Novo Email: ");
                        String novoEmailFuncionario = scanner.nextLine();
                        System.out.println("Novo Horário de Trabalho (HH:mm): ");
                        String novoHorarioTrabalhoString = scanner.nextLine();

                        try {
                            SimpleDateFormat formatoHorario = new SimpleDateFormat("HH:mm");
                            Date novoHorarioTrabalho = formatoHorario.parse(novoHorarioTrabalhoString);

                            funcionarioParaEditar.setCpf(novoCpfFuncionario);
                            funcionarioParaEditar.setNome(novoNomeFuncionario);
                            funcionarioParaEditar.setEmail(novoEmailFuncionario);
                            funcionarioParaEditar.setHorarioTrabalho(novoHorarioTrabalho);

                            if (controlandoFuncionario.editarFuncionario(funcionarioParaEditar)) {
                                System.out.println("Funcionário editado com sucesso!");
                            } else {
                                System.out.println("Erro ao editar o funcionário no arquivo.");
                            }
                        } catch (ParseException e) {
                            System.out.println(
                                    "Erro, pois o formato de horário está invalido , coloque horas:minutos por favor");
                        }
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    break;

                case 17:
                    System.out.println("ID do Tipo De Assento: ");
                    int idTipoAssento = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Descrição do Tipo De Assento: ");
                    String descricaoTipoAssento = scanner.nextLine();
                    System.out.println("Status do Tipo De Assento: ");
                    String statusTipoAssento = scanner.nextLine();

                    TipoAssento novoTipoAssento = new TipoAssento(idTipoAssento, descricaoTipoAssento,
                            statusTipoAssento);
                    if (novoTipoAssento.cadastrarTiposDeAssentos(novoTipoAssento)) {
                        tiposAssentos.add(novoTipoAssento);
                        System.out.println("Tipos de assentos cadastrados com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar. ");
                    }
                    break;

                case 18:
                    for (TipoAssento tipoAssento : tiposAssentos) {
                        System.out.println(tipoAssento.toString());
                    }
                    break;

                case 19:
                    System.out.println("Digite o ID do Tipo de assento para consultar: ");
                    int idTipoAssentoConsulta = scanner.nextInt();
                    TipoAssento tiposAssentosConsultado = controlandoTipoAssento.consultarTiposDeAssentos(tiposAssentos,
                            idTipoAssentoConsulta);
                    if (tiposAssentosConsultado != null) {
                        System.out.println("ID: " + tiposAssentosConsultado.getIdTipoAssento() + "; Descrição: "
                                + tiposAssentosConsultado.getDescricao() + "; Status: "
                                + tiposAssentosConsultado.getStatus());
                    } else {
                        System.out.println("Tipo de assento não encontrado.");
                    }
                    break;

                case 20:
                    System.out.println("Digite o ID do Tipo De Assento para editar: ");
                    int idEditarTipoAssento = scanner.nextInt();
                    scanner.nextLine();

                    TipoAssento tipoAssentoParaEditar = controlandoTipoAssento.consultarTiposDeAssentos(tiposAssentos,
                            idEditarTipoAssento);
                    if (tipoAssentoParaEditar != null) {
                        System.out.println("Nova Descrição: ");
                        String novaDescricao = scanner.nextLine();
                        System.out.println("Novo Status: ");
                        String novoStatus = scanner.nextLine();

                        tipoAssentoParaEditar.setDescricao(novaDescricao);
                        tipoAssentoParaEditar.setStatus(novoStatus);

                        if (controlandoTipoAssento.editarTiposDeAssentos(tipoAssentoParaEditar)) {
                            System.out.println("Tipo de assento alterado com sucesso!");
                        } else {
                            System.out.println("Erro ao editar. ");
                        }
                    } else {
                        System.out.println("Tipo não encontrado.");
                    }
                    break;
                case 21:
                    System.out.println("ID do FilmeAtor: ");
                    int idFilmeAtor = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Registro do Ator: ");
                    int registroAtor = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("ID do Filme: ");
                    int idFilmeDoAtor = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Nome do Personagem: ");
                    String nomePersonagem = scanner.nextLine();
                    System.out.println("É principal? (true/false): ");
                    boolean principal = scanner.nextBoolean();
                    scanner.nextLine();

                    Ator atorDoFilme = controlandoAtor.consultarAtores(atores, registroAtor);
                    Filme filmeDoAtor = controlandoFilme.consultarFilmes(filmes, idFilmeDoAtor);

                    if (atorDoFilme != null && filmeDoAtor != null) {
                        FilmeAtor novoFilmeAtor = new FilmeAtor(idFilmeAtor, atorDoFilme, filmeDoAtor, nomePersonagem, principal);
                        if (novoFilmeAtor.cadastrarFilmeAtor(novoFilmeAtor)) {
                            filmesAtores.add(novoFilmeAtor);
                            System.out.println("FilmeAtor cadastrado com sucesso!");
                        } else {
                            System.out.println("Erro ao cadastrar o FilmeAtor.");
                        }
                    } else {
                        System.out.println("Ator ou Filme não encontrado.");
                    }
                    break;

                case 22:
                    for (FilmeAtor filmeAtor : filmesAtores) {
                        System.out.println(filmeAtor.toString());
                    }
                    break;

                case 23:
                    System.out.println("Digite o ID do FilmeAtor para consulta: ");
                    int idConsultaFilmeAtor = scanner.nextInt();
                    scanner.nextLine();

                    FilmeAtor filmeAtorConsultado = controlandoFilmeAtor.consultarFilmeAtor(filmesAtores,
                            idConsultaFilmeAtor);
                    if (filmeAtorConsultado != null) {
                        System.out.println(filmeAtorConsultado.toString());
                    } else {
                        System.out.println("FilmeAtor não encontrado.");
                    }
                    break;

                case 24:
                    System.out.println("Digite o ID do FilmeAtor para editar: ");
                    int idEditarFilmeAtor = scanner.nextInt();
                    scanner.nextLine();

                    FilmeAtor filmeAtorParaEditar = controlandoFilmeAtor.consultarFilmeAtor(filmesAtores,
                            idEditarFilmeAtor);
                    if (filmeAtorParaEditar != null) {
                        System.out.println("Novo Registro do Ator: ");
                        int novoRegistroAtor = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Novo ID do Filme: ");
                        int novoIdFilme = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Novo Nome do Personagem: ");
                        String novoPersonagem = scanner.nextLine();
                        System.out.println("É principal? (true/false): ");
                        boolean novoPrincipal = scanner.nextBoolean();
                        scanner.nextLine();

                        Ator novoFilmeAtor = controlandoAtor.consultarAtores(atores, novoRegistroAtor);
                        Filme novoFilme = controlandoFilme.consultarFilmes(filmes, novoIdFilme);

                        if (novoFilmeAtor != null && novoFilme != null) {
                            filmeAtorParaEditar.setAtor(novoFilmeAtor);
                            filmeAtorParaEditar.setFilme(novoFilme);
                            filmeAtorParaEditar.setPersonagem(novoPersonagem);
                            filmeAtorParaEditar.setPrincipal(novoPrincipal);

                            if (controlandoFilmeAtor.editarFilmeAtor(filmeAtorParaEditar, atores, filmes)) {
                                System.out.println("Filme-Ator editado com sucesso!");
                            } else {
                                System.out.println("Erro ao editar o Filme-Ator.");
                            }
                        } else {
                            System.out.println("Ator ou Filme não encontrado.");
                        }
                    } else {
                        System.out.println("Filme-Ator não encontrado.");
                    }
                    break;

                case 25:
                    System.out.println("ID do Assento: ");
                    int idAssento = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("ID do Tipo De Assento: ");
                    int idparaDefinirTipoDeAssento = scanner.nextInt();
                    scanner.nextLine();

                    TipoAssento tipoAssento = controlandoTipoAssento.consultarTiposDeAssentos(tiposAssentos,
                            idparaDefinirTipoDeAssento);
                    if (tipoAssento != null) {
                        Assento novoAssento = new Assento(idAssento, tipoAssento);
                        if (novoAssento.cadastrarAssento(novoAssento)) {
                            assentos.add(novoAssento);
                            System.out.println("Assento cadastrado com sucesso!");
                        } else {
                            System.out.println("Erro ao cadastrar.");
                        }
                    } else {
                        System.out.println("Tipo de assento informado não encontrado.");
                    }
                    break;

                case 26:
                    for (Assento assento : assentos) {
                        System.out.println(assento.toString());
                    }
                    break;

                case 27:
                    System.out.println("Digite o ID do Assento para consultar: ");
                    int idConsultaAssento = scanner.nextInt();
                    Assento assentoConsultado = controlandoAssento.consultarAssento(assentos, idConsultaAssento);
                    if (assentoConsultado != null) {
                        System.out.println(
                                "ID: " + assentoConsultado.getIdAssento() + "; Tipo de assento: "
                                        + assentoConsultado.getTipoAssento().getIdTipoAssento());
                    } else {
                        System.out.println("Assento não encontrado.");
                    }
                    break;

                case 28:
                    System.out.println("Digite o ID do Assento para editar: ");
                    int idEditarAssento = scanner.nextInt();
                    scanner.nextLine();

                    Assento assentoParaEditar = controlandoAssento.consultarAssento(assentos, idEditarAssento);
                    if (assentoParaEditar != null) {
                        System.out.println("ID do novo Tipo de Assento: ");
                        int novoIdTipoAssento = scanner.nextInt();
                        scanner.nextLine();

                        TipoAssento tipoAssentoNovo = controlandoTipoAssento.consultarTiposDeAssentos(tiposAssentos,
                                novoIdTipoAssento);
                        if (tipoAssentoNovo != null) {
                            assentoParaEditar.setTipoAssento(tipoAssentoNovo);

                            if (controlandoAssento.editarAssento(assentoParaEditar, tiposAssentos)) {
                                System.out.println("Assento editado com sucesso!");
                            } else {
                                System.out.println("Erro ao editar o assento.");
                            }
                        } else {
                            System.out.println("Tipo de assento informado não encontrado.");
                        }
                    } else {
                        System.out.println("Assento não encontrado.");
                    }
                    break;

                case 29:
                    System.out.println("Diga ID da Sala: ");
                    int idNovaSala = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Capacidade da Sala: ");
                    int capacidadeNovaSala = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Descrição da Sala: ");
                    String descricaoNovaSala = scanner.nextLine();

                    System.out.println("Status da Sala: ");
                    String statusNovaSala = scanner.nextLine();

                    Sala novaSala = new Sala(idNovaSala, capacidadeNovaSala, descricaoNovaSala, statusNovaSala);

                    if (novaSala.cadastrarSalas(novaSala)) {
                        salas.add(novaSala);
                        System.out.println("Sala cadastrada com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar a sala.");
                    }
                    break;

                case 30:

                    System.out.println("Listando Salas cadastradas:");
                    for (Sala sala : salas) {
                        System.out.println(sala.toString());
                    }
                    break;

                case 31:

                    System.out.println("Digite o ID da Sala para consulta: ");
                    int idConsultaSala = scanner.nextInt();
                    scanner.nextLine();

                    Sala salaConsultada = controlandoSala.consultarSalas(salas, idConsultaSala);
                    if (salaConsultada != null) {
                        System.out.println(salaConsultada.toString());
                    } else {
                        System.out.println("Sala não encontrada.");
                    }
                    break;

                case 32:

                    System.out.println("Digite o ID da Sala para editar: ");
                    int idEditarSala = scanner.nextInt();
                    scanner.nextLine();

                    Sala salaParaEditar = controlandoSala.consultarSalas(salas, idEditarSala);
                    if (salaParaEditar != null) {
                        System.out.println("Nova Capacidade da Sala: ");
                        int novaCapacidadeSala = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Nova Descrição da Sala: ");
                        String novaDescricaoSala = scanner.nextLine();
                        System.out.println("Novo Status da Sala: ");
                        String novoStatusSala = scanner.nextLine();

                        salaParaEditar.setCapacidadeSala(novaCapacidadeSala);
                        salaParaEditar.setDescricao(novaDescricaoSala);
                        salaParaEditar.setStatus(novoStatusSala);

                        if (controlandoSala.editarSalas(salaParaEditar)) {
                            System.out.println("Sala editada com sucesso!");
                        } else {
                            System.out.println("Erro ao editar a sala.");
                        }
                    } else {
                        System.out.println("Sala não encontrada.");
                    }
                    break;
                case 33:
                    System.out.println("ID do Sala-Assento: ");
                    int idSalaAssento = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("ID do Assento: ");
                    int idAssentoParaSala = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("ID da Sala: ");
                    int idSala = scanner.nextInt();
                    scanner.nextLine();

                    Assento assento = controlandoAssento.consultarAssento(assentos, idAssentoParaSala);
                    Sala sala = controlandoSala.consultarSalas(salas, idSala);

                    if (assento != null && sala != null) {
                        SalaAssento novoSalaAssento = new SalaAssento(idSalaAssento, assento, sala);
                        if (novoSalaAssento.cadastrarSalaAssento(novoSalaAssento)) {
                            salaAssentos.add(novoSalaAssento);
                            System.out.println("Sala-Assento cadastrado com sucesso!");
                        } else {
                            System.out.println("Erro ao cadastrar o Sala-Assento.");
                        }
                    } else {
                        System.out.println("Assento ou Sala não encontrado.");
                    }
                    break;

                case 34:
                    for (SalaAssento salaAssento : salaAssentos) {
                        System.out.println(salaAssento.toString());
                    }
                    break;

                case 35:
                    System.out.println("Digite o ID do Sala-Assento para consulta: ");
                    int idConsultaSalaAssento = scanner.nextInt();
                    scanner.nextLine();

                    SalaAssento salaAssentoConsultado = controlandoSalaAssento.consultarSalaAssento(salaAssentos,
                            idConsultaSalaAssento);
                    if (salaAssentoConsultado != null) {
                        System.out.println(salaAssentoConsultado.toString());
                    } else {
                        System.out.println("Sala-Assento não encontrado.");
                    }
                    break;

                case 36:
                    System.out.println("Digite o ID do Sala-Assento para editar: ");
                    int idEditarSalaAssento = scanner.nextInt();
                    scanner.nextLine();

                    SalaAssento salaAssentoParaEditar = controlandoSalaAssento.consultarSalaAssento(salaAssentos,
                            idEditarSalaAssento);
                    if (salaAssentoParaEditar != null) {
                        System.out.println("Novo ID do Assento: ");
                        int novoIdAssento = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Novo ID da Sala: ");
                        int novoIdSala = scanner.nextInt();
                        scanner.nextLine();

                        Assento novoAssento = controlandoAssento.consultarAssento(assentos, novoIdAssento);
                        Sala novaSalaParaAssento = controlandoSala.consultarSalas(salas, novoIdSala);

                        if (novoAssento != null && novaSalaParaAssento != null) {
                            salaAssentoParaEditar.setAssento(novoAssento);
                            salaAssentoParaEditar.setSala(novaSalaParaAssento);

                            if (controlandoSalaAssento.editarSalaAssento(salaAssentoParaEditar, assentos, salas)) {
                                System.out.println("Sala-Assento editado com sucesso!");
                            } else {
                                System.out.println("Erro ao editar o Sala-Assento.");
                            }
                        } else {
                            System.out.println("Assento ou Sala não encontrado.");
                        }
                    } else {
                        System.out.println("Sala-Assento não encontrado.");
                    }
                    break;

                case 37:
                    System.out.println("ID da Sessão: ");
                    int idSessao = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Data e Hora da Sessão (dd/MM/yyyy HH:mm): ");
                    String dataHoraString = scanner.nextLine();
                    SimpleDateFormat formatoDataHoraSessao = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    Date dataHoraSessao;
                    try {
                        dataHoraSessao = formatoDataHoraSessao.parse(dataHoraString);
                    } catch (ParseException e) {
                        System.out.println("Formato de data/hora inválido!");
                        break;
                    }

                    System.out.println("ID do Filme: ");
                    int idFilmeParaSessao = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("ID da Sala: ");
                    int idSalaParaSessao = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Matrícula do Funcionário: ");
                    int matriculaFuncionarioParaSessao = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Status da Sessão: ");
                    String statusSessao = scanner.nextLine();

                    Filme filme = controlandoFilme.consultarFilmes(filmes, idFilmeParaSessao);
                    Sala salaParaCadastrarSessao = controlandoSala.consultarSalas(salas, idSalaParaSessao);
                    Funcionario funcionario = controlandoFuncionario.consultarFuncionario(funcionarios,
                            matriculaFuncionarioParaSessao);

                    if (filme != null && salaParaCadastrarSessao != null && funcionario != null) {
                        Sessao novaSessao = new Sessao(idSessao, dataHoraSessao, filme, salaParaCadastrarSessao,
                                funcionario, statusSessao);
                        if (novaSessao.cadastrarSessao(novaSessao)) {
                            sessoes.add(novaSessao);
                            System.out.println("Sessão cadastrada com sucesso!");
                        } else {
                            System.out.println("Erro ao cadastrar a sessão.");
                        }
                    } else {
                        System.out.println("Filme, Sala ou Funcionário não encontrado.");
                    }
                    break;

                case 38:
                    for (Sessao sessao : sessoes) {
                        System.out.println(sessao.toString());
                    }
                    break;

                case 39:
                    System.out.println("Digite o ID da Sessão para consulta: ");
                    int idConsultaSessao = scanner.nextInt();
                    scanner.nextLine();

                    Sessao sessaoConsultada = controlandoSessao.consultarSessao(sessoes, idConsultaSessao);
                    if (sessaoConsultada != null) {
                        System.out.println(sessaoConsultada.toString());
                    } else {
                        System.out.println("Sessão não encontrada.");
                    }
                    break;

                case 40:
                    System.out.println("Digite o ID da Sessão para editar: ");
                    int idEditarSessao = scanner.nextInt();
                    scanner.nextLine();

                    Sessao sessaoParaEditar = controlandoSessao.consultarSessao(sessoes, idEditarSessao);
                    if (sessaoParaEditar != null) {
                        System.out.println("Nova Data e Hora da Sessão (dd/MM/yyyy HH:mm): ");
                        String novaDataHoraString = scanner.nextLine();

                        SimpleDateFormat formatoDataHoraSessaoEditar = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                        try {
                            sessaoParaEditar.setDataHoraSessao(formatoDataHoraSessaoEditar.parse(novaDataHoraString));
                        } catch (ParseException e) {
                            System.out.println("Formato de data/hora inválido!");
                            break;
                        }

                        System.out.println("Novo ID do Filme: ");
                        int novoIdFilme = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Novo ID da Sala: ");
                        int novoIdSala = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Nova Matrícula do Funcionário: ");
                        int novaMatriculaFuncionario = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Novo Status da Sessão: ");
                        String novoStatusSessao = scanner.nextLine();

                        Filme novoFilme = controlandoFilme.consultarFilmes(filmes, novoIdFilme);
                        Sala novaSalaParaEditar = controlandoSala.consultarSalas(salas, novoIdSala);
                        Funcionario novoFuncionario = controlandoFuncionario.consultarFuncionario(funcionarios,
                                novaMatriculaFuncionario);

                        if (novoFilme != null && novaSalaParaEditar != null && novoFuncionario != null) {
                            sessaoParaEditar.setFilme(novoFilme);
                            sessaoParaEditar.setSala(novaSalaParaEditar);
                            sessaoParaEditar.setFuncionario(novoFuncionario);
                            sessaoParaEditar.setStatus(novoStatusSessao);

                            if (controlandoSessao.editarSessao(sessaoParaEditar, filmes, salas, funcionarios)) {
                                System.out.println("Sessão editada com sucesso!");
                            } else {
                                System.out.println("Erro ao editar a sessão.");
                            }
                        } else {
                            System.out.println("Filme, Sala ou Funcionário não encontrado.");
                        }
                    } else {
                        System.out.println("Sessão não encontrada.");
                    }
                    break;

                case 41:
                    System.out.println("ID do Ingresso: ");
                    int idIngresso = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Valor Pago: ");
                    double valorPago = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("ID do Sala-Assento: ");
                    int idSalaAssentoParaIngresso = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("ID da Sessão: ");
                    int idSessaoParaIngresso = scanner.nextInt();
                    scanner.nextLine();

                    SalaAssento salaAssentoParaIngresso = controlandoSalaAssento.consultarSalaAssento(salaAssentos,
                            idSalaAssentoParaIngresso);
                    Sessao sessaoParaIngresso = controlandoSessao.consultarSessao(sessoes, idSessaoParaIngresso);

                    if (salaAssentoParaIngresso != null && sessaoParaIngresso != null) {
                        Ingresso novoIngresso = new Ingresso(idIngresso, valorPago, salaAssentoParaIngresso,
                                sessaoParaIngresso);
                        if (novoIngresso.cadastrarIngresso(novoIngresso)) {
                            ingressos.add(novoIngresso);
                            System.out.println("Ingresso cadastrado com sucesso!");
                        } else {
                            System.out.println("Erro ao cadastrar o ingresso.");
                        }
                    } else {
                        System.out.println("Sala-Assento ou Sessão não encontrado.");
                    }
                    break;

                case 42:
                    for (Ingresso ingresso : ingressos) {
                        System.out.println(ingresso.toString());
                    }
                    break;

                case 43:
                    System.out.println("Digite o ID do Ingresso para consulta: ");
                    int idConsultaIngresso = scanner.nextInt();
                    scanner.nextLine();
                    Ingresso ingressoConsultado = controlandoIngresso.consultarIngresso(ingressos, idConsultaIngresso);
                    if (ingressoConsultado != null) {
                        System.out.println(ingressoConsultado.toString());
                    } else {
                        System.out.println("Ingresso não encontrado.");
                    }
                    break;

                case 44:
                    System.out.println("Digite o ID do Ingresso para editar: ");
                    int idEditarIngresso = scanner.nextInt();
                    scanner.nextLine();

                    Ingresso ingressoParaEditar = controlandoIngresso.consultarIngresso(ingressos, idEditarIngresso);
                    if (ingressoParaEditar != null) {
                        System.out.println("Novo Valor Pago: ");
                        double novoValorPago = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("Novo ID do Sala-Assento: ");
                        int novoIdSalaAssento = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Novo ID da Sessão: ");
                        int novoIdSessao = scanner.nextInt();
                        scanner.nextLine();

                        SalaAssento novoSalaAssento = controlandoSalaAssento.consultarSalaAssento(salaAssentos,
                                novoIdSalaAssento);
                        Sessao novaSessao = controlandoSessao.consultarSessao(sessoes, novoIdSessao);

                        if (novoSalaAssento != null && novaSessao != null) {
                            ingressoParaEditar.setValorPago(novoValorPago);
                            ingressoParaEditar.setSalaAssento(novoSalaAssento);
                            ingressoParaEditar.setSessao(novaSessao);

                            if (controlandoIngresso.editarIngresso(ingressoParaEditar, salaAssentos, sessoes)) {
                                System.out.println("Ingresso editado com sucesso!");
                            } else {
                                System.out.println("Erro ao editar o ingresso.");
                            }
                        } else {
                            System.out.println("Sala-Assento ou Sessão não encontrado.");
                        }
                    } else {
                        System.out.println("Ingresso não encontrado.");
                    }
                    break;

                case 0:
                    System.out.println("Fechando o menu , voltando para site");
                    break;

                default:
                    System.out.println("Não possui essa opção , tente novamente");
            }
        } while (menuDeOpcoesParaControlar != 0);

        scanner.close();
    }
}
