package model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Task(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Task>(tables.Tasks)

    var name by tables.Tasks.name
    var shortName by tables.Tasks.shortName
    var deadline by tables.Tasks.deadline
    var description by tables.Tasks.description
    var maxValue by tables.Tasks.maxValue

    var type by Type referencedOn tables.Tasks.type
    var course by Course referencedOn tables.Tasks.course
}