package org.example;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Main {
    private List<Funcionario> funcionarioList;

    public Main(){
        this.funcionarioList =  new ArrayList<>();
    }
    public void adicionarFuncionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao){
        Funcionario funcionario = new Funcionario(nome, dataNascimento, salario, funcao);
        funcionarioList.add(funcionario);
    }

    public void removerFuncionario(String nome){
        List<Funcionario> funcionarioParaRemover = new ArrayList<>();

        if(!(funcionarioList.isEmpty())){
            for ( Funcionario i : funcionarioList){
                if (i.getNome().equalsIgnoreCase(nome)){
                    funcionarioParaRemover.add(i);
                }
            }
            funcionarioList.removeAll(funcionarioParaRemover);
        }else{
            System.out.println("A lista está vazia!");
        }

    }

    public void exibirFuncionario(){
        if (!funcionarioList.isEmpty()){
            for (Funcionario funcionario : funcionarioList) {
                System.out.println(funcionario);
                System.out.println();
            }
        }else{
            System.out.println("A lista está vazia!");
        }
    }

    public void aumentarSalario(){
        BigDecimal novoSalario;
        BigDecimal antigoSalario;
        BigDecimal aumento;

        if (!funcionarioList.isEmpty()){
            for (Funcionario funcionario : funcionarioList){
                antigoSalario = funcionario.getSalario();
                aumento = antigoSalario.multiply(new BigDecimal("0.10"));
                novoSalario = antigoSalario.add(aumento);

                funcionario.setSalario(novoSalario);
            }
        }else {
            System.out.println("A lista está vazia!");
        }
    }

    public Map<String, List<Funcionario>> agruparFuncionariosPorFuncao() {
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario funcionario : funcionarioList) {
            String funcao = funcionario.getFuncao();
            if (!funcionariosPorFuncao.containsKey(funcao)) {
                funcionariosPorFuncao.put(funcao, new ArrayList<>());
            }
            funcionariosPorFuncao.get(funcao).add(funcionario);
        }
        return funcionariosPorFuncao;
    }

    public void exibirFuncionariosAgrupados(Map<String, List<Funcionario>> funcionariosPorFuncao) {
        if(!funcionarioList.isEmpty()){
            for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
                System.out.println("Função: " + entry.getKey() + "\n");
                for (Funcionario funcionario : entry.getValue()) {
                    System.out.println(funcionario);
                    System.out.println();
                }
            }
        }else {
            System.out.println("A lista está vazia!");
        }

    }

    public void exibirFuncionarioMes10e12(){
        if (!funcionarioList.isEmpty()){
            for (Funcionario funcionario : funcionarioList){
                int mes = funcionario.getDataNascimento().getMonthValue();
                if (mes == 12 || mes == 10){
                    System.out.println(funcionario);
                    System.out.println();
                }
            }
        }else {
            System.out.println("A lista está vazia!");
        }
    }

    public void FuncionarioMaisVelho(List<Funcionario> funcionarioList) {
        if (!funcionarioList.isEmpty()) {
            LocalDate dataAtual = LocalDate.now();
            Funcionario funcionarioMaisVelho = Collections.max(funcionarioList, Comparator.comparingInt(f -> Period.between(f.getDataNascimento(), dataAtual).getYears()));
            int idade = Period.between(funcionarioMaisVelho.getDataNascimento(), dataAtual).getYears();

            System.out.println(funcionarioMaisVelho.getNome());
            System.out.println("Idade: " + idade);
        }else {
            System.out.println("A lista está vazia!");
        }


    }

    public void exibirFuncionarioPorNome(List<Funcionario> funcionarioList){
        funcionarioList.sort(new Comparator<Funcionario>() {
            @Override
            public int compare(Funcionario o1, Funcionario o2) {
                return o1.getNome().compareTo(o2.getNome());
            }
        });
        if (!funcionarioList.isEmpty()){
            for (Funcionario funcionario :  funcionarioList){
                System.out.println(funcionario);
                System.out.println();;
            }
        }else {
            System.out.println("A lista está vazia!");
        }

    }

    public String totalSalarioFuncionario(){
        BigDecimal total = new BigDecimal("0");
        if (!funcionarioList.isEmpty()){
            for (Funcionario funcionario :  funcionarioList){
                total = total.add(funcionario.getSalario());
            }
        }else {
            System.out.println("A lista está vazia!");
        }
        NumberFormat formatadorNumero =  NumberFormat.getNumberInstance(new Locale("pt", "BR"));
        formatadorNumero.setMaximumFractionDigits(2);

        return formatadorNumero.format(total);
    }

    public void quantidadeDeSalarioMin(){
        int quantidadeDeSalarios = 0;

        if (!funcionarioList.isEmpty()){
            for (Funcionario funcionario : funcionarioList){
                int salarioInt = funcionario.getSalario().intValue();
                quantidadeDeSalarios = salarioInt/1212;

                System.out.println(funcionario.getNome() + "\nQuantidade de salários minímos: " + quantidadeDeSalarios + "\n");
            }
        }else {
            System.out.println("A lista está vazia!");
        }
    }

    public static void main(String[] args) {
        Main gerente = new Main(); // nomeado  com gerente só pra dar uma dinâmica ao código.

        gerente.adicionarFuncionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador");
        gerente.adicionarFuncionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador");
        gerente.adicionarFuncionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador");
        gerente.adicionarFuncionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor");
        gerente.adicionarFuncionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista");
        gerente.adicionarFuncionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador");
        gerente.adicionarFuncionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador");
        gerente.adicionarFuncionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente");
        gerente.adicionarFuncionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista");
        gerente.adicionarFuncionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente");
        gerente.exibirFuncionario();

        System.out.println("------------------Removendo João ------------------");

        gerente.removerFuncionario("João");

        gerente.exibirFuncionario();

        gerente.aumentarSalario();
        System.out.println("------------------AUMENTO DE SALÁRIO EM 10%(João removido)------------------");
        gerente.exibirFuncionario();

        System.out.println("------------------POR FUNÇÃO(João removido)------------------");
        Map<String, List<Funcionario>> funcionarioAgrupados = gerente.agruparFuncionariosPorFuncao();
        gerente.exibirFuncionariosAgrupados(funcionarioAgrupados);

        System.out.println("------------------ANIVERSARIANTES MES 10 E 12------------------");
        gerente.exibirFuncionarioMes10e12();

        System.out.println("------------------FUNCIONÁRIO MAIS VELHO(João removido)------------------");
        gerente.FuncionarioMaisVelho(gerente.funcionarioList);

        System.out.println("------------------FUNCIONÁRIO EM ORDEM ALFABÉTICA(João removido)------------------");
        gerente.exibirFuncionarioPorNome(gerente.funcionarioList);

        System.out.println("------------------TOTAL DOS SALÁRIOS(após aumento)------------------");
        System.out.println("R$ " + gerente.totalSalarioFuncionario() + "\n");

        System.out.println("------------------QUANTIDADE SALÁRIOS MÍNIMOS(após aumento)------------------");
        gerente.quantidadeDeSalarioMin();



    }
}