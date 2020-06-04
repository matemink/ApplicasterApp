package android.igorkostenko.applicasterapp

import android.igorkostenko.applicasterapp.model.Author
import android.igorkostenko.applicasterapp.model.EmployeeEntries
import android.igorkostenko.applicasterapp.model.Entry


object MockTestUtil {

    fun mockEntry() = Entry(
        id = "343297",
        title = "נתניהו בראיון: \\\"מאשימים אותי בבגידה? את כולם אני אתבע\\",
        summary = "אש הממשלה סבור כי הרמטכ\\\"לים לשעבר של 'כחול לבן' יודעים שלא בחדשות ",
        media_group = listOf()
    )

    fun mockList(): EmployeeEntries {
        return EmployeeEntries(
            author = Author(),
            entry = listOf(mockEntry())
        )
    }
}
