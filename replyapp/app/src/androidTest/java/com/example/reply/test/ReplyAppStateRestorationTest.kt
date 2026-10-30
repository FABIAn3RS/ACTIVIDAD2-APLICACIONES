/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.reply.data.local.LocalEmailsDataProvider
import com.example.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppStateRestorationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_selectedEmailStateRetained_afterConfigChange() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Compact)
        }

        val email = LocalEmailsDataProvider.allEmails[2]
        val subjectText = composeTestRule.activity.getString(email.subject)
        val bodyText = composeTestRule.activity.getString(email.body)

        composeTestRule.onNodeWithText(subjectText).performClick()
        composeTestRule.onNodeWithText(bodyText).assertIsDisplayed()

        stateRestorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNodeWithText(bodyText).assertIsDisplayed()
    }

    @Test
    @TestMediumWidth
    fun mediumDevice_selectedEmailStateRetained_afterConfigChange() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Medium)
        }

        val email = LocalEmailsDataProvider.allEmails[2]
        val subjectText = composeTestRule.activity.getString(email.subject)
        val bodyText = composeTestRule.activity.getString(email.body)

        composeTestRule.onNodeWithText(subjectText).performClick()
        composeTestRule.onNodeWithText(bodyText).assertIsDisplayed()

        stateRestorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNodeWithText(bodyText).assertIsDisplayed()
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_selectedEmailStateRetained_afterConfigChange() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Expanded)
        }

        val email = LocalEmailsDataProvider.allEmails[2]
        val subjectText = composeTestRule.activity.getString(email.subject)
        val bodyText = composeTestRule.activity.getString(email.body)

        composeTestRule.onNodeWithText(subjectText).performClick()
        composeTestRule.onNodeWithText(bodyText).assertIsDisplayed()

        stateRestorationTester.emulateSavedInstanceStateRestore()

        composeTestRule.onNodeWithText(bodyText).assertIsDisplayed()
    }
}
