# Задание
Разработайте программу по диаграммам UML, спроектированных в предыдущей работе. Можно реализовывать не все функции системы (достаточно реализовать 3 прецедента).
## Ход работы:
1. На основании задания разработан код рейтинга, его диаграмма и тест к нему. На рисунке 1 изображена диаграмма рейтинга, здесь преподаватель берет данные из курса, после чего с помощью функции CountRating() рассчитывает рейтинг на основе имеющегося, после чего добавляет его в итоговый рейтинг. Ниже представлены диаграмма, код и тест: <br>
![тут должен быть код](https://sun9-24.userapi.com/Wi1WAN7DPWjuFGvkFvvk_wKui8PNWvPZ0pn_KA/-a49p53I72w.jpg)
```
//Код 
    fun CountRating(students: ArrayList<Student>, tasks: ArrayList<Task>) {
        var sum = 0.0
        for (i in 0 until students.size) {
            tasks.forEach { task ->
                val value = task.grades.maxBy { it.value }?.value
                val grade = if (value != null) value else 0
                sum += weights.getValue(task.name) * (grade.toDouble() / task.maxValue.toDouble())
                ratings[students[i].name] = sum
            }
        }
    }
}
 // Тест
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
```
2. Далее был разработан прецендент setTask, в котором добавляется новое задание в курс. На диаграмме видно, что сначала мы обращаемся к коллекции курсов (к конкретному курсу в коллекции), затем заполняем все необходимые переменные для объекта класса Task, после этого через класс Course в коллекцию заданий добавляется новое задание. Также если через функцию поиска если название нового задания совпадает с уже имеющимся, то выходит сообщение о том, что подобное задание уже сущетсвует. Ниже представлены диаграмма, код и тест: <br>
![тут должен быть код](https://sun9-73.userapi.com/m3Stfb3EPFg2RkZfF5qAkA0hTHmExRgXAwpT8A/CS51hbzwkYs.jpg)
```
//Код 
   fun setTask(task: Task) {
        val newTask = tasks.find { it.name == task.name }
        if(newTask != null) {
            print("Such task already exists")
        }
        else
            tasks += task
    }
 // Тест
 @Test
    fun setTaskTest() {
        val phis = courses["Phis"] ?: fail()
        phis.setTask(Task("LabWork",Type("LabWork", "LW"),"test task",7))
        assertEquals(
            true,
                phis.tasks.find {it.name == "LabWork"} != null
        )
    }
```
3. Далее был реализован прецендент addStudentByName, в котором мы добавляем студента по имени. Ниже представлены диаграмма, код и тест: <br>
![тут должен быть код](https://sun9-6.userapi.com/SA3rpbX1HQuxFAGJWfSARw5waEq5SFflycl0cw/UlBYg6hzWyA.jpg)
```
//Код 
   fun  addStudentByName(name: String) {
        persons[name]?.let {
            if (it is Student)
                students.add(it)
        }
    }
 // Тест
 @Test
   courses["Math"]?.run {
            addTutorByName("Sheldon")
            addStudentByName("Howard")
            addStudentByName("Penny")
```
4. Ниже представлено выполнение теста
