package model

import java.time.LocalDate

class Grade (
    val value: Int,
    val date: LocalDate,
    val student: Student
) {
    fun readGrade(student: Student, grade: Int, date: LocalDate) {

    }
}