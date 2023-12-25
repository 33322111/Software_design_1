package cinema

import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.text.ParseException

class CinemaManager {
    private val movies = mutableListOf<Movie>()
    private val sessions = mutableListOf<Session>()
    private val soldTickets = mutableListOf<Ticket>()
    private val ticketsFilePath = "tickets.csv"
    private val moviesFilePath = "movies.csv"
    private val sessionsFilePath = "sessions.csv"


    init {
        loadMoviesFromCsv("movies.csv")
        loadSessionsFromCsv("sessions.csv")
        if (!File(ticketsFilePath).exists()) {
            File(ticketsFilePath).createNewFile()
        }
    }

    fun loadMoviesFromCsv(filePath: String) {
        try {
            File(filePath).forEachLine { line ->
                val (id, title, duration) = line.split(",")
                movies.add(Movie(id.toInt(), title, duration.toInt()))
            }
        } catch (e: IOException) {
            println("Error loading movies from CSV: ${e.message}")
        }
    }

    fun loadSessionsFromCsv(filePath: String) {
        try {
            File(filePath).forEachLine { line ->
                val (id, movieId, startTime, seats) = line.split(",")
                val availableSeats = seats.split(",").map { it.toInt() }.toMutableList()
                sessions.add(Session(id.toInt(), movieId.toInt(), startTime, availableSeats))
            }
        } catch (e: IOException) {
            println("Error loading sessions from CSV: ${e.message}")
        }
    }

    fun showAvailableSeats(sessionId: Int) {
        val session = sessions.find { it.id == sessionId }
        if (session != null) {
            val availableSeats = session.availableSeats
            val soldSeats = soldTickets.filter { it.sessionId == sessionId }.map { it.seatNumber }

            println("Available seats for session $sessionId:")
            println("Available: $availableSeats")
            println("Sold: $soldSeats")
        } else {
            println("Session not found.")
        }
    }

    fun sellTicket(sessionId: Int, seatNumber: Int) {
        val session = sessions.find { it.id == sessionId }
        if (session != null && session.availableSeats.contains(seatNumber)) {
            val ticket = Ticket(sessionId, seatNumber)
            session.availableSeats.remove(seatNumber)
            soldTickets.add(ticket)
            updateTicketsCsv()
            println("Ticket sold for session $sessionId, seat $seatNumber.")
        } else {
            println("Invalid session, seat number, or the seat is already sold.")
        }
    }

    fun returnTicket(sessionId: Int, seatNumber: Int) {
        val ticket = soldTickets.find { it.sessionId == sessionId && it.seatNumber == seatNumber }
        if (ticket != null) {
            val session = sessions.find { it.id == sessionId }
            session?.availableSeats?.add(seatNumber)
            soldTickets.remove(ticket)
            updateTicketsCsv()
            println("Ticket returned for session $sessionId, seat $seatNumber.")
        } else {
            println("Invalid session or seat.")
        }
    }


    fun addMovie(id: Int, title: String, duration: Int) {
        if (id <= 0 || duration <= 0) {
            println("Invalid ID or duration. Please enter positive numeric values.")
            return
        }

        val existingMovie = movies.find { it.id == id }
        if (existingMovie == null) {
            val newMovie = Movie(id, title, duration)
            movies.add(newMovie)
            updateMoviesCsv()
            println("New movie added: $title (ID: $id)")
        } else {
            println("Movie with ID $id already exists. Please choose a different ID.")
        }
    }

