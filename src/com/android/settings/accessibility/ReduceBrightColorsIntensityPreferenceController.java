/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.accessibility;

import android.content.Context;
import android.provider.Settings;

import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;

import com.android.settings.core.SliderPreferenceController;
import com.android.settings.widget.SeekBarPreference;

/** PreferenceController for feature intensity. */
public class ReduceBrightColorsIntensityPreferenceController extends SliderPreferenceController {

    public ReduceBrightColorsIntensityPreferenceController(Context context, String key) {
        super(context, key);
    }

    @Override
    public int getAvailabilityStatus() {
        // TODO(b/170970675): Call into ColorDisplayService (CDS) to get availability/config status
        return AVAILABLE;
    }

    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        final SeekBarPreference preference = screen.findPreference(getPreferenceKey());
        preference.setContinuousUpdates(true);
        preference.setMax(getMax());
        preference.setMin(getMin());
        preference.setHapticFeedbackMode(SeekBarPreference.HAPTIC_FEEDBACK_MODE_ON_ENDS);
        updateState(preference);
    }


    @Override
    public final void updateState(Preference preference) {
        super.updateState(preference);
        preference.setEnabled(Settings.Secure.getInt(mContext.getContentResolver(),
                Settings.Secure.REDUCE_BRIGHT_COLORS_ACTIVATED, 0) == 1);
    }

    @Override
    public int getSliderPosition() {
        // TODO(b/170970675): Call into CDS to get intensity
        return 0;
    }

    @Override
    public boolean setSliderPosition(int position) {
        // TODO(b/170970675): Call into CDS to set intensity
        return true;
    }

    @Override
    public int getMax() {
        // TODO(b/170970675): Call into CDS to get config max intensity
        return 100;
    }

    @Override
    public int getMin() {
        // TODO(b/170970675): Call into CDS to get config min intensity
        return 0;
    }
}
