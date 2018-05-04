package helper;

import edge.Edge;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.AbstractGraph;
import edu.uci.ics.jung.graph.UndirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import factory.graph.GraphFactory;
import graph.Graph;
import graph.GraphPoet;
import graph.SocialNetwork;
import org.apache.commons.collections15.Transformer;
import vertex.Vertex;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Iterator;

public class GraphVisualizationHelper {
    public static void visualize(Graph g) {
        CircleLayout<Vertex, Edge> layout = new CircleLayout<>(transferGraph(g));
        layout.setSize(new Dimension(300, 300)); // sets the initial size of the space     // The BasicVisualizationServer<V,E> is parameterized by the edge types
        BasicVisualizationServer<Vertex, Edge> vv = new BasicVisualizationServer<>(layout);
        vv.setPreferredSize(new Dimension(600, 600)); //Sets the viewing area size
        JFrame frame = new JFrame("Simple Graph View");

        Transformer<Vertex, Paint> vertexPaint = vertex -> Color.BLUE;
        // Set up a new stroke Transformer for the edges
        float dash[] = {10.0f};
        final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
        Transformer<Edge, Stroke> edgeStrokeTransformer = s -> edgeStroke;
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<>());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<>());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        Graph poet, socialNetwork, topologyNetwork, movie;
        poet = GraphFactory.createGraph("./test/graph/data/GraphPoet.txt");
        socialNetwork = GraphFactory.createGraph("./test/graph/data/GraphSocial.txt");
        topologyNetwork = GraphFactory.createGraph("./test/graph/data/GraphTopology.txt");
        movie = GraphFactory.createGraph("./test/graph/data/GraphMovie.txt");
        GraphVisualizationHelper.visualize(poet);
        GraphVisualizationHelper.visualize(socialNetwork);
        GraphVisualizationHelper.visualize(topologyNetwork);
        GraphVisualizationHelper.visualize(movie);
    }


    public static AbstractGraph<Vertex, Edge> transferGraph(Graph g) {
        AbstractGraph<Vertex, Edge> graph;
        if (g instanceof GraphPoet || g instanceof SocialNetwork) {
            graph = new DirectedSparseMultigraph<>();
            for (Edge item : g.edges()) {
                Iterator<Vertex> iterator = item.vertices().iterator();
                graph.addEdge(item, iterator.next(), iterator.next(), EdgeType.DIRECTED);
            }
        } else {
            graph = new UndirectedOrderedSparseMultigraph<>();
            for (Edge item : g.edges()) {
                Iterator<Vertex> iterator = item.vertices().iterator();
                graph.addEdge(item, iterator.next(), iterator.next(), EdgeType.UNDIRECTED);
            }
        }
        return graph;
    }
}
