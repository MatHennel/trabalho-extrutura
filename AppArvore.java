import java.util.Random;
import java.util.Scanner;

public class AppArvore {
    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);
        Arvore arvore = new Arvore();
        int opcao;
        Aluno novoAluno;
        String nome;
        int dia;
        int mes;
        int ano;
        int matricula;
        Random random = new Random();
        

        System.out.println("Arvore Binaria de Aluno");

        do {
            System.out.print("\n***********************************");
            System.out.print("\nEntre com a opcao:");
            System.out.print("\n ----1: Adicionar");
            System.out.print("\n ----2: Excluir");
            System.out.print("\n ----3: Pesquisar");
            System.out.print("\n ----4: Exibir");
            System.out.print("\n ----5: Sair do programa");
            System.out.print("\n***********************************");
            System.out.print("\n-> ");
            opcao = ler.nextInt();
            switch (opcao) {
                case 1: {
                    matricula = random.nextInt(1000000000);

                    System.out.println("Informe os dados do aluno: ");
                    System.out.print("Nome do aluno: ");
                    nome = ler.next();
                    System.out.println("Data de nascimento: ");
                    System.out.print("Dia: ");
                    dia = ler.nextInt();
                    System.out.print("Mes: ");
                    mes = ler.nextInt();
                    System.out.print("Ano: ");
                    ano = ler.nextInt();

                    

                    novoAluno = new Aluno(Integer.toString(matricula), nome,new DataNascimento(dia, mes, ano));

                    
                    arvore.adicionar(novoAluno);
                    break;
                }
                case 2: {
                    System.out.print("\n Informe o valor (long) -> ");
                    x = le.nextLong();
                    if (!arvore.remover(x))
                        System.out.print("\n Valor nao encontrado!");
                    ;
                    break;
                }
                case 3: {
                    System.out.println("Qual o numero da matricula do aluno que deseja buscar? \n>");
                    matricula = ler.nextInt();
                    String matriculaS = matricula + "";
                    
                    if (arvore.buscar(matriculaS) != null)
                        System.out.print("\n Valor Encontrado");
                    else
                        System.out.print("\n Valor nao encontrado!");
                    break;
                }
                case 4: {
                    arvore.caminhar();
                    break;
                }
            } // fim switch
        } while (opcao != 5);
    }
}
