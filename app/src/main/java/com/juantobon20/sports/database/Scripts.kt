package com.juantobon20.sports.database

const val SCRIPTEAM = "CREATE TABLE IF NOT EXISTS Team" +
        "(Id TEXT NOT NULL," +
        "Name TEXT NOT NULL," +
        "NameAlt TEXT," +
        "Description TEXT," +
        "Badge TEXT," +
        "Jersey TEXT," +
        "Stadium TEXT," +
        "FoundationYear TEXT," +
        "WebFacebook TEXT," +
        "WebInstagram TEXT," +
        "WebTwitter TEXT)"