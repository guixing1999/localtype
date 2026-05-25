/*
 * SPDX-FileCopyrightText: 2015 - 2025 Rime community
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package com.localtype.app.ime.candidates.popup

import com.localtype.app.R
import com.localtype.app.data.prefs.PreferenceDelegateEnum

enum class PopupCandidatesLayout(override val stringRes: Int) : PreferenceDelegateEnum {
    AUTOMATIC(R.string.automatic),
    HORIZONTAL(R.string.horizontal),
    VERTICAL(R.string.vertical),
}
