package com.eroglu.newsapp.database

import androidx.room.TypeConverter
import com.eroglu.newsapp.models.Source

class Converters {

    @TypeConverter // @TypeConverter anotasyonu, bu fonksiyonun bir tür dönüştürücü olduğunu belirtir.
    fun fromSource(source: Source): String { // Bu dönüştürücü, Source tipinden bir nesneyi String tipine dönüştürür.
        return source.name // fromSource fonksiyonu, bir Source nesnesini alır ve bu nesnenin
        // name özelliğini döndürür. Bu, Source nesnesini temsil eden bir String haline getirir.
    }

    @TypeConverter
    fun toSource(name: String): Source { // Bu dönüştürücü, String tipinden bir değeri Source tipine dönüştürür.
        return Source(name,name) // toSource fonksiyonu, bir String değer alır ve bu değeri kullanarak yeni bir
        // Source nesnesi oluşturur. Bu durumda, Source nesnesinin id ve name özellikleri aynı değeri alır.
    }
}