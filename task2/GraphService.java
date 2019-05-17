package task2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class GraphService {

    private int getCheapest(int[] distances, boolean[] visited, int length) {
        int best = -1;
        for (int i = 0; i < length; i++) {
            if (!visited[i] && ((best < 0) || (distances[i] < distances[best]))) {
                best = i;
            }
        }
        return best;
    }

    private int[] distancesFrom(int source, GraphModel graph) {
        int length = graph.getMatrix().length;
        int[] result = new int[length];
        Arrays.fill(result, 10000);
        result[source] = 0;
        boolean[] visited = new boolean[length];
        for (int i = 0; i < length; i++) {
            int vertex = getCheapest(result, visited, length);
            visited[vertex] = true;
            for (int j = 0; j < length; j++) {
                result[j] = Math.min(result[j], result[vertex] + graph.getCost(vertex, j));
            }
        }
        return result;
    }

    private int getKey(String value, Map<Integer, String> map) {
        return map
                .entrySet()
                .stream()
                .filter(e -> Objects.equals(e.getValue(), value))
                .map(Map.Entry::getKey)
                .findAny().get();
    }

    private void findMinimumCost(Map<Integer, String> citiesMap,
                                 BufferedReader bufferedReader,
                                 GraphModel graph) throws IOException {
        String line = bufferedReader.readLine();
        int routesToFind = Integer.parseInt(line);
        for (int routesIndex = 0; routesIndex < routesToFind; routesIndex++) {
            line = bufferedReader.readLine();
            String[] cityNames = line.split("\\s+");
            String source = cityNames[0];
            String destination = cityNames[1];

            int sourceIndex = getKey(source, citiesMap);
            int destinationIndex = getKey(destination, citiesMap);
            int[] distancesFromSource = distancesFrom(sourceIndex, graph);
            System.out.println(distancesFromSource[destinationIndex]);
        }
    }

    private void setMatrixWithParams(int citiesNumber, BufferedReader bufferedReader,
                                     Map<Integer, String> citiesMap,
                                     GraphModel graph) throws IOException {
        for (int cityIndex = 0; cityIndex < citiesNumber; cityIndex++) {
            String line = bufferedReader.readLine();
            String cityName = line;
            int auxCityIndex = cityIndex + 1;
            citiesMap.put(auxCityIndex, cityName);
            line = bufferedReader.readLine();
            int neighborsNumber = Integer.parseInt(line);

            for (int neighborIndex = 0; neighborIndex < neighborsNumber; neighborIndex++) {
                line = bufferedReader.readLine();
                String[] brokenLine = line.split("\\s+");
                int cityToConnect = Integer.parseInt(brokenLine[0]);
                int weightOfConnection = Integer.parseInt(brokenLine[1]);
                graph.setEdge(auxCityIndex, cityToConnect, weightOfConnection);
            }
        }
    }

    void getMinimumCostFromInput() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(
                new File("src" + File.separator +
                        "main" + File.separator +
                        "java" + File.separator +
                        "task2" + File.separator +
                        "input.txt")))) {
            String line = bufferedReader.readLine();
            int testsNumber = Integer.parseInt(line);
            for (int testIndex = 0; testIndex < testsNumber; testIndex++) {
                line = bufferedReader.readLine();
                int citiesNumber = Integer.parseInt(line);
                int graphSize = citiesNumber + 1;
                Map<Integer, String> citiesMap = new HashMap<>(graphSize);
                int[][] edges = new int[graphSize][graphSize];
                GraphModel graph = new GraphModel(edges);
                setMatrixWithParams(citiesNumber, bufferedReader, citiesMap, graph);
                findMinimumCost(citiesMap, bufferedReader, graph);
            }
        }
    }
}

