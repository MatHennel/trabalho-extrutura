

public class Aluno {
    private String matricula;
    private String nome;
    private DataNascimento dataNascimento;
    
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public DataNascimento getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(DataNascimento dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Aluno(String matricula, String nome, DataNascimento dataNascimento) {
        this.matricula = matricula;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        String str = "Nome: " + getNome();
        str += "Matricula: " + getMatricula();
        str += "Data Nascimento: " + getDataNascimento();

        return str;
    }
    
}