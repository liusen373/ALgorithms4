package com.liusen.algorithms.chapter2_3;

import java.util.Random;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class Ex5
{
    //三向快速排序
    public static void sort(Comparable[] a)
    {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo) return;
        int lt = lo, gt = hi, i = lo+1;
        Comparable v = a[lo];

        while(i <= gt)
        {
            int cmp = a[i].compareTo(v);
            if      (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, gt--, i++);
            else                i++;
        }
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
    }

    private static void exch(Comparable[] a, int i, int j)
    {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    private static void show(Comparable[] a)
    {
        for (Comparable comparable : a)
            StdOut.print(comparable + " ");
        StdOut.println();
    }
    private static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
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