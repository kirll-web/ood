/*
Шаблонный интерфейс IObserver. Его должен реализовывать класс,
желающий получать уведомления от соответствующего IObservable
Параметром шаблона является тип аргумента,
передаваемого Наблюдателю в метод Update
*/

interface IObserver<T, Parameter> {
    val informationDisplay: IInformationDisplay
    val getInfo: List<() -> Double>
    fun update(name: String, data: T, changedParameter: Parameter)
}

data class InfoItem(val name: String, val value: Double)

/*
Шаблонный интерфейс IObservable. Позволяет подписаться и отписаться на оповещения, а также
инициировать рассылку уведомлений зарегистрированным наблюдателям.
*/
interface IObservable<T, Parameter> {
    fun registerObserver(
        token: Token,
        parameter: Parameter,
        observer: IObserver<T, Parameter>
    )
    fun notifyObservers(changedParameter: Parameter)
    fun removeObserver(token: Token)
}


typealias Token = Int

// Реализация интерфейса IObservable
abstract class Observable<T, Parameter> : IObservable<T, Parameter> {
    private var mObservers: MutableMap<Token, Pair<Parameter, IObserver<T, Parameter>>> =
        mutableMapOf()
    abstract val name: String


    abstract fun getChangedData(): T

    override fun registerObserver(
        token: Token,
        parameter: Parameter,
        observer: IObserver<T, Parameter>
    ) {
        mObservers[token] = parameter to observer
    }

    override fun notifyObservers(changedParameter: Parameter) {
        val data = getChangedData()
        val temp = mutableMapOf<Token, Pair<Parameter, IObserver<T, Parameter>>>()
        temp.putAll(mObservers)
        temp.forEach {
            it.value.second.update(name, data, changedParameter)
        }

    }

    override fun removeObserver(token: Token) {
        mObservers.remove(token)
    }
}

interface IInformationDisplay {
    fun display(args: List<InfoItem>)
}


