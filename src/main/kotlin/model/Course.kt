package model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SizedCollection
import tables.CourseStudent
import tables.CourseTutor

class Course(id: EntityID<Int>) : IntEntity(id){
    companion object : IntEntityClass<Course>(tables.Courses)

    var name by tables.Courses.name

    var students by Student via CourseStudent
    var tutors by Tutor via CourseTutor

    fun addTutorByName(name: String, post: String) {
        val tutor = Tutor.all().find {
            it.name == name &&
                    it.post == post
        }?:return

        this.tutors = SizedCollection(tutors + listOf(tutor))
    }

    fun addStudentByName(name: String, group: String) {
        val student = Student.all().find {
            it.name == name &&
                    it.group == group
        }?:return

        this.students = SizedCollection(students + listOf(student))
    }
}