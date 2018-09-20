package pawelsmolarski95.gmail.com.tablefootball.infrastructure.injection

interface HasComponent<out C> {
    fun getComponent(): C
}