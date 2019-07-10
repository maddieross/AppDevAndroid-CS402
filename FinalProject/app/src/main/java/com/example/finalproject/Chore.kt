package com.example.finalproject
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.io.Serializable
import java.util.*

@Entity
data class Chore constructor(@Id var id:Long =0,
                             val title:String,
                             val description:String,
                             val dateAdded:Date,
                             val dueDate: String,
                             val assignedTo:String):Serializable