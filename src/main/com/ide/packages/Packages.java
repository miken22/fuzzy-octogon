package main.com.ide.packages;

/**
 * List of all packages to display in the package loaders
 * 
 * @author Mike Nowicki
 *
 */
public final class Packages {

	/**
	 * All java packages that can be imported
	 */
	public static final String[] javaPackages = { 
		"java.beans", 
		"java.beans.beancontext",
		"java.io",
		"java.lang",
		"java.lang.annotation",
		"java.lang.instrument",
		"java.lang.invoke",
		"java.lang.management",
		"java.lang.ref",
		"java.lang.reflect",
		"java.math",
		"java.net",
		"java.nio",
		"java.nio.channels",
		"java.nio.channels.spi",
		"java.nio.charset",
		"java.nio.charset.spi",
		"java.nio.file",
		"java.nio.file.attribute",
		"java.nio.file.spi",
		"java.rmi",
		"java.rmi.activation",
		"java.rmi.dgc",
		"java.rmi.registry",
		"java.rmi.server",
		"java.security",
		"java.security.acl",
		"java.security.cert",
		"java.security.interfaces",
		"java.security.spec",
		"java.sql",
		"java.text",
		"java.text.spi",
		"java.time",
		"java.time.chrono",
		"java.time.format",
		"java.time.temporal",
		"java.time.zone",
		"java.util",
		"java.util.concurrent",
		"java.util.concurrent.atomic",
		"java.util.concurrent.locks",
		"java.util.function",
		"java.util.jar",
		"java.util.logging",
		"java.util.prefs",
		"java.util.regex",
		"java.util.spi",
		"java.util.stream",
		"java.util.zip"
	};
	
	/**
	 * List of all JUNG2 packages to import
	 */
	public static final String[] jungPackages = {
		"edu.uci.ics.jung.algorithms.blockmodel", 
		"edu.uci.ics.jung.algorithms.cluster",
		"edu.uci.ics.jung.algorithms.filters", 
		"edu.uci.ics.jung.algorithms.flows",
		"edu.uci.ics.jung.algorithms.generators", 
		"edu.uci.ics.jung.algorithms.generators.random",
		"edu.uci.ics.jung.algorithms.importance",
		"edu.uci.ics.jung.algorithms.layout",
		"edu.uci.ics.jung.algorithms.layout.util",
		"edu.uci.ics.jung.algorithms.layout3d",
		"edu.uci.ics.jung.algorithms.matrix", 
		"edu.uci.ics.jung.algorithms.metrics", 
		"edu.uci.ics.jung.algorithms.scoring", 
		"edu.uci.ics.jung.algorithms.scoring.util", 
		"edu.uci.ics.jung.algorithms.shortestpath", 
		"edu.uci.ics.jung.algorithms.transformation", 
		"edu.uci.ics.jung.algorithms.util", 
		"edu.uci.ics.jung.graph", 
		"edu.uci.ics.jung.graph.event", 
		"edu.uci.ics.jung.graph.util", 
		"edu.uci.ics.jung.io", 
		"edu.uci.ics.jung.io.graphml", 
		"edu.uci.ics.jung.io.graphml.parser", 
		"edu.uci.ics.jung.visualization", 
		"edu.uci.ics.jung.visualization.annotations", 
		"edu.uci.ics.jung.visualization.control", 
		"edu.uci.ics.jung.visualization.decorators", 
		"edu.uci.ics.jung.visualization.jai", 
		"edu.uci.ics.jung.visualization.layout", 
		"edu.uci.ics.jung.visualization.picking",
		"edu.uci.ics.jung.visualization.renderers", 
		"edu.uci.ics.jung.visualization.subLayout",
		"edu.uci.ics.jung.visualization.transform",
		"edu.uci.ics.jung.visualization.transform.shape",
		"edu.uci.ics.jung.visualization.util",
		"edu.uci.ics.jung.visualization3d",
		"edu.uci.ics.jung.visualization3d.control",
		"edu.uci.ics.jung.visualization3d.decorators",
		"edu.uci.ics.jung.visualization3d.layout"
	};
	
	/**
	 * List of custom packages to import
	 */
	public static final String[] libraries = {
		   "algorithms.connectivity", 
		   "algorithms.graphloader", 
		   "algorithms.search", 
		   "algorithms.search.tools",
		   "algorithms.spanningtree",
		   "core.components",
		   "core.stocks",
		   "core.visualizer",
		   "core.webcrawler"
	};
	
}
