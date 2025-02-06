import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SalaAssento {
    private int idSalaAssento;
    private Assento assento;
    private Sala sala;

    public SalaAssento(int idSalaAssento, Assento assento, Sala sala) {
        this.idSalaAssento = idSalaAssento;
        this.assento = assento;
        this.sala = sala;
    }

    public int getIdSalaAssento() {
        return this.idSalaAssento;
    }

    public void setIdSalaAssento(int idSalaAssento) {
        this.idSalaAssento = idSalaAssento;
    }

    public Assento getAssento() {
        return this.assento;
    }

    public void setAssento(Assento assento) {
        this.assento = assento;
    }

    public Sala getSala() {
        return this.sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public boolean cadastrarSalaAssento(SalaAssento novaSalaAssento) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("salaAssento.txt", true))) {
            bw.write(novaSalaAssento.idSalaAssento + ";" +
                     novaSalaAssento.assento.getIdAssento() + ";" +
                     novaSalaAssento.sala.getIdSala());
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<SalaAssento> listarSalaAssento(ArrayList<SalaAssento> salaAssentoLista, ArrayList<Assento> assentos, ArrayList<Sala> salas) {
        salaAssentoLista.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("salaAssento.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int idAssento = Integer.parseInt(dados[1]);
                int idSala = Integer.parseInt(dados[2]);

                Assento assento = null;
                for (Assento a : assentos) {
                    if (a.getIdAssento() == idAssento) {
                        assento = a;
                        break;
                    }
                }
                Sala sala = null;
                for (Sala s : salas) {
                    if (s.getIdSala() == idSala) {
                        sala = s;
                        break;
                    }
                }
                SalaAssento salaAssento = new SalaAssento(
                    Integer.parseInt(dados[0]), assento, sala);
                salaAssentoLista.add(salaAssento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salaAssentoLista;
    }

    public SalaAssento consultarSalaAssento(ArrayList<SalaAssento> salaAssentoLista, int idSalaAssento) {
        for (SalaAssento salaAssento : salaAssentoLista) {
            if (salaAssento.getIdSalaAssento() == idSalaAssento) {
                return salaAssento;
            }
        }
        return null;
    }

    public boolean editarSalaAssento(SalaAssento salaAssentoEditada, ArrayList<Assento> assentos, ArrayList<Sala> salas) {
        ArrayList<SalaAssento> salaAssentoLista = listarSalaAssento(new ArrayList<>(), assentos, salas);
        boolean salaAssentoEncontrado = false;

        for (int i = 0; i < salaAssentoLista.size(); i++) {
            if (salaAssentoLista.get(i).getIdSalaAssento() == salaAssentoEditada.getIdSalaAssento()) {
                salaAssentoLista.get(i).setAssento(salaAssentoEditada.getAssento());
                salaAssentoLista.get(i).setSala(salaAssentoEditada.getSala());
                salaAssentoEncontrado = true;
                break;
            }
        }

        if (salaAssentoEncontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("salaAssento.txt", false))) {
                for (SalaAssento salaAssento : salaAssentoLista) {
                    bw.write(salaAssento.getIdSalaAssento() + ";" +
                             salaAssento.getAssento().getIdAssento() + ";" +
                             salaAssento.getSala().getIdSala());
                    bw.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("SalaAssento não encontrado.");
        }
        return false;
    }

    @Override
    public String toString() {
        return "SalaAssento [idSalaAssento=" + idSalaAssento + ", assento=" + assento + ", sala=" + sala + "]";
    }
}
