import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ator extends Pessoa {
    private int registro;

    public Ator(String cpf, String nome, String email, int registro) {
        super(cpf, nome, email);
        this.registro = registro;
    }

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }

    @Override
    public String toString() {
        return "CPF: " + getCpf() + ", Nome: " + getNome() + ", Email: " + getEmail() + ", Registro: " + registro;
    }

    public boolean cadastrarAtores(Ator novoAtor) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("atores.txt", true))) {
            bw.write(novoAtor.getCpf() + ";" + novoAtor.getNome() + ";" + novoAtor.getEmail() + ";" + novoAtor.getRegistro());
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao cadastrar ator: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<Ator> listarAtores(ArrayList<Ator> atoresParaListar) {
        atoresParaListar.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("atores.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Ator ator = new Ator(dados[0], dados[1], dados[2], Integer.parseInt(dados[3]));
                atoresParaListar.add(ator);
            }
        } catch (IOException e) {
            System.out.println("Erro ao listar atores: " + e.getMessage());
        }
        return atoresParaListar;
    }

    public Ator consultarAtores(ArrayList<Ator> atoresParaConsultar, int registro) {
        for (Ator ator : atoresParaConsultar) {
            if (ator.getRegistro() == registro) {
                return ator;
            }
        }
        return null;
    }

    public boolean editarAtores(Ator atorEditado) {
        ArrayList<Ator> atores = listarAtores(new ArrayList<>());
        boolean atorEncontrado = false;

       
        for (int i = 0; i < atores.size(); i++) {
            if (atores.get(i).getRegistro() == atorEditado.getRegistro()) {
                atores.get(i).setCpf(atorEditado.getCpf());
                atores.get(i).setNome(atorEditado.getNome());
                atores.get(i).setEmail(atorEditado.getEmail());
                atorEncontrado = true;
                break;
            }
        }

        if (atorEncontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("atores.txt", false))) {
                for (Ator ator : atores) {
                    bw.write(ator.getCpf() + ";" + ator.getNome() + ";" + ator.getEmail() + ";" + ator.getRegistro());
                    bw.newLine();
                }
                return true;
            } catch (IOException e) {
                System.out.println("Erro ao sobrescrever o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Ator não encontrado para edição.");
        }
        return false;
    }
}
