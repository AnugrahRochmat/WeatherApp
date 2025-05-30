package anugrah.rochmat.weatherapp.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    /**
     * Convert Unix timestamp (UTC) to local time in HH:mm format
     */
    fun formatTimeFromUnix(unixTimestamp: Long): String {
        return try {
            val date = Date(unixTimestamp * 1000L) // Convert seconds to milliseconds
            val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
            formatter.timeZone = TimeZone.getDefault() // Convert to local timezone
            formatter.format(date)
        } catch (e: Exception) {
            "00:00" // Default fallback
        }
    }

    /**
     * Convert Unix timestamp to date string (e.g., "Mon, Dec 02")
     */
    fun formatDateFromUnix(unixTimestamp: Long): String {
        return try {
            val date = Date(unixTimestamp * 1000L)
            val formatter = SimpleDateFormat("EEEE", Locale.getDefault())
            formatter.timeZone = TimeZone.getDefault()
            formatter.format(date)
        } catch (e: Exception) {
            "Today" // Default fallback
        }
    }

    /**
     * Convert Unix timestamp to full date and time
     */
    fun formatFullDateTime(unixTimestamp: Long): String {
        return try {
            val date = Date(unixTimestamp * 1000L)
            val formatter = SimpleDateFormat("EEE, MMM dd - HH:mm", Locale.getDefault())
            formatter.timeZone = TimeZone.getDefault()
            formatter.format(date)
        } catch (e: Exception) {
            "Unknown time"
        }
    }

    /**
     * Check if timestamp is today
     */
    fun isToday(unixTimestamp: Long): Boolean {
        return try {
            val calendar = Calendar.getInstance()
            val today = calendar.get(Calendar.DAY_OF_YEAR)
            val todayYear = calendar.get(Calendar.YEAR)

            calendar.time = Date(unixTimestamp * 1000L)
            val timestampDay = calendar.get(Calendar.DAY_OF_YEAR)
            val timestampYear = calendar.get(Calendar.YEAR)

            today == timestampDay && todayYear == timestampYear
        } catch (e: Exception) {
            false
        }
    }
}