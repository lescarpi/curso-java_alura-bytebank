package br.com.bytebank.banco.modelo;

/**
 * Classe representa a moldura de uma conta.
 *
 * @author Leandro Scarpi
 * @version 0.1
 */
public abstract class Conta implements Comparable<Conta> {

    protected double saldo;
    private int agencia;
    private int numero;
    private Cliente titular;
    private static int total = 0;

    /**
     * Construtor para inicializar o objeto Conta a partir de agencia e numero.
     *
     * @param agencia
     * @param numero
     */
    public Conta(int agencia, int numero){
        Conta.total++;
        this.agencia = agencia;
        this.numero = numero;
    }

    public abstract void deposita(double valor);

    public void saca(double valor) throws SaldoInsuficienteException{

        if(this.saldo < valor) {
            throw new SaldoInsuficienteException("Saldo: " + this.saldo + ", Valor: " + valor);
        }
        this.saldo -= valor;
    }

    public void transfere(double valor, Conta destino) throws SaldoInsuficienteException{
        this.saca(valor);
        destino.deposita(valor);
    }

    public double getSaldo(){
        return this.saldo;
    }

    public int getNumero(){
        return this.numero;
    }

    public void setNumero(int numero){
        if(numero <= 0) {
            System.out.println("Nao pode valor menor igual a 0");
            return;
        }
        this.numero = numero;
    }

    public int getAgencia(){
        return this.agencia;
    }

    public void setAgencia(int agencia){
        if(agencia <= 0) {
            System.out.println("Nao pode valor menor igual a 0");
            return;
        }
        this.agencia = agencia;
    }

    public void setTitular(Cliente titular){
        this.titular = titular;
    }

    public Cliente getTitular(){
        return this.titular;
    }

    public static int getTotal(){
        return Conta.total;
    }

    @Override
    public boolean equals(Object object) {

        if (object.getClass() != this.getClass()) {
            return false;
        }

        Conta conta = (Conta) object;

        if (conta.getAgencia() != this.agencia || conta.getNumero() != this.numero) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Conta c) {
        return Double.compare(this.saldo, c.saldo);
    }

    @Override
    public String toString() {
        return "Agência: " + this.getAgencia() + ", Número: " + this.getNumero();
    }

}
