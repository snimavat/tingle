package me.nimavat

class Book {

    String name
    BigDecimal price

    static belongsTo = [author:Author]
    static constraints = {
        name unique: true
    }
}
