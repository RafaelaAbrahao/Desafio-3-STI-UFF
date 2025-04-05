import java.io.FileReader;
import java.util.*;
import java.io.BufferedReader;

public class CalculoCR {
    private List<Disciplina> disciplinas = new ArrayList<>();
    private Map<Integer, Aluno> alunos = new HashMap<>();
    private Map<Integer, List<Double>> crPorCurso = new HashMap<>();

    public void lerCSV(String caminhoCSV) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCSV))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }
                String[] dados = linha.split(",");
                int matricula = Integer.parseInt(dados[0].trim());
                String codDisciplina = dados[1].trim();
                int codCurso = Integer.parseInt(dados[2].trim());
                double nota = Double.parseDouble(dados[3].trim());
                int ch = Integer.parseInt(dados[4].trim());
                String anoSemestre = dados[5].trim();

                Disciplina disciplina = new Disciplina(matricula, codDisciplina, codCurso, nota, ch, anoSemestre);

                disciplinas.add(disciplina);

                alunos.putIfAbsent(matricula, new Aluno(matricula));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calcularCR() {
        Map<Integer, Double> somaNotaPonderada = new HashMap<>();
        Map<Integer, Integer> somaCh = new HashMap<>();

        for (Disciplina disciplina : disciplinas) {
            int matricula = disciplina.getMatricula();
            double nota = disciplina.getNota();
            int ch = disciplina.getCh();

            somaNotaPonderada.merge(matricula, nota * ch, Double::sum);
            somaCh.merge(matricula, ch, Integer::sum);
        }

        alunos.forEach((matricula, aluno) -> {
            double cr = somaNotaPonderada.getOrDefault(matricula, 0.0) / somaCh.getOrDefault(matricula, 1);
            aluno.setCr(cr);
        });
    }

    public void calcularMediaCRPorCurso() {
        for (Disciplina disciplina : disciplinas) {
            int curso = disciplina.getCodCurso();
            double crAluno = alunos.get(disciplina.getMatricula()).getCr();

            crPorCurso.computeIfAbsent(curso, k -> new ArrayList<>()).add(crAluno);
        }
    }

    public void exibirResultado() {
        System.out.println("---- O CR dos alunos é: ----");

        List<Aluno> listaAlunos = new ArrayList<>(alunos.values());
        Collections.sort(listaAlunos, new Comparator<Aluno>() {
            @Override
            public int compare(Aluno a1, Aluno a2) {
                return Integer.compare(a1.getMatricula(), a2.getMatricula());
            }
        });

        for (Aluno aluno : listaAlunos) {
            System.out.printf("%d - %.2f%n", aluno.getMatricula(), aluno.getCr());
        }

        System.out.println("\n---- Média de CR por Cursos: ----");

        List<Integer> cursosOrdenados = new ArrayList<>(crPorCurso.keySet());
        Collections.sort(cursosOrdenados);

        for (Integer curso : cursosOrdenados) {
            List<Double> crs = crPorCurso.get(curso);
            double soma = 0;
            for (Double cr : crs) {
                soma += cr;
            }
            double media = soma / crs.size();
            System.out.printf("%d - %.2f%n", curso, media);
        }
    }
}