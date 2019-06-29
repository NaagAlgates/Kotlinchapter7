import kotlin.jvm.internal.Intrinsics

const val TAVERN_NAME = "Taernyl's Folly"
fun main() {
    placeOrder("shandy,DRAGON'S BREATH; IT'S GOT WHAT ADVENTURES CRAVE,5.91")
}
fun placeOrder(menuData:String){
    try {
        val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
        var tavernMster = TAVERN_NAME.substring(0 until indexOfApostrophe)
        println("Madrigal speaks with $tavernMster about their order")
        /*val data = menuData.split(",")
        val type = data[0]
        val name = data[1]
        val price = data[2]*/
        val (type,name,price) = menuData.split(',')
        val message = "Madrigal buys a ${name.split(';')[0].toLowerCase().capitalize()} ($type) for $price"
        val phrase = "Ah, delicious $name"
        println(message)
        println(toDragonSpeak(phrase))
    }catch (e:Exception){
        println(e)
        println("Oh No!. Details not fully entered.")
    }
}

fun toDragonSpeak(phrase:String)=phrase.replace(Regex("[aAeEiIoOuU]")){
    when(it.value){
        "a"->"4"
        "e"->"3"
        "i"->"1"
        "o"->"0"
        "u"->"|_|"
        "A"->"4"
        "E"->"3"
        "I"->"1"
        "O"->"0"
        "U"->"|_|"
        else->it.value
    }
}