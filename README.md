# Разработка программы по UML диаграммам
# Задание
Разработайте программу по диаграммам UML, спроектированных в предыдущей работе. Можно реализовывать не все функции системы (достаточно реализовать 3 прецедента).
## Ход работы:
1. На основании задания разработан код рейтинга, его диаграмма и тест к нему. На рисунке 1 изображена диаграмма рейтинга, здесь преподаватель берет данные из курса, после чего с помощью функции CountRating() рассчитывает рейтинг на основе имеющегося, после чего добавляет его в итоговый рейтинг. Ниже представлены диаграмма, код и тест: <br>
![тут должен быть код](https://sun9-24.userapi.com/Wi1WAN7DPWjuFGvkFvvk_wKui8PNWvPZ0pn_KA/-a49p53I72w.jpg)
```
//Код 
    fun сalculateRating(students: ArrayList<Student>, tasks: ArrayList<Task>) {
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
	fun сalculateRatingTest(){

	val math = courses["Math"] ?: fail()

	math.setGrade("Intro", "Howard", 3)
	math.setGrade("UML", "Howard", 3)
	math.setGrade("Uml lab", "Howard", 3)

	math.setGrade("Intro", "Penny", 4)
	math.setGrade("UML", "Penny", 4)
	math.setGrade("Uml lab", "Penny", 4)

	val weights = mapOf(
	math.tasks[0].name to 0.4,
	math.tasks[1].name to 0.5,
	math.tasks[2].name to 0.6
	)
	val newRating = Rating(math.name,weights)
	newRating.сalculateRating(math.students, math.tasks)
	assertEquals(
	2, newRating.ratings.size)
	}
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
	val bio = courses["Bio"] ?: fail()
	bio.setTask(Task("Practise",Type("Practise", "Pr"),"test task",6))
	assertEquals(
		true,
	bio.tasks.find {it.name == "Practise"} != null
		)
}
```
3. Далее был реализован прецендент addStudentByName, в котором мы добавляем студента по имени. Ниже представлены диаграмма, код и тест: <br>
![тут д](https://sun9-6.userapi.com/SA3rpbX1HQuxFAGJWfSARw5waEq5SFflycl0cw/UlBYg6hzWyA.jpg)
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

4. Ниже представлено выполнение тестов : <br>
![тут апкмыкуд](https://sun9-6.userapi.com/w6p3zffz7JnRnea7zR_9-ZaM0N_VWZ61UQlgIA/5-VQNiR4_04.jpg)
![тут апсуккмыкуд](https://sun9-20.userapi.com/KJNCAFKTPIehVQQYwdoQWXfTVFxRx9e4Ajqpnw/O8nEXsb5QHM.jpg)
