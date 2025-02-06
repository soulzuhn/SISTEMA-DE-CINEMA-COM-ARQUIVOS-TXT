import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FilmeAtor {
    private int idFilmeAtor;
    private Ator ator;
    private Filme filme;
    private String personagem;
    private boolean principal;

    public FilmeAtor(int idFilmeAtor, Ator ator, Filme filme, String personagem, boolean principal) {
        this.idFilmeAtor = idFilmeAtor;
        this.ator = ator;
        this.filme = filme;
        this.personagem = personagem;
        this.principal = principal;
    }

    
    public int getIdFilmeAtor() {
        return idFilmeAtor;
    }

    public void setIdFilmeAtor(int idFilmeAtor) {
        this.idFilmeAtor = idFilmeAtor;
    }

    public Ator getAtor() {
        return this.ator;
    }

    public void setAtor(Ator ator) {
        this.ator = ator;
    }

    public Filme getFilme() {
        return this.filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public String getPersonagem() {
        return this.personagem;
    }

    public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }


    public boolean cadastrarFilmeAtor(FilmeAtor novoFilmeAtor) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("filmeAtor.txt", true))) {
            bw.write(novoFilmeAtor.idFilmeAtor + ";" +
                     novoFilmeAtor.ator.getRegistro() + ";" +
                     novoFilmeAtor.filme.getIdFilme() + ";" +
                     novoFilmeAtor.personagem + ";" +
                     novoFilmeAtor.principal);
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<FilmeAtor> listarFilmeAtor(ArrayList<FilmeAtor> filmeAtorLista, ArrayList<Ator> atores, ArrayList<Filme> filmes) {
        filmeAtorLista.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("filmeAtor.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int RegistroAtor = Integer.parseInt(dados[1]);
                int idFilme = Integer.parseInt(dados[2]);

                Ator ator = null;
                for (Ator a : atores) {
                    if (a.getRegistro() == RegistroAtor) {
                        ator = a;
                        break;
                    }
                }
                Filme filme = null;
                for (Filme f : filmes) {
                    if (f.getIdFilme() == idFilme) {
                        filme = f;
                        break;
                    }
                }
                FilmeAtor filmeAtor = new FilmeAtor(
                    Integer.parseInt(dados[0]),ator, filme, dados[3], Boolean.parseBoolean(dados[4]));
                filmeAtorLista.add(filmeAtor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filmeAtorLista;
    }

    // Método para consultar FilmeAtor
    public FilmeAtor consultarFilmeAtor(ArrayList<FilmeAtor> filmeAtorLista, int idFilmeAtor) {
        for (FilmeAtor filmeAtor : filmeAtorLista) {
            if (filmeAtor.getIdFilmeAtor() == idFilmeAtor) {
                return filmeAtor;
            }
        }
        return null;
    }

    // Método para editar FilmeAtor
    public boolean editarFilmeAtor(FilmeAtor filmeAtorEditado, ArrayList<Ator> atores, ArrayList<Filme> filmes) {
        ArrayList<FilmeAtor> filmeAtorLista = listarFilmeAtor(new ArrayList<>(), atores, filmes);
        boolean filmeAtorEncontrado = false;

        for (int i = 0; i < filmeAtorLista.size(); i++) {
            if (filmeAtorLista.get(i).getIdFilmeAtor() == filmeAtorEditado.getIdFilmeAtor()) {
                filmeAtorLista.get(i).setAtor(filmeAtorEditado.getAtor());
                filmeAtorLista.get(i).setFilme(filmeAtorEditado.getFilme());
                filmeAtorLista.get(i).setPersonagem(filmeAtorEditado.getPersonagem());
                filmeAtorLista.get(i).setPrincipal(filmeAtorEditado.isPrincipal());
                filmeAtorEncontrado = true;
                break;
            }
        }

        if (filmeAtorEncontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("filmeAtor.txt", false))) {
                for (FilmeAtor filmeAtor : filmeAtorLista) {
                    bw.write(filmeAtor.getIdFilmeAtor() + ";" +
                             filmeAtor.getAtor().getRegistro() + ";" +
                             filmeAtor.getFilme().getIdFilme() + ";" +
                             filmeAtor.getPersonagem() + ";" +
                             filmeAtor.isPrincipal());
                    bw.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("FilmeAtor não encontrado.");
        }
        return false;
    }

    @Override
    public String toString() {
        return "FilmeAtor [idFilmeAtor=" + idFilmeAtor + ", ator=" + ator + ", filme=" + filme + 
               ", personagem=" + personagem + ", principal=" + principal + "]";
    }
}
