import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Genero {
    private int id;
    private String descricao;
    private String status;
    private ArrayList<Genero> generos = new ArrayList<>();
    

    public Genero(int id, String descricao, String status) {
        this.id = id;
        this.descricao = descricao;
        this.status = status;
    }
      public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Genero> getGeneros() {
        return this.generos;
    }

    public void setGeneros(ArrayList<Genero> generos) {
        this.generos = generos;
    }

    public boolean cadastrarGeneros(Genero novoGenero) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("generos.txt", true))) {
            bw.write(this.id + ";" + this.descricao + ";" + this.status);
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<Genero> listarGeneros(ArrayList<Genero> generosParaListar) {
        generosParaListar.clear(); 
        try (BufferedReader reader = new BufferedReader(new FileReader("generos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                Genero genero = new Genero(Integer.parseInt(dados[0]), dados[1], dados[2]);
                generosParaListar.add(genero);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return generosParaListar;
    }
    
    

    public Genero consultarGeneros(ArrayList<Genero> generosParaConsultar, int idGenero) {
        for (Genero genero : generosParaConsultar) {
            if (genero.getId() == idGenero) {
                return genero;
            }
        }
        return null;
    }
    
    public boolean editarGeneros(Genero generoEditado) {
        ArrayList<Genero> generos = listarGeneros(new ArrayList<>()); 
        boolean generoEncontrado = false;
      
        for (int i = 0; i < generos.size(); i++) {
            if (generos.get(i).getId() == generoEditado.getId()) {
                generos.get(i).setDescricao(generoEditado.getDescricao());
                generos.get(i).setStatus(generoEditado.getStatus());
                generoEncontrado = true;
                break;
            }
        }
        if (generoEncontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("generos.txt", false))) { 
                for (Genero genero : generos) {
                    bw.write(genero.getId() + ";" + genero.getDescricao() + ";" + genero.getStatus());
                    bw.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false; 
            }
        } else {
            System.out.println("Gênero não encontrado.");
        }
        return false;
    }
    @Override
    public String toString() {
        return "Genero [id=" + id + ", descricao=" + descricao + ", status=" + status + ", generos=" + generos + "]";
    }
    
}