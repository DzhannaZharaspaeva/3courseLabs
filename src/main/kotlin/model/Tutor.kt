package model

import model.Person
import java.time.LocalDate

class Tutor(
    name: String,
    val post: String
) : Person(name) {

    val students = ArrayList<Student>()
    val tasks = ArrayList<Task>()

   /* fun setGrade(taskName: String, studentName: String, value: Int, date: LocalDate = LocalDate.now()) {
        val task = tasks.find { it.name == taskName } ?: return
        val student = students.find { it.name == studentName } ?: return
        if (value !in 0..task.maxValue) return
        val grade = Grade(value, date, student)
        task.grades += grade
    }*/

}