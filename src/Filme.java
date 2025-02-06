import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Filme {
    private int idFilme;
    private String titulo;
    private String status;
    private Genero genero;
    private int classificacao;
    private ArrayList<Filme> filmes = new ArrayList<>();

    public Filme(int idFilme, String titulo, String status, Genero genero, int classificacao) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.status = status;
        this.genero = genero;
        this.classificacao = classificacao;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }
    public ArrayList<Filme> getFilmes() {
        return this.filmes;
    }

    public void setFilmes(ArrayList<Filme> filmes) {
        this.filmes = filmes;
    }
    public boolean cadastrarFilmes(Filme novoFilme) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("filmes.txt", true))) {
            bw.write(this.idFilme + ";" + this.titulo + ";" + this.status + ";" + this.genero.getId() + ";" + this.classificacao);
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Filme> listarFilmes(ArrayList<Filme> filmesParaListar, ArrayList<Genero> generos) {
        filmesParaListar.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("filmes.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int idGenero = Integer.parseInt(dados[3]);
            
                Genero genero = null;
                for (Genero generoEncontrar : generos) {
                    if (generoEncontrar.getId() == idGenero) {
                        genero = generoEncontrar;
                        break;
                    }
                }  
                Filme filme = new Filme(
                    Integer.parseInt(dados[0]), dados[1], dados[2],genero,Integer.parseInt(dados[4]));
                filmesParaListar.add(filme);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filmesParaListar;
    }
    public Filme consultarFilmes(ArrayList<Filme> filmesParaConsultar, int idFilme) {
        for (Filme filme : filmesParaConsultar) {
            if (filme.getIdFilme() == idFilme) {
                return filme;
            }
        }
        return null;
    }
    public boolean editarFilmes(Filme filmeEditado, ArrayList<Genero> generos) {
        ArrayList<Filme> filmes = listarFilmes(new ArrayList<>(), generos);
        boolean filmeEncontrado = false;

        for (int i = 0; i < filmes.size(); i++) {
            if (filmes.get(i).getIdFilme() == filmeEditado.getIdFilme()) {
                filmes.get(i).setTitulo(filmeEditado.getTitulo());
                filmes.get(i).setStatus(filmeEditado.getStatus());
                filmes.get(i).setGenero(filmeEditado.getGenero());
                filmes.get(i).setClassificacao(filmeEditado.getClassificacao());
                filmeEncontrado = true;
                break;
            }
        }

        if (filmeEncontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("filmes.txt", false))) {
                for (Filme filme : filmes) {
                    bw.write(filme.getIdFilme() + ";" + filme.getTitulo() + ";" + filme.getStatus() + ";" + filme.getGenero().getId() + ";" + filme.getClassificacao());
                    bw.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Filme não encontrado.");
        }
        return false;
    }

    @Override
    public String toString() {
        return "Filme [idFilme=" + idFilme + ", titulo=" + titulo + ", status=" + status + ", genero=" + genero
                + ", classificacao=" + classificacao + ", filmes=" + filmes + "]";
    }
    
}