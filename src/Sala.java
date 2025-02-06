import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Sala {
    private int idSala;
    private int capacidadeSala;
    private String descricao;
    private String status;

    public Sala(int idSala, int capacidadeSala, String descricao, String status) {
        this.idSala = idSala;
        this.capacidadeSala = capacidadeSala;
        this.descricao = descricao;
        this.status = status;
    }

    public int getIdSala() {
        return this.idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getCapacidadeSala() {
        return this.capacidadeSala;
    }

    public void setCapacidadeSala(int capacidadeSala) {
        this.capacidadeSala = capacidadeSala;
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

    public boolean cadastrarSalas(Sala novaSala) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("salas.txt", true))) {
            bw.write(this.idSala + ";" + this.capacidadeSala + ";" + this.descricao + ";" + this.status);
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Sala> listarSalas(ArrayList<Sala> salasParaListar) {
        salasParaListar.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("salas.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Sala sala = new Sala(Integer.parseInt(dados[0]),Integer.parseInt(dados[1]),dados[2], dados[3]);
                salasParaListar.add(sala);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salasParaListar;
    }

    public Sala consultarSalas(ArrayList<Sala> salasParaConsultar, int idSala) {
        for (Sala sala : salasParaConsultar) {
            if (sala.getIdSala() == idSala) {
                return sala;
            }
        }
        return null;
    }

    public boolean editarSalas(Sala salaEditada) {
        ArrayList<Sala> salas = listarSalas(new ArrayList<>());
        boolean salaEncontrada = false;

        for (int i = 0; i < salas.size(); i++) {
            if (salas.get(i).getIdSala() == salaEditada.getIdSala()) {
                salas.get(i).setCapacidadeSala(salaEditada.getCapacidadeSala());
                salas.get(i).setDescricao(salaEditada.getDescricao());
                salas.get(i).setStatus(salaEditada.getStatus());
                salaEncontrada = true;
                break;
            }
        }
        if (salaEncontrada) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("salas.txt", false))) {
                for (Sala sala : salas) {
                    bw.write(sala.getIdSala() + ";" + sala.getCapacidadeSala() + ";" + sala.getDescricao() + ";" + sala.getStatus());
                    bw.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Sala não encontrada.");
        }
        return false;
    }

    @Override
    public String toString() {
        return "Sala [ID: " + idSala + ", Capacidade: " + capacidadeSala + ", Descrição: " + descricao + ", Status: " + status + "]";
    }
}
