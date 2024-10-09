/*
Шаблонный интерфейс IObserver. Его должен реализовывать класс,
желающий получать уведомления от соответствующего IObservable
Параметром шаблона является тип аргумента,
передаваемого Наблюдателю в метод Update
*/

interface IObserver<T, Param>{
    fun update(data: T, observable: IObservable<T, Param>)
}

data class InfoItem(val name: String, val value: Double)

/*
Шаблонный интерфейс IObservable. Позволяет подписаться и отписаться на оповещения, а также
инициировать рассылку уведомлений зарегистрированным наблюдателям.
*/
interface IObservable<T, Param> {
    val name: String
    fun registerObserver(token: Token, observer: Pair<Param, IObserver<T, Param>>)
    fun notifyObservers(changedParam: List<Param>)
    fun removeObserver(token: Token)
}

typealias Token = Int
// Реализация интерфейса IObservable
abstract class Observable<T, Param> : IObservable<T, Param> {
    private var mObservers: MutableMap<Token, Pair<Param, IObserver<T, Param>>> = mutableMapOf()


    abstract fun getChangedData(): T

    override fun registerObserver(token: Token, observer: Pair<Param, IObserver<T, Param>>) {
        mObservers[token] = observer
    }

    override fun notifyObservers(changedParam: List<Param>) {
        val data = getChangedData()
        val temp = mutableMapOf<Token,Pair<Param, IObserver<T, Param>>>()
        val needUpdateObservers = mutableListOf<IObserver<T, Param>>()

        temp.putAll(mObservers)
        temp.toSortedMap().forEach {
            if(changedParam.contains(it.value.first)) {
                needUpdateObservers.add(it.value.second)
            }
        }

        needUpdateObservers.distinct().forEach {
            it.update(data, this)
        }
    }

    override fun removeObserver(token: Token) {
        mObservers.remove(token)
    }
}

interface IInformationDisplay {
    fun display(args: List<InfoItem>)
}


