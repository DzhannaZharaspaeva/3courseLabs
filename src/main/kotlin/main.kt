import model.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import tables.*
import tables.Grades
import tables.Ratings
import java.time.LocalDate

fun main() {
    Database.connect(
            "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            driver = "org.h2.Driver"
    )

    transaction {
        SchemaUtils.create(Admins, Courses, Grades, Ratings, Students, Tasks, Tutors, Types, CourseTutor, CourseStudent)

        init()

        SchemaUtils.drop(Admins, Courses, Grades, Ratings, Students, Tasks, Tutors, Types, CourseTutor, CourseStudent)
    }
}

fun init() {
    listOf(
            "Math",
            "Phis",
            "History"
    ).forEach {
        Course.new{
            this.name = it
        }
    }
    mapOf(
            "Howard" to "Footprint on the Moon",
            "David" to "Footprint on the Moon",
            "Penny" to "Waitress"
    ).forEach {
        Student.new {
            name = it.key
            group = it.value
        }
    }
    mapOf(
            "Sheldon" to "Professor",
    "Leonard" to "Professor"
    ).forEach {
        Tutor.new {
            name = it.key
            post = it.value
        }
    }
    mapOf(
            "Dzhanna" to "Adm"
    ).forEach {
        Admin.new {
            name = it.key
        }
    }
    mapOf(
            "Lecture" to "lec",
            "Laboratory" to "lab",
            "Practice" to "pract"
    ).forEach {
        Type.new{
            this.name = it.key
            this.shortName = it.value
        }
    }
    listOf(
            "Laboratory",
            "Practise",
            "Intro",
            "Lecture"
    ).forEach{
        Task.new  {
            this.name = it
            this.type = Type.all().find { it.name == "Laboratory"}?:return@new
            this.maxValue = 5
            this.deadline = LocalDate.now()
            this.description = ""
            this.shortName = ""
            this.course = Course.all().find { it.name == "Math" }?:return@new
        }
    }
    Course.all().forEach {
        it.students = Student.all()
    }
    Course.all().forEach {
        it.tutors = Tutor.all()
    }
    val student1 = Student.all().find { it.name == "Howard" }?:return
    val student2 = Student.all().find { it.name == "David" }?:return
    val task1 = Task.all().find { it.name == "Practise" }?:return
    val task2 = Task.all().find { it.name == "Lecture" }?:return

    Grade.new {
        this.value = 5
        this.date = LocalDate.now()

        this.student = student1
        this.task = task1
    }
    Grade.new {
        this.value = 5
        this.date = LocalDate.now()

        this.student = student2
        this.task = task2
    }
}