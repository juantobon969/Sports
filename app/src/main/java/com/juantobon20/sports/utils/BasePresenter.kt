package co.dito.abako.abako.base

interface BasePresenter<V> {
     fun attachView(view: V)
     fun detachView()
}