import com.haulmont.cuba.security.entity.User

def users = dataManager.load(User.class).query('select c from sec$User c').list()

jsonResult(
        summary: "Hello ${users*.instanceName}"
)
