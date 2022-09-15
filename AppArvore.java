import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AppArvore{

    
    public static void main(String[] args){

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

        List<Aluno> alunos = new ArrayList<>();
        

        alunos = arvore.carregarDados(alunos);

        

        System.out.println("Arvore Binaria de Aluno");

        do {
            System.out.print("\n***********************************");
            System.out.print("\nEntre com a opcao:");
            System.out.print("\n ----1: Adicionar");
            System.out.print("\n ----2: Remover");
            System.out.print("\n ----3: Buscar");
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
                    ler.nextLine();
                    nome = ler.nextLine();

                    System.out.println("Data de nascimento: ");
                    System.out.print("Dia: ");
                    dia = ler.nextInt();
                    System.out.print("Mes: ");
                    mes = ler.nextInt();
                    System.out.print("Ano: ");
                    ano = ler.nextInt();

                    novoAluno = new Aluno(Integer.toString(matricula), nome, new DataNascimento(dia, mes, ano));
                    alunos.add(novoAluno);

                    System.out.println("A matricula desse aluno é: " + matricula);
                    System.out.println("Digite 1 para continuar");
                    String pausa = ler.next();
                    arvore.adicionar(novoAluno);

                    arvore.salvarDados(alunos);

                    break;
                }
                case 2: {
                    System.out.println("Informe o numero da matricula do aluno que deseja remover");
                    matricula = ler.nextInt();
                    String matriculaS = matricula + "";
                    Aluno alunoAux = null;
                    if (arvore.remover(matriculaS)){
                        System.out.println("Aluno removido com sucesso");
                        for (Aluno aluno : alunos) {
                            if(matriculaS.equals(aluno.getMatricula())){
                                alunoAux = aluno;
                            } 

                        }
                        if(alunoAux != null){
                            alunos.remove(alunoAux);
                        }

                        arvore.salvarDados(alunos);
                            
                        

                    }

                    else
                        System.out.println("Aluno nao encontrado");
                    ;
                    break;
                }
                case 3: {

                    System.out.println("Qual o numero da matricula do aluno que deseja buscar? \n>");
                    matricula = ler.nextInt();
                    String matriculaS = matricula + "";

                    Node aluno = arvore.buscar(matriculaS);

                    if (aluno != null) {
                        System.out.print("\n Valor Encontrado: ");
                        System.out.println(aluno.getAluno().getNome());
                    } else
                        System.out.print("\n Valor nao encontrado!");
                    break;
                }
                case 4: {
                    System.out.println("Exibir");

                    arvore.exibir();
                    break;
                }
            } // fim switch
        } while (opcao != 5);
    }

    

}
