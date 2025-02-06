import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ingresso {
    private int idIngresso;
    private double valorPago;
    private SalaAssento salaAssento;
    private Sessao sessao;

    public Ingresso(int idIngresso, double valorPago, SalaAssento salaAssento, Sessao sessao) {
        this.idIngresso = idIngresso;
        this.valorPago = valorPago;
        this.salaAssento = salaAssento;
        this.sessao = sessao;
    }

    public int getIdIngresso() {
        return this.idIngresso;
    }

    public void setIdIngresso(int idIngresso) {
        this.idIngresso = idIngresso;
    }

    public double getValorPago() {
        return this.valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public SalaAssento getSalaAssento() {
        return this.salaAssento;
    }

    public void setSalaAssento(SalaAssento salaAssento) {
        this.salaAssento = salaAssento;
    }

    public Sessao getSessao() {
        return this.sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public boolean cadastrarIngresso(Ingresso novoIngresso) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ingressos.txt", true))) {
            bw.write(novoIngresso.idIngresso + ";" +
                     novoIngresso.valorPago + ";" +
                     novoIngresso.salaAssento.getIdSalaAssento() + ";" +
                     novoIngresso.sessao.getIdSessao());
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Ingresso> listarIngressos(ArrayList<Ingresso> ingressos, ArrayList<SalaAssento> salaAssentos, ArrayList<Sessao> sessoes) {
        ingressos.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("ingressos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int idSalaAssento = Integer.parseInt(dados[2]);
                int idSessao = Integer.parseInt(dados[3]);

                SalaAssento salaAssento = null;
                for (SalaAssento sa : salaAssentos) {
                    if (sa.getIdSalaAssento() == idSalaAssento) {
                        salaAssento = sa;
                        break;
                    }
                }

                Sessao sessao = null;
                for (Sessao s : sessoes) {
                    if (s.getIdSessao() == idSessao) {
                        sessao = s;
                        break;
                    }
                }

                Ingresso ingresso = new Ingresso(
                    Integer.parseInt(dados[0]),
                    Double.parseDouble(dados[1]),
                    salaAssento,
                    sessao
                );
                ingressos.add(ingresso);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingressos;
    }

    public Ingresso consultarIngresso(ArrayList<Ingresso> ingressos, int idIngresso) {
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getIdIngresso() == idIngresso) {
                return ingresso;
            }
        }
        return null;
    }

    public boolean editarIngresso(Ingresso ingressoEditado, ArrayList<SalaAssento> salaAssentos, ArrayList<Sessao> sessoes) {
        ArrayList<Ingresso> ingressos = listarIngressos(new ArrayList<>(), salaAssentos, sessoes);
        boolean ingressoEncontrado = false;

        for (int i = 0; i < ingressos.size(); i++) {
            if (ingressos.get(i).getIdIngresso() == ingressoEditado.getIdIngresso()) {
                ingressos.get(i).setValorPago(ingressoEditado.getValorPago());
                ingressos.get(i).setSalaAssento(ingressoEditado.getSalaAssento());
                ingressos.get(i).setSessao(ingressoEditado.getSessao());
                ingressoEncontrado = true;
                break;
            }
        }

        if (ingressoEncontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("ingressos.txt", false))) {
                for (Ingresso ingresso : ingressos) {
                    bw.write(ingresso.getIdIngresso() + ";" +
                             ingresso.getValorPago() + ";" +
                             ingresso.getSalaAssento().getIdSalaAssento() + ";" +
                             ingresso.getSessao().getIdSessao());
                    bw.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Ingresso não encontrado.");
        }
        return false;
    }

    @Override
    public String toString() {
        return "Ingresso [idIngresso=" + idIngresso + ", valorPago=" + valorPago + 
               ", salaAssento=" + salaAssento + ", sessao=" + sessao + "]";
    }
}
