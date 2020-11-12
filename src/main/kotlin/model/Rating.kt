package model

class Rating (
    val course: String,
    val weights: Map<String, Double>
) {
    val ratings = mutableMapOf<String, Double>()

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