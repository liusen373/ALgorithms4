package com.liusen.algorithms.chapter4_3;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.Edge;

public class PrimMST {
    private Edge[] edgeTo;
    private double[] distoTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;
    private double weight = 0.0;

    public PrimMST(EdgeWeightedGraph G)
    {
        edgeTo = new Edge[G.V()];
        distoTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            distoTo[v] = Double.POSITIVE_INFINITY;
        pq = new IndexMinPQ<>(G.V());

        distoTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty())
            visit(G, pq.delMin());

        for(int i = 0; i < distoTo.length; i++)
            weight += distoTo[i];
    }
    private void visit(EdgeWeightedGraph G, int v)
    {
        marked[v] = true;
        for (Edge e: G.adj(v))
        {
            int w = e.other(v);
            if(marked[w]) continue;
            if (e.weight() < distoTo[w])
            {
                edgeTo[w] = e;
                distoTo[w] = e.weight();
                if (pq.contains(w)) pq.changeKey(w, distoTo[w]);
                else                pq.insert(w, distoTo[w]);
            }
        }
    }
    public Iterable<Edge> edges()
    {
        Bag<Edge> mst = new Bag<>();
        for (int i = 1; i < edgeTo.length; i++) // edgeTo[0] is null
            mst.add(edgeTo[i]);
        return mst;
    }
    public double weight() {return weight;}
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        PrimMST mst = new PrimMST(G);
        for(Edge e: mst.edges())
        {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
