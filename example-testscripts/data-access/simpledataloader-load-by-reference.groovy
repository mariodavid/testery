import com.haulmont.cuba.security.entity.Group
import com.haulmont.cuba.security.entity.User

def companyGroupId = UUID.fromString('0fa2b1a5-1d68-4d69-9fbd-dff348347f93')
def companyGroup = dataManager.load(Group.class).id(companyGroupId).one()

// this will throw an exception (in this particular case), because there is more than one user
// that have that group, but generally it will work
def user = simpleDataLoader.loadByReference(User.class, 'group', companyGroup)


jsonResult(
        summary: "Hello ${user.instanceName} from Company"
)
