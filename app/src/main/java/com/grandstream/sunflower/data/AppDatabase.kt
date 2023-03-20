package com.grandstream.sunflower.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.grandstream.sunflower.entity.Plant
import com.grandstream.sunflower.utils.DATABASE_NAME

@Database(entities = [Plant::class], version = 1, exportSchema = true/*, autoMigrations = [
    AutoMigration(from = 1, to = 2),
    AutoMigration(from = 2, to = 3, spec = AppDatabase.DeleteHotColumn::class),
    AutoMigration(from = 3, to = 4),
    AutoMigration(from = 4, to = 5, spec = AppDatabase.DeleteAaColumn::class),
    AutoMigration(from = 5, to = 6),
    AutoMigration(from = 6, to = 7),
]*/)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
//                .allowMainThreadQueries()
                .build()
        }
    }

    @DeleteColumn(tableName = "plants",columnName = "hot")
    class DeleteHotColumn: AutoMigrationSpec {}

    @DeleteColumn(tableName = "plants",columnName = "aa")
    class DeleteAaColumn: AutoMigrationSpec {}
}