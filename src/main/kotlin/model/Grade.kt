package model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import tables.Grades

class Grade(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Grade>(tables.Grades)

    var value by Grades.value
    var date by Grades.date

    var student by Student referencedOn Grades.student
    var task by Task referencedOn Grades.task
}