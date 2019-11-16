package com.liusen.algorithms.chapter2_3;

import java.util.Random;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class Ex17
{
    public static void sort(Comparable[] a)
    {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }
    private static int partition(Comparable[] a, int lo, int hi)
    {
        int i = lo, j = hi+1;
        Comparable v = a[lo];

        int max = lo;
        for (; i < j; i++)
        {
            if (less(a[max], a[i]))
                max = i;
        }
        exch(a, max, hi);

        i = lo;

        while(true)
        {
            while(less(a[++i],v));
            while(less(v, a[--j]));
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }
    private static boolean less_or_equal(Comparable v, Comparable w)
    {
        return v.compareTo(w) <= 0;
    }
    private static void exch(Comparable[] a, int i, int j)
    {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
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
        int N = Integer.parseInt(args[0]);

        Integer[] a = new Integer[N];
        Random r = new Random();
        for (int i = 0; i < a.length; i++)
        {
            a[i] = r.nextInt(100);
        }
        StdOut.println("original:");
        show(a);

        sort(a);
        assert isSorted(a);

        StdOut.println("sorted:");
        show(a);
    }
}
