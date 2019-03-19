package transform

import io.reactivex.Observable
import io.reactivex.functions.Predicate
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit


/* * * * * * * * * * * * * * * *
    Transform observables
* * * * * * * * * * * * * * * */

//*** map start ***/

fun mapFromIntToString()
{
    val source = Observable.just(1,2,3,4,5)
    // val sourceString : Observable<String> = source.map { "From integer to string $it" }

    val sourceString = source.map { "From integer to string $it" }
    sourceString.subscribe { println(it) }
}

fun mapFromStringToObject()
{
    val formate = DateTimeFormatter.ofPattern("M/d/yyyy")
    val source = Observable.just("01/01/2017","01/01/2018","01/01/2019")
    source.map { LocalDate.parse(it,formate) }.subscribe{ println(it)}
}

fun mapStringLength()
{
    val source = Observable.just("one","two","three","four","five")
    source.map { it.length }
        .subscribe { println(it) }
}

fun maoStringToHashcode(){

    val source = Observable.just("one","two","three","four","five")
    source.map { it.hashCode() }
        .subscribe { println(it) }
}

//*** map ends ***/




//*** scan ***/

fun scan() {

    val source = Observable.range(1,5)

    source.scan { acc , next -> acc + next } .subscribe(::println)
}

fun scanWithInit() {

    val source = Observable.range(1,5)

    val init = 100

    source.scan(init) {acc, next -> acc + next}.subscribe(::println)
}

fun scanWithString(){

    val source = Observable.just("one","two","three","four","five")

    source.scan{ acc, next -> "$acc $next" }.subscribe(::println)

}

//*** scan ends ***/



//*** reduce start ***/

fun reduce() {

    val source = Observable.just(1,2,3,4,5)
    source.reduce { acc,next -> acc + next }
        .subscribe(::println)
}

fun reduceWithInit() {

    val source = Observable.just(1,2,3,4,5)
    val init = 100
    source.reduce(init) {acc, next -> acc + next}
        .subscribe{it -> println(it)}

}

fun startWith() {

    val source = Observable.range(1,5)
    source.startWith(0)
        .subscribe{ println(it)}


    val food = Observable.just("Mutton","Chicken","Mix vegetables","Cold Drinks")
    food.startWith("Today's Menu")
        .subscribe{ println(it) }

}


fun defaultIfEmpty() {

    val source = Observable.just("one","two","three","four","five")

    source.filter{ it.startsWith("s") }
        .defaultIfEmpty("Result is empty")
        .subscribe{ println(it) }

}

fun switchIfEmpty(){

    val source = Observable.just("one","two","three","four","five")

    val switchTo = Observable.just("six","seven","eight")

    source.filter{ it.startsWith("s") }
        .switchIfEmpty(switchTo)
        .subscribe{ println(it) }

}

fun sorted() {

    val source = Observable.just(5,3,2,1,4)

    source.sorted()
        .subscribe{println(it)}



    source.sorted(Comparator.reverseOrder())
        .subscribe{println(it)}


}




/* * * * * * * * * * * * * * *
    Transform observable ends
* * * * * * * * * * * * * * * */



fun main()
{
    sorted()
}


fun Sleep(seconds: Long)
{
    runBlocking {
        delay(seconds * 1000)
    }

}
