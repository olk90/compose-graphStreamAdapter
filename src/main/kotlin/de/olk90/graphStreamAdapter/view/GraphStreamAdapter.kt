package de.olk90.graphStreamAdapter.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import org.graphstream.graph.Graph
import org.graphstream.ui.swing.SwingGraphRenderer
import org.graphstream.ui.swing_viewer.SwingViewer
import org.graphstream.ui.view.View
import org.graphstream.ui.view.Viewer
import org.graphstream.ui.view.Viewer.CloseFramePolicy
import java.awt.Component

@Composable
fun GraphStreamPanel(
    graphState: MutableState<Graph>
) {
    val view = getView(graphState)

    SwingPanel(
        modifier = Modifier
            .fillMaxWidth(.7f)
            .fillMaxHeight()
            .animateContentSize(), // Add smooth animation for size changes
        factory = { view as Component }
    )
}

private fun getView(graphState: MutableState<Graph>): View {

    val viewer = SwingViewer(graphState.value, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD)
    viewer.closeFramePolicy = CloseFramePolicy.EXIT

    // Show the viewer window
    viewer.enableAutoLayout()

    val view = viewer.addView("graphView", SwingGraphRenderer(), false)
    return view
}