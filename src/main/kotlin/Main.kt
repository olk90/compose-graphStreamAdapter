import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import de.olk90.graphStreamAdapter.view.GraphStreamPanel
import org.graphstream.graph.Graph
import org.graphstream.graph.implementations.SingleGraph

@Composable
@Preview
fun App() {

    val graphState = mutableStateOf(createSimpleGraph())

    MaterialTheme {
        GraphStreamPanel(graphState)
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}


fun createSimpleGraph(): Graph {
    val graph = SingleGraph("Simple Graph")

    // Add 5 nodes
    for (i in 1..5) {
        val node = graph.addNode("N$i")
        node.setAttribute("ui.label", "Node $i")
    }

    // Add some edges
    graph.addEdge("E1-2", "N1", "N2")
    graph.addEdge("E2-3", "N2", "N3")
    graph.addEdge("E3-4", "N3", "N4")
    graph.addEdge("E4-5", "N4", "N5")
    graph.addEdge("E5-1", "N5", "N1")

    // Set some styling
    graph.setAttribute(
        "ui.stylesheet", """
        node {
            size: 30px;
            fill-color: #66CCFF;
            text-size: 14;
        }
        edge {
            shape: line;
            fill-color: #999;
            arrow-size: 8px, 6px;
        }
    """
    )

    return graph
}