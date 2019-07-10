package com.example.finalproject

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class HouseCode constructor(@Id var id:Long = 0) {
}