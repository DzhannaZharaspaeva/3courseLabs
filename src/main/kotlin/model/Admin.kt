package model

import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Admin(id: EntityID<Int>) : Person(id) {
    companion object : IntEntityClass<Admin>(tables.Admins)

    var name by tables.Admins.name

    fun setCourse(name: String) {
        Course.new {
            this.name = name
        }
    }

    fun setPerson(position: String, name: String, identity: String = "") {
        if (position == "Student")
                Student.new {
                    this.name = name
                    group = identity
                }
        else
            if (position == "Tutor")
                Tutor.new {
                    this.name = name
                    post = identity
                }
        else
                if (position == "Admin")
                    Admin.new {
                    this.name = name
                }
    }
}