package com.tripoli.cpuclion.toolWindow

import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.openapi.actionSystem.impl.ActionButton
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBRadioButton
import com.intellij.ui.content.ContentFactory
import com.tripoli.cpuclion.Bundle
import com.tripoli.cpuclion.services.MyProjectService
import com.tripoli.cpuclion.services.SVGLoaderService
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JTextArea
import javax.swing.ScrollPaneConstants
import javax.swing.UIManager


class CPUWindowFactory : ToolWindowFactory {


    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val myToolWindow = MyToolWindow(toolWindow)
        val content = ContentFactory.getInstance().createContent(myToolWindow.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }

    override fun shouldBeAvailable(project: Project) = true

    class MyToolWindow(toolWindow: ToolWindow) {

        private val service = toolWindow.project.service<SVGLoaderService>()

        class PlayAction : AnAction("Play", "Run the action", AllIcons.Actions.Execute) {
            override fun actionPerformed(e: AnActionEvent) {
                println("Play button clicked!")
            }
        }

        fun getContent(): JBPanel<JBPanel<*>> = JBPanel<JBPanel<*>>().apply {
            layout = null
            val playAction = PlayAction()
            val executeAction = ActionManager.getInstance().getAction("Run")

            // Create an ActionButton using the "Execute" action
            val executeButton = ActionButton(
                executeAction,
                executeAction.templatePresentation.clone(),
                ActionPlaces.UNKNOWN,
                Dimension(40, 40)  // You can adjust the size as needed
            ).apply {
                setBounds(10,10,30,30)
            }
            add(executeButton)
        }
    }
}
