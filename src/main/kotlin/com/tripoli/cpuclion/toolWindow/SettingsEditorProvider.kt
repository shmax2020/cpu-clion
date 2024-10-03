package com.tripoli.cpuclion.toolWindow

import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorPolicy
import com.intellij.openapi.fileEditor.FileEditorProvider
import com.intellij.openapi.fileEditor.FileEditorState
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.fileEditor.impl.NonProjectFileWritingAccessExtension
import javax.swing.JComponent
import javax.swing.JPanel

class SettingsEditorProvider : FileEditorProvider {
    override fun accept(project: Project, file: VirtualFile): Boolean {
        return file.name == "settingsFile" // You can match based on specific criteria
    }

    override fun createEditor(project: Project, file: VirtualFile): FileEditor {
        return SettingsFileEditor()
    }

    override fun getEditorTypeId(): String = "SettingsEditor"

    override fun getPolicy(): FileEditorPolicy = FileEditorPolicy.HIDE_DEFAULT_EDITOR
}

