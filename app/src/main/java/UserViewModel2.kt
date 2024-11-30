import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.saveinstancestate.User

class UserViewModel2: ViewModel (){
    val userList: MutableLiveData<MutableList<User>> = MutableLiveData(mutableListOf())

    fun addUser(user: User) {
        val currentList = userList.value ?: mutableListOf()
        currentList.add(user)
        userList.value = currentList
    }

    fun removeUser(user: User) {
        val currentList = userList.value ?: mutableListOf()
        currentList.remove(user)
        userList.value = currentList
    }
}