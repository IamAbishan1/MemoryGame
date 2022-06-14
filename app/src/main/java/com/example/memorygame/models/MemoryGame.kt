package com.example.memorygame.models

import com.example.memorygame.utils.DEFAULT_ICONS

class MemoryGame(
    private val boardSize: BoardSize,
    private val customImages: List<String>?) {

    val cards: List<MemoryCard>
    var numPairsFound = 0

    private var numCardFlips = 0
    private  var indexOfSingleSelectedCard: Int? = null

    init {
        if (customImages == null) {
            val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
            val randomizedImage = (chosenImages + chosenImages).shuffled()
            cards = randomizedImage.map{ MemoryCard(it) }
        }
        else{
            val randomizedImages = (customImages + customImages).shuffled()
            cards = randomizedImages.map { MemoryCard(it.hashCode(),it) }
        }
    }


    fun flipCard(position: Int): Boolean {
        numCardFlips++
        val card : MemoryCard = cards[position]
        //Three Cases:
        //0 cards previously flipped over => restore cards + flip over the selected card
        //1 card previously flipped over => flip over the selected card + check if the images match
        //2 cards previously flipped over => restore cards + flip over the selected card
        var foundMatch = false

        if (indexOfSingleSelectedCard == null){
            restoreCards()
            indexOfSingleSelectedCard = position
        }
        else {
            //1 card previously flipped over => flip over the selected card + check if the images match

            var foundMatch = checkForMatch(indexOfSingleSelectedCard!!,position)
        }
        card.isFaceup = !card.isFaceup
        return foundMatch
    }

    private fun checkForMatch(position1: Int, position2: Int): Boolean {
        if(cards[position1].identifer != cards[position2].identifer){
            return false
        }
        cards[position1].isMatched = true
        cards[position2].isMatched = true
        numPairsFound++
        return true
    }

    private fun restoreCards() {
        for (card in cards){
            if(!card.isMatched){
                card.isFaceup = false
            }
        }
    }

    fun haveWonGame(): Boolean {
        return numPairsFound == boardSize.getNumPairs()
    }

    fun isCardFaceUp(position: Int): Boolean {
        return cards[position].isFaceup
    }

    fun getMoves(): Int {
        return numCardFlips / 2
    }
}
