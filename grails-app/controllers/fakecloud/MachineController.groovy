package fakecloud

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured("ROLE_USER")
class MachineController extends RestfulController<Machine> {
    static responseFormats = ['json', 'xml']

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    MachineService machineService

    MachineController() {
        super(Machine)
    }

    /**
     * Needs to be implemented
     * @return
     */
    def start(){
        def resp = ["success":true]
        render resp as JSON
    }

    /**
     * Needs to be implemented
     * @return
     */
    def stop(){
        def resp = ["success":true]
        render resp as JSON
    }

    /**
     * Needs to be implemented
     * @return
     */
    def restart(){
        def resp = ["success":true]
        render resp as JSON
    }


    def create() {
        respond new Machine(params)
    }

    /**
     * Needs to be implemented
     * @return
     */
    def destroy(){
        def resp = ["success":true]
        render resp as JSON
    }

    def search(){
        def resp = ["success":true]
        render resp as JSON
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond machineService.list(params), model:[machineCount: machineService.count()]
    }

    def show(Long id) {
        respond machineService.get(id)
    }

    def save(Machine machine) {
        if (machine == null) {
            notFound()
            return
        }

        try {
            machineService.save(machine)
        } catch (ValidationException e) {
            respond machine.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'machine.label', default: 'Machine'), machine.id])
                redirect machine
            }
            '*' { respond machine, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond machineService.get(id)
    }

    def update(Machine machine) {
        if (machine == null) {
            notFound()
            return
        }

        try {
            machineService.save(machine)
        } catch (ValidationException e) {
            respond machine.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'machine.label', default: 'Machine'), machine.id])
                redirect machine
            }
            '*'{ respond machine, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        machineService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'machine.label', default: 'Machine'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'machine.label', default: 'Machine'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
