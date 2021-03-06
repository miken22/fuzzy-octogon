package tests.searches;

import java.util.List;

import algorithms.search.UniformCostSearch;
import core.components.Edge;
import core.components.Vertex;
import core.visualizer.Visualizer;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;

public class UniformCostExample {
		
	public void runExample() {
		
		Graph<Vertex, Edge> graph = new SparseGraph<>();
		
		Vertex v1 = new Vertex("a");
		Vertex v2 = new Vertex("b");
		Vertex v3 = new Vertex("c");
		Vertex v4 = new Vertex("d");
		Vertex v5 = new Vertex("e");
		Vertex v6 = new Vertex("f");
		Vertex v7 = new Vertex("g");
		Vertex v8 = new Vertex("h");
		
		graph.addVertex(v1);
		graph.addVertex(v2);
		graph.addVertex(v3);
		graph.addVertex(v4);
		graph.addVertex(v5);
		graph.addVertex(v6);
		graph.addVertex(v7);
		graph.addVertex(v8);
		
		graph.addEdge(new Edge(2), v1, v2);
		graph.addEdge(new Edge(2), v1, v3);
		graph.addEdge(new Edge(1), v2, v4);
		graph.addEdge(new Edge(7), v2, v5);
		graph.addEdge(new Edge(2), v3, v6);
		graph.addEdge(new Edge(5), v3, v7);
		graph.addEdge(new Edge(2), v5, v8);
		
		UniformCostSearch<Vertex, Edge> ucs = new UniformCostSearch<>();
		
		List<Vertex> ordering = ucs.search(graph, v4, v1);
		
		for (Vertex n : ordering) {
			System.out.println(n.toString());
		}

		Visualizer.viewGraph(graph, true);
		
	}
	
	
}
