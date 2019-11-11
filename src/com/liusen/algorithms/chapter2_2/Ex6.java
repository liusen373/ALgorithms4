package com.liusen.algorithms.chapter2_2;

import java.util.Random;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Ex6
{
    //这道题存在问题

    //如果直接修改merge函数，对某个变量进行递增
    //对于一个长度为2N的数组，统计出来的访问次数为
    //归并2^0个长度为2N，2^1个长度为N，2^2个长度为N/2等等相加的结果

    //如果通过修改排序函数，只取最上层的一次结果，呃，太丑了
    private static Comparable[] aux;
    private static int  ArrayVisitCount;

    private static void MergeBUSort(Comparable[] a)
    {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz *= 2)
        {
            for (int lo = 0; lo < N-sz; lo += 2*sz)
                merge(a, lo, lo+sz-1, Math.min(lo+2*sz-1, N-1));//避免最后一次越界
        }
    }
    private static void MergeSort(Comparable[] a)
    {
        aux = new Comparable[a.length];//避免多次分配空间
        sort(a, 0, a.length-1);
    }
    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo) return; //递归终止条件，即只有一个元素时
        int mid = (lo + hi) / 2;
        sort(a, lo, mid);
        sort(a, mid+1, hi);
        merge(a, lo, mid, hi);
    }
    public static void merge(Comparable[] a, int lo, int mid, int hi)
    {
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++)
        {
            aux[k] = a[k];
            ArrayVisitCount += 2;
        }
        for (int k = lo; k <= hi; k++)
        {
            if (i > mid)                a[k] = aux[j++];
            else if (j > hi)                 a[k] = aux[i++];
            else if (less(aux[j], aux[i]))   {a[k] = aux[j++]; ArrayVisitCount+= 2;}
            else                        a[k] = aux[i++];

            ArrayVisitCount += 2;
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
        int NN = 8;
        int N = (int)Math.pow(2, NN);
        Integer[] a = new Integer[N];
        Integer[] b = new Integer[N];
        Random r = new Random();
        for (int i = 0; i < N; i++) {
            a[i] = r.nextInt(100);
            b[i] = r.nextInt(100);
        }

        int c1 = 0;
        int c2 = 0;
        int c3 = 0;

        for (int i = 0; i < NN ; i++) {
            int t = (int)Math.pow(2, NN - i);
            c3 += 6* Math.pow(2, i) * t * Math.log10(t);
        }

        ArrayVisitCount = 0;
        MergeSort(a);
        c1 = ArrayVisitCount;

        ArrayVisitCount = 0;
        MergeBUSort(b);
        c2 = ArrayVisitCount;

        StdOut.printf("n:%d\n", N);
        StdOut.printf("c1:%d\n", c1);
        StdOut.printf("c2:%d\n", c2);
        StdOut.printf("6nlgn:");
        StdOut.println(c3);
    }
}
