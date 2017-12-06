package br.com.cesar.apresentacao;

import java.io.IOException;
import java.util.Scanner;

import br.com.cesar.entidades.Chamado;
import br.com.cesar.entidades.Cliente;
import br.com.cesar.interfaces.INegocioCliente;
import br.com.cesar.negocios.NegocioChamado;
import br.com.cesar.negocios.NegocioCliente;

public class ChamadosCliente {
    private Scanner sc;
    private INegocioCliente negocioCliente;
    private NegocioChamado negocioChamado;
    private Cliente cliente;

    public ChamadosCliente() {
        sc = new Scanner(System.in);
        cliente = new Cliente();
        negocioCliente = new NegocioCliente();
        negocioChamado = new NegocioChamado();
    }

    public void painelCliente(Cliente cliente) {
        System.out.println("Logado como cliente com sucesso!!");

        boolean running = true;
        while (running) {
            //fazer verificação de cadastro para usuario
            System.out.println("=================================================");
            System.out.println("Bem-vindo à criação de chamados para usuários");
            System.out.println("Você está logado como: " + cliente.getNomeCompleto() +
                    "\nE seu usuário é: " + cliente.getLogin() + '[' + cliente.getId() + ']');
            System.out.println("Oque você deseja fazer?" +
                    "\n1 - Abrir chamado" +
                    "\n2 - Ver chamados abertos" +
                    "\n3 - Sair");
            System.out.println("=================================================");
            int opcaoCliente = Integer.parseInt(sc.nextLine());

            if (opcaoCliente == 1) {
                abrirChamado(cliente);
            } else if (opcaoCliente == 2) {
                try {
                    listarChamados(cliente);
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            } else if (opcaoCliente == 3) {
                System.out.println("Saindo..");
                System.exit(0);
            }
        }
    }

    private void abrirChamado(Cliente cliente) {
        Chamado chamado = new Chamado();

        System.out.println("Entre com o título do Chamado:");
        // set Titulo
        chamado.setTitulo(sc.nextLine());

        int opc = 0;
        do {
            System.out.println("Entre com o setor do Chamado:");
            System.out.println("1 - Administração");
            System.out.println("2 - RH");
            System.out.println("3 - Diretoria");
            opc = Integer.parseInt(sc.nextLine());

            if (opc < 1 || opc > 3) {
                System.out.println("Valor aceitável: 1, 2 e 3");
            }
        } while (opc < 1 || opc > 3);
        // set Setor
        chamado.setSetor(((opc == 1) ? "Administração" : ((opc == 2) ? "RH" : "Diretoria")));

        opc = 0;
        do {
            System.out.println("Entre com a Categoria do Chamado:");
            System.out.println("1 - Hardware");
            System.out.println("2 - Software");
            System.out.println("3 - Rede");
            System.out.println("4 - Manutenção");
            System.out.println("5 - Backup");
            opc = Integer.parseInt(sc.nextLine());

            if (opc < 1 || opc > 5) {
                System.out.println("Valor aceitável: 1, 2, 3, 4 e 5");
            }
        } while (opc < 1 || opc > 5);

        String categoria = null;
        switch (opc) {
            case 1:
                categoria = "Hardware";
                break;
            case 2:
                categoria = "Software";
                break;
            case 3:
                categoria = "Rede";
                break;
            case 4:
                categoria = "Manutenção";
                break;
            case 5:
                categoria = "Backup";
                break;
        }

        //set Categoria
        chamado.setCategoria(categoria);

        System.out.println("Entre com a Descrição do Chamado: ");
        //set Descrição
        chamado.setDescricao(sc.nextLine());

        opc = 0;
        do {
            System.out.println("Qual a prioridade?\n1 - Alta\n2 - Média\n3 - Baixa\n");
            opc = Integer.parseInt(sc.nextLine());

            if (opc < 1 || opc > 3) {
                System.out.println("Valor aceitável: 1, 2 e 3");
            }
        } while (opc < 1 || opc > 3);
        //set Prioridade
        chamado.setPrioridade(opc);

        //setCliente
        chamado.setCliente(cliente);

        try {
            negocioChamado.adicionarChamado(chamado);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listarChamados(Cliente cliente) throws ClassNotFoundException, IOException {

        if (negocioChamado.listarTodosChamados().size() > 0) {
            System.out.println("Título de chamados registrados, em nome de " + cliente.getNomeCompleto() + " e seus IDs:");
            for (Chamado chamado : negocioChamado.listarTodosChamados()) {
                if (chamado.getCliente().getId() == cliente.getId()) {
                    System.out.println(chamado.getTitulo() + " [" + chamado.getNumeroChamado() + ']' +"[Categoria: "+chamado.getCategoria()+']'+ "[Descrição: "+chamado.getDescricao()+']');
                }
            }
        } else {
            System.out.println("Não há chamados abertos.");
        }
    }
}
