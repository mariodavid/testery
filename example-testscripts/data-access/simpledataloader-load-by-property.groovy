import com.haulmont.cuba.security.entity.User

def user = simpleDataLoader.loadByProperty(User.class, 'login', 'admin')

jsonResult(
        summary: "Hello ${user.instanceName}"
)
