package com.eroglu.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Ignore
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.eroglu.newsapp.models.Article
import javax.annotation.Nullable

@Database( // @Database anotasyonu, bu sınıfın bir Room veritabanı sınıfı olduğunu belirtir.
    entities = [Article::class], // entities parametresi, bu veritabanında bulunacak varlık sınıflarını belirtir.
    version = 2 // version parametresi, veritabanının sürüm numarasını belirtir.
)

@TypeConverters(Converters::class)

abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao // DAO (Data Access Object)

    companion object {
        @Volatile // @Volatile , değişkenin diğer thread'lerle güvenli bir şekilde paylaşılmasını sağlar.
        //private var instance: ArticleDatabase = null //instance değişkeni, bu sınıfın tek bir örneğini saklamak için kullanılır.
        private var instance: ArticleDatabase? = null //instance değişkeni, bu sınıfın tek bir örneğini saklamak için kullanılır.
        private val LOCK = Any() // LOCK değişkeni, aynı anda sadece bir thread'in bu bölüme erişmesini sağlar.

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }
        // invoke metodu, bu sınıfın bir örneğini oluştururken kullanılır.
        // Eğer instance null ise veya bir önceki işlem henüz tamamlanmamışsa, senkronize edilmiş bir blok içinde
        // createDatabase fonksiyonunu çağırarak veritabanını oluşturur ve instance değişkenine atar.


        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_database.db"
            ).fallbackToDestructiveMigration()
                .build()

    }

}