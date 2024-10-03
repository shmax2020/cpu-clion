package com.tripoli.cpuclion.toolWindow

import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorState
import com.intellij.openapi.util.Key
import java.beans.PropertyChangeListener
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel

class SettingsFileEditor : FileEditor {
    private val panel = JPanel() // Customize this panel to include your settings UI

    init {
       panel.add(JLabel("Settings Editor"))
       panel.add(JButton("Settings Editor").apply { setBounds(10,10,30,30) })
    }

    override fun getComponent(): JComponent = panel
    override fun getPreferredFocusedComponent(): JComponent {
        return panel
    }

    override fun getName(): String = "Settings Editor"

    override fun isModified(): Boolean = false
    override fun isValid(): Boolean = true
    override fun addPropertyChangeListener(p0: PropertyChangeListener) {

    }

    override fun removePropertyChangeListener(p0: PropertyChangeListener) {

    }

    override fun <T : Any?> getUserData(p0: Key<T>): T? {
        return null
    }

    override fun <T : Any?> putUserData(p0: Key<T>, p1: T?) {

    }

    override fun dispose() {}
    override fun setState(state: FileEditorState) {}
}
