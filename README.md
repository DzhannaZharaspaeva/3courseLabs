# Проектирование ORM
# Задание
Разработайте ER диаграмму по диаграмме классов из первой лабораторной работы. В отчете приведите обоснование для выбора способа преобразования каждой ассоциации.
## Ход работы:
Здесь Tutor имеет связь Zero or Many с CourseTutor потому что в курсе могут быть не все преподаватели. Далее CourseTutor имеет такую же связь с Course потому как не все преподаватели не входят в курс, аналогично с CourseStudent и Course. Также Student имеет связь Zero or Many с CourseStudent потому что в курсе могут быть не все студенты. Course имеет связь One or Many с Rating потому как на один курс приходится множество рейтингов. Далее Task имеет связь Zero or Many с Type так как не все типы используются для определения типа заданий. Task имеет связь One or Many с Course потому что в одном курсе может быть множество заданий. Grade имеет связь One or Many с Task и Student, так как одинаковая оценка  может быть выставлена многим студентам за множество заданий: <br>
![тут должен быть код](https://sun9-24.userapi.com/MJDaixyFJi9EoFKiAi-WGSjWQuoaRBgJtm394w/RTY0jT3NSMk.jpg)
 
