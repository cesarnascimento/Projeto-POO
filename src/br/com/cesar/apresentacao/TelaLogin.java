package br.com.cesar.apresentacao;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

import br.com.cesar.entidades.Cliente;
import br.com.cesar.entidades.Funcionario;
import br.com.cesar.entidades.Pessoa;
import br.com.cesar.excecoes.ChamadoNaoExistente;
import br.com.cesar.excecoes.ClienteNaoExistente;
import br.com.cesar.excecoes.FuncionarioNaoExistente;
import br.com.cesar.negocios.NegocioCliente;
import br.com.cesar.negocios.NegocioFuncionario;
import br.com.cesar.utils.Utils;

public class TelaLogin {
    private NegocioCliente negocioCliente;
    private NegocioFuncionario negocioFuncionario;
    private Scanner sc;


    public TelaLogin() {
        negocioCliente = new NegocioCliente();
        negocioFuncionario = new NegocioFuncionario();
        sc = new Scanner(System.in);

        rodar();
    }

    private void rodar() {
        try {
            System.out.println("Já é cadastrado? (S/N)");
            char opc = sc.nextLine().charAt(0);

            if (opc == 'S' || opc == 's') {
                System.out.println();
                jaCadastrado();
            } else if (opc == 'N' || opc == 'n') {
                System.out.println("=================================================");
                System.out.println("Cadastrar como cliente - 1\nFuncionário - 2 \nAdministrador - 3");
                System.out.println("=================================================");
                int opcao = Integer.parseInt(sc.nextLine());
                if (opcao == 1) {
                    cadastroCliente();
                } else if (opcao == 2) {
                    cadastroFuncionario();
                } else if (opcao == 3) {
                    cadastroAdministrador();
                } else {
                    System.out.println("Opção inválida.");
                }

            }else{
                System.out.println("Opção inválida.");
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } catch (ClienteNaoExistente | FuncionarioNaoExistente e) {
            System.out.println(e.getMessage());
        } catch (ChamadoNaoExistente chamadoNaoExistente) {
            chamadoNaoExistente.printStackTrace();
        }
    }

    private void jaCadastrado() throws ClassNotFoundException, IOException, ClienteNaoExistente, FuncionarioNaoExistente, ChamadoNaoExistente {
        System.out.println("Entre com seu login abaixo:");
        String login = sc.nextLine();

        Pessoa pessoa = null;

        if (negocioCliente.contemCliente(login)) {
            System.out.println("Entre com sua senha:");
            String senha = sc.nextLine();

            pessoa = negocioCliente.buscarCliente(login);

            if (senha.equals(((Cliente) pessoa).getSenha())) {
                // menu de clientes
                new ChamadosCliente().painelCliente((Cliente) pessoa);
            } else {
                System.out.println("Senha incorreta.");
            }
        } else if (negocioFuncionario.contemFuncionario(login)) {
            pessoa = negocioFuncionario.buscarFuncionario(login);

            System.out.println("Entre com sua senha:");
            String senha = sc.nextLine();

            if (senha.equals(((Funcionario) pessoa).getSenha())) {
                if (((Funcionario) pessoa).getCargo().equals("Funcionario")) {
                    new ChamadosFuncionario().painelFuncionario((Funcionario) pessoa);
                } else if (((Funcionario) pessoa).getCargo().equals("Administrador")) {
                    // menu de administradores
                    new ChamadosAdministrador().painelAdministrador((Funcionario) pessoa);
                }
            } else {
                System.out.println("Senha incorreta.");
            }
        }

    }

    public void cadastroCliente() throws ClienteNaoExistente, FuncionarioNaoExistente, ChamadoNaoExistente {
        TreeSet<String> tree = new TreeSet<String>();

        try {
            System.out.println("=================================================");
            System.out.println("Façamos seu cadastro de cliente então:\n");

            System.out.println("Digite seu nome: ");
            String nome = sc.nextLine();
            tree.add(nome);

            System.out.println("Digite seu login: ");
            String login = sc.nextLine();
            tree.add(login);

            System.out.println("Digite sua senha: ");
            String senha = sc.nextLine();

            System.out.println("Digite seu ano de nascimento: ");
            String ano = sc.nextLine();
            tree.add(ano);
            Iterator<String> iterator = tree.iterator();
            System.out.println("Elementos do TreeSet do usuário: ");
            while(iterator.hasNext()) {
                System.out.println(iterator.next()+ " ");
            }
            //utilizar treeSet aqui

            negocioCliente.adicionarCliente(new Cliente(Utils.gerarIdCliente(), nome, null, login, senha));

            System.out.println("Cadastro efetuado com sucesso.\n");

            jaCadastrado();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

    public void cadastroFuncionario() throws ClienteNaoExistente, FuncionarioNaoExistente, ChamadoNaoExistente {
        try {
            System.out.println("=================================================");
            System.out.println("Façamos seu cadastro de funcionário então:\n");

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
            jaCadastrado();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

    public void cadastroAdministrador() throws ClienteNaoExistente, FuncionarioNaoExistente, ChamadoNaoExistente {
        try {
            System.out.println("=================================================");
            System.out.println("Façamos seu cadastro de administrador então:\n");

            System.out.println("Digite seu nome: ");
            String nome = sc.nextLine();

            System.out.println("Digite seu login: ");
            String login = sc.nextLine();

            System.out.println("Digite sua senha: ");
            String senha = sc.nextLine();
            String cargo = "Administrador";
            negocioFuncionario.adicionarFuncionario(new Funcionario(Utils.gerarIdFuncionario(), nome,null,login,senha, "Administrador",null));

            System.out.println("Cadastro de administrador efetuado com sucesso.\n");
            jaCadastrado();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

}