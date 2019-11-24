package com.liusen.algorithms.chapter4_3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;

public class LazyPrimMST {
    private double weight = 0.0;
    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G)
    {
        pq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();

        visit(G, 0);
        while(!pq.isEmpty())
        {
            Edge e = pq.delMin();

            int v = e.either(), w = e.other(v);
            if(marked[v] && marked[w]) continue;
            mst.enqueue(e);
            weight += e.weight();
            if(!marked[v]) visit(G, v);
            if(!marked[w]) visit(G, w);
        }
    }
    private void visit(EdgeWeightedGraph G, int v)
    {
        marked[v] = true;
        for (Edge e: G.adj(v))
            if(!marked[e.other(v)])
                pq.insert(e);
    }
    public Iterable<Edge> edges()
    { return mst; }
    public double weight() { return weight; }

    public static void main(String[] args)
    {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        LazyPrimMST mst = new LazyPrimMST(G);
        for(Edge e: mst.edges())
        {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
