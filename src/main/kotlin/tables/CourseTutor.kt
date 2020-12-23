package tables

import org.jetbrains.exposed.dao.id.IntIdTable

object CourseTutor: IntIdTable("CourseTutor")  {
    val course = reference("Course", Courses)
    val tutor = reference("Tutor", Tutors)

    override val primaryKey =
        PrimaryKey(course, tutor, name = "PK_COURSE_TUTOR_ID")
}