package model

import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import tables.CourseTutor
import java.time.LocalDate

class Tutor(id: EntityID<Int>) : Person(id) {
    companion object : IntEntityClass<Tutor>(tables.Tutors)

    var name by tables.Tutors.name
    var post by tables.Tutors.post

    var course by Course via CourseTutor

    fun setTask(nameCourse: String,
                name: String, type: String, maxValue: Int = 5,
                description: String = "", shortName: String = "",
                deadline: LocalDate = LocalDate.now()){
        Task.new  {
            this.name = name
            this.maxValue = maxValue
            this.deadline = deadline
            this.description = description
            this.type = Type.all().find { it.name == type }?:return@new
            this.shortName = shortName
            this.course = Course.all().find { it.name == nameCourse }?:return@new
        }
    }

    fun setGrade(nameCourse: String, nameTask: String, nameStudent: String,
                 value: Int, date: LocalDate = LocalDate.now()) {
        val course = Course.all().find { it.name == nameCourse }
        val task = Task.all().find { it.course == course && it.name == nameTask }?:return
        val student = Student.all().find { it.name == nameStudent }?:return

        Grade.new {
            this.value = value
            this.date = date
            this.student = student
            this.task = task
        }
    }
}