import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Funcionario extends Pessoa {
    private int matricula;
    private Date horarioTrabalho;

    public Funcionario(String cpf, String nome, String email, int matricula, Date horarioTrabalho) {
        super(cpf, nome, email);
        this.matricula = matricula;
        this.horarioTrabalho = horarioTrabalho;
    }

    public int getMatricula() {
        return this.matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public Date getHorarioTrabalho() {
        return this.horarioTrabalho;
    }

    public void setHorarioTrabalho(Date horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }

   public boolean cadastrarFuncionario(Funcionario novoFuncionario) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("funcionarios.txt", true))) {
        SimpleDateFormat formatoHorario = new SimpleDateFormat("HH:mm");
        bw.write(
            novoFuncionario.getCpf() + ";" +
            novoFuncionario.getNome() + ";" +
            novoFuncionario.getEmail() + ";" +
            novoFuncionario.getMatricula() + ";" +
            formatoHorario.format(novoFuncionario.getHorarioTrabalho()) 
        );
        bw.newLine();
        return true;
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}

    public ArrayList<Funcionario> listarFuncionarios(ArrayList<Funcionario> funcionariosParaListar) {
    funcionariosParaListar.clear();
    try (BufferedReader br = new BufferedReader(new FileReader("funcionarios.txt"))) {
        String linha;
        while ((linha = br.readLine()) != null) {
            String[] dados = linha.split(";");
            SimpleDateFormat formatoHorario = new SimpleDateFormat("HH:mm");
            Date horario = formatoHorario.parse(dados[4]); 
            Funcionario funcionario = new Funcionario(dados[0], dados[1], dados[2], Integer.parseInt(dados[3]), horario);
            funcionariosParaListar.add(funcionario);
        }
    } catch (IOException | ParseException e) {
        e.printStackTrace();
    }
    return funcionariosParaListar;
}
    
    public Funcionario consultarFuncionario(ArrayList<Funcionario> funcionariosParaConsultar, int matricula) {
        for (Funcionario funcionario : funcionariosParaConsultar) {
            if (funcionario.getMatricula() == matricula) {
                return funcionario;
            }
        }
        return null;
    }

   
    public boolean editarFuncionario(Funcionario funcionarioEditado) {
        ArrayList<Funcionario> funcionarios = listarFuncionarios(new ArrayList<>());
        boolean funcionarioEncontrado = false;

        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getMatricula() == funcionarioEditado.getMatricula()) {
                funcionarios.get(i).setCpf(funcionarioEditado.getCpf());
                funcionarios.get(i).setNome(funcionarioEditado.getNome());
                funcionarios.get(i).setEmail(funcionarioEditado.getEmail());
                funcionarios.get(i).setHorarioTrabalho(funcionarioEditado.getHorarioTrabalho());
                funcionarioEncontrado = true;
                break;
            }
        }

        if (funcionarioEncontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("funcionarios.txt", false))) {
                SimpleDateFormat formatoHorario = new SimpleDateFormat("HH:mm");
                for (Funcionario funcionario : funcionarios) {
                    bw.write(
                        funcionario.getCpf() + ";" +
                        funcionario.getNome() + ";" +
                        funcionario.getEmail() + ";" +
                        funcionario.getMatricula() + ";" +
                        formatoHorario.format(funcionario.getHorarioTrabalho())
                    );
                    bw.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            System.out.println("Funcionário não encontrado.");
        }
        return false;
    }

    @Override
    public String toString() {
        return "Funcionario [cpf=" + getCpf() + ", nome=" + getNome() + ", email=" + getEmail() +
                ", matricula=" + matricula + ", horarioTrabalho=" + horarioTrabalho + "]";
    }
}
