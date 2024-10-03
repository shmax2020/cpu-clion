package com.tripoli.cpuclion.services

import com.intellij.openapi.components.Service
import com.kitfox.svg.SVGDiagram
import com.kitfox.svg.SVGUniverse
import java.awt.Graphics2D
import java.awt.GraphicsEnvironment
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.IOException
import java.net.URI
import javax.swing.ImageIcon
@Service(Service.Level.PROJECT)
class SVGLoaderService {
    private val universe = SVGUniverse()

    private fun loadSvgAsBufferedImage(resourcePath: String, width: Int, height: Int): BufferedImage? {
        try {
            // Load the SVG as a URI
            val svgUri: URI? = javaClass.getResource(resourcePath)?.toURI()
            if (svgUri == null) {
                println("SVG resource not found at $resourcePath")
                return null
            }

            // Load the SVG diagram
            val diagram: SVGDiagram = universe.getDiagram(svgUri)
            diagram.updateTime(0.0)

            val graphicsConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().defaultScreenDevice.defaultConfiguration
            val bufferedImage = graphicsConfig.createCompatibleImage(width, height, BufferedImage.TYPE_INT_ARGB)
            val g2: Graphics2D = bufferedImage.createGraphics()
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC)

            // Render the SVG directly on the button
            diagram.render(g2)
            diagram.render(g2)

            return bufferedImage
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun loadSvgAsIcon(resourcePath: String, width: Int, height: Int): ImageIcon? {
        val image = loadSvgAsBufferedImage(resourcePath, width, height)
        return if (image != null) ImageIcon(image) else null
    }
}
