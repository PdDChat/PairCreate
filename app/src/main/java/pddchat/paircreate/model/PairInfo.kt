package pddchat.paircreate.model

data class PairInfo (
    val pairNo: Int, // TODO 不要な気がするが、一旦つけている
    val pairName1: String,
    val pairName2: String? = null
)
