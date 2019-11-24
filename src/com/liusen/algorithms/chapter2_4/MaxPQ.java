package com.liusen.algorithms.chapter2_4;

import java.util.Random;
import edu.princeton.cs.algs4.StdOut;
import com.liusen.algorithms.chapter2_4.MaxPQ;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN)
    {pq = (Key[]) new Comparable[maxN+1];}

    public boolean isEmpty()
    {return N == 0;}

    public int size()
    {return N;}

    public void insert(Key t)
    {
        pq[++N] = t;
        swim(N);
    }
    public Key delMax()
    {
        Key max = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }

    private boolean less(int i, int j) {return pq[i].compareTo(pq[j]) < 0; }
    private void exch(int i, int j)
    {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
    private void swim(int k)
    {
        while (k > 1 && less(k/2, k))
        {
            exch(k/2, k);
            k = k /2;
        }
    }
    private void sink(int k)
    {
        while (2*k <= N)
        {
            int j = 2 * k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
    private static void show(Comparable[] a)
    {
        for (Comparable comparable : a)
            StdOut.print(comparable + " ");
        StdOut.println();
    }
    public static void main(String[] args)
    {
        int N = Integer.parseInt(args[0]);

        Integer[] a = new Integer[N];
        MaxPQ<Integer> q = new MaxPQ<Integer>(N);

        Random r = new Random();
        for (int i = 0; i < a.length; i++)
        {
            a[i] = r.nextInt(100);
            q.insert(a[i]);
        }
        StdOut.println("original:");
        show(a);

        StdOut.println("sorted:");
        while(!q.isEmpty())
        {
            StdOut.print(q.delMax());
            StdOut.print(" ");
        }
    }
}
