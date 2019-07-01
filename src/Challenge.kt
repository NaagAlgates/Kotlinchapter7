import kotlin.jvm.internal.Intrinsics
import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"
var playerGold = 10
var playerSilver = 10
var dragonBreathQuantity = 5.0
val onePint = 0.125
val showPintCountThreshhold = 12
fun main() {
    displayRemaningDragonsBreath(0)
    placeOrder("shandy,DRAGON'S BREATH; IT'S GOT WHAT ADVENTURES CRAVE,5.91")
    //displayRemaningDragonsBreath(13)
}

fun placeOrder(menuData: String) {
    try {
        val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
        val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
        println("Madrigal speaks with $tavernMaster about their order")
        /*val data = menuData.split(",")
        val type = data[0]
        val name = data[1]
        val price = data[2]*/
        val (type, name, price) = menuData.split(',')
        val message = "Madrigal buys a ${name.split(';')[0].toLowerCase().capitalize()} ($type) for $price"
        val phrase = "Ah, delicious $name"
        println(message)
        performPurchase(price = price.toDouble())
        println(toDragonSpeak(phrase))
        performPurchase(price = price.toDouble())
    } catch (e: Exception) {
        println(e)
        println("Oh No!. Details not fully entered.")
    }
}

fun toDragonSpeak(phrase: String) = phrase.replace(Regex("[aAeEiIoOuU]")) {
    when (it.value) {
        "a" -> "4"
        "e" -> "3"
        "i" -> "1"
        "o" -> "0"
        "u" -> "|_|"
        "A" -> "4"
        "E" -> "3"
        "I" -> "1"
        "O" -> "0"
        "U" -> "|_|"
        else -> it.value
    }
}

fun performPurchase(price: Double) {
    displayBalance()
    val totalPurse = playerGold + (playerSilver / 100.0)
    println("Total purse: $totalPurse")
    println("Purchasing item for $price")
    val remainingBalance = totalPurse - price
    if (remainingBalance > 0) {
        println("Remaning Balance: ${"%.2f".format(remainingBalance)}")
        val remaningGold = remainingBalance.toInt()
        val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
        playerGold = remaningGold
        playerSilver = remainingSilver
        displayBalance()
    } else {
        println("You don't have sufficient balance.")
    }
}
private fun displayBalance() {
    println("Player's purse balance: Gold: $playerGold, Silver $playerSilver")
}

private fun displayRemaningDragonsBreath(purchasedquantity: Int) {
    dragonBreathQuantity -= (purchasedquantity * onePint)
    println("Remaining Breath (Gallons): $dragonBreathQuantity")
    if (purchasedquantity >= showPintCountThreshhold) {
        println("Remaining Pints: ${(dragonBreathQuantity / onePint).roundToInt()}")
    }
}