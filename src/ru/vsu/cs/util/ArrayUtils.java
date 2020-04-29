package ru.vsu.cs.util;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ArrayUtils {
    private static final Random RND = new Random();


    public static int[] toPrimitive(Integer[] arr) {
        if (arr == null) {
            return null;
        }
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            // автоматическая распаковка из объекта
            result[i] = arr[i];
        }
        return result;
    }

    /**
     * Cоздание одномерного массива целых чисел, заполненного случайными числами
     *
     * @param length   Кол-во элементов в массиве
     * @param minValue Минимальное значение для случайных чисел (включая)
     * @param maxValue Максимальное значение (не включая)
     * @return Массив int[]
     */
    public static int[] createRandomIntArray(int length, int minValue, int maxValue) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++)
            arr[i] = minValue + RND.nextInt(maxValue - minValue);
        return arr;
    }
}
