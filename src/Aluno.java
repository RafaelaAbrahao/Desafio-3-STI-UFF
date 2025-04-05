public class Aluno {
    private int matricula;
    private double cr;

    public Aluno(int matricula) {
        this.matricula = matricula;
        this.cr = 0.0;
    }

    public int getMatricula() {
        return matricula;
    }

    public double getCr() {
        return cr;
    }

    public void setCr(double cr) {
        this.cr = cr;
    }
}