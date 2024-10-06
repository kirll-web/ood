package org.example

/*
Шаблонный интерфейс IObserver. Его должен реализовывать класс,
желающий получать уведомления от соответствующего IObservable
Параметром шаблона является тип аргумента,
передаваемого Наблюдателю в метод Update
*/

interface IObserver<T>{
    val informationDisplay: IInformationDisplay
    val getInfo: List<() -> Double>
    val token: Token
    open fun update(name: String, data: T)
}
/*
Шаблонный интерфейс IObservable. Позволяет подписаться и отписаться на оповещения, а также
инициировать рассылку уведомлений зарегистрированным наблюдателям.
*/
interface IObservable<T>{
    open fun registerObserver(observer: IObserver<T>)
    open fun notifyObservers()
    open fun removeObserver(token: Token)
}

typealias Token = Int
// Реализация интерфейса IObservable
abstract class Observable<T> : IObservable<T> {
    private var mObservers: MutableMap<Token, IObserver<T>> = mutableMapOf()
    private var mTokens: MutableList<Token> = mutableListOf()
    abstract val name: String


    abstract fun getChangedData(): T

    override fun registerObserver(observer: IObserver<T>) {
        mObservers[observer.token] = observer
        mTokens.add(observer.token)
    }

    override fun notifyObservers() {
        val data = getChangedData()
        mTokens.sortBy{it}.let {
            for (i in 0..<mTokens.size) {
                if(i < mObservers.size - 1) {
                    mObservers[mTokens[i]]?.update(name, data)
                }
            }
        }

    }

    override fun removeObserver(token: Token) {
        mObservers.remove(token)
        mTokens.remove(token)
    }
}

interface IInformationDisplay{
    fun display(v1: Double, v2: Double, v3: Double)
}


