/*
Шаблонный интерфейс IObserver. Его должен реализовывать класс,
желающий получать уведомления от соответствующего IObservable
Параметром шаблона является тип аргумента,
передаваемого Наблюдателю в метод Update
*/

interface IObserver<T>{
    fun update(data: T)
}

data class InfoItem(val name: String, val value: Double)

/*
Шаблонный интерфейс IObservable. Позволяет подписаться и отписаться на оповещения, а также
инициировать рассылку уведомлений зарегистрированным наблюдателям.
*/
interface IObservable<T> {
    val name: String
    fun registerObserver(token: Token, observer: IObserver<T>)
    fun notifyObservers()
    fun removeObserver(token: Token)
}

typealias Token = Int
// Реализация интерфейса IObservable
abstract class Observable<T> : IObservable<T> {
    private var mObservers: MutableMap<Token, IObserver<T>> = mutableMapOf()


    abstract fun getChangedData(): T

    override fun registerObserver(token: Token, observer: IObserver<T>) {
        mObservers[token] = observer
    }

    override fun notifyObservers() {
        val data = getChangedData()
        val temp = mutableMapOf<Token, IObserver<T>>()
        temp.putAll(mObservers)
        temp.toSortedMap().forEach {
            it.value.update(data)
        }
    }

    override fun removeObserver(token: Token) {
        mObservers.remove(token)
    }
}

interface IInformationDisplay {
    fun display(args: List<InfoItem>)
}


