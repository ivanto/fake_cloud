package fakecloud


import me.quantox.fakecloud.auth.Role
import me.quantox.fakecloud.auth.User
import me.quantox.fakecloud.auth.UserRole

class BootStrap {

    def init = { servletContext ->
        //TODO move this to general service
        def adminRoleName = "ROLE_ADMIN"
        def userRoleName = "ROLE_USER"

        def testUsername = "fakecloud.test@quantox.com"
        def adminUsername = "fakecloud.admin@quantox.com"

        //Roles
        def adminRole = Role.findByAuthority(adminRoleName) ?: new Role(authority: adminRoleName).save()
        Role.findByAuthority(adminRoleName) ?: new Role(authority: adminRoleName).save()
        def userRole = Role.findByAuthority(userRoleName) ?: new Role(authority: userRoleName).save()

        //Users
        def testUser = User.findByUsername(testUsername) ?: new User(username: testUsername,
                password: '4E~ke8}2Sx',
                firstName: "Test",
                lastName: "Test").save()

        if (!UserRole.findByUserAndRole(testUser, userRole)) {
            UserRole.findByUserAndRole(testUser, userRole)
        }
    }
    def destroy = {
    }
}
