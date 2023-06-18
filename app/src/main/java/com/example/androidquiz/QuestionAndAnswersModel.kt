package com.example.androidquiz

data class QuestionAndAnswersModel(val question: String, val answers: Array<String>) : java.io.Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as QuestionAndAnswersModel

        if (question != other.question) return false
        if (!answers.contentEquals(other.answers)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = question.hashCode()
        result = 31 * result + answers.contentHashCode()
        return result
    }
}
