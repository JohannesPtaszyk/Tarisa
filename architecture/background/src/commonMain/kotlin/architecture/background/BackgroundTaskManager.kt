package architecture.background

interface BackgroundTaskManager {
    fun enqueueOneTimeTask(task: String)
    fun enqueuePeriodicTask(task: String, repeatIntervalMillis: Long)
    fun enqueueUniqueTask(task: String)
}