/*
 * SPDX-FileCopyrightText: 2015 - 2025 Rime community
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package com.localtype.app.ime.broadcast

import android.view.inputmethod.EditorInfo
import com.localtype.app.core.CompositionProto
import com.localtype.app.core.MenuProto
import com.localtype.app.core.RimeMessage
import com.localtype.app.core.SchemaItem
import com.localtype.app.core.StatusProto
import com.localtype.app.ime.window.BoardWindow

interface InputBroadcastReceiver {
    fun onStartInput(info: EditorInfo) {}

    fun onSelectionUpdate(start: Int, end: Int) {}

    fun onRimeSchemaUpdated(schema: SchemaItem) {}

    fun onRimeOptionUpdated(value: RimeMessage.OptionMessage.Data) {}

    fun onCandidateListUpdate(data: RimeMessage.CandidateListMessage.Data) {}

    fun onCompositionUpdate(data: CompositionProto) {}

    fun onCandidateMenuUpdate(data: MenuProto) {}

    fun onKeyAppearanceUpdate(composing: Boolean, menu: Boolean, paging: Boolean) {}

    fun onInputStatusUpdate(value: StatusProto) {}

    fun onWindowAttached(window: BoardWindow) {}

    fun onWindowDetached(window: BoardWindow) {}

    fun onEnterKeyLabelUpdate(label: String) {}
}
