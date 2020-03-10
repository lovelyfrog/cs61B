import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Queue;

public class SeparableEnemySolver {

    Graph g;

    /**
     * Creates a SeparableEnemySolver for a file with name filename. Enemy
     * relationships are biderectional (if A is an enemy of B, B is an enemy of A).
     */
    SeparableEnemySolver(String filename) throws java.io.FileNotFoundException {
        this.g = graphFromFile(filename);
    }

    /** Alterntive constructor that requires a Graph object. */
    SeparableEnemySolver(Graph g) {
        this.g = g;
    }

    /**
     * Returns true if input is separable, false otherwise.
     * my solution is a little complex. I construct two Set X and Y,  which save strings.
     * A less complex way is to make a Map<String, String> party that saves string and the set it belongs to.
     *  A - B
     *  |   |  party: {<A, "U">, <B, "V">, <D, "U">, <C, "V">}
     *  C - D
     *  all tests pass!
     */
    public boolean isSeparable() {
        // TODO: Fix me
        Set<String> X = new HashSet<>();
        Set<String> Y = new HashSet<>();
        List<String> labels = new ArrayList<>(g.labels());
        Map<String, Integer> labelIsVisited = new HashMap<>();
        for (int i=0; i<labels.size(); i++) {
            labelIsVisited.put(labels.get(i), 0);
        }

        for (String label: labels) {
            if (labelIsVisited.get(label) == 0) {
                if (!DFSSolver(X, Y, 0, label, null, labelIsVisited)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean DFSSolver(Set<String> X, Set<String> Y, int flag, String node, String nodeParent, Map<String, Integer> isVisited) {

        if (flag == 0) {
            if (Y.contains(node)) return false;
            if (X.contains(node)) return true;
            X.add(node);
        } else {
            if (X.contains(node)) return false;
            if (Y.contains(node)) return true;
            Y.add(node);
        }
        isVisited.put(node, 1);
        Set<String> nodeNeighbors = g.neighbors(node);
        for (String neighbor: nodeNeighbors) {
            if (!neighbor.equals(nodeParent)) {
                if (!DFSSolver(X, Y, 1 - flag, neighbor, node, isVisited)) return false;
            }
        }
        return true;
    }


    /**
     *   BFS version of isSeparable()
     *   all tests pass!
     */
    public boolean isSeparableBFS() {
        Map<String, String> party = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        List<String> labels = new ArrayList<>(g.labels());
        for (String label: labels) {
            if (!party.containsKey(label)) {
                party.put(label, "U");
                queue.add(label);
                while (queue.size() != 0) {
                    String tmp = queue.poll();
                    for (String neighbor: g.neighbors(tmp)) {
                        String tmpParty = party.get(tmp);
                        if (!party.containsKey(neighbor)) {
                            party.put(neighbor, tmpParty.equals("U") ? "V" : "U");
                            queue.add(neighbor);
                        }
                        else {
                            if (tmpParty.equals(party.get(neighbor))) return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /* HELPERS FOR READING IN CSV FILES. */

    /**
     * Creates graph from filename. File should be comma-separated. The first line
     * contains comma-separated names of all people. Subsequent lines each have two
     * comma-separated names of enemy pairs.
     */
    private Graph graphFromFile(String filename) throws FileNotFoundException {
        List<List<String>> lines = readCSV(filename);
        Graph input = new Graph();
        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) {
                for (String name : lines.get(i)) {
                    input.addNode(name);
                }
                continue;
            }
            assert(lines.get(i).size() == 2);
            input.connect(lines.get(i).get(0), lines.get(i).get(1));
        }
        return input;
    }

    /**
     * Reads an entire CSV and returns a List of Lists. Each inner
     * List represents a line of the CSV with each comma-seperated
     * value as an entry. Assumes CSV file does not contain commas
     * except as separators.
     * Returns null if invalid filename.
     *
     * @source https://www.baeldung.com/java-csv-file-array
     */
    private List<List<String>> readCSV(String filename) throws java.io.FileNotFoundException {
        List<List<String>> records = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            records.add(getRecordFromLine(scanner.nextLine()));
        }
        return records;
    }

    /**
     * Reads one line of a CSV.
     *
     * @source https://www.baeldung.com/java-csv-file-array
     */
    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        Scanner rowScanner = new Scanner(line);
        rowScanner.useDelimiter(",");
        while (rowScanner.hasNext()) {
            values.add(rowScanner.next().trim());
        }
        return values;
    }

    /* END HELPERS  FOR READING IN CSV FILES. */

}
