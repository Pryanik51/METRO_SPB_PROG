package org.example;

import java.util.Arrays;
import java.util.Stack;

/**
 * Класс MetroMinTime реализует алгоритм Дейкстры для расчета
 * минимального времени и оптимального пути между станциями.
 */
public class MetroMinTime extends GraphMatrix {
    /**
     * Метод dijkstra рассчитывает минимальное время и оптимальный путь между начальной и всеми остальными станциями
     * в графе метрополитена Санкт-Петербурга.
     * Реализация алгоритма Дейкстры:
     * - Использует матрицу смежности для представления графа.
     * - Рассчитывает минимальное время от начальной до каждой станции.
     * - Записывает предыдущую станцию для восстановления оптимального пути.
     *
     * @param graph матрица смежности графа метрополитена
     * @param start индекс начальной станции
     * @return список минимального времени
     */
    public static int[] dijkstra(int[][] graph, int start) {
        int[] distance = new int[NUM_STATIONS];
        boolean[] visited = new boolean[NUM_STATIONS];

        Arrays.fill(distance, INF);
        Arrays.fill(prevStation, -1);
        distance[start] = 0;

        for (int count = 0; count < NUM_STATIONS - 1; count++) {
            int minDistance = minDistance(distance, visited);
            visited[minDistance] = true;

            for (int v = 0; v < NUM_STATIONS; v++) {
                if (!visited[v] && graph[minDistance][v] != INF && distance[minDistance] != INF &&
                        distance[minDistance] + graph[minDistance][v] < distance[v]) {
                    distance[v] = distance[minDistance] + graph[minDistance][v];
                    prevStation[v] = minDistance;
                }
            }
        }
        return distance;
    }

    /**
     * Метод minDistance находит индекс станции с минимальным временем из непосещенных станций.
     * Поиск индекса станции с минимальным временем:
     * - Игнорирует уже посещенные станции.
     * - Возвращает индекс станции с минимальным временем.
     *
     * @param distance массив с минимальным временем до каждой станции
     * @param visited  массив, отмечающий посещенные станции
     * @return индекс станции с минимальным временем
     */
    private static int minDistance(int[] distance, boolean[] visited) {
        int min = INF;
        int minIndex = -1;

        for (int v = 0; v < NUM_STATIONS; v++) {
            if (!visited[v] && distance[v] <= min) {
                min = distance[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    /**
     * Метод printSolution выводит минимальное время и оптимальный путь от начальной до конечной станции.
     * Вывод результата:
     * - Минимальное время от начальной до конечной станции.
     * - Оптимальный путь в обратном порядке с использованием стека.
     *
     * @param distance    массив с минимальным временем до каждой станции
     * @param prevStation массив с индексами предыдущих станций для восстановления пути
     * @param startname   название начальной станции
     * @param endname     название конечной станции
     * @param end         индекс конечной станции
     */
    public static void printSolution(int[] distance, int[] prevStation, String startname, String endname, int end) {
        int time = distance[end];
        System.out.println("Минимальное время от станции " + startname + " до станции " + endname + ": " + time + " минут");

        Stack<String> path = new Stack<>();
        int currentStation = end;

        while (currentStation != -1) {
            int k = currentStation + 1;
            path.push(GlobalDictionarySPB.dictionaryspb1.get(k));
            currentStation = prevStation[currentStation];
        }

        System.out.print("Оптимальный путь от станции " + startname + " до станции " + endname + ": ");
        while (!path.peek().equals(endname)) {
            System.out.print(path.pop() + " -> ");
        }
        if (!path.isEmpty()) {
            System.out.print(path.pop());
        } else {
            System.out.println("Стек пуст!");
        }
        System.out.println();
    }
}