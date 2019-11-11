package com.liusen.algorithms.chapter2_2;

import java.util.Random;
import edu.princeton.cs.algs4.StdOut;

public class Ex9
{
    //private static Comparable[] aux;

    public static void sort(Comparable[] a, Comparable[] aux)
    {
        sort(a, aux, 0, a.length-1);
    }
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi)
    {
        if (hi <= lo) return; //递归终止条件，即只有一个元素时
        int mid = (lo + hi) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux,mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }
    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
    {
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++)
        {
            if (i > mid)                a[k] = aux[j++];
            else if (j > hi)                 a[k] = aux[i++];
            else if (less(aux[j], aux[i]))   a[k] = aux[j++];
            else                        a[k] = aux[i++];
        }
    }
    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }

    private static void show(Comparable[] a)
    {
        for (int i = 0; i < a.length; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }
    public static boolean isSorted(Comparable[] a)
    {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1]))
                return false;
        return true;
    }
    public static void main(String[] args)
    {
        Integer[] a = new Integer[50];
        Random r = new Random();
        for (int i = 0; i < a.length; i++)
        {
            a[i] = r.nextInt(100);
        }
        StdOut.println("original:");
        show(a);

        Comparable[] aux;
        aux = new Comparable[a.length];//避免多次分配空间
        sort(a, aux);

        assert isSorted(a);
        StdOut.println("sorted:");
        show(a);
    }
}