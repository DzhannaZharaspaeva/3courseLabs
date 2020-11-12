# Задание
Разработайте программу по диаграммам UML, спроектированных в предыдущей работе. Можно реализовывать не все функции системы (достаточно реализовать 3 прецедента).
## Ход работы:
1. На основании задания разработан код рейтинга, его диаграмма и тест к нему. На рисунке 1 изображена диаграмма рейтинга, здесь преподаватель берет данные из курса, после чего с помощью функции CountRating() рассчитывает рейтинг на основе имеющегося, после чего добавляет его в итоговый рейтинг. Ниже представлены диаграмма, код и тест: <br>
![тут должен быть код](https://sun9-24.userapi.com/Wi1WAN7DPWjuFGvkFvvk_wKui8PNWvPZ0pn_KA/-a49p53I72w.jpg)
![тут должен быть](https://sun9-19.userapi.com/BYs7nEViHIzhPi-pJmRWBpNk4s_SC69sK9xkQw/v5njH3TfjLw.jpg)
![тут должен амиапбыть](https://sun9-55.userapi.com/OBuv2iDIEKw2c2-IjrK4OBcNPanVk1UzUWzNEw/PLfdZ1f0mHc.jpg) <br>
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

