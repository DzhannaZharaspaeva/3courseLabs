package model

import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import tables.CourseStudent


class Student (id: EntityID<Int>) : Person(id) {
    companion object : IntEntityClass<Student>(tables.Students)

    var name by tables.Students.name
    var group by tables.Students.group

    var course by Course via CourseStudent
}