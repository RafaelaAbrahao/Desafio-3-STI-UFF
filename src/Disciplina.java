public class Disciplina {
    private int matricula;
    private String codDisciplina;
    private int codCurso;
    private double nota;
    private int ch;
    private String anoSemestre;

    public Disciplina(int matricula, String codDisciplina, int codCurso, double nota, int cargaHoraria, String anoSemestre) {
        this.matricula = matricula;
        this.codDisciplina = codDisciplina;
        this.codCurso = codCurso;
        this.nota = nota;
        this.ch = cargaHoraria;
        this.anoSemestre = anoSemestre;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getCodDisciplina() {
        return codDisciplina;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public double getNota() {
        return nota;
    }

    public int getCh() {
        return ch;
    }

    public String getAnoSemestre() {
        return anoSemestre;
    }
}