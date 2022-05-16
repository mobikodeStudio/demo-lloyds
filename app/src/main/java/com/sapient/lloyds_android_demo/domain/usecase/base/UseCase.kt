package com.sapient.lloyds_android_demo.domain.usecase.base

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class UseCase {

    var lastDisposable: Disposable? = null
    val compositeDisposable = CompositeDisposable()

    fun disposeLast() {
        lastDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    fun dispose() {
        compositeDisposable.clear()
    }
}
