# Разработка ORM
# Задание
Переработайте программу из 2 лабораторной работы так, чтобы данные хранились в базе данных. Программа должна использовать фреймворк Kotlin Exposed, реализовывать можно как на уровне DSL, так и на уровне DAO. Можно реализовывать не все функции системы (достаточно реализовать 3 прецедента).
## Ход работы:
1. Используя уровень DAO фреймворка Kotlin Exposed, преобразовала функции второй лабораторной, например функция setTask, таблица и тест к ней.  Преобразовала функцию так, чтобы она работала с нашей базой данных:
```
//таблица 
object Tasks: IntIdTable()  {
    var name = varchar("name", length = 50)
    val shortName = varchar("shortName", length = 50)
    var description = varchar("description", length = 50)
    var maxValue = integer("maxValue")
    var deadline = date("deadline")

    var type = reference("Task", Types)
    var course = reference("Course", Courses)

    override val primaryKey =
        PrimaryKey(id, name = "PK_TASK_ID")
}
//функция
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
 // Тест
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
```
Остальные функции разработаны по аналогии.
В синтаксисе используются такие команды как: 
 - reference используется для создания поля со ссылкой на таблицу или класс
 - transaction - это "контейнер" для создания запроса к Базе Данных
2. Результаты тестов представлены ниже:  <br>
![тут должен быть код](https://sun9-5.userapi.com/impg/I2nYNVufWvsGFf72YsP5cuPaJddEyUcbJR6KgQ/wiKgVw_JVx4.jpg)
