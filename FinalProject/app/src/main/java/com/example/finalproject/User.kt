package com.example.finalproject

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import java.io.Serializable

@Entity
data class User constructor(@Id var id:Long =0,
                            val houseCode:Int,
                            val email:String,
                            val displayName:String,
                            val password:String):Serializable