import jobs.ExpenseTracker

object Main extends App {

  val FILE_PATH: String = "expenses/expenses.txt"

  val expenseTrackerJob = new ExpenseTracker(FILE_PATH)

  expenseTrackerJob.run()

}
