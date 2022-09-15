import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arvore {

    private Node raiz;

    public Arvore() {
        this.raiz = null;
    }

    public void adicionar(Aluno novoAluno) {
        Node novoNo = new Node(novoAluno);

        if (raiz == null) {
            this.raiz = novoNo; // arvore n existe
        } else {
            Node atual = raiz;
            Node anterior;
            while (true) {
                anterior = atual;
                if (Integer.parseInt(novoAluno.getMatricula()) <= Integer.parseInt(atual.getAluno().getMatricula())) { // Se a matricula do novo aluno for menor do 
                    atual = atual.getEsquerda();                                                                       // atual a ser comparado, vai para esquerda
                    if (atual == null) { // se o atual for nulo, chegou no final da árvore, e o novo aluno vira a folha 
                        anterior.setEsquerda(novoNo);
                        return;
                    }
                } else { // se for maior para direita
                    atual = atual.getDireita();
                    if (atual == null) { // se o atual for nulo, chegou no final da árvore, e o novo aluno vira a folha 
                        anterior.setDireita(novoNo); 
                        return;
                    }
                }
            }
        }

    }

    public void exibir() {
        exibir(raiz);
    }

    public Node buscar(String matricula) {
        if (raiz == null)
            return null; // se arvore vazia
        Node atual = raiz; // começa a procurar desde raiz
        while (!atual.getAluno().getMatricula().equals(matricula)) { // enquanto nao encontrou
            if (Integer.parseInt(matricula) < Integer.parseInt(atual.getAluno().getMatricula()))
                atual = atual.getEsquerda(); // caminha para esquerda
            else
                atual = atual.getDireita(); // caminha para direita
            if (atual == null)
                return null; // se nao encontrou nada, retorna null
        } 
        return atual; // terminou o laço while e chegou aqui é pq encontrou item
    }

    public Boolean remover(String matricula) {
        if (raiz == null)
            return false;

        Node atual = raiz; // nó a ser comparado
        Node pai = raiz; // nó anterior do comparado
        Boolean filho_esq = true; // se existe esquerda

        while (!atual.getAluno().getMatricula().equals(matricula)) { // enquanto a matricula do no atual a ser comparado é diferente da matricula a ser comparado
            pai = atual;
            if (Integer.parseInt(matricula) < Integer.parseInt(atual.getAluno().getMatricula())) { // se for menor, corre para esquerda
                atual = atual.getEsquerda(); 
                filho_esq = true;
            } else { // se for maior, corre para direita
                atual = atual.getDireita();
                filho_esq = false;
            }
            if (atual == null) // se nao encontrou nada, retorna falso
                return false;
        }

        if (atual.getEsquerda() == null && atual.getDireita() == null) { // se nao exitir filhos a esquerda ou direita
            if (atual == raiz) // se for a raiz, então ele remove o valor da raiz, pois existe apenas esse valor
                raiz = null;
            else if (filho_esq) // se ele for filho da esquerda
                pai.setEsquerda(null); // ele irá apagar o valor, a partir do pai dele
            else // se for filho da fireita
                pai.setDireita(null);
        }

        else if (atual.getDireita() == null) { // se nao existir filho a direita, apenas existir na esquerda
            if (atual == raiz) // se o no atual for raiz, recebe o filho da esquerda e o filho da esquerda vira a raiz
                raiz = atual.getEsquerda();  
            else if (filho_esq) // se ele for filho da esquerda
                pai.setEsquerda(atual.getEsquerda()); // ele ira alterar o valor dele a partir do pai, o valor dele será alterado para o filho da esquerda( trazendo toda arvore junto)
            else // se ele for filho da direita
                pai.setDireita(atual.getEsquerda()); 
        }

        else if (atual.getEsquerda() == null) { // se nao existir filho na esquerda, apenas existir na direita
            if (atual == raiz) // se o no atual for raiz, recebe o filho da direita e o filho da direita vira a raiz
                raiz = atual.getDireita();
            else if (filho_esq) // se ele for filho da esquerda
                pai.setEsquerda(atual.getDireita());  // ele ira alterar o valor dele a partir do pai, o valor dele será alterado para o filho da direita( trazendo toda arvore junto)
            else // se ele for filho da direita
                pai.setDireita(atual.getDireita());
        }

        else {
            Node sucessor = no_sucessor(atual);
            if (atual == raiz)
                raiz = sucessor;
            else if (filho_esq)
                pai.setEsquerda(sucessor);
            else
                pai.setDireita(sucessor);
            sucessor.setEsquerda(atual.getEsquerda());
        }
        return true;
    }

    public Node no_sucessor(Node apaga) { // O parametro é a referencia para o No que deseja-se apagar
        Node paidosucessor = apaga;
        Node sucessor = apaga;
        Node atual = apaga.getDireita(); // vai para a subarvore a direita

        while (atual != null) { // enquanto nao chegar no Nó mais a esquerda
            paidosucessor = sucessor;
            sucessor = atual;
            atual = atual.getEsquerda(); // caminha para a esquerda
        }
        if (sucessor != apaga.getDireita()) { // se sucessor nao é o filho a direita do Nó que deverá ser eliminado
            paidosucessor.setEsquerda(sucessor.getDireita()); // pai herda os filhos do sucessor que sempre serão a
                                                              // direita
            // lembrando que o sucessor nunca poderá ter filhos a esquerda, pois, ele sempre
            // será o
            // Nó mais a esquerda da subarvore a direita do Nó apaga.
            // lembrando também que sucessor sempre será o filho a esquerda do pai

            sucessor.setDireita(apaga.getDireita());// guardando a referencia a direita do sucessor para
            // quando ele assumir a posição correta na arvore
        }
        return sucessor;
    }

    public void exibir(Node atual) {
        if (atual != null) {
            exibir(atual.getEsquerda());
            System.out.print(atual.getAluno().getNome() + " ");
            exibir(atual.getDireita());
        }
    }

    public static void salvarDados(List alunos) {
        try (FileWriter fout = new FileWriter("alunos.txt");
                BufferedWriter bout = new BufferedWriter(fout)) {

            ArrayList<Aluno> alunoS = new ArrayList<>(alunos);

            for (Aluno aluno : alunoS) {
                bout.write("Nome: " + aluno.getNome() + "\n" + "Matricula: " + aluno.getMatricula() + "\n"
                        + "Data de Nascimento: " + aluno.getDataNascimento() + "\n");
                bout.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao salvar itens");
        }

    }

    public void carregarDados(){
        try (FileReader fin = new FileReader("itens.txt");
                BufferedReader bin = new BufferedReader(fin)) {
            
            String linha = bin.readLine();
            while(linha != null){
                String[] tokens = linha.split("\n");
                String nome = tokens[0];
                String matricula = tokens[1];
                String dataNascimento = tokens[2];
                
                

                

                linha = bin.readLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar itens");
        }
    

    }

}
