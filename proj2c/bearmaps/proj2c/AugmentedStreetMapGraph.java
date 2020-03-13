package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.KDTree;
import bearmaps.proj2ab.Point;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {

    private List<Node> nodes;
    private Map<Point, Node> P2N;
    private Map<String, String> cleanString2String;
    private Map<String, List<Node>> location2Nodes;
    private KDTree KD;
    private List<Point> points;
    private MyTrieSet trie;
    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        List<Node> nodes = this.getNodes();
        trie = new MyTrieSet();
        points = new ArrayList<>();
        P2N = new HashMap<>();
        cleanString2String = new HashMap<>();
        location2Nodes = new HashMap<>();
        Point point;
        String cleanString;
        List<Node> tmpList;
        for (Node tmp: nodes) {
            point = new Point(tmp.lat(), tmp.lon());
            points.add(point);
            P2N.put(point, tmp);
            if (tmp.name() != null) {
                cleanString = cleanString(tmp.name());
                if (!location2Nodes.containsKey(cleanString)) {
                    tmpList = new LinkedList<>();
                    tmpList.add(tmp);
                    location2Nodes.put(cleanString, tmpList);
                } else {
                    location2Nodes.get(cleanString).add(tmp);
                }
                cleanString2String.put(cleanString, tmp.name());
                trie.add(cleanString);
            }
        }
        KD = new KDTree(points);
    }

//    public String cleanString(String s) {
//        String ans = "";
//        char c;
//        for (int i=0; i<s.length(); i++) {
//            c = s.charAt(i);
//            if (c >= 'A' && c <= 'Z') {
//                c = (char) (c - 'A' + 'a');
//                ans += String.valueOf(c);
//            } else if (c >= 'a' && c <= 'z') {
//                ans += String.valueOf(c);
//            } else if (c == ' ') {
//                ans += String.valueOf(c);
//            }
//        }
//        return ans;
//    }

    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        Point target = new Point(lon, lat);
        Point nearest2target = KD.nearest(target.getX(), target.getY());
        Node ans = P2N.get(nearest2target);
        return ans.id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        List<String> ans = trie.keysWithPrefix(cleanString(prefix));
        for (int i=0; i<ans.size(); i++) {
            ans.set(i, cleanString2String.get(ans.get(i)));
        }
        return ans;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        String cleanLN = cleanString(locationName);
        List<Node> locationNodes = location2Nodes.get(trie.longestPrefixOf(cleanLN));
        List<Map<String, Object>> locationInfo = new LinkedList<>();
        Map<String, Object> tmpMap;
        for (Node tmp: locationNodes) {
            tmpMap = new HashMap<>();
            tmpMap.put("lat", tmp.lat());
            tmpMap.put("lon", tmp.lon());
            tmpMap.put("name", tmp.name());
            tmpMap.put("id", tmp.id());
            locationInfo.add(tmpMap);
        }
        return locationInfo;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
