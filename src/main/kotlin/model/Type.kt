package model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Type (id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Type>(tables.Types)

    var name by tables.Types.name
    var shortName by tables.Types.shortName
}