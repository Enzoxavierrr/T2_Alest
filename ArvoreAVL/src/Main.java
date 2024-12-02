import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        AVLTree tree = new AVLTree();
        int op;


        while (true) {
            System.out.println("\nÁRVORE AVL");
            System.out.println("---------------------------------------");
            System.out.println("1 | Adicionar números (10 a 90 em ordem crescente)");
            System.out.println("2 | Apresentar a visualização hierárquica da árvore");
            System.out.println("3 | Apresentar a altura da árvore");
            System.out.println("4 | Limpar o conteúdo da árvore");
            System.out.println("5 | Adicionar um numero inteiro na arvore");
            System.out.println("6 | Incluir números (90 a 10 em ordem decrescente)");
            System.out.println("7 | Apresentar o conteúdo da árvore usando caminhamento central");
            System.out.println("8 | Verificar se um elemento está armazenado na árvore ou não");
            System.out.println("9 | Retornar o pai de um elemento na árvore");
            System.out.println("10 | Verificar quantos elementos tem na árvore");
            System.out.println("11 | Remover um elemento da árvore");
            System.out.println("0 | Sair do programa");
            System.out.println("---------------------------------------");
            System.out.print("Digite a opção desejada: ");

            op = scan.nextInt();

            switch (op){
                case 1 -> {
                    int[] values = {10, 20, 30, 40, 50, 60, 70, 80, 90};
                    for (int value : values) {
                        tree.add(value);
                    }
                    System.out.println("Números adicionados na árvore em ordem crescente.");
                }
                case 2 -> {
                    System.out.println("Visualização hierárquica da árvore:\n" + tree.printTree());
                }
                case 3 -> {
                    System.out.println("Altura da árvore: " + tree.height());
                }
                case 4 -> {
                    tree.clear();
                    System.out.println("Conteúdo da árvore limpo.");
                }
                case 5 -> {
                    System.out.println("Digite o número inteiro que deseja adicionar na árvore: ");
                    int value = scan.nextInt();
                    tree.add(value);
                    System.out.println("Número adicionado na árvore.");
                }
                case 6 -> {
                    int[] values = {90, 80, 70, 60, 50, 40, 30, 20, 10};
                    for (int value : values) {
                        tree.add(value);
                    }
                    System.out.println("Números adicionados na árvore em ordem decrescente.");
                }
                case 7 -> {
                    System.out.println("Caminhamento central da árvore (InOrder): " + tree.inOrder());
                }
                case 8 -> {
                    System.out.println("Digite o número inteiro que deseja verificar se está armazenado na árvore: ");
                    int value = scan.nextInt();
                    System.out.println("O número " + value + " está armazenado na árvore? " + tree.contains(value));
                }
                case 9 -> {
                    System.out.println("Digite o número inteiro que deseja verificar o pai na árvore: ");
                    int value = scan.nextInt();
                    System.out.println("O pai do número " + value + " é: " + tree.parent(value));
                }
                case 10 -> {
                    if(tree.isEmpty()){
                        System.out.println("A árvore está vazia.");
                    } else {
                        System.out.println("Quantos elementos tem na árvore? " + tree.size());
                    }
                }
                case 11 -> {
                    System.out.println("Digite o número inteiro que deseja remover da árvore: ");
                    int value = scan.nextInt();
                    tree.remove(value);
                    System.out.println("Número removido da árvore.");
                }

                case 0 -> {
                    System.out.println("Programa encerrado.");
                    scan.close();
                    System.exit(0);
                }
            }
        }
    }
}