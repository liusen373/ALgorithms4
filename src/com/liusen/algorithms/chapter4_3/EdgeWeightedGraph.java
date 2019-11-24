package com.liusen.algorithms.chapter4_3;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class EdgeWeightedGraph {
    private final int V;
    private int E = 0;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V)
    {
        this.V = V;
        adj = (Bag<Edge>[]) new Bag[V];
    }
    public EdgeWeightedGraph(In in)
    {
        V = in.readInt();
        adj = (Bag<Edge>[]) new Bag[V];
        int t = in.readInt();
        for (int i = 0; i < t; i++)
        {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge e = new Edge(v, w, weight);
            addEdge(e);
        }
    }
    public int V() { return V; }
    public int E() { return E; }

    public void addEdge(Edge e)
    {
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }
    public Iterable<Edge> adj(int v) { return adj[v];}
    public Iterable<Edge> edges()
    {
        Bag<Edge> b = new Bag<Edge>();
        for (int v = 0; v < V; v++)
            for (Edge e: adj[v])
                if (e.other(v) > v)
                    b.add(e);
         return b;
    }
}
