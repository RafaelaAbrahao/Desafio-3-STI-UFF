public class Main {
    public static void main(String[] args) {
        CalculoCR calculadora = new CalculoCR();
        String caminhoCSV = "database/notas.csv";
        calculadora.lerCSV(caminhoCSV);
        calculadora.calcularCR();
        calculadora.calcularMediaCRPorCurso();
        calculadora.exibirResultado();
    }
}