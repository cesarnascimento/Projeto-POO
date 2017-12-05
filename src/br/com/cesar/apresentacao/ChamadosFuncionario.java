package br.com.cesar.apresentacao;

import br.com.cesar.entidades.Chamado;
import br.com.cesar.entidades.Cliente;
import br.com.cesar.entidades.Funcionario;
import br.com.cesar.excecoes.ChamadoNaoExistente;
import br.com.cesar.interfaces.INegocioFuncionario;
import br.com.cesar.negocios.NegocioChamado;
import br.com.cesar.negocios.NegocioFuncionario;

import java.io.IOException;
import java.util.Scanner;

public class ChamadosFuncionario {

    private Scanner sc;
    private INegocioFuncionario negocioFuncionario;
    private NegocioChamado negocioChamado;
    private Funcionario funcionario;
    private ChamadosCliente chamadosCliente;
    private Chamado chamado;


    public ChamadosFuncionario() {
        sc = new Scanner(System.in);
        negocioFuncionario = new NegocioFuncionario();
        negocioChamado = new NegocioChamado();
        funcionario = new Funcionario();
        chamadosCliente = new ChamadosCliente();
        chamado = new Chamado();
    }

    public void painelFuncionario(Cliente cliente) throws ChamadoNaoExistente, IOException, ClassNotFoundException {
        System.out.println("Logado como funcionário com sucesso!");

        boolean running = true;
        while(running){
            System.out.println("=================================================");
            System.out.println("Bem-vindo ao painel de controle de funcionários");
            System.out.println("Você está logado como: "+funcionario.getNomeCompleto()
            +"\nE seu usuário é: "+funcionario.getLogin()+ '[' + funcionario.getId() + ']');
            System.out.println("Oque você deseja fazer?" +
                    "\n1 - Ver chamados abertos" +
                    "\n2- Apagar chamado por ID" +
                    "\n3 - Sair");
            int opcao = Integer.parseInt(sc.nextLine());

            if(opcao == 1){
                //ver chamados abertos
                try {
                    chamadosCliente.listarChamados(cliente);
                } catch (ClassNotFoundException | IOException e) {
                    e.printStackTrace();
                }
            }else if(opcao == 2){
                //apagar chamado por ID
                System.out.println("Digite o ID do chamado que você deseja apagar: ");
                int id = Integer.parseInt(sc.nextLine());
                if(id == chamado.getNumeroChamado()){
                    negocioChamado.removerChamado(id);
                }else{
                    System.out.println("Número de chamado não existente no banco de dados.");
                }

            }else if(opcao == 3){
                System.out.println("Saindo..");
                System.exit(0);
            }else{
                System.out.println("Opção inválida.");
            }

        }
    }


}
//Atendente consegue buscar as que estão em aberto, e conseguir finalizar uma, um Administrador consegue fazer o cadastro de todos (Clientes, Atendentes e Administradores),
//consegue criar uma Ocorrência e tb finalizá-la e um Administrador pode apagar/alterar/buscar um Cliente ou Funcionário