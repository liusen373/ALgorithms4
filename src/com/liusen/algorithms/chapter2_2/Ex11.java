package com.liusen.algorithms.chapter2_2;

import java.util.Random;
import edu.princeton.cs.algs4.StdOut;
import com.liusen.algorithms.chapter2_1.Insertion;

public class Ex11
{
    public static void sort(Comparable[] a, Comparable[] aux)
    {
        for (int i = 0; i < a.length; i++)
            aux[i] = a[i];
        sorta(a, aux, 0, a.length-1);
        if (!isSorted(a)) //排序后的结果可能位于a,也可能位于aux，故需纠正结果
        {
            for (int i = 0; i < a.length; i++)
                a[i] = aux[i];
        }
    }
    private static void sorta(Comparable[] a, Comparable[] aux, int lo, int hi)
    {
        if (hi-lo <= 15) {
            Insertion.sort(a); //递归终止条件，当数组较小时采用插入排序
            return;
        }
        int mid = (lo + hi) / 2;
        sortaux(a, aux, lo, mid);
        sortaux(a, aux,mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }
    private static void sortaux(Comparable[] aux, Comparable[] a, int lo, int hi)
    {
        if (hi-lo <= 15) {
            Insertion.sort(a); //递归终止条件，当数组较小时采用插入排序
            return;
        }
        int mid = (lo + hi) / 2;
        sorta(a, aux, lo, mid);
        sorta(a, aux,mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }
    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
    {
        int i = lo;
        int j = mid + 1;

        if (less_or_equal(a[mid], a[mid+1])) //判断是否已经有序
            return;
        //for (int k = lo; k <= hi; k++) //只在递归开始之前复制一次，得到内容完全相同的a[]和aux[]
        //    aux[k] = a[k];             //然后使a、aux交替用作输入输出
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
        return v.compareTo(w) < 0;//>返回1; <返回-1;=返回0
    }
    private static boolean less_or_equal(Comparable v, Comparable w)
    {
        return v.compareTo(w) <= 0;
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