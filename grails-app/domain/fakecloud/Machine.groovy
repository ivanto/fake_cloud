package fakecloud

import me.quantox.fakecloud.auth.User

enum Status {
    STOPPED('STOPPED'),
    RUNNING('RUNNING')
    String id

    Status(String id){
        this.id = id
    }
}


class Machine {

    String uid
    Status status
    Date createdAt
    Boolean active = true

    static belongsTo = [createdBy: User]

    static constraints = {
    }
    /*
    static mapping = {
        dateCreated column: 'created'
    }
    */

}
