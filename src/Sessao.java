import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Sessao {
    private int idSessao;
    private Date dataHoraSessao;
    private Filme filme;
    private Sala sala;
    private Funcionario funcionario;
    private String status;

    public Sessao(int idSessao, Date dataHoraSessao, Filme filme, Sala sala, Funcionario funcionario, String status) {
        this.idSessao = idSessao;
        this.dataHoraSessao = dataHoraSessao;
        this.filme = filme;
        this.sala = sala;
        this.funcionario = funcionario;
        this.status = status;
    }
 
    public int getIdSessao() {
        return this.idSessao;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public Date getDataHoraSessao() {
        return this.dataHoraSessao;
    }

    public void setDataHoraSessao(Date dataHoraSessao) {
        this.dataHoraSessao = dataHoraSessao;
    }

    public Filme getFilme() {
        return this.filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Sala getSala() {
        return this.sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Funcionario getFuncionario() {
        return this.funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean cadastrarSessao(Sessao novaSessao) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("sessao.txt", true))) {
            SimpleDateFormat formatoDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            bw.write(novaSessao.getIdSessao() + ";" +
                     formatoDataHora.format(novaSessao.getDataHoraSessao()) + ";" +
                     novaSessao.getFilme().getIdFilme() + ";" +
                     novaSessao.getSala().getIdSala() + ";" +
                     novaSessao.getFuncionario().getMatricula() + ";" +
                     novaSessao.getStatus());
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public ArrayList<Sessao> listarSessoes(ArrayList<Sessao> sessoes, ArrayList<Filme> filmes, ArrayList<Sala> salas, ArrayList<Funcionario> funcionarios) {
        sessoes.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("sessao.txt"))) {
            String linha;
            SimpleDateFormat formatoDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Date dataHora = formatoDataHora.parse(dados[1]);

                Filme filme = null;
                for (Filme f : filmes) {
                    if (f.getIdFilme() == Integer.parseInt(dados[2])) {
                        filme = f;
                        break;
                    }
                }

                Sala sala = null;
                for (Sala s : salas) {
                    if (s.getIdSala() == Integer.parseInt(dados[3])) {
                        sala = s;
                        break;
                    }
                }

                Funcionario funcionario = null;
                for (Funcionario func : funcionarios) {
                    if (func.getMatricula() == Integer.parseInt(dados[4])) {
                        funcionario = func;
                        break;
                    }
                }

                Sessao sessao = new Sessao(
                    Integer.parseInt(dados[0]), dataHora, filme, sala, funcionario, dados[5]);
                sessoes.add(sessao);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return sessoes;
    }

    public Sessao consultarSessao(ArrayList<Sessao> sessoes, int idSessao) {
        for (Sessao sessao : sessoes) {
            if (sessao.getIdSessao() == idSessao) {
                return sessao;
            }
        }
        return null;
    }

    public boolean editarSessao(Sessao sessaoEditada, ArrayList<Filme> filmes, ArrayList<Sala> salas, ArrayList<Funcionario> funcionarios) {
        ArrayList<Sessao> sessoes = listarSessoes(new ArrayList<>(), filmes, salas, funcionarios);
        boolean sessaoEncontrado = false;

        for (int i = 0; i < sessoes.size(); i++) {
            if (sessoes.get(i).getIdSessao() == sessaoEditada.getIdSessao()) {
                sessoes.get(i).setDataHoraSessao(sessaoEditada.getDataHoraSessao());
                sessoes.get(i).setFilme(sessaoEditada.getFilme());
                sessoes.get(i).setSala(sessaoEditada.getSala());
                sessoes.get(i).setFuncionario(sessaoEditada.getFuncionario());
                sessoes.get(i).setStatus(sessaoEditada.getStatus());
                sessaoEncontrado = true;
                break;
            }
        }

        if (sessaoEncontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("sessao.txt", false))) {
                SimpleDateFormat formatoDataHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                for (Sessao sessao : sessoes) {
                    bw.write(sessao.getIdSessao() + ";" +
                             formatoDataHora.format(sessao.getDataHoraSessao()) + ";" +
                             sessao.getFilme().getIdFilme() + ";" +
                             sessao.getSala().getIdSala() + ";" +
                             sessao.getFuncionario().getMatricula() + ";" +
                             sessao.getStatus());
                    bw.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Sessão não encontrado.");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Sessao [idSessao=" + idSessao + ", dataHoraSessao=" + dataHoraSessao +
               ", filme=" + filme + ", sala=" + sala + ", funcionario=" + funcionario +
               ", status=" + status + "]";
    }
}
