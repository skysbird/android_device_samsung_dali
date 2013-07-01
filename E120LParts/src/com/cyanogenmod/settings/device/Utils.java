/*
 * Copyright (C) 2013 The MoKee OpenSource Project
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
 
package com.cyanogenmod.settings.device;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;

public class Utils {

    private static final String HARDWARE_UI_PROPERTY = "persist.sys.ui.hw";
    public static final String KEY_DISABLE_OVERLAYS = "disable_overlays";
    public static final String KEY_FORCE_HW_UI = "force_hw_ui";

    public static void writeHardwareUiOptions(boolean enable) {
        SystemProperties.set(HARDWARE_UI_PROPERTY, enable ? "true" : "false");
    }

    public static void writeDisableOverlaysOption(boolean enable) {
        try {
            IBinder flinger = ServiceManager.getService("SurfaceFlinger");
            if (flinger != null) {
                Parcel data = Parcel.obtain();
                data.writeInterfaceToken("android.ui.ISurfaceComposer");
                final int disableOverlays = enable ? 1 : 0;
                data.writeInt(disableOverlays);
                flinger.transact(1008, data, null, 0);
                data.recycle();
            }
        } catch (RemoteException ex) {
        }
    }
}
