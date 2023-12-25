package cinema

data class Session(val id: Int, val movieId: Int, var startTime: String, var availableSeats: MutableList<Int>)
