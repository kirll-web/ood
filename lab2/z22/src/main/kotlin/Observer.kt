package org.example

/*
Шаблонный интерфейс IObserver. Его должен реализовывать класс,
желающий получать уведомления от соответствующего IObservable
Параметром шаблона является тип аргумента,
передаваемого Наблюдателю в метод Update
*/

interface IObserver<T>{
    val informationDisplay: IInformationDisplay
    val getInfo: () -> Double //() -> Double
    open fun update(data: T)
}

/*
Шаблонный интерфейс IObservable. Позволяет подписаться и отписаться на оповещения, а также
инициировать рассылку уведомлений зарегистрированным наблюдателям.
*/
interface IObservable<T>{
    open fun registerObserver(observer: IObserver<T>)
    open fun notifyObservers()
    open fun removeObserver(observer: IObserver<T>)
}

// Реализация интерфейса IObservable
abstract class Observable<T> : IObservable<T> {
    private var mObservers: MutableSet<IObserver<T>> = mutableSetOf()

    // Классы-наследники должны перегрузить данный метод,
    // в котором возвращать информацию об изменениях в объекте
    abstract fun getChangedData(): T

    override fun registerObserver(observer: IObserver<T>) {
        //todo один и тот же наблюдатель должен получать 1 раз(поправить) //fix
        mObservers.add(observer)
    }

    override fun notifyObservers() {
        val data = getChangedData()
        val tempObservers = buildSet {
            addAll(mObservers)
        }
        tempObservers.forEach {
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


