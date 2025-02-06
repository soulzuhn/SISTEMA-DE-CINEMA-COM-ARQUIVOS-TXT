import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Assento {
    private int idAssento;
    private TipoAssento tipoAssento;

    public Assento(int idAssento, TipoAssento tipoAssento) {
        this.idAssento = idAssento;
        this.tipoAssento = tipoAssento;
    }

    public int getIdAssento() {
        return idAssento;
    }

    public void setIdAssento(int idAssento) {
        this.idAssento = idAssento;
    }

    public TipoAssento getTipoAssento() {
        return tipoAssento;
    }

    public void setTipoAssento(TipoAssento tipoAssento) {
        this.tipoAssento = tipoAssento;
    }

  
    public boolean cadastrarAssento(Assento novoAssento) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("assentos.txt", true))) {
            bw.write(this.idAssento + ";" + this.tipoAssento.getIdTipoAssento());
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Assento> listarAssentos(ArrayList<Assento> assentosParaListar, ArrayList<TipoAssento> tiposDeAssento) {
        assentosParaListar.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("assentos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int idTipoAssento = Integer.parseInt(dados[1]);

                TipoAssento tipoAssento = null;
                for (TipoAssento tipo : tiposDeAssento) {
                    if (tipo.getIdTipoAssento() == idTipoAssento) {
                        tipoAssento = tipo;
                        break;
                    }
                }

                Assento assento = new Assento(Integer.parseInt(dados[0]),tipoAssento);
                assentosParaListar.add(assento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return assentosParaListar;
    }

    public Assento consultarAssento(ArrayList<Assento> assentosParaConsultar, int idAssento) {
        for (Assento assento : assentosParaConsultar) {
            if (assento.getIdAssento() == idAssento) {
                return assento;
            }
        }
        return null;
    }

   
    public boolean editarAssento(Assento assentoEditado, ArrayList<TipoAssento> tiposDeAssento) {
        ArrayList<Assento> assentos = listarAssentos(new ArrayList<>(), tiposDeAssento);
        boolean assentoEncontrado = false;

        for (int i = 0; i < assentos.size(); i++) {
            if (assentos.get(i).getIdAssento() == assentoEditado.getIdAssento()) {
                assentos.get(i).setTipoAssento(assentoEditado.getTipoAssento());
                assentoEncontrado = true;
                break;
            }
        }
        if (assentoEncontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("assentos.txt", false))) {
                for (Assento assento : assentos) {
                    bw.write(assento.getIdAssento() + ";" + assento.getTipoAssento().getIdTipoAssento());
                    bw.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Assento não encontrado.");
        }
        return false;
    }

    @Override
    public String toString() {
        return "Assento [idAssento=" + idAssento + ", tipoAssento=" + tipoAssento + "]";
    }
}
