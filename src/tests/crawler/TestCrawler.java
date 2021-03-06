package tests.crawler;

import algorithms.search.BreadthFirstSearch;
import algorithms.search.UninformedSearch;
import core.stocks.StockEdge;
import core.stocks.StockVertex;
import core.webcrawler.Crawler;
import core.webcrawler.StockGraphBuilder;
import edu.uci.ics.jung.graph.Graph;

public class TestCrawler {

	public void runExample() {
		
		Crawler crawler = new Crawler();
		crawler.crawl();
	
		StockGraphBuilder sgb = new StockGraphBuilder();
		
		Graph<StockVertex, StockEdge> graph = sgb.buildGraph();
		
//		System.out.println(graph.getVertexCount());
//		System.out.println(graph.getEdgeCount());
		
		UninformedSearch<StockVertex, StockEdge> bfs = new BreadthFirstSearch<>();
		bfs.visualizeSearch(graph, graph.getVertices().iterator().next());
		
	}
}
