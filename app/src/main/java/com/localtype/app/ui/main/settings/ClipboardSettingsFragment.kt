/*
 * SPDX-FileCopyrightText: 2015 - 2025 Rime community
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package com.localtype.app.ui.main.settings

import com.localtype.app.data.prefs.AppPrefs
import com.localtype.app.data.prefs.PreferenceDelegateFragment

class ClipboardSettingsFragment : PreferenceDelegateFragment(AppPrefs.defaultInstance().clipboard)