    fun editSession(sessionId: Int, newStartTime: String, newNumSeats: Int) {
        val sessionToEdit = sessions.find { it.id == sessionId }

        if (sessionToEdit != null) {
            try {
                val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")
                val currentTime = Date()

                val newSessionTime = dateFormat.parse(newStartTime)

                if (newSessionTime.before(currentTime)) {
                    println("Error: Cannot reschedule a session for the past time.")
                    return
                }

                if (newNumSeats < 1) {
                    println("Error: Number of seats must be greater than 0.")
                    return
                }

                sessionToEdit.startTime = newStartTime
                sessionToEdit.availableSeats.clear()
                sessionToEdit.availableSeats.addAll(1..newNumSeats)

                updateSessionsCsv()

                println("Session $sessionId updated successfully.")
            } catch (e: ParseException) {
                println("Error: Invalid date/time format. Please use the format dd-MM-yyyy HH:mm.")
            }
        } else {
            println("Error: Session with ID $sessionId not found.")
        }
    }

    fun saveDataToCsv() {
        saveToCsv("movies.csv", movies)
        saveToCsv("sessions.csv", sessions)
    }

    fun editMovie(movieId: Int, title: String, duration: Int) {
        val movie = movies.find { it.id == movieId }
        if (movie != null) {
            movie.title = title
            movie.duration = duration
            println("Movie details updated.")
        } else {
            println("Movie not found.")
        }
    }

    fun markAttendance(sessionId: Int, seatNumber: Int) {
        val session = sessions.find { it.id == sessionId }
        if (session != null && session.availableSeats.contains(seatNumber)) {
            session.availableSeats.remove(seatNumber)
            println("Visitor attended session $sessionId, seat $seatNumber.")
        } else {
            println("Invalid session or seat.")
        }
    }

    fun addSession(movieId: Int, startTime: String, numSeats: Int) {
        try {
            val file = File(sessionsFilePath)
            if (!file.exists()) {
                file.createNewFile()
            }

            val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")
            val currentTime = Date()

            try {
                val sessionTime = dateFormat.parse(startTime)
                if (sessionTime.before(currentTime)) {
                    println("Error: Cannot schedule a session for the past time.")
                    return
                }
            } catch (e: ParseException) {
                println("Error: Invalid date/time format. Please use the format dd-MM-yyyy HH:mm.")
                return
            }

            val newSessionId = sessions.size + 1
            val newSession = Session(newSessionId, movieId, startTime, (1..numSeats).toMutableList())
            sessions.add(newSession)
            updateSessionsCsv()
            println("New session added with ID $newSessionId.")
        } catch (e: Exception) {
            println("Error adding session: ${e.message}")
        }
    }

    private fun updateTicketsCsv() {
        try {
            FileWriter(ticketsFilePath, false).use { writer ->
                soldTickets.forEach { ticket ->
                    writer.append("${ticket.sessionId},${ticket.seatNumber}\n")
                }
            }
        } catch (e: IOException) {
            println("Error updating tickets CSV: ${e.message}")
        }
    }

    private fun updateSessionsCsv() {
        try {
            FileWriter(sessionsFilePath, false).use { writer ->
                sessions.forEach { session ->
                    writer.append(
                        "${session.id},${session.movieId},${session.startTime}," +
                                "${session.availableSeats.joinToString(",")}\n"
                    )
                }
                writer.flush()
            }
        } catch (e: IOException) {
            println("Error updating sessions CSV: ${e.message}")
        }
    }

    private fun updateMoviesCsv() {
        try {
            FileWriter(moviesFilePath, false).use { writer ->
                movies.forEach { movie ->
                    writer.append("${movie.id},${movie.title},${movie.duration}\n")
                }
            }
        } catch (e: IOException) {
            println("Error updating movies CSV: ${e.message}")
        }
    }

    private fun <T> saveToCsv(filePath: String, data: List<T>) {
        try {
            File(filePath).bufferedWriter().use { writer ->
                data.forEach {
                    when (it) {
                        is Movie -> writer.write("${it.id},${it.title},${it.duration}\n")
                        is Session -> writer.write(
                            "${it.id},${it.movieId},${it.startTime},${
                                it.availableSeats.joinToString(
                                    ","
                                )
                            }\n"
                        )
                    }
                }
            }
        } catch (e: IOException) {
            println("Error saving data to CSV: ${e.message}")
        }
    }
}