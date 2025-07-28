package com.example.listatarefas.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context): SQLiteOpenHelper(
    context,
    NOME_BANCO_DADOS,
    null,
    VERSAO
){
    companion object{
        const val NOME_BANCO_DADOS = "ListaTarefas.db"
        const val VERSAO = 1
        const val NOME_TEBELA_TAREFAS = "tarefas"
        const val COLUNA_ID = "id_tarefa"
        const val COLUNA_DESCRICAO = "descricao"
        const val COLUNA_DATA_CADASTRO = "data_cadastro"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS $NOME_TEBELA_TAREFAS("+
               "$COLUNA_ID INTEGER not NULL PRIMARY KEY AUNTOINCREMENT,"+
               "$COLUNA_DESCRICAO VARCHAR(70)"+
               "$COLUNA_DATA_CADASTRO DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP"+
               ");"
        try {
            db?.execSQL(sql)
            Log.i("BANCO_DADOS", "Tabela criada com sucesso")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("BANCO_DADOS", "Erro ao criar tabela")
        }
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?,
        p1: Int,
        p2: Int
    ) {
        TODO("Not yet implemented")
    }

}