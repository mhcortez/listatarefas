package com.example.listatarefas.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.listatarefas.AdicionarTarefaActivity
import com.example.listatarefas.model.Tarefa

class TarefaDAO(context: Context) : ITarefaDAO {

    private val escrita = DatabaseHelper(context).writableDatabase
    private val leitura = DatabaseHelper(context).readableDatabase

    override fun salvar(tarefa: Tarefa): Boolean {
        val conteudos = ContentValues()
        conteudos.put("${DatabaseHelper.COLUNA_DESCRICAO}", tarefa.descricao)

        try {
            escrita.insert(
                DatabaseHelper.NOME_TEBELA_TAREFAS,
                null,
                conteudos
            )
            Log.i("info_db", "sucesso Ao Criar tarefa")
        }catch (e: Exception){
            e.printStackTrace()
            Log.i("info_db", "Erro ao Salvar Tare fa")
            return false
        }
        return true
    }

    override fun atualizar(tarefa: Tarefa): Boolean {
        TODO("Not yet implemented")
    }

    override fun remover(idTarefa: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun listar(): List<Tarefa> {
        val listaTarefas = mutableListOf<Tarefa>()

        val sql = "SELECT ${DatabaseHelper.COLUNA_ID}, ${DatabaseHelper.COLUNA_DESCRICAO}, "+
                "strftime('%d/%m/%Y', ${DatabaseHelper.COLUNA_DATA_CADASTRO}) as ${DatabaseHelper.COLUNA_DATA_CADASTRO}"+
                "FROM ${DatabaseHelper.NOME_TEBELA_TAREFAS} "

        val cursor = leitura.rawQuery(sql, null)
        val indiceId = cursor.getColumnIndex(DatabaseHelper.COLUNA_ID)
        val indiceDescricao = cursor.getColumnIndex(DatabaseHelper.COLUNA_DESCRICAO)
        val indiceDataCadastro = cursor.getColumnIndex(DatabaseHelper.COLUNA_DATA_CADASTRO)

        while ( cursor.moveToNext()){
            val idtarefa = cursor.getInt(indiceId)
            val descricao = cursor.getString(indiceDescricao)
            val dataCadastro = cursor.getString(indiceDataCadastro)

            listaTarefas.add(
                Tarefa(idtarefa, descricao, dataCadastro))
        }
        return listaTarefas
    }

}