package br.com.cesar.apresentacao;

import br.com.cesar.entidades.Chamado;
import br.com.cesar.entidades.Cliente;
import br.com.cesar.entidades.Funcionario;
import br.com.cesar.excecoes.ChamadoNaoExistente;
import br.com.cesar.excecoes.FuncionarioNaoExistente;
import br.com.cesar.interfaces.INegocioFuncionario;
import br.com.cesar.negocios.NegocioChamado;
import br.com.cesar.negocios.NegocioFuncionario;
import br.com.cesar.utils.Utils;

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

    public void painelAdministrador(Funcionario funcionario) throws ChamadoNaoExistente, IOException, ClassNotFoundException, FuncionarioNaoExistente {
        System.out.println("Logado como administrador com sucesso!!");

        boolean running = true;
        int id;
        while (running) {
            System.out.println("=================================================");
            System.out.println("Bem-vindo ao painel de controle de administradores");
            System.out.println("Você está logado como: " + funcionario.getNomeCompleto()
                    + "\nE seu usuário é: " + funcionario.getLogin() + '[' + funcionario.getId() + ']');
            System.out.println("Oque você deseja fazer?" +
                    "\n1 - Ver chamados abertos" +//ok
                    "\n2 - Apagar chamado por ID" +//ok
                    "\n3 - Alterar chamado por ID" + //faltando
                    "\n4 - Buscar chamado por ID" + //ok
                    "\n5 - Buscar chamado por ID(Map)" +//ok
                    "\n6 - Adicionar chamado" +//ok
                    "\n7 - Adicionar funcionário" +//ok
                    "\n8 - Buscar funcionário" + //faltando
                    "\n9 - Remover funcionário" + //faltando
                    "\n10 - Alterar funcionário" +//faltando
                    "\n11 - Estatísticas de Chamados" +
                    "\n12 - Sair");
            System.out.println("=================================================");
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
                    System.out.println("Digite o novo título do chamado de ID "+chamado.getNumeroChamado());
                    chamado.setTitulo(sc.nextLine());
                    System.out.println("Digite a nova categoria: ");
                    chamado.setCategoria(sc.nextLine());
                    System.out.println("Digite a nova descrição: ");
                    chamado.setDescricao(sc.nextLine());
                    negocioChamado.alterarChamado(chamado.getNumeroChamado(), chamado);
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
                cliente.setNomeCompleto("Administrador");
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

                negocioChamado.adicionarChamado(chamado);
            } else if (opcao == 7) {
                //adicionar funcionário
                try {
                    System.out.println("=================================================");
                    System.out.println("Façamos o cadastro de funcionário então:\n");

                    System.out.println("Digite seu nome: ");
                    String nome = sc.nextLine();

                    System.out.println("Digite seu login: ");
                    String login = sc.nextLine();

                    System.out.println("Digite sua senha: ");
                    String senha = sc.nextLine();
                    String cargo = "Funcionario";
                    negocioFuncionario.adicionarFuncionario(new Funcionario(Utils.gerarIdFuncionario(), nome,null,login,senha, "Funcionario",null));
                    //negocioCliente.adicionarCliente(new Cliente(Utils.gerarIdCliente(), nome, null, login, senha));

                    System.out.println("Cadastro de funcionário efetuado com sucesso.\n");
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            } else if(opcao == 8){
                //buscar funcionário
                System.out.println("Digite o ID do funcionário que você deseja buscar:");
                id = Integer.parseInt(sc.nextLine());
                if(id == funcionario.getId()){
                    System.out.println(negocioFuncionario.buscarFuncionario(id));
                }else{
                    System.out.println("Esse ID não existe.");
                }
            } else if(opcao == 9){
                //remover funcionario
                System.out.println("Digite o ID do funcionário que você deseja remover:");
                id = Integer.parseInt(sc.nextLine());
                if(id == funcionario.getId()){
                    negocioFuncionario.removerFuncionario(id);
                    System.out.println("Removido.");
                }else{
                    System.out.println("Esse ID não existe.");
                }
            } else if(opcao == 10){
                //alterar funcionário

            }else if(opcao == 11){
                chamadosCliente.estatisticas();

            }else if(opcao == 12){
                System.out.println("Saindo..");
                System.exit(0);
            }
        }
    }
}
