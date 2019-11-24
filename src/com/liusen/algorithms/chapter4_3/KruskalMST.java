package com.liusen.algorithms.chapter4_3;

import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;

public class KruskalMST {
    private Queue<Edge> mst;
    private double weight = 0.0;

    public KruskalMST(EdgeWeightedGraph G)
    {
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        for(Edge e: G.edges())
            pq.insert(e);
        UF uf = new UF(G.V());

        while (!pq.isEmpty() && mst.size() < G.V() -1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.enqueue(e);
        }
        for (Edge e: mst)
            weight += e.weight();
    }
    public Iterable<Edge> edges() { return mst;}
    public double weight()  { return weight;}

    public static void main(String[] args)
    {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        KruskalMST mst = new KruskalMST(G);
        for(Edge e: mst.edges())
        {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
