package org.example;

import java.util.Scanner;

/**
 * Главный класс программы, предназначенной для расчета минимального времени в метро Санкт-Петербурга.
 * Программа позволяет пользователю ввести станцию отправления и станцию прибытия,
 * после чего она использует алгоритм Дейкстры для определения минимального времени
 * и пути между указанными станциями в сети метро Санкт-Петербурга.
 */
public class Main extends MetroMinTime {
    /**
     * Главный метод программы.
     * В этом методе пользователю предоставляется интерфейс ввода: сначала запрашивается станция отправления,
     * затем станция прибытия.
     * Программа вызывает классы MyClass1 и MyClass2, которые при помочи метода addToDictionary заполняют словари
     * с названиями станций и их индексами.
     * Программа осуществляет проверку корректности введенных данных, применяет алгоритм
     * Дейкстры для определения минимального времени и оптимального маршрута между указанными станциями в сети
     * метро Санкт-Петербурга, и выводит результат на экран.
     * В случае ошибочного ввода станций, программа уведомляет пользователя об ошибке.
     *
     * @param args Аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        Scanner ScanName = new Scanner(System.in);
        GraphMatrix.MyClass1.addToDictionary();
        GraphMatrix.MyClass2.addToDictionary();

        System.out.print("Введите станцию отправления: ");
        String StationNameStart = ScanName.nextLine();
        if (GlobalDictionarySPB.dictionaryspb.get(StationNameStart) == null) {
            System.out.println("Введено неверное название станции отправления!");
        } else {
            System.out.print("Введите станцию прибытия: ");
            String StationNameEnd = ScanName.nextLine();
            if (GlobalDictionarySPB.dictionaryspb.get(StationNameEnd) == null) {
                System.out.println("Введено неверное название станции прибытия!");
            } else {
                int startStation = GlobalDictionarySPB.dictionaryspb.get(StationNameStart);
                int endStation = GlobalDictionarySPB.dictionaryspb.get(StationNameEnd);

                int[] minTime = dijkstra(metroGraph, startStation);
                printSolution(minTime, prevStation, StationNameStart, StationNameEnd, endStation);
            }
        }
    }
}




