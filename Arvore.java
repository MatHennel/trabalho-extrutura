public class Arvore {

    private Node raiz;

    public Arvore() {
        this.raiz = null;
    }

    public void adicionar(Aluno novoAluno) {
        Node novoNo = new Node(novoAluno);

        if (raiz == null) {
            this.raiz = novoNo;
        } else {
            Node atual = raiz;
            Node anterior;
            while (true) {
                anterior = atual;
                if (Integer.parseInt(novoAluno.getMatricula()) <= Integer.parseInt(atual.getAluno().getMatricula())) {
                    atual = atual.getEsquerda();
                    if (atual == null) {
                        anterior.setEsquerda(novoNo);
                        return;
                    }
                } else {
                    atual = atual.getDireita();
                    if (atual == null) {
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
                return null; // encontrou uma folha -> sai
        } // fim laço while
        return atual; // terminou o laço while e chegou aqui é pq encontrou item
    }

    public Boolean remover(String matricula) {
        if (raiz == null)
            return false;

        Node atual = raiz;
        Node pai = raiz;
        Boolean filho_esq = true;

        while (!atual.getAluno().getMatricula().equals(matricula)) {
            pai = atual;
            if (Integer.parseInt(matricula) < Integer.parseInt(atual.getAluno().getMatricula())) {
                atual = atual.getEsquerda();
                filho_esq = true;
            } else {
                atual = atual.getDireita();
                filho_esq = false;
            }
            if (atual == null)
                return false;
        }

        if (atual.getEsquerda() == null && atual.getDireita() == null) {
            if (atual == raiz)
                raiz = null;
            else if (filho_esq)
                pai.setEsquerda(null);
            else
                pai.setDireita(null);
        }

        else if (atual.getDireita() == null) {
            if (atual == raiz)
                raiz = atual.getEsquerda();
            else if (filho_esq)
                pai.setEsquerda(atual.getEsquerda());
            else
                pai.setDireita(atual.getEsquerda());
        }

        else if(atual.getEsquerda() == null){
            if(atual == raiz) raiz = atual.getEsquerda();
            else if(filho_esq) pai.setEsquerda(atual.getDireita());
            else pai.setDireita(atual.getDireita());
        }

        else{
            Node sucessor = no_sucessor(atual);
            if(atual == raiz) raiz = sucessor;
            else if(filho_esq) pai.setEsquerda(sucessor);
            else pai.setDireita(sucessor);
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
        // *********************************************************************************
        // quando sair do while "sucessor" será o Nó mais a esquerda da subarvore a direita
        // "paidosucessor" será o o pai de sucessor e "apaga" o Nó que deverá ser eliminado
        // *********************************************************************************
        if (sucessor != apaga.getDireita()) { // se sucessor nao é o filho a direita do Nó que deverá ser eliminado
          paidosucessor.setEsquerda(sucessor.getDireita()); // pai herda os filhos do sucessor que sempre serão a direita
          // lembrando que o sucessor nunca poderá ter filhos a esquerda, pois, ele sempre será o
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
}
