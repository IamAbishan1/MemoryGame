package com.example.memorygame.models

data class MemoryCard (
    val identifer: Int,
    val imageUrl: String? = null,
    var isFaceup : Boolean = false,
    var isMatched : Boolean = false
)

