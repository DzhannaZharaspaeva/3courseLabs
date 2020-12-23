package tables

import org.jetbrains.exposed.dao.id.IntIdTable

object CourseStudent: IntIdTable("CourseStudent")  {
    val course = reference("Course", Courses)
    val student = reference("Student", Students)

    override val primaryKey =
        PrimaryKey(course, student, name = "PK_COURSE_STUDENT_ID")
}