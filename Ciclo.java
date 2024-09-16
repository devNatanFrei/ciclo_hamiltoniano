import java.util.*;

public class Ciclo {

    private static final Map<String, List<String>> grafo1 = new HashMap<>();
    private static final Map<String, List<String>> grafo2 = new HashMap<>();
    private static final Map<String, List<String>> grafo3 = new HashMap<>();
    private static final Map<String, List<String>> grafo4 = new HashMap<>();

    // Inicializa o grafo 1
    public static void setGrafo1() {
        grafo1.put("A", Arrays.asList("B", "C", "F", "G"));
        grafo1.put("B", Arrays.asList("A", "C", "D", "F"));
        grafo1.put("C", Arrays.asList("A", "B", "D", "E"));
        grafo1.put("D", Arrays.asList("B", "C", "E", "G"));
        grafo1.put("E", Arrays.asList("C", "D", "F", "G"));
        grafo1.put("F", Arrays.asList("A", "B", "E", "G"));
        grafo1.put("G", Arrays.asList("A", "D", "E", "F"));
    }

    // Inicializa o grafo 2
    public static void setGrafo2() {
        grafo2.put("A", Arrays.asList("B", "C", "F", "G"));
        grafo2.put("B", Arrays.asList("A", "C", "D", "F"));
        grafo2.put("C", Arrays.asList("A", "B", "D"));
        grafo2.put("D", Arrays.asList("B", "C", "E", "G"));
        grafo2.put("E", Arrays.asList("D", "F", "G"));
        grafo2.put("F", Arrays.asList("A", "B", "E", "G"));
        grafo2.put("G", Arrays.asList("A", "D", "E", "F"));
    }

    // Inicializa o grafo 3
    public static void setGrafo3() {
        grafo3.put("A", Arrays.asList("B", "C", "F", "G"));
        grafo3.put("B", Arrays.asList("A", "C"));
        grafo3.put("C", Arrays.asList("A", "B", "D"));
        grafo3.put("D", Arrays.asList("C", "E"));
        grafo3.put("E", Arrays.asList("D", "F", "G"));
        grafo3.put("F", Arrays.asList("A", "E", "G"));
        grafo3.put("G", Arrays.asList("A", "E", "F"));
    }

    // Inicializa o grafo 4
    public static void setGrafo4() {
        grafo4.put("A", Arrays.asList("B", "C", "F", "G"));
        grafo4.put("B", Arrays.asList("A", "C"));
        grafo4.put("C", Arrays.asList("A", "B", "D"));
        grafo4.put("D", Arrays.asList("C", "E"));
        grafo4.put("E", Arrays.asList("D", "F"));
        grafo4.put("F", Arrays.asList("A", "E", "G"));
        grafo4.put("G", Arrays.asList("A", "F"));
    }

    static {
        setGrafo1();
        setGrafo2();
        setGrafo3();
        setGrafo4();
    }

    // Teorema de Dirac
    private static boolean Dirac(Map<String, List<String>> graph) {
        int n = graph.size();
        if (n < 3) {
            return false;
        }

        double minDegree =  n / 2.0;

        for (String v : graph.keySet()) {
            if (graph.get(v).size() < minDegree) {
                return false;
            }
        }
        return true;
    }


    // Teorema de Ore
    private static boolean Ore(Map<String, List<String>> graph) {
        int n = graph.size();
        for (String u : graph.keySet()) {
            for (String v : graph.keySet()) {
                if (!u.equals(v) && !graph.get(u).contains(v)) {
                    int grauU = graph.get(u).size();
                    int grauV = graph.get(v).size();
                    if (grauU + grauV < n) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Teorema de Bondy & Chvátal
    private static boolean BondyChvatal(Map<String, List<String>> graph) {
        if (Ore(graph) && Dirac(graph)){
            Map<String, Set<String>> closure = new HashMap<>();
            for (String vertex : graph.keySet()) {
                closure.put(vertex, new HashSet<>(graph.get(vertex)));
            }

            boolean changed = true;
            while (changed) {
                changed = false;
                for (String u : closure.keySet()) {
                    for (String v : closure.keySet()) {
                        if (!u.equals(v) && !closure.get(u).contains(v)) {
                            Set<String> neighborsU = new HashSet<>(closure.get(u));
                            Set<String> neighborsV = new HashSet<>(closure.get(v));
                            if (neighborsU.size() + neighborsV.size() >= graph.size()) {
                                neighborsU.add(v);
                                neighborsV.add(u);
                                closure.put(u, neighborsU);
                                closure.put(v, neighborsV);
                                changed = true;
                            }
                        }
                    }
                }
            }

            for (String u : closure.keySet()) {
                for (String v : closure.keySet()) {
                    if (!u.equals(v) && !closure.get(u).contains(v)) {
                        return false;
                    }
                }
            }
            return true;
        }

      return false;
    }

    public static void main(String[] args) {
        System.out.println("Dirac - Grafo 1: " + Dirac(grafo1));
        System.out.println("Ore - Grafo 1: " + Ore(grafo1));
        System.out.println("Bondy & Chvátal - Grafo 1: " + BondyChvatal(grafo1));

        System.out.println();

        System.out.println("Dirac - Grafo 2: " + Dirac(grafo2));
        System.out.println("Ore - Grafo 2: " + Ore(grafo2));
        System.out.println("Bondy & Chvátal - Grafo 2: " + BondyChvatal(grafo2));

        System.out.println();

        System.out.println("Dirac - Grafo 3: " + Dirac(grafo3));
        System.out.println("Ore - Grafo 3: " + Ore(grafo3));
        System.out.println("Bondy & Chvátal - Grafo 3: " + BondyChvatal(grafo3));

        System.out.println();

        System.out.println("Dirac - Grafo 4: " + Dirac(grafo4));
        System.out.println("Ore - Grafo 4: " + Ore(grafo4));
        System.out.println("Bondy & Chvátal - Grafo 4: " + BondyChvatal(grafo4));
    }
}
