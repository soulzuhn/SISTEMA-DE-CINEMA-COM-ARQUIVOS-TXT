import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TipoAssento{
    private int idTipoAssento;
    private String descricao;
    private String status;

    public TipoAssento(int idTipoAssento, String descricao, String status) {
        this.idTipoAssento = idTipoAssento;
        this.descricao = descricao;
        this.status = status;
    }
    public int getIdTipoAssento() {
        return this.idTipoAssento;
    }
    public void setIdTipoAssento(int idTipoAssento) {
        this.idTipoAssento = idTipoAssento;
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
    
   public boolean cadastrarTiposDeAssentos(TipoAssento novoTipoAssento) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("tiposdeassentos.txt", true))) {
            bw.write(this.idTipoAssento + ";" + this.descricao + ";" + this.status);
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<TipoAssento> listarTiposDeAssentos(ArrayList<TipoAssento> TiposDeAssentosParaListar) {
        TiposDeAssentosParaListar.clear(); 
        try (BufferedReader reader = new BufferedReader(new FileReader("tiposdeassentos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                TipoAssento tipoAssento = new TipoAssento(Integer.parseInt(dados[0]), dados[1], dados[2]);
                TiposDeAssentosParaListar.add(tipoAssento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TiposDeAssentosParaListar;
    }
    
    

    public TipoAssento consultarTiposDeAssentos(ArrayList<TipoAssento> TiposDeAssentosParaConsultar, int idTipoAssento) {
        for (TipoAssento tipoAssento : TiposDeAssentosParaConsultar) {
            if (tipoAssento.getIdTipoAssento() == idTipoAssento) {
                return tipoAssento;
            }
        }
        return null;
    }
    
    public boolean editarTiposDeAssentos(TipoAssento tipoAssentoEditado) {
        ArrayList<TipoAssento> tiposDeAssentos = listarTiposDeAssentos(new ArrayList<>()); 
        boolean TipoAssentoEncontrado = false;
      
        for (int i = 0; i < tiposDeAssentos.size(); i++) {
            if (tiposDeAssentos.get(i).getIdTipoAssento() == tipoAssentoEditado.getIdTipoAssento()) {
                tiposDeAssentos.get(i).setDescricao(tipoAssentoEditado.getDescricao());
                tiposDeAssentos.get(i).setStatus(tipoAssentoEditado.getStatus());
                TipoAssentoEncontrado = true;
                break;
            }
        }
        if (TipoAssentoEncontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("tiposdeassentos.txt", false))) { 
                for (TipoAssento tipoAssento : tiposDeAssentos) {
                    bw.write(tipoAssento.getIdTipoAssento() + ";" + tipoAssento.getDescricao() + ";" + tipoAssento.getStatus());
                    bw.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false; 
            }
        } else {
            System.out.println("Tipo não encontrado.");
        }
        return false;
    }
    @Override
    public String toString() {
        return "TipoAssento [idTipoAssento=" + idTipoAssento + ", descricao=" + descricao + ", status=" + status + "]";
    }
}