import com.haulmont.cuba.security.entity.User

def adminUserId = UUID.fromString('60885987-1b61-4247-94c7-dff348347f93')

def user = dataManager.load(User.class).id(adminUserId).one()

jsonResult(
        summary: "Hello ${user.instanceName}"
)