public class Node {
    private Aluno aluno;
    private Node direita;
    private Node esquerda;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Node getDireita() {
        return direita;
    }

    public void setDireita(Node direita) {
        this.direita = direita;
    }

    public Node getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Node esquerda) {
        this.esquerda = esquerda;
    }

    public Node(Aluno novoAluno) {
        this.aluno = novoAluno;
        this.direita = null;
        this.esquerda = null;
    }
}
