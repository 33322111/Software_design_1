package cinema

fun main() {
    val cinemaManager = CinemaManager()

    while (true) {
        println("1. Show available seats")
        println("2. Sell ticket")
        println("3. Return ticket")
        println("4. Edit movie")
        println("5. Mark attendance")
        println("6. Add session")
        println("7. Add movie")
        println("8. Edit session")
        println("9. Exit")

        print("Enter your choice: ")
        val choice = readLine()

        if (choice != null && choice.toIntOrNull() != null) {
            when (choice.toInt()) {
                1 -> {
                    print("Enter session ID: ")
                    val sessionId = readLine()?.toIntOrNull()
                    if (sessionId != null) {
                        cinemaManager.showAvailableSeats(sessionId)
                    } else {
                        println("Invalid input. Please enter a valid session ID.")
                    }
                }

                2 -> {
                    print("Enter session ID: ")
                    val sessionId = readLine()?.toIntOrNull()
                    print("Enter seat number: ")
                    val seatNumber = readLine()?.toIntOrNull()
                    if (sessionId != null && seatNumber != null) {
                        cinemaManager.sellTicket(sessionId, seatNumber)
                    } else {
                        println("Invalid input. Please enter valid session ID and seat number.")
                    }
                }

                3 -> {
                    print("Enter session ID: ")
                    val sessionId = readLine()?.toIntOrNull()
                    print("Enter seat number: ")
                    val seatNumber = readLine()?.toIntOrNull()
                    if (sessionId != null && seatNumber != null) {
                        cinemaManager.returnTicket(sessionId, seatNumber)
                    } else {
                        println("Invalid input. Please enter valid session ID and seat number.")
                    }
                }

                4 -> {
                    print("Enter movie ID: ")
                    val movieId = readLine()?.toIntOrNull()
                    print("Enter new title: ")
                    val title = readLine()
                    print("Enter new duration: ")
                    val duration = readLine()?.toIntOrNull()
                    if (movieId != null && title != null && duration != null) {
                        cinemaManager.editMovie(movieId, title, duration)
                    } else {
                        println("Invalid input. Please enter valid movie ID, title, and duration.")
                    }
                }

                5 -> {
                    print("Enter session ID: ")
                    val sessionId = readLine()?.toIntOrNull()
                    print("Enter seat number: ")
                    val seatNumber = readLine()?.toIntOrNull()
                    if (sessionId != null && seatNumber != null) {
                        cinemaManager.markAttendance(sessionId, seatNumber)
                    } else {
                        println("Invalid input. Please enter valid session ID and seat number.")
                    }
                }

                6 -> {
                    print("Enter movie ID for the new session: ")
                    val movieId = readLine()?.toIntOrNull()
                    print("Enter start time (dd-MM-yyyy HH:mm) for the new session: ")
                    val startTime = readLine()
                    print("Enter the number of seats for the new session: ")
                    val numSeats = readLine()?.toIntOrNull()
                    if (movieId != null && startTime != null && numSeats != null) {
                        cinemaManager.addSession(movieId, startTime, numSeats)
                    } else {
                        println("Invalid input. Please enter valid movie ID, start time, and number of seats.")
                    }
                }

                7 -> {
                    print("Enter the ID of the new movie: ")
                    val newId = readLine()?.toIntOrNull()
                    if (newId != null && newId > 0) {
                        print("Enter the title of the new movie: ")
                        val newTitle = readLine() ?: continue
                        print("Enter the duration of the new movie: ")
                        val newDuration = readLine()?.toIntOrNull()
                        if (newDuration != null && newDuration > 0) {
                            cinemaManager.addMovie(newId, newTitle, newDuration)
                        } else {
                            println("Invalid duration. Please enter a positive numeric value.")
                        }
                    } else {
                        println("Invalid ID. Please enter a positive numeric value.")
                    }
                }

                8 -> {
                    print("Enter session ID to edit: ")
                    val sessionIdToEdit = readLine()?.toIntOrNull() ?: continue
                    print("Enter new start time for the session (dd-MM-yyyy HH:mm): ")
                    val newStartTime = readLine() ?: continue
                    print("Enter new number of seats for the session: ")
                    val newNumSeats = readLine()?.toIntOrNull() ?: continue

                    cinemaManager.editSession(sessionIdToEdit, newStartTime, newNumSeats)
                }

                9 -> {
                    cinemaManager.saveDataToCsv()
                    println("Exiting...")
                    return
                }

                else -> println("Invalid choice. Please try again.")
            }
        } else {
            println("Invalid input. Please enter a valid numeric choice.")
        }
    }
}
