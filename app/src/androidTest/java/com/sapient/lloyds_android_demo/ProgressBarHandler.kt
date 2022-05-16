package com.sapient.lloyds_android_demo


import androidx.annotation.IdRes
import androidx.test.InstrumentationRegistry.getTargetContext
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject
import androidx.test.uiautomator.UiSelector


fun waitUntilGoneProgressBar() {
    progressBar().waitUntilGone(10000)
}

private fun progressBar(): UiObject {
    return uiObjectWithId(R.id.progressBar)
}

private fun uiObjectWithId(@IdRes id: Int): UiObject {
    val resourceId = getTargetContext().resources.getResourceName(id)
    val selector = UiSelector().resourceId(resourceId)
    return UiDevice.getInstance(getInstrumentation()).findObject(selector)
}
