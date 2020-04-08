package pddchat.paircreate.model

data class Pair (
    val teamNumber: Int,
    val developer: List<Developer>
)

data class Developer (
    val name: String
)
