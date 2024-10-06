/*
Шаблонный интерфейс IObserver. Его должен реализовывать класс,
желающий получать уведомления от соответствующего IObservable
Параметром шаблона является тип аргумента,
передаваемого Наблюдателю в метод Update
*/

interface IObserver<T>{
    val informationDisplay: IInformationDisplay
    val getInfo: () -> Double
    open fun update(data: T)
}
/*
Шаблонный интерфейс IObservable. Позволяет подписаться и отписаться на оповещения, а также
инициировать рассылку уведомлений зарегистрированным наблюдателям.
*/
interface IObservable<T>{
    open fun registerObserver(token: Token, observer: IObserver<T>)
    open fun notifyObservers()
    open fun removeObserver(token: Token)
}

typealias Token = Int
// Реализация интерфейса IObservable
abstract class Observable<T> : IObservable<T> {
    private var mObservers: MutableMap<Token, IObserver<T>> = mutableMapOf()
    // todo токены не должны удаляться за линейное время //fix
    // Классы-наследники должны перегрузить данный метод,
    // в котором возвращать информацию об изменениях в объекте
    abstract fun getChangedData(): T

    override fun registerObserver(token: Token, observer: IObserver<T>) {
        mObservers[token] = observer
    }

    override fun notifyObservers() {
        val data = getChangedData()
        val temp = mutableMapOf<Token, IObserver<T>>()
        temp.putAll(mObservers)
        temp.forEach {
            it.value.update(data)
            mObservers.remove(it.key)
        }

    }

    override fun removeObserver(token: Token) {
        mObservers.remove(token)
    }
}

interface IInformationDisplay{
    fun display(v1: Double, v2: Double, v3: Double)
}


