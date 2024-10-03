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
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileListener
import com.intellij.openapi.vfs.VirtualFileSystem
import com.intellij.openapi.vfs.newvfs.impl.FakeVirtualFile
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.ui.components.JBRadioButton
import com.intellij.ui.content.ContentFactory
import com.tripoli.cpuclion.Bundle
import com.tripoli.cpuclion.services.MyProjectService
import org.gradle.internal.impldep.org.h2.command.dml.Set
import java.awt.BorderLayout
import java.awt.Dimension
import java.io.InputStream
import java.io.OutputStream
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

        class PlayAction : AnAction("Play", "Run the action", AllIcons.Actions.Execute) {
            fun openSettingsTab(project: Project) {
                val virtualFile = object : VirtualFile() {
                    override fun getName(): String = "settingsFile"
                    override fun getFileSystem(): VirtualFileSystem {
                        return object : VirtualFileSystem() {
                            override fun getProtocol(): String = "fake"
                            override fun findFileByPath(path: String): VirtualFile? = null
                            override fun refreshAndFindFileByPath(path: String): VirtualFile? = null
                            override fun addVirtualFileListener(p0: VirtualFileListener) {
                                TODO("Not yet implemented")
                            }

                            override fun removeVirtualFileListener(p0: VirtualFileListener) {
                                TODO("Not yet implemented")
                            }

                            override fun deleteFile(p0: Any?, p1: VirtualFile) {
                                TODO("Not yet implemented")
                            }

                            override fun moveFile(p0: Any?, p1: VirtualFile, p2: VirtualFile) {
                                TODO("Not yet implemented")
                            }

                            override fun renameFile(p0: Any?, p1: VirtualFile, p2: String) {
                                TODO("Not yet implemented")
                            }

                            override fun createChildFile(p0: Any?, p1: VirtualFile, p2: String): VirtualFile {
                                TODO("Not yet implemented")
                            }

                            override fun createChildDirectory(p0: Any?, p1: VirtualFile, p2: String): VirtualFile {
                                TODO("Not yet implemented")
                            }

                            override fun copyFile(p0: Any?, p1: VirtualFile, p2: VirtualFile, p3: String): VirtualFile {
                                TODO("Not yet implemented")
                            }

                            override fun isReadOnly(): Boolean {
                                TODO("Not yet implemented")
                            }

                            override fun refresh(asynchronous: Boolean) {}
                        }
                    }
                    override fun getPath(): String = "settings"
                    override fun isWritable(): Boolean = true
                    override fun isDirectory(): Boolean = false
                    override fun isValid(): Boolean = true
                    override fun getParent(): VirtualFile? = null

                    override fun getChildren(): Array<VirtualFile> = emptyArray()

                    override fun getOutputStream(p0: Any?, p1: Long, p2: Long): OutputStream {
                        throw UnsupportedOperationException("Not implemented")
                    }

                    override fun contentsToByteArray(): ByteArray = ByteArray(0)

                    override fun getTimeStamp(): Long = 0

                    override fun getLength(): Long = 0

                    override fun refresh(p0: Boolean, p1: Boolean, p2: Runnable?) {}

                    override fun getPresentableName(): String = "Settings"
                    override fun getInputStream(): InputStream {
                        throw UnsupportedOperationException("Not implemented")
                    }
                }
                FileEditorManager.getInstance(project).openFile(virtualFile, true)
            }

            override fun actionPerformed(e: AnActionEvent) {
                val project = e.project
                openSettingsTab(project!!)
            }
        }

        fun getContent(): JBPanel<JBPanel<*>> = JBPanel<JBPanel<*>>().apply {
            layout = null
            val playAction = PlayAction()
            val executeButton = ActionButton(
                playAction,
                playAction.templatePresentation.clone(),
                ActionPlaces.UNKNOWN,
                Dimension(40, 40)
            ).apply {
                setBounds(10, 10, 30, 30)
            }
            add(executeButton)
        }
    }
}
