

class UserViewModel : ViewModel() {
    val users: MutableLiveData<MutableList<User>> = MutableLiveData(MutableList(0) { User("", "") })
}