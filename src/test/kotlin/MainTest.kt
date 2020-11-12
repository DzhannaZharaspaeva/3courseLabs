import model.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainTest {

    @BeforeAll
    fun init() {
        mapOf(
            "Lecture" to "Lec",
            "Laboratory" to "Lab",
            "Test" to "Tst"
        ).forEach {
            taskTypes.add(Type(it.key, it.value))
        }
        mapOf(
            "Sheldon" to "Professor",
            "Leonard" to "Professor"
        ).forEach {
            persons.add(Tutor(it.key, it.value))
        }
        mapOf(
            "Howard" to "Footprint on the Moon",
            "Raj" to "Footprint on the Moon",
            "Penny" to "Waitress"
        ).forEach {
            persons.add(Student(it.key, it.value))
        }
        mapOf(
                "Bio" to Rating("Bio" , mapOf("Intro" to 0.5)),
                "Math" to Rating("Math" , mapOf("Intro" to 0.5)),
                "Phis" to Rating("Phis" , mapOf("UML" to 0.6)),
                "History" to Rating("History" , mapOf("Uml lab" to 0.7))
        ).forEach {
            courses.add(Course(it.key, it.value))
        }
        courses["Math"]?.run {
            addTutorByName("Sheldon")
            addStudentByName("Howard")
            addStudentByName("Penny")
            tasks.add(Task("Intro", taskTypes["Lecture"]!!))
            tasks.add(Task("UML", taskTypes["Lecture"]!!))
            tasks.add(Task("Uml lab", taskTypes["Laboratory"]!!, maxValue = 5))
        }
    }

    @Test
    fun initTest() {
        assertEquals(2, persons.all().filterIsInstance<Tutor>().size)
        assertEquals(3, persons.all().filterIsInstance<Student>().size)
        assertEquals(2, persons.all().filter {
            when (it) {
                is Student -> it.group == "Footprint on the Moon"
                else -> false
            }
        }.size)
        assertEquals(2, courses["Math"]?.students?.size)
        assertEquals(1, courses["Math"]?.tutors?.size)
    }

    @Test
    fun setGradeTest() {
        val math = courses["Math"] ?: fail()
        math.setGrade("UML", "Howard", 1)
        assertEquals(
            1,
            math.tasks.find { it.name == "UML" }?.grades?.size
        )
    }

    @Test
    fun setTaskTest() {
        val phis = courses["Phis"] ?: fail()
        phis.setTask(Task("LabWork",Type("LabWork", "LW"),"test task",7))
        assertEquals(
            true,
                phis.tasks.find {it.name == "LabWork"} != null
        )
    }

    @Test
    fun studentGradesTest() {
        val math = courses["Math"] ?: fail()
        math.setGrade("Intro", "Penny", 1)
        math.setGrade("Uml lab", "Penny", 3)
        math.setGrade("Uml lab", "Penny", 4)
        val grades = math.studentGrades("Penny")
        assertEquals(1, grades["Intro"])
        assertEquals(4, grades["Uml lab"])
    }

    @Test
    fun СountRatingTest(){

        val bio = courses["Bio"] ?: fail()

        bio.setGrade("Intro", "Howard", 3)
        bio.setGrade("UML", "Howard", 3)
        bio.setGrade("Uml lab", "Howard", 3)

        bio.setGrade("Intro", "Penny", 4)
        bio.setGrade("UML", "Penny", 4)
        bio.setGrade("Uml lab", "Penny", 4)

        val weights = mapOf(
                bio.tasks[0].name to 0.4,
                bio.tasks[1].name to 0.5,
                bio.tasks[2].name to 0.6
        )
        val newRating = Rating(bio.name,weights)
        newRating.СountRating(bio.students, bio.tasks)
        assertEquals(
            2, newRating.ratings.size
        )
    }
}