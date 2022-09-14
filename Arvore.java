public class Arvore {

    private Node raiz;

    public Arvore() {
        this.raiz = null;
    }

    public void adicionar(Aluno novoAluno){
        Node novoNo = new Node(novoAluno);

        if(raiz == null){
            this.raiz = novoNo;
        }else{
            Node atual = raiz;
            Node anterior;
            while(true){
                anterior = atual;
                if(Integer.parseInt(novoAluno.getMatricula()) <= Integer.parseInt(atual.getAluno().getMatricula())){
                    atual = atual.getEsquerda();
                    if(atual == null){
                        anterior.setEsquerda(novoNo);
                        return;
                    }
                }else{
                    atual = atual.getDireita();
                    if(atual == null){
                        anterior.setDireita(novoNo);
                        return;
                    }
                }
            }
        }

    }

    public void exibir(){

    }

    public Node buscar(String matricula) {
        if (raiz == null) return null; // se arvore vazia
        Node atual = raiz;  // começa a procurar desde raiz
        while (!atual.getAluno().getMatricula().equals(matricula)) { // enquanto nao encontrou
          if(Integer.parseInt(matricula) < Integer.parseInt(atual.getAluno().getMatricula()) ) atual = atual.getEsquerda(); // caminha para esquerda
          else atual = atual.getDireita(); // caminha para direita
          if (atual == null) return null; // encontrou uma folha -> sai
        } // fim laço while
        return atual; // terminou o laço while e chegou aqui é pq encontrou item
      }
}
