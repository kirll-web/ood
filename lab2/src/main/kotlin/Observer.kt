package org.example

/*
Шаблонный интерфейс IObserver. Его должен реализовывать класс,
желающий получать уведомления от соответствующего IObservable
Параметром шаблона является тип аргумента,
передаваемого Наблюдателю в метод Update
*/

data class Information(val v1: Double, val v2: Double, val v3: Double)

interface IObserver<T>{
    val calculate: () ->
    val informationDisplay: IInformationDisplay
    val getInfo: () -> Double
    open fun update(data: T)
}

interface IObservable<T>{
    open fun registerObserver(observer: IObserver<T>)
    open fun notifyObservers()
    open fun removeObserver(observer: IObserver<T>)
}

abstract class Observable<T> : IObservable<T> {
    private var mObservers: MutableList<IObserver<T>> = mutableListOf()

    abstract fun getChangedData(): T

    override fun registerObserver(observer: IObserver<T>) {
        mObservers.add(observer)
    }

    override fun notifyObservers() {
        val data = getChangedData()

        mObservers.forEach {
            it.update(data)
        }
    }

    override fun removeObserver(observer: IObserver<T>) {
        mObservers.remove(observer)
    }
}

interface IInformationDisplay{
    fun display(v1: Double, v2: Double, v3: Double)
}



