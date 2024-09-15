import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ciclo {
    private static final Map<String, List<String>> matrix_adj_1 = new HashMap<>();
    private static final Map<String, List<String>> matrix_adj_2 = new HashMap<>();
    private static final Map<String, List<String>> matrix_adj_3 = new HashMap<>();
    private static final Map<String, List<String>> matrix_adj_4 = new HashMap<>();
    public static void setMatrix_adj_1(){
        matrix_adj_1.put("A", Arrays.asList("B", "C", "F", "G"));
        matrix_adj_1.put("B", Arrays.asList("A", "C"));
        matrix_adj_1.put("C", Arrays.asList("A", "B","D"));
        matrix_adj_1.put("D", Arrays.asList("C","E"));
        matrix_adj_1.put("E", Arrays.asList("D","F"));
        matrix_adj_1.put("F", Arrays.asList("A", "E","G"));
        matrix_adj_1.put("G", Arrays.asList("A", "F"));
    }

    public static void setMatrix_adj_2(){
        matrix_adj_2.put("A", Arrays.asList("B", "C", "F", "G"));
        matrix_adj_2.put("B", Arrays.asList("A", "C"));
        matrix_adj_2.put("C", Arrays.asList("A", "B","D"));
        matrix_adj_2.put("D", Arrays.asList("C","E"));
        matrix_adj_2.put("E", Arrays.asList("D","F", "G"));
        matrix_adj_2.put("F", Arrays.asList("A", "E","G"));
        matrix_adj_2.put("G", Arrays.asList("A","E" ,"F"));
    }
    public static void setMatrix_adj_3(){
        matrix_adj_3.put("A", Arrays.asList("B", "C", "F", "G"));
        matrix_adj_3.put("B", Arrays.asList("A", "C","D","F"));
        matrix_adj_3.put("C", Arrays.asList("A", "B","D"));
        matrix_adj_3.put("D", Arrays.asList("B","C","E","G"));
        matrix_adj_3.put("E", Arrays.asList("D","F", "G"));
        matrix_adj_3.put("F", Arrays.asList("A", "E","G"));
        matrix_adj_3.put("G", Arrays.asList("A","D","E" ,"F"));
    }
    public static void setMatrix_adj_4(){
        matrix_adj_4.put("A", Arrays.asList("B", "C", "F", "G"));
        matrix_adj_4.put("B", Arrays.asList("A", "C","D","F"));
        matrix_adj_4.put("C", Arrays.asList("A", "B","D"));
        matrix_adj_4.put("D", Arrays.asList("B","C","E","G"));
        matrix_adj_4.put("E", Arrays.asList("C","D","F", "G"));
        matrix_adj_4.put("F", Arrays.asList("A","B", "E","G"));
        matrix_adj_4.put("G", Arrays.asList("A","D","E" ,"F"));
    }

    static {
        setMatrix_adj_1();
        setMatrix_adj_2();
        setMatrix_adj_3();
        setMatrix_adj_4();
    }
    private static boolean Dirac(Map<String, List<String>> graph) {
        int n = graph.size();
        if (n < 3) return false;
        for (String vertex : graph.keySet()) {
            int degree = graph.get(vertex).size();
            if (degree < n / 2) return false;
        }
        return true;
    }
    private static boolean Ore(Map<String, List<String>> graph) {
        int n = graph.size();
        for (String u : graph.keySet()) {
            for (String v: graph.keySet()){
                if (!u.equals(v) && !graph.get(u).contains(v)){
                    int degreeU = graph.get(u).size();
                    int degreeV = graph.get(v).size();
                    if (degreeU + degreeV < n){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean BondyChvatal(Map<String, List<String>> graph) {
        return Ore(graph);
    }



    public static void main(String[] args) {
        System.out.println("Dirac - Grafo 1: " + Dirac(matrix_adj_1));
        System.out.println("Ore - Grafo 1: " + Ore(matrix_adj_1));
        System.out.println("Bondy & Chvátal - Grafo 1: " + BondyChvatal(matrix_adj_1));

        System.out.println();

        System.out.println("Dirac - Grafo 2: " + Dirac(matrix_adj_2));
        System.out.println("Ore - Grafo 2: " + Ore(matrix_adj_2));
        System.out.println("Bondy & Chvátal - Grafo 2: " + BondyChvatal(matrix_adj_2));

        System.out.println();

        System.out.println("Dirac - Grafo 3: " + Dirac(matrix_adj_3));
        System.out.println("Ore - Grafo 3: " + Ore(matrix_adj_3));
        System.out.println("Bondy & Chvátal - Grafo 3: " + BondyChvatal(matrix_adj_3));

        System.out.println();

        System.out.println("Dirac - Grafo 4: " + Dirac(matrix_adj_4));
        System.out.println("Ore - Grafo 4: " + Ore(matrix_adj_4));
        System.out.println("Bondy & Chvátal - Grafo 4: " + BondyChvatal(matrix_adj_4));
    }

}

