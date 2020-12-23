package tables

import model.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.Test
import kotlin.test.assertEquals

class MainTest {

    @Test
    fun setCourseTest() {
        Database.connect(
                "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
                driver = "org.h2.Driver"
        )
        transaction {
            SchemaUtils.create(Admins, Courses, Grades, Ratings, Students, Tasks, Tutors, Types)
            val admin = Admin.new {
                name = "Shon"
            }
            admin.setCourse("Math")

            assertEquals("Math", Course.all().find { it.name == "Math" }
                    ?.name)

            SchemaUtils.drop(Admins, Courses, Grades, Ratings, Students, Tasks, Tutors, Types)
        }
    }

    @Test
    fun setPersonTest() {
        Database.connect(
                "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
                driver = "org.h2.Driver"
        )
        transaction {
            SchemaUtils.create(Admins, Courses, Grades, Ratings, Students, Tasks, Tutors, Types)
            val admin = Admin.new { name = "Dzhanna" }
            admin.setPerson("Student", "David", "28z")
            admin.setPerson("Tutor", "Sheldon", "Professor")
            admin.setPerson("Admin", "Shon")

            assertEquals(Student.all().find { it.name == "David" }
                    ?.name, "David")
            assertEquals(Tutor.all().find { it.name == "Sheldon" }
                    ?.name, "Sheldon")
            assertEquals("Shon", Admin.all().find { it.name == "Shon" }
                    ?.name)

            SchemaUtils.drop(Admins, Courses, Grades, Ratings, Students, Tasks, Tutors, Types)
        }
    }

    @Test
    fun addTutorByNameTest() {
        Database.connect(
                "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
                driver = "org.h2.Driver"
        )

        transaction {
            SchemaUtils.create(Admins, Courses, Grades, Ratings, Students, Tasks, Tutors, Types, CourseTutor, CourseStudent)

            val course = Course.new { name = "Math" }
            Tutor.new {
                name = "Dzhanna"
                post = "28z"
            }

            course.addTutorByName("Dzhanna", "28z")
            assertEquals("Dzhanna", course.tutors.find { it.name == "Dzhanna" }
                    ?.name)

            SchemaUtils.drop(Admins, Courses, Grades, Ratings, Students, Tasks, Tutors, Types, CourseTutor, CourseStudent)
        }
    }

    @Test
    fun addStudentByNameTest() {
        Database.connect(
                "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
                driver = "org.h2.Driver"
        )

        transaction {
            SchemaUtils.create(Admins, Courses, Grades, Ratings, Students, Tasks, Tutors, Types, CourseTutor, CourseStudent)

            val course = Course.new { name = "Math" }
            Student.new {
                name = "Dzhanna"
                group = "28z"
            }

            course.addStudentByName("Dzhanna", "28z")
            assertEquals("Dzhanna", course.students.find { it.name == "Dzhanna" }
                    ?.name)

            SchemaUtils.drop(Admins, Courses, Grades, Ratings, Students, Tasks, Tutors, Types, CourseTutor, CourseStudent)
        }
    }

    @Test
    fun setTaskTest() {
        Database.connect(
                "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
                driver = "org.h2.Driver"
        )

        transaction {
            SchemaUtils.create(Admins, Courses, Grades, Ratings, Students, Tasks, Tutors, Types, CourseTutor, CourseStudent)

            val tutor = Tutor.new {
                name = "Sheldon"
                post = "Professor"
            }
            Course.new { name = "Math" }
            Type.new {
                this.name = "Lecture"
                this.shortName = "lect"
            }

            tutor.setTask("Math", "Last", "Lecture")
            assertEquals(1, Task.all().count())

            SchemaUtils.drop(Admins, Courses, Grades, Ratings, Students, Tasks, Tutors, Types, CourseTutor, CourseStudent)
        }
    }

    @Test
    fun setGradeTest() {
        Database.connect(
                "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
                driver = "org.h2.Driver"
        )

        transaction {
            SchemaUtils.create(Admins, Courses, Grades, Ratings, Students, Tasks, Tutors, Types, CourseTutor, CourseStudent)

            val tutor = Tutor.new { name = "Sheldon"
                post = "Professor" }
            Course.new { name = "Math" }
            Type.new {
                this.name = "Lecture"
                this.shortName = "lect"
            }
            Student.new {
                name = "Lena"
                group = "28m"
            }
            tutor.setTask("Math", "Intro", "Lecture")

            tutor.setGrade("Math", "Intro", "Lena", 3)
            tutor.setGrade("Math", "Intro", "Lena", 5)
            assertEquals(2, Grade.all().count())
            SchemaUtils.drop(Admins, Courses, Grades, Ratings, Students, Tasks, Tutors, Types, CourseTutor, CourseStudent)
        }
    }
}