package br.com.cesar.apresentacao;

import br.com.cesar.entidades.Chamado;
import br.com.cesar.entidades.Cliente;
import br.com.cesar.entidades.Funcionario;
import br.com.cesar.excecoes.ChamadoNaoExistente;
import br.com.cesar.interfaces.INegocioFuncionario;
import br.com.cesar.negocios.NegocioChamado;
import br.com.cesar.negocios.NegocioFuncionario;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChamadosAdministrador {
    private Scanner sc;
    private INegocioFuncionario negocioFuncionario;
    private NegocioChamado negocioChamado;
    private Funcionario funcionario;
    private Chamado chamado;
    private ChamadosCliente chamadosCliente;
    private Map<Integer, Integer> buscar;
    private Cliente cliente;

    public ChamadosAdministrador() {
        sc = new Scanner(System.in);
        funcionario = new Funcionario();
        negocioFuncionario = new NegocioFuncionario();
        negocioChamado = new NegocioChamado();
        chamado = new Chamado();
        chamadosCliente = new ChamadosCliente();
        buscar = new HashMap<>();
        cliente = new Cliente();
    }

    public void painelAdministrador(Funcionario funcionario) throws ChamadoNaoExistente, IOException, ClassNotFoundException {
        System.out.println("Logado como administrador com sucesso!!");

        boolean running = true;
        int id;
        while (running) {
            System.out.println("=================================================");
            System.out.println("Bem-vindo ao painel de controle de administradores");
            System.out.println("Você está logado como: " + funcionario.getNomeCompleto()
                    + "\nE seu usuário é: " + funcionario.getLogin() + '[' + funcionario.getId() + ']');
            System.out.println("Oque você deseja fazer?" +
                    "\n1 - Ver chamados abertos" +
                    "\n2 - Apagar chamado por ID" +
                    "\n3 - Alterar chamado por ID" +
                    "\n4 - Buscar chamado por ID" +
                    "\n5 - Buscar chamado por ID(Map)" +
                    "\n6 - Adicionar chamado" +
                    "\n7 -  " +
                    "\n - Sair");
            int opcao = Integer.parseInt(sc.nextLine());
            if (opcao == 1) {
                System.out.println(negocioChamado.listarTodosChamados());
            } else if (opcao == 2) {
                System.out.println("Digite o ID do chamado que você deseja apagar: ");
                id = Integer.parseInt(sc.nextLine());
                if (id == chamado.getNumeroChamado()) {
                    negocioChamado.removerChamado(chamado.getNumeroChamado());
                    System.out.println("Chamado de número " + '[' + chamado.getNumeroChamado() + ']' + " apagado com sucesso!");
                } else {
                    System.out.println("Não existe um chamado com esse ID.");
                }
            } else if (opcao == 3) {
                //alterar chamado por id
                System.out.println("Digite o ID do chamado que você deseja alterar: ");
                id = Integer.parseInt(sc.nextLine());
                if (id == chamado.getNumeroChamado()) {
                    negocioChamado.alterarChamado(chamado.getNumeroChamado(), null);
                    System.out.println("Chamado de número " + '[' + chamado.getNumeroChamado() + ']' + " alterado com sucesso!");
                } else {
                    System.out.println("Não existe um chamado com esse ID.");
                }
            } else if (opcao == 4) {
                System.out.println("Digite o ID do chamado que você deseja buscar: ");
                id = Integer.parseInt(sc.nextLine());
                if (id == chamado.getNumeroChamado()) {
                    System.out.println(negocioChamado.buscarChamado(id));
                } else {
                    System.out.println("Não existe um chamado com esse ID.");
                }
            } else if (opcao == 5) {
                System.out.println("Digite o ID do chamado que você deseja buscar(map): ");
                Integer key = Integer.parseInt(sc.nextLine());
                System.out.println("Digite o ID do cliente que você deseja buscar(map): ");
                Integer idc = Integer.parseInt(sc.nextLine());
                buscar.put(key, idc);
                if(key == chamado.getNumeroChamado() && idc == cliente.getId()){
                    for(Integer buscarChamado : buscar.keySet()){
                        System.out.println("Chamado de ID: "+chamado.getNumeroChamado());
                        System.out.println(negocioChamado.buscarChamado(key));
                    }
                }
            } else if (opcao == 6) {
                //add chamado
                //negocioChamado.adicionarChamado();
            } else if (opcao == 7) {
                System.out.println("Saindo..");
                System.exit(0);
            }
        }
    }
}
